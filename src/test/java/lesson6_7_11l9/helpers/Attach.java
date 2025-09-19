package lesson6_7_11l9.helpers;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import io.qameta.allure.Attachment;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

  @Attachment(value = "{attachName}", type = "image/png")
  public static byte[] screenshotAs(String attachName) {
    return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Attachment(value = "{attachName}", type = "text/plain")
  public static byte[] pageSource(String attachName) {
    return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
  }

  @Attachment(value = "{attachName}", type = "text/html", fileExtension = "html")
  public static byte[] pageSnapshot(String attachName) {
    return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
  }

  @Attachment(value = "{attachName}", type = "text/plain")
  public static String attachAsText(String attachName, String message) {
    return message;
  }

  public static void browserConsoleLogs(String attachName) {
    attachAsText(
        attachName,
        String.join("\n", Selenide.getWebDriverLogs(BROWSER))
    );
  }

  @Attachment(value = "{attachName}", type = "text/html", fileExtension = ".html")
  public static String addVideo(String attachName) {
    return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
        + getVideoUrl()
        + "' type='video/mp4'></video></body></html>";
  }

  public static URL getVideoUrl() {
    String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId() + ".mp4";
    try {
      return new URL(videoUrl);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
