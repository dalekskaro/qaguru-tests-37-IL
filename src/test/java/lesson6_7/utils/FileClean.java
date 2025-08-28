package lesson6_7.utils;

import java.io.File;

public class FileClean {
    public static void cleanTmpFolder() {
        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (folder.exists() && folder.isDirectory()) {
            deleteRecursively(folder);
            System.out.println("Папка tmp очищена и удалена.");
        } else {
            System.out.println("Папка tmp не найдена.");
        }
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }
        boolean deleted = file.delete();
        if (!deleted) {
            System.out.println("Не удалось удалить: " + file.getAbsolutePath());
        }
    }
}
