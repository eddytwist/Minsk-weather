package org.example.util;

/**
 * Used for get information about weather.
 * The interface should be implemented by <code>Parser</code>.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-08
 */
public interface WeatherInfo {
  /**
   * @return String with information about weather, date and time of request.
   */
  String getWeatherInfo();
}
