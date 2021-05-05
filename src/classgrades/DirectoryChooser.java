package classgrades;

/**
 *
 * @author Jonas McCartha
 */
import javax.swing.*;
import java.io.File;


public final class DirectoryChooser {
    
    public File DirectoryChooser() {
          JFileChooser f = new JFileChooser();
          f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
          f.showOpenDialog(null);
          File directoryPath = new File(f.getSelectedFile().toString());
          return directoryPath;
    }
}

