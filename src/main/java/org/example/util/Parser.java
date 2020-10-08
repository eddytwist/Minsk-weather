package org.example.util;

import org.example.exceptions.NoDataException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;
/**
 * The <code>Parser</code> class implements <code>WeatherInfo</code> interface and used for parsing the HTML page.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-08
 */
public class Parser implements WeatherInfo{

    /**
     * The URL field of HTML page for parsing.
     */
    private final static String GISMETEO_URL = "https://www.gismeteo.by/weather-minsk-4248/";
    /**
     * The HTML page that we got parsed.
     */
    private Document page = null;

    /**
     * @return a parsed via Jsoup page for further selection.
     * @throws IOException if HTML page could not be read.
     */
    private Document getPage() {
        try {
            page = Jsoup.parse(new URL(GISMETEO_URL), 10000);
        } catch (IOException e) {
            System.out.println("Проверьте интернет соединение.");
        }
        return page;
    }

    /**
     *
     * Does finishing selections and concatenation and makes the String ready for printing.
     * @return a String from parsed page.
     * @throws @exception NoDataException if HTML page could not be read.
     */
    @Override
    public String getWeatherInfo() throws NoDataException {
        Document page = getPage();
        if (page == null)
            throw new NoDataException("Соединение отсутствует, нет данных.");
        Element tableWth = page.select("div[class=tabs _center]").first();
        String todayWeather = tableWth.select("div[class=tab-weather]").select("span[class=unit unit_temperature_c]").first().text();
        String todayDate = tableWth.select("div[class=tab  tooltip]").select("div[class=tab-content]").select("div[class=date]").text();
        String timeNow = tableWth.select("div[id=time]").first().text();
        return "Вас приветстует прогноз погоды!" +
                "\nСегодня: " + todayDate +
                "\nСейчас: " + timeNow +
                "\nТемпература: " + todayWeather +
                "\nЖелаем хорошего дня!";
    }
}
