package pl.sda.mp3analysis;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paweł Matyaszczyk
 */
public class Program {
    public static void main(String[] args) {
        Path dir = Path.of(args[0]);
        List<Song> songList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.mp3")){
            for (Path entry: stream) {
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
                song.setSize((float) Files.size(entry));
                songList.add(song);
                String path = dir+"description.txt";
                FileWriter writer = new FileWriter(dir+"\\description.txt",false);
                for (Song listElement: songList) {
                    writer.write("Autor: "+ listElement.getArtist()+"\n");
                    writer.write("Rok produkcji: "+listElement.getYear()+"\n");
                    writer.write("Album: "+listElement.getAlbum()+"\n");
                    writer.write("Tytuł: "+listElement.getTitle()+"\n");
                    writer.write("Rozmiar w MB: "+String.format("%.2f",listElement.getSize()/(1024*1024))+"\n");
                }
                writer.close();
                System.out.println("Szczegóły plików zapisane w description.txt ");
            }

        } catch (IOException x ){
            System.out.println(x.getMessage());
        } catch (InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }

    }
}
