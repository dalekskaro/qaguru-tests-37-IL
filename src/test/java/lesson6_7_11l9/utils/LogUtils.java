package lesson6_7_11l9.utils;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;


@UtilityClass
public class LogUtils {

  public static void logVariable(String variableName, Object variableValue, Logger log) {
    log.info("Была создана переменная {} со значением {}", variableName, variableValue);
  }

  public static void logInfoMessage(String message, Logger log) {
    log.info(message);
  }
}
