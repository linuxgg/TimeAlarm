package timealarm.linuxgg.com.timealarm.models;

import java.io.Serializable;

/**
 * Created by tom on 2016/10/2.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class AlarmItem implements Serializable {
    private Long ID;
    private String START_TIME = "start_time";
    private String END_TIME = "end_time";
    private String HAS_AUDIO = "has_audio";
    private String DESC = "has_audio";
    private String AUDIO_PATH = "audio_path";

    public String getAUDIO_PATH() {
        return AUDIO_PATH;
    }

    public void setAUDIO_PATH(String AUDIO_PATH) {
        this.AUDIO_PATH = AUDIO_PATH;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getHAS_AUDIO() {
        return HAS_AUDIO;
    }

    public void setHAS_AUDIO(String HAS_AUDIO) {
        this.HAS_AUDIO = HAS_AUDIO;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    @Override
    public String toString() {
        return "AlarmItem{" +
                "ID=" + ID +
                ", START_TIME='" + START_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", HAS_AUDIO='" + HAS_AUDIO + '\'' +
                ", DESC='" + DESC + '\'' +
                ", AUDIO_PATH='" + AUDIO_PATH + '\'' +
                '}';
    }
}
