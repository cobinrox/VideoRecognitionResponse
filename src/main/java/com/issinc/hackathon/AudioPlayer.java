package com.issinc.hackathon;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by bryan.warren on 11/13/2015.
 */
public class AudioPlayer
{
    public static void main(String args[])
    {
        System.out.println("Working directory: [" + System.getProperty("user.dir") + "]");
        try
        {
            AudioPlayer ap = new AudioPlayer();
            if( args != null && args.length > 0 )
            {
                File test = new File(args[0]);
                if( !test.exists() )
                {
                    System.out.println("Warning your file [" + args[0] + "] (" + test.getAbsolutePath() + ") does not exist");
                }
                System.out.println("Playing [" + test.getAbsolutePath() + "]");
                ap.playAudioFile(args[0]);
            }
            else
            {
                File test = new File("laugh.wav");
                System.out.println("Playing [" + test.getAbsolutePath() + "]");
                ap.playAudioFile("laugh.wav");
            }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }
    public void playAudioFile( String audioFileName ) throws Exception {
        URL o = ClassLoader.getSystemResource(audioFileName);
        URL o2 = this.getClass().getClassLoader().getResource(audioFileName);
        AudioInputStream audioIn = null;
        try
        {
            audioIn = AudioSystem.getAudioInputStream(o);
        }
        catch(Throwable t)
        {
            try
            {
                audioIn = AudioSystem.getAudioInputStream(o2);
            }
            catch(Exception e)
            {
                throw e;
            }
        }

        AudioFormat format = audioIn.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class,format);

        Clip clip = (Clip)AudioSystem.getLine(info);
        //Line.Info linfo = new Line.Info(Clip.class);
        //Line line = AudioSystem.getLine(linfo);
        //Clip clip = (Clip) line;
        //Clip clip = AudioSystem.getClip();


        //Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        long sleepMillis = clip.getMicrosecondLength()/1000;
        clip.start();
        Thread.sleep(sleepMillis);
        clip.flush();
        clip.close();
    }
}
