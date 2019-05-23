package main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class SplitImage {

    public static BufferedImage[] getImages(Image img, int rows, int column) {
        BufferedImage[] splittedImages = new BufferedImage[rows * column];
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, null);
        int width = bi.getWidth();
        int height = bi.getHeight();
        int pos = 0;
        int swidth = width / column;
        int sheight = height / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                BufferedImage bimg = bi.getSubimage(j * swidth, i * sheight, swidth, sheight);
                splittedImages[pos] = bimg;
                pos++;
            }
        } 
        return splittedImages;
    }

    public static void main(String args[]) throws IOException { 
            BufferedImage bi = ImageIO.read(new File("0.jpg"));  // add image 
            int rcount = Integer.parseInt("4");  // add rows
            int ccount = Integer.parseInt("3");  // add columns
            Image img = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage[] imgs = SplitImage.getImages(img, rcount, ccount);
            for(int i=0; i < imgs.length; i++){
                ImageIO.write(imgs[i], "jpg", new File("img"+i+".jpg"));
            } 
    }
}