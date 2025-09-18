package lesson6_7_11l9.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCreator {

  private static final Logger log = LoggerFactory.getLogger(CreateRandomData.class);

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
      LogUtils.logInfoMessage("Папка создана: " + created, log);
    }

    File file = new File(folder, fileName + ".png");
    ImageIO.write(image, "png", file);
    LogUtils.logInfoMessage("Создан png: " + file.getPath(), log);

    return file.getAbsoluteFile();
  }
}
