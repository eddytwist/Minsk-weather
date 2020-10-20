package org.example.util;

import org.example.Main;
import org.example.db.Repository;
import org.example.exceptions.NoConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * The <code>Parser</code> class implements <code>WeatherInfo</code> interface and used for parsing the HTML page.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-08
 */
public class Parser implements WeatherInfo{

    /**
     * The Logger Log4j gets logs and puts them into /logs/logfile/log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * The URL field of HTML page for parsing.
     */
    private final static String GISMETEO_URL = "https://www.gismeteo.by/weather-minsk-4248/";

    /**
     * The HTML page that we got parsed.
     */
    private Document page = null;

    /**
     * The MySQL database instance.
     */
    private final Repository repository = new Repository();

    /**
     * @return a parsed via Jsoup page for further selection.
     * @throws NoConnectionException if it's not possible to connect to the page.
     * @throws MalformedURLException if HTML page could not be read.
     */
    private Document getPage() throws NoConnectionException, MalformedURLException {
        try {
            page = Jsoup.parse(new URL(GISMETEO_URL), 10000);
            LOG.info("The page was found.");
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new NoConnectionException("Check internet connection.");
        }
        return page;
    }

    /**
     *
     * Does finishing selections and concatenation, and makes the String ready for printing.
     * @return a String from parsed page.
     */
    @Override
    public String getWeatherInfo() {
        String todayWeather;
        String todayDate;
        String timeNow;
        try {
            page = getPage();
            LOG.info("The page was parsed.");
        } catch (NoConnectionException | MalformedURLException e) {
            repository.printWeatherInfo();
            LOG.error(e.getMessage(), e);
            return "That was the last weather forecast update from database.";
        }
        Element tableWth = page.select("div[class=tabs _center]").first();
        todayWeather = tableWth.select("div[class=tab-weather]").select("span[class=unit unit_temperature_c]").first().text();
        todayDate = tableWth.select("div[class=tab  tooltip]").select("div[class=tab-content]").select("div[class=date]").text();
        timeNow = tableWth.select("div[id=time]").first().text();
        repository.insertWeatherInfo(todayDate, timeNow, todayWeather);
        LOG.info("Printed data: {} {} {}", todayDate, timeNow, todayWeather);
        return ("Welcome to the weather forecast!" +
                "\nToday is: " + todayDate +
                "\nTime now: " + timeNow +
                "\nTemperature: " + todayWeather +
                "\nHave a nice day!");
    }
}
