import java.util.LinkedHashMap;

public class Forecast {

    private String cityName, countryName, date, maxTem, mimTem, forecast, urlForecastImage;
    private LinkedHashMap<String, ForecastDuringOneDay> hashMapTemperatureDuringDay = null;

    Forecast() {}

    Forecast(String cityName, String countryName, String date, String forecast,
             String urlForecastImage, String mimTem, String maxTem,
             LinkedHashMap<String, ForecastDuringOneDay> hashMapTemperatureDuringDay){
        this.cityName = cityName;
        this.countryName = countryName;
        this.date = date;
        this.forecast = forecast;
        this.urlForecastImage = urlForecastImage;
        this.mimTem = mimTem;
        this.maxTem = maxTem;
        this.hashMapTemperatureDuringDay = hashMapTemperatureDuringDay;
    }

    public void returnAllForecast(){
        System.out.println("City: " + cityName
                + "\nCountry: " + countryName
                + "\nDate: " + date
                + "\nForecast: " + forecast
                + "\nPhoto: " + urlForecastImage
                + "\nMin temperature: " + mimTem
                + "\nMax temperature: " + maxTem);
        for(String key : hashMapTemperatureDuringDay.keySet()){
            hashMapTemperatureDuringDay.get(key).output();
        }
    }

    public String returnDate(){
        return date;
    }

    public String returnMaxTem(){
        return maxTem;
    }

    public String returnMinTem(){
        return mimTem;
    }

    public String returnForecast(){
        return forecast;
    }

    public String returnUrlForecastImage(){
        return urlForecastImage;
    }

    public LinkedHashMap<String, ForecastDuringOneDay> getHashMapTemperatureDuringDay() {
        return hashMapTemperatureDuringDay;
    }
}
