package org.example;

import org.example.util.Parser;
import org.example.util.WeatherInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Makes program run.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-08
 */
public class Main {

  /**
   * The Logger Log4j gets logs and puts them into /logs/logfile/log.
   */
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  /**
   * The entry point.
   */
  public static void main(String... args) {
    log.info("The program started");
    WeatherInfo weatherInfo = new Parser();
    String weather = weatherInfo.getWeatherInfo();
    System.out.println(weather);
    log.info("The program successfully finished");
  }
}
