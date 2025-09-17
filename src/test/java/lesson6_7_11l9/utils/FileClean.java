package lesson6_7_11l9.utils;

import java.io.File;

public class FileClean {
    public static void cleanTmpFolder() {
        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (folder.exists() && folder.isDirectory()) {
            deleteRecursively(folder);
            System.out.println("##### The tmp folder has been cleared and deleted");
        } else {
            System.out.println("##### Folder tmp not found");
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
            System.out.println("##### Failed to delete: " + file.getAbsolutePath());
        }
    }
}
