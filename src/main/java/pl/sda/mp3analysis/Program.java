package pl.sda.mp3analysis;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Paweł Matyaszczyk
 */
public class Program {
    public static void main(String[] args) {
        Path dir = Path.of(args[0]);
        List<Song> songList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.mp3")){
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
                Mp3File mp3file = new Mp3File(entry);
                Song song = new Song();
                if (mp3file.hasId3v1Tag()) {
                    song.setArtist(mp3file.getId3v1Tag().getArtist());
                    song.setYear(mp3file.getId3v1Tag().getYear());
                    song.setAlbum(mp3file.getId3v1Tag().getAlbum());
                    song.setTitle(mp3file.getId3v1Tag().getTitle());
                }
                if (mp3file.hasId3v2Tag()){
                    song.setArtist(mp3file.getId3v2Tag().getArtist());
                    song.setYear(mp3file.getId3v2Tag().getYear());
                    song.setAlbum(mp3file.getId3v2Tag().getAlbum());
                    song.setTitle(mp3file.getId3v2Tag().getTitle());
                }
                song.setSize(String.valueOf(Files.size(entry)));
                songList.add(song);
                String path = String.valueOf(dir)+"description.txt";
                FileWriter writer = new FileWriter(String.valueOf(dir+"\\description.txt"),false);
                for (Song listElement: songList) {
                    writer.write("Name of file: " + String.valueOf(entry.getFileName())+"\n");
                    writer.write("Artist: "+ listElement.getArtist()+"\n");
                    writer.write("Year: "+listElement.getYear()+"\n");
                    writer.write("Album: "+listElement.getAlbum()+"\n");
                    writer.write("Title: "+listElement.getTitle()+"\n");
                    writer.write("Size: "+listElement.getSize()+"\n");
                }
                writer.close();
            }

        } catch (IOException x ){
            System.out.println(x);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }

    }
}
