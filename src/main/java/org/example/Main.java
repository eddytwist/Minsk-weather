package org.example;

import org.example.util.Parser;
import org.example.util.WeatherInfo;
/**
 * Makes program run.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-08
 */
public class Main {
  /**
   * The entry point.
   */
  public static void main(String... args) {
    WeatherInfo weatherInfo = new Parser();
      System.out.println(weatherInfo.getWeatherInfo());
  }
}
