import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {
    public static void main(String[] args) {
        //reference image: 
        BufferedImage refImage = null;
        //comparative image 
        BufferedImage compImage = null;
        String imgOnePath = "lebronJames.jpg"; 
        String imgTwoPath = "lebronJames2.jpg";
        try {
            refImage = ImageIO.read(new File(imgOnePath)); 
            compImage = ImageIO.read(new File(imgTwoPath)); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(compareTwoImages(pixelsOfImage(refImage), pixelsOfImage(compImage)));
    }


    public static int[][][] pixelsOfImage(BufferedImage image){
            int width = image.getWidth();
            int height = image.getHeight();

            int[][][] rgbValues = new int[width][height][3]; // 3D array to store RGB values

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int pixel = image.getRGB(i, j);

                    rgbValues[i][j][0] = (pixel >> 16) & 0xff; 
                    rgbValues[i][j][1] = (pixel >> 8) & 0xff;  
                    rgbValues[i][j][2] = pixel & 0xff;        
                }
            }
        return rgbValues;
    }

    public static double compareTwoImages(int[][][] imgOne, int[][][] imgTwo){
        double sum = 0; 
        for(int i = 0; i < imgOne.length && i < imgTwo.length; i++){
            int dR = 0; 
            int dG = 0;
            int dB = 0;
            for(int j = 0; j < imgOne[i].length && j < imgTwo[i].length; j++){
                dR = imgOne[i][j][0] - imgTwo[i][j][0];
                dR *= dR;
                dG = imgOne[i][j][1] - imgTwo[i][j][1];
                dG *= dG;
                dB = imgOne[i][j][2] - imgTwo[i][j][2];
                dB *= dB;
            }
            sum += dR + dG + dB; 
            sum = Math.sqrt(sum);
        }
        return sum;
    }
}
