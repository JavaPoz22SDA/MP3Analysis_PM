package pl.sda.mp3analysis;

/**
 * @author Paweł Matyaszczyk
 */
public class Song {
    private String artist;
    private String year;
    private String album;
    private String title;
    private Float size;

    public Song() {
    }

    public Song(String artist, String year, String album, String title, Float size) {
        this.artist = artist;
        this.year = year;
        this.album = album;
        this.title = title;
        this.size = size;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (artist != null) this.artist = artist;
        else this.artist = "";
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        if (year != null) this.year = year;
        else this.year = "";
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        if (album !=null) this.album = album;
        else this.album = "";

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) this.title = title;
        else this.title = "";
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }
}
