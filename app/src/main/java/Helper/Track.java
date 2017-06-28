package Helper;

/**
 * Created by Assis on 14/06/2017.
 */

public class Track {

    private String trackId;
    private String trackNumero;
    private int trackRatin;

   public Track(){

   }

    public Track(String trackId, String trackNumero, int trackRatin) {
        this.trackId = trackId;
        this.trackNumero = trackNumero;
        this.trackRatin = trackRatin;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getTrackNumero() {
        return trackNumero;
    }

    public int getTrackNome() {
        return trackRatin;
    }
}


