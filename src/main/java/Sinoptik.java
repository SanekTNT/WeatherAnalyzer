import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sinoptik{

    private ArrayList<Forecast> forecasts = new ArrayList<Forecast>();

    Sinoptik(String cityName, String countryName, int days){
        ArrayList<String> dates = new Dates().datesForForecast(days);
        for(int i=0; i<days; i++){
            if(i<=1)
                forecasts.add(returnForecast(cityName, countryName, dates.get(i), i+1));
            else
                forecasts.add(returnForecast(cityName, countryName, dates.get(i), i+1));
        }
    }

    private Document connection(String cityName, String date){
        try {
            Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + cityName + "/" + date).get();
            return doc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Forecast returnForecast(String cityName, String countryName, String date, int i){
        Element elementSimpleForecast = connection(cityName, date).getElementById("bd" + i);
        return new Forecast(cityName, countryName, date,
                parsingHtmlStringValueFromTitle(elementSimpleForecast. // forecast
                        getElementsByAttributeValueContaining("class", "weatherIco").toString()),
                parsingHtmlStringValueFromSrc(elementSimpleForecast. // image
                        getElementsByAttributeValueContaining("class", "weatherImg").toString()),
                parsingHtmlStrings(elementSimpleForecast.getElementsByTag("span").first().toString()),
                parsingHtmlStrings(elementSimpleForecast.getElementsByTag("span").last().toString()),
                setDataForHashMap(cityName, date));
    }

    public LinkedHashMap<String, ForecastDuringOneDay> setDataForHashMap(
            String cityName, String date){
        Elements elements = connection(cityName, date).
                getElementsByClass("wMain clearfix").first().getElementsByTag("tr");
        LinkedHashMap<String, ForecastDuringOneDay> hashMapTemperatureDuringOneDay
                = creatingHashMap(elements.get(0).getElementsByTag("td"),
                elements.get(2).getElementsByTag("td"),
                elements.get(3).getElementsByTag("td"));
        return hashMapTemperatureDuringOneDay;
    }

    private LinkedHashMap<String, ForecastDuringOneDay> creatingHashMap(
            Elements elementsDayTime, Elements elementsForecast, Elements elementsTemperature) {
        LinkedHashMap<String, ForecastDuringOneDay> hashMapTemperatureDuringOneDay =
                new LinkedHashMap<String, ForecastDuringOneDay>();
        if(elementsTemperature.size() == 8) {
            for (int i = 1; i <= 4; i++) {
                hashMapTemperatureDuringOneDay.put(
                        parsingHtmlStringValueFromEndTd(elementsDayTime.get(i - 1).toString()).
                                substring(0,1).toUpperCase()
                        + parsingHtmlStringValueFromEndTd(elementsDayTime.get(i - 1).toString()).substring(1),
                        new ForecastDuringOneDay(
                                parsingHtmlStringValueFromTitle(elementsForecast.get((i * 2) - 1).toString()),
                                parsingHtmlStringValueFromSrc(elementsForecast.get((i * 2) - 1).toString()),
                                parsingHtmlStringValueFromEndTd(elementsTemperature.get((i * 2) - 1).toString())));
            }
        }
        else {
            for (int i = 0; i < 4; i++) {
                hashMapTemperatureDuringOneDay.put(
                        parsingHtmlStringValueFromEndTd(elementsDayTime.get(i).toString()).
                                substring(0,1).toUpperCase()
                                + parsingHtmlStringValueFromEndTd(elementsDayTime.get(i).toString()).substring(1),
                        new ForecastDuringOneDay(
                                parsingHtmlStringValueFromTitle(elementsForecast.get(i).toString()),
                                parsingHtmlStringValueFromSrc(elementsForecast.get(i).toString()),
                                parsingHtmlStringValueFromEndTd(elementsTemperature.get(i).toString())));
            }
        }
        return hashMapTemperatureDuringOneDay;
    }

    private String parsingHtmlStrings(String str){
        return str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
    }

    private String parsingHtmlStringValueFromTitle(String str){
        return str.substring(str.indexOf("title") + 7, str.indexOf(">", str.indexOf("title") + 7)-1);
    }

    private String parsingHtmlStringValueFromSrc(String str){
        return str.substring(str.indexOf("src") + 5, str.indexOf("gif") + 3);
    }

    private String parsingHtmlStringValueFromEndTd(String str){
        return str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
    }

    public ArrayList<Forecast> returnForecasts(){
        return forecasts;
    }

}
