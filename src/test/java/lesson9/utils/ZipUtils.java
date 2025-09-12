package lesson9.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private ClassLoader classLoader = getClass().getClassLoader();

    private String createTmpFolder() {
        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            System.out.println("##### Папка tmp создана: " + created);
        } else {
            System.out.println("##### Папка tmp уже существует");
        }
        return folder.getAbsolutePath();
    }

    public String createZipWithOneFile(String resourceName, String zipName) throws IOException {
        String pathToTmpFolder = createTmpFolder();
        File zipFile = new File(pathToTmpFolder + "/" + zipName + ".zip");

        try (InputStream inputStream = classLoader.getResourceAsStream("lesson9/" + resourceName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("##### Файл не найден в ресурсах: lesson9/" + resourceName);
            }

            if (zipFile.exists()) {
                throw new FileNotFoundException("##### ZIP уже существует: " + zipFile.getAbsolutePath());
            } else {
                try (ZipOutputStream zipOutputStream = new ZipOutputStream(
                        new FileOutputStream(zipFile.getAbsolutePath()))
                ) {
                    ZipEntry zipEntry = new ZipEntry(resourceName);
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();

                    System.out.println("##### ZIP создан: " + zipFile.getAbsolutePath());
                }
            }
        }
        return zipFile.getAbsolutePath();
    }

    public static void cleanTmpFolder() {
        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (folder.exists() && folder.isDirectory()) {
            deleteRecursively(folder);
            System.out.println("##### Папка tmp очищена и удалена");
        } else {
            System.out.println("##### Папки tmp не было найдено для удаления");
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
            System.out.println("##### Ошибка удаления: " + file.getAbsolutePath());
        }
    }
}


