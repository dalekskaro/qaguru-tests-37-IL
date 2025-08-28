package lesson6_7.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileCreator {

    public static File pngCreator(String fileName) throws IOException {
        int width = 200;
        int height = 100;

        // создаём пустое изображение (RGB)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // получаем "кисть"
        Graphics2D g = image.createGraphics();

        // фон
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.dispose(); // освобождаем ресурсы

        // рабочая директория проекта
        String projectDir = System.getProperty("user.dir");

        // путь к папке в ресурсах для тестов
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (!folder.exists()) {
            boolean created = folder.mkdirs(); // создаём папку, если её нет
            System.out.println("Папка создана: " + created);
        }

        File file = new File(folder,fileName + ".png");
        System.out.println("fileName: " + fileName);

        ImageIO.write(image, "png", file);
        System.out.println("File created getAbsolutePath: " + file.getAbsolutePath());
        System.out.println("File created getPath: " + file.getPath());

        return file.getAbsoluteFile();
    }
}
