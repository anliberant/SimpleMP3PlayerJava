package utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MP3FileFilter extends FileFilter {
    private String fileExtention;
    private String fileDescription;
    
    public MP3FileFilter(String fileExtention, String fileDescription){
        this.fileExtention = fileExtention;
        this.fileDescription = fileDescription;
    }

    @Override
    public boolean accept(File file) {
    return file.isDirectory() || file.getAbsolutePath().endsWith(fileExtention);
    }

    @Override
    public String getDescription() {
        return fileDescription+" (*."+fileExtention+")";
    }
    
}
