package com.issinc.hackathon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryan.warren on 11/13/2015.
 */
public class Color2AudioFileMatcher
{
    static private Map<String,String> colorAudioFileMap;

    static
    {
        colorAudioFileMap = new HashMap<String,String>();
        colorAudioFileMap.put("GRAY","storm.wav");
        colorAudioFileMap.put("ORANGE","laugh.wav");
//        colorAudioFileMap.put("","");
//        colorAudioFileMap.put("","");
//        colorAudioFileMap.put("","");
    }

    public static String getAudioFileName(String colorName)
    {
        if (colorAudioFileMap.containsKey(colorName))
        {
            return colorAudioFileMap.get(colorName);
        }
        return "ghostly1.wav";
    }
}
