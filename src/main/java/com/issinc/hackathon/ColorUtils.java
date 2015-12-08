package com.issinc.hackathon;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryan.warren on 11/11/2015.
 */
public class ColorUtils {

    private static Map<Integer, String> colorList;
    /**
     * Initialize the color list that we have.
     */
    private static void initColorList() {
        if (colorList != null)
        {
            return;
        }
        colorList = new HashMap<Integer, String>();
        for (Field field : Color.class.getFields())
        {
            if (field.getType() == Color.class)
            {
                try
                {
                    Color color = (Color) field.get(null);
                    colorList.put(color.getRGB(), field.getName());
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }
    }

    /**
     * Convert hexColor to rgb, then call getColorNameFromRgb(r, g, b)
     *
     * @param hexColor
     * @return
     */
    public static String getColorNameFromHex(int hexColor) {
        initColorList();

        int hexRed   = ((hexColor >> 16) & 0xFF);
        int hexGreen = ((hexColor >>  8) & 0xFF);
        int hexBlue  = ((hexColor      ) & 0xFF);

        System.out.println( Integer.toHexString(hexColor) + " = " +
                            Integer.toHexString(hexRed) + " " +
                            Integer.toHexString(hexGreen) + " " +
                            Integer.toHexString(hexBlue) );

        int maxDiff = Integer.MAX_VALUE;
        int picked = 0;
        for (Integer color : colorList.keySet())
        {
            int red   = ((color >> 16) & 0xFF);
            int green = ((color >>  8) & 0xFF);
            int blue  = ((color      ) & 0xFF);
            int diff = Math.abs(red - hexRed) + Math.abs(green - hexGreen) + Math.abs(blue - hexBlue);
            if (diff < maxDiff)
            {
                maxDiff = diff;
                picked = color;
                System.out.print(".." + colorList.get(color));
            }
        }

        System.out.println();
        return colorList.get(picked);
    }
}