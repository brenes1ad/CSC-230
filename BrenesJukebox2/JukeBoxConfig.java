package BrenesJukebox2;
import java.io.Serializable;

/**
 * JukeBoxConfig.java -- simple class to handle the serialization of the JukeBox Controls
 * CSC 230 F24
 *
 * @author Tony Brenes
 */

public class JukeBoxConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mediaFolder;

    /**
     * Default constructor: sets mediaFolder path to given absolute path
     * @param mediaFolder String of the absolute path name of the media directory.
     */
    public JukeBoxConfig(String mediaFolder) {
        this.mediaFolder = mediaFolder;
    }

    /**
     * Used to set local dir after a config object is created
     * @return media folder absolute path as string
     */
    public String getMediaFolder() {
        return mediaFolder;
    }

    /**
     * Default setter for standard class.
     * @param mediaFolder String of the absolute path name of the media directory.
     */
    public void setMediaFolder(String mediaFolder) {
        this.mediaFolder = mediaFolder;
    }

}
