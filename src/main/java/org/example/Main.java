package org.example;

import org.example.exceptions.NoDataException;
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
    try {
      System.out.println(weatherInfo.getWeatherInfo());
    } catch (NoDataException e) {
      System.out.println(e.getMessage());
    }
  }
}
