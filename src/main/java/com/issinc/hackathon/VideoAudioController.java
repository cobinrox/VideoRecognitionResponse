package com.issinc.hackathon;

import com.pi4j.util.ExecUtil;

import java.io.IOException;

/**
 * Created by bryan.warren on 10/30/2015.
 */
public class VideoAudioController
{
    private String imageFilePath = null;
    private boolean autoColorSpeech = false;

    public static void main( String... args ) throws Exception {
        String[] results;
        try
        {
            results = ExecUtil.execute("fswebcam --no-timestamp --no-banner image.jpg");
        }
        catch (IOException e)
        {
            results = new String[0];
        }

        for( String result : results ) {
            System.out.println( result );
        }

        String colorName;
        try {
            VideoAudioController controller = new VideoAudioController();
            colorName = ImageAnalyzer.getColorName("target/classes/img.jpg");
            if( colorName == null )
            {
                throw new IOException("Could not find color matching image or image not found");
            }
            String soundFile = Color2AudioFileMatcher.getAudioFileName(colorName);

            System.out.println(colorName + " = " + soundFile);
            controller.playAudioFile(soundFile);
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public void playAudioFile( String audioFileName ) throws Exception {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playAudioFile(audioFileName);
    }

    public String getImageFilePath()
    {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath)
    {
        this.imageFilePath = imageFilePath;
    }

    public boolean isAutoColorSpeech()
    {
        return autoColorSpeech;
    }

    public void setAutoColorSpeech(boolean autoColorSpeech)
    {
        this.autoColorSpeech = autoColorSpeech;
    }
}
