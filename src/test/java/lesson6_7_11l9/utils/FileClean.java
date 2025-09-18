package lesson6_7_11l9.utils;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileClean {
    private static final Logger log = LoggerFactory.getLogger(CreateRandomData.class);

    public static void cleanTmpFolder() {
        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (folder.exists() && folder.isDirectory()) {
            deleteRecursively(folder);
            LogUtils.logInfoMessage("Папка tmp была очищена и удалена",log);

        } else {
            LogUtils.logInfoMessage("Папка tmp не найдена",log);
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
            LogUtils.logInfoMessage("Не удалось удалить: " + file.getAbsolutePath(),log);
        }
    }
}
