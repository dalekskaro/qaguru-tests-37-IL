package lesson6_7_11l9.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileCreator {

    public static File pngCreator(String fileName) throws IOException {
        int width = 200;
        int height = 100;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.dispose();

        String projectDir = System.getProperty("user.dir");
        File folder = new File(projectDir + "/src/test/resources/tmp");

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            System.out.println("##### Folder created: " + created);
        }

        File file = new File(folder,fileName + ".png");
        ImageIO.write(image, "png", file);

        return file.getAbsoluteFile();
    }
}
