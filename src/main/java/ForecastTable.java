import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ForecastTable {

    private JTable weatherTable;
    private MyTableModel model;
    private Object[] headers;
    private Object[][] data;
    private Sinoptik sinoptik;
    private Gismeteo gismeteo;
    private HashMap<String, String> hashMapRelationsGismeteoSinoptik;

    ForecastTable(Sinoptik sinoptik, Gismeteo gismeteo, String date){
        this.headers = new Object[]{"Sinoptik", "Gismeteo"};
        hashMapRelationsGismeteoSinoptik = new RelationsGismeteoSinoptikPhotos().getHashMap();
        data = new Object[4][2];
        this.sinoptik = sinoptik;
        this.gismeteo = gismeteo;
        //downloadAllPhotos();
        setData(date);
        model = new MyTableModel(data);
        model.setColumnIdentifiers(headers);
        weatherTable = new JTable(model);
        weatherTable.setRowHeight(28);
        Forecast forecast = getCurrentForecast(sinoptik.returnForecasts(), date);
        weatherTable.getColumnModel().getColumn(0).setCellRenderer(
                new ImageRenderer(data, "pictures\\" + forecast.returnUrlForecastImage().
                        substring(forecast.returnUrlForecastImage().lastIndexOf("/")+1)));
        forecast = getCurrentForecast(gismeteo.returnForecasts(), date);
        weatherTable.getColumnModel().getColumn(1).setCellRenderer(
                new ImageRenderer(data, "pictures\\" +
                        hashMapRelationsGismeteoSinoptik.get(forecast.returnUrlForecastImage().
                                substring(forecast.returnUrlForecastImage().indexOf("."))).
                                substring(hashMapRelationsGismeteoSinoptik.get(forecast.returnUrlForecastImage().
                                substring(forecast.returnUrlForecastImage().indexOf("."))).lastIndexOf("/"))));
    }

    private void setData(String date){
        setDataFromWeatherSite(sinoptik.returnForecasts(), date, 0);
        setDataFromWeatherSite(gismeteo.returnForecasts(), date, 1);
    }

    private void setDataFromWeatherSite(ArrayList<Forecast> forecasts, String date,
                                        int numberOfColumn){
        Forecast forecast = new Forecast();
        for (Forecast forecastsInArray : forecasts) {
            if (forecastsInArray.returnDate().equals(date)) {
                forecast = forecastsInArray;
                break;
            }
        }
        insertDataInTable(forecast, numberOfColumn);
    }

    private void insertDataInTable(Forecast forecast, int column){
        data[0][column] = forecast.returnForecast();
        //getPictureFromWeatherSite(forecast);
        data[1][column] = forecast.returnUrlForecastImage();
        data[2][column] = forecast.returnMinTem();
        data[3][column] = forecast.returnMaxTem();
    }

    public void changeData(String date){
        for(int i=3; i>=0; i--)
            model.removeRow(i);
        setData(date);
        for(int i=0; i<data.length; i++){
            model.insertRow(i, data[i]);
        }
        weatherTable.setModel(model);
        Forecast forecast = getCurrentForecast(sinoptik.returnForecasts(), date);
        weatherTable.getColumnModel().getColumn(0).setCellRenderer(
                new ImageRenderer(data, "pictures\\" + forecast.returnUrlForecastImage().
                        substring(forecast.returnUrlForecastImage().lastIndexOf("/")+1)));
        forecast = getCurrentForecast(gismeteo.returnForecasts(), date);
        weatherTable.getColumnModel().getColumn(1).setCellRenderer(
                new ImageRenderer(data, "pictures\\" +
                        hashMapRelationsGismeteoSinoptik.get(forecast.returnUrlForecastImage().
                                substring(forecast.returnUrlForecastImage().indexOf("."))).
                                substring(hashMapRelationsGismeteoSinoptik.get(forecast.returnUrlForecastImage().
                                        substring(forecast.returnUrlForecastImage().indexOf("."))).lastIndexOf("/"))));
    }

    private Forecast getCurrentForecast(ArrayList<Forecast> forecastArrayList, String date){
        for (Forecast forecastsInArray : forecastArrayList) {
            if (forecastsInArray.returnDate().equals(date)) {
                return  forecastsInArray;
            }
        }
        return null;
    }

    public JTable getWeatherTable() {
        return weatherTable;
    }
}
