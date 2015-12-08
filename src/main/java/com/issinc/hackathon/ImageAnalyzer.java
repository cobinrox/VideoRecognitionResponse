package com.issinc.hackathon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by bryan.warren on 11/13/2015.
 */
public class ImageAnalyzer
{
    public static String getColorName(String imageFilePath) throws Exception
    {
        return getColorName(imageFilePath, 100);
    }

    public static String getColorName(String imageFilePath, Integer imagePercent) throws Exception
    {
        BufferedImage img;
        String colorName;
        File f = new File(imageFilePath);
        if( !f.exists() ) throw new Exception("Cannot find image file [" + imageFilePath + "]/(" + f.getAbsolutePath() + ")");
        img = ImageIO.read(new File(imageFilePath));

        int width = img.getWidth();
        int height = img.getHeight();

        if (imagePercent < 1)   imagePercent=1;
        if (imagePercent > 100) imagePercent=100;

        int widthRetain = width * imagePercent / 100;
        int heightRetain = height * imagePercent / 100;

        int startX = (width - widthRetain ) / 2;
        int startY = (height - heightRetain ) / 2;

        System.out.println( width + " X " + height );
        System.out.println( widthRetain + " X " + heightRetain );

        int red   = 0;
        int green = 0;
        int blue  = 0;
        for( int x=0; x<widthRetain; x++ ) {
            for( int y=startY; y<heightRetain; y++ ) {
                int rgb = img.getRGB( x + startX, y + startY );

                red   += ((rgb >> 16) & 0xFF);
                green += ((rgb >>  8) & 0xFF);
                blue  += ((rgb      ) & 0xFF);
            }
        }

        int pixels = width * height;
        red   /= pixels;
        green /= pixels;
        blue  /= pixels;
        int finalRGB = (0xff << 24) + (red << 16) + (green << 8) + blue;

        colorName = ColorUtils.getColorNameFromHex(finalRGB);
        img.flush();
        return colorName;
    }
}
