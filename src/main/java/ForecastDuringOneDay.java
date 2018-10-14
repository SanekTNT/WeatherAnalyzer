public class ForecastDuringOneDay {

    private String forecast, photoURL, temperature;

    ForecastDuringOneDay(){}

    ForecastDuringOneDay(String forecast, String photoURL, String temperature){
        this.forecast = forecast;
        this.photoURL = photoURL;
        this.temperature = temperature;
    }

    public String getForecast() {
        return forecast;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void output(){
        System.out.println("forecast: " + forecast);
        System.out.println("photoURL: " + photoURL);
        System.out.println("temperature: " + temperature);
    }

}
