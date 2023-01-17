package dev.chancho.amoeba.utilities;
import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sketch {
    /*
    This class is used to store and modify Buffered Images, store information for *scene*.render()
    There is also a subclass for animated sprites
    Calls to render() will refer back to the active scene in Board
     */

    public BufferedImage tilesetPNG;
    public int spriteResolution = 32;
    public float fontSize = 32.0f;
    public int SCALE = 128;
    Dimension tilesetDimension = new Dimension(320,320);
    public Font pcs,vt323;
    public FontMetrics fontMetrics = null;
    public Color
        textColor = Color.decode("#dddddd");
    public BufferedImage[][] tiles = new BufferedImage[tilesetDimension.width/spriteResolution][tilesetDimension.height/spriteResolution];
    public Sprite[] sprites;

    public Sketch(){
        try{
            pcs = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Sketch.class.getResourceAsStream("res/fonts/pcsenior.ttf")));
            vt323 = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Sketch.class.getResourceAsStream("res/fonts/VT323-Regular.ttf")));
            tilesetPNG = toBufferedImage(new ImageIcon(Objects.requireNonNull(Sketch.class.getResource("res/img/tileset.png"))).getImage());
            for(int i=0;i<tiles.length;i++)
                for(int j=0; j<tiles[0].length; j++)
                    tiles[i][j] = getScaledBufferedImage(i,j,SCALE,SCALE);
            sprites = new Sprite[255];
            //populate sprites
            //splash sprites
            BufferedImage[] left = new BufferedImage[8], right = new BufferedImage[8];
            for(int i=0; i<8; i++){
                left[i] = tiles[i][9];
                right[i] = createRotated(createFlipped(tiles[i][9]));
            }
            sprites[0] = new Sprite("splashRight", right, 30, false);
            sprites[1] = new Sprite("splashLeft", left, 30, false);
        } catch (IOException | FontFormatException e) {
            String msg = String.format("Caught an exception from the Sketch constructor:\n%s",e.getMessage());
            System.out.println(msg);
        }
    }

    public void render(Graphics g, Board b){
        Graphics2D g2d = (Graphics2D) g;
        setFont(g2d,pcs,fontSize);
        b.getActiveScene().render(g2d);
        for(UIButton button : b.getActiveScene().getButtons()){
            //g.drawRect(button.x, button.y, button.width, button.height);
            int stringWidth = fontMetrics.stringWidth(button.text);
            int stringHeight = fontMetrics.getHeight();
            int buttonSpriteOffset = 0;
            switch (button.state){
                case HOVER:
                    buttonSpriteOffset = 1;
                    break;
                case CLICKED:
                    buttonSpriteOffset = 2;
                    break;
            }
            int buttonScale = button.height/2;
            g2d.drawImage(scaleImage(getTile((3 * buttonSpriteOffset),0,16,16),buttonScale),button.x,button.y,null);//TOP LEFT
            g2d.drawImage(scaleImage(getTile((3 * buttonSpriteOffset),1,16,16),buttonScale),button.x,button.y+buttonScale, null);//BOTTOM LEFT
            g2d.drawImage(scaleImage(getTile((3 * buttonSpriteOffset)+1,0, 16,32), button.width - buttonScale*2, button.height),button.x+buttonScale, button.y,null); //MIDDLE
            g2d.drawImage(scaleImage(getTile((3 * buttonSpriteOffset)+2,0,16,16),buttonScale),button.x+button.width - buttonScale,button.y,null);//TOP RIGHT
            g2d.drawImage(scaleImage(getTile((3 * buttonSpriteOffset)+2,1,16,16),buttonScale),button.x+button.width - buttonScale,button.y+buttonScale, null);//BOTTOM RIGHT
            g2d.setColor(textColor);
            g2d.drawString(button.text,button.x + button.width/2 - stringWidth/2 , button.y + button.height/2 + fontMetrics.getMaxDescent());
        }
    }

    public void setFont(Graphics g, Font f, float fontSize){
        g.setFont(f.deriveFont(fontSize));
        fontMetrics = g.getFontMetrics();
    }

    //Buffered Image Tools
    public BufferedImage toBufferedImage(Image img){
        if(img instanceof BufferedImage)
            return (BufferedImage) img;
        BufferedImage bImage = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bImage.createGraphics();
        bGr.drawImage(img,0,0, null);
        return bImage;
    }
    public BufferedImage getTile(int x, int y){
        return getTile(x,y,spriteResolution,spriteResolution);
    }
    public BufferedImage getTile(int x, int y, int w, int h){
        return toBufferedImage(tilesetPNG.getSubimage(x*w, y*h, w, h));
    }

    private BufferedImage createTransformed(BufferedImage image, AffineTransform at){
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image,0,0,null);
        return newImage;
    }
    private BufferedImage createFlipped(BufferedImage image){
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1,-1));
        at.concatenate(AffineTransform.getTranslateInstance(0,-image.getHeight()));
        return createTransformed(image,at);
    }
    private BufferedImage createRotated(BufferedImage image)
    {
        AffineTransform at = AffineTransform.getRotateInstance(
                Math.PI, image.getWidth()/2.0, image.getHeight()/2.0);
        return createTransformed(image, at);
    }
    private BufferedImage scaleImage(BufferedImage image, int scaleX, int scaleY){
        return toBufferedImage(image.getScaledInstance(scaleX,scaleY,Image.SCALE_DEFAULT));

    }
    private BufferedImage scaleImage(BufferedImage image, int scale){
        return scaleImage(image,scale,scale);
    }

    private BufferedImage getScaledBufferedImage(int x, int y, int scaleX, int scaleY){
        return toBufferedImage(getTile(x,y).getScaledInstance(scaleX,scaleY,Image.SCALE_DEFAULT));
    }

    //Sprites
    public static class Sprite{
        String name;
        boolean loop;
        public int frameCount, currentFrame = 0, duration, durationDelta = 0;
        BufferedImage[] frames;
        private Sprite(String name, BufferedImage[] frames,int duration, boolean loop){
            this.name = name;
            this.frames = new BufferedImage[frames.length];
            System.arraycopy(frames, 0, this.frames, 0, frames.length);
            this.frameCount = frames.length;
            this.duration = duration;
            this.loop = loop;
        }
        public BufferedImage getFrame(){
            return frames[currentFrame];
        }

        public void play(){
            if(durationDelta >= duration){
                currentFrame++;
                if(currentFrame >= frameCount){
                    currentFrame = loop?0:frameCount-1;
                }
               durationDelta = 0;
            }else{
                durationDelta++;
            }
        }
    }
    public Sprite getSprite(String name){
        for (Sprite sprite : sprites) {
            if (sprite.name.equals(name))
                return sprite;
        }
        System.out.printf("Sprite not found: %s\n",name);
        return null;
    }
}
