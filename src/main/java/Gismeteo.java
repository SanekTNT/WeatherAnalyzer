import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Gismeteo {

    private ArrayList<Forecast> forecasts = new ArrayList<Forecast>();

    Gismeteo(String cityName, String countryName, int numberOfDays){
        Element elementForecast = connection(getCurrentURL(cityName, countryName));
        Elements elementsAllForecasts = elementForecast.getElementsByClass("wbfull");
        ArrayList<String> dates = new Dates().datesForForecast(numberOfDays);
        for(int i=0; i<numberOfDays; i++){
            forecasts.add(createNewForecast(cityName, countryName, elementsAllForecasts.get(i),
                    dates.get(i)));
        }
    }

    private Document connectionByCitySearching(String cityName){
        try {
            Document doc =
                    Jsoup.connect("https://www.gismeteo.ua/city/?gis=" + cityName + "&searchQueryData").get();
            return doc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Document connection(String partOfURL){
        try {
            Document doc = Jsoup.connect("https://www.gismeteo.ua/" + partOfURL + "/14-days/").get();
            return doc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Forecast createNewForecast(String cityName, String countryName, Element element, String date){
        LinkedHashMap<String, ForecastDuringOneDay> hashMap = setHashMapTemperatureDuringDay(
                element.getElementById("tbwdaily1").getElementsByClass("png"),
                element.getElementById("tbwdaily1").getElementsByClass("temp"),
                element.getElementById("tbwdaily1").getElementsByTag("th"));
        return new Forecast(cityName, countryName, date, hashMap.get("День").getForecast(),
                hashMap.get("День").getPhotoURL(), hashMap.get("Ночь").getTemperature(),
                hashMap.get("День").getTemperature(), hashMap);
    }

    private LinkedHashMap<String, ForecastDuringOneDay> setHashMapTemperatureDuringDay
            (Elements photoAndForecast, Elements temperature, Elements title){
        LinkedHashMap<String, ForecastDuringOneDay> hashMap = new LinkedHashMap<String, ForecastDuringOneDay>();
        for(int i=0; i<4; i++){
            Element elementPhotoForecast = photoAndForecast.get(i),
                    elementTemperature = temperature.get(i),
                    elementTitle = title.get(i);
                hashMap.put(parsingLiElementForDayTime(elementTitle.toString()),
                        new ForecastDuringOneDay(
                                parsingLiElementForDayTimeForecast(elementPhotoForecast.toString()),
                                parsingLiElementForDayTimeImageURL(elementPhotoForecast.toString()),
                                parsingLiElementForDayTimeTemperature(elementTemperature.toString())
                        )
                );
        }
        return hashMap;
    }

    private Elements getAllForecastURLs(String cityName){
        Element element = connectionByCitySearching(cityName);
        return element.getElementsByClass("cities_by_name");
    }

    private String getCurrentURL(String cityName, String countryName){
        Elements elements = getAllForecastURLs(cityName), liElements = null;
        for (Element element : elements) {
            liElements = element.getElementsByTag("li");
        }
        for (Element element : liElements) {
            if(element.toString().contains(cityName + "</b></span>") && element.toString().contains(countryName))
                return parsingLiElementForForecastURL(element.toString());
        }
        return null;
    }

    private String parsingLiElementForForecastURL(String str){
        return str.substring(str.indexOf("weather"), str.indexOf("/", str.indexOf("weather")));
    }

    private String parsingLiElementForDayTime(String str){
        return str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
    }

    private String parsingLiElementForDayTimeForecast(String str){
        return str.substring(str.indexOf("title")+7, str.lastIndexOf("width")-2);
    }

    private String parsingLiElementForDayTimeImageURL(String str){
        return str.substring(str.indexOf("src")+5, str.lastIndexOf("alt")-2);
    }

    private String parsingLiElementForDayTimeTemperature(String str){
        return str.substring(str.indexOf("m_temp c")+10, str.indexOf("/span")-1);
    }

    public ArrayList<Forecast> returnForecasts(){
        return forecasts;
    }

}