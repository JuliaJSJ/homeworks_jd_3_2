import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(1, 11, 1, 20);
        GameProgress save2 = new GameProgress(2, 454, 88, 50);
        GameProgress save3 = new GameProgress(3, 5, 2, 50);

        saveGame("D://Games/savegames/save1.dat", save1);
        saveGame("D://Games/savegames/save2.dat", save2);
        saveGame("D://Games/savegames/save3.dat", save3);

        ArrayList<String> saves = new ArrayList<>();
        saves.add("D://Games/savegames/save1.dat");
        saves.add("D://Games/savegames/save2.dat");
        saves.add("D://Games/savegames/save3.dat");

        zipFiles("D://Games/savegames/save.zip", saves);

        File save1Dat = new File("D:/Games/savegames/save1.dat");
        File save2Dat = new File("D:/Games/savegames/save2.dat");
        File save3Dat = new File("D:/Games/savegames/save3.dat");

        if (save1Dat.delete()) System.out.println("Файл удален");
        if (save2Dat.delete()) System.out.println("Файл удален");
        if (save3Dat.delete()) System.out.println("Файл удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream save = new ObjectOutputStream(fos)) {
            save.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> saves) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(path))) {
            for (String i : saves) {
                try (FileInputStream streamSave = new FileInputStream(i)) {
                    ZipEntry entry = new ZipEntry(i);
                    zip.putNextEntry(entry);
                    while (streamSave.available() > 0) {
                        zip.write(streamSave.read());
                    }
                    zip.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
