package objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;


public class MP3Player {
    BasicPlayer pl;
    private BasicPlayer player = new BasicPlayer();
   
    
    private String currentFileName;
    private Double currentVolumeValue;
    
    public MP3Player(BasicPlayerListener listener){
        player.addBasicPlayerListener(listener);
    }
    
    public void play(String fileName){
        try{
            if(currentFileName != null && currentFileName.equals(fileName) && player.getStatus() == BasicPlayer.PAUSED){
                player.resume();
                return;
            }
            currentFileName = fileName;
            player.open(new File(fileName));
            player.play();
            player.setGain(currentVolumeValue);
        } catch(BasicPlayerException e){
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void stop(){
        try{
            player.stop();
        } catch(BasicPlayerException e){
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void pause(){
        try{
            player.pause();
        } catch(BasicPlayerException e){
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void setVolume(int currentValue, int maxValue){
        try {
            this.currentVolumeValue = (double)currentValue;
            
            if (currentValue == 0.0){
                player.setGain(0.0);
            } else {
                player.setGain(calcVolume(currentValue, maxValue));
            }
        } catch(BasicPlayerException e){
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public double calcVolume(int currentValue, int maxValue){
        currentVolumeValue = (double) currentValue / (double) maxValue;
        return currentVolumeValue;
    }

    public void jump(long skipBytes) {
           try {
               player.seek(skipBytes);
               player.setGain(currentVolumeValue);
                 } catch(BasicPlayerException e){
                     Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, e);
                 }
    }
}
