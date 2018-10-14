import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ForecastTableDayTime {

    private JTable weatherTable;
    private MyTableModelDayTime model;
    private Object[] headers;
    private Object[][] data;
    private ArrayList<Forecast> forecasts;
    private boolean isGismeteo;
    private HashMap<String, String> hashMapRelationsGismeteoSinoptik;

    ForecastTableDayTime(ArrayList<Forecast> forecasts, String date, boolean isGismeteo){
        this.headers = new Object[]{"Ночь", "Утро", "День", "Вечер"};
        this.isGismeteo = isGismeteo;
        hashMapRelationsGismeteoSinoptik = new RelationsGismeteoSinoptikPhotos().getHashMap();
        data = new Object[3][4];
        this.forecasts = forecasts;
        setData(date);
        model = new MyTableModelDayTime(data);
        model.setColumnIdentifiers(headers);
        weatherTable = new JTable(model);
        weatherTable.setRowHeight(23);
        Forecast forecast = getCurrentForecast(forecasts, date);
        for(int i=0; i<4; i++){
            if(isGismeteo) {
                String stringDayTime = hashMapRelationsGismeteoSinoptik.get(forecast.getHashMapTemperatureDuringDay().
                                get(headers[i].toString()).getPhotoURL().
                        substring(forecast.getHashMapTemperatureDuringDay().
                                get(headers[i].toString()).getPhotoURL().indexOf(".")) + "Small");
                weatherTable.getColumnModel().getColumn(i).setCellRenderer(
                        new ImageRenderer(data, "pictures\\"
                                + stringDayTime.substring(stringDayTime.lastIndexOf("/") - 1,
                                stringDayTime.lastIndexOf("/"))
                                + stringDayTime.substring(stringDayTime.lastIndexOf("/") + 1)));
            }
            else {
                String stringDayTime = forecast.getHashMapTemperatureDuringDay().get(headers[i].toString()).
                        getPhotoURL();
                weatherTable.getColumnModel().getColumn(i).setCellRenderer(
                        new ImageRenderer(data, "pictures\\"
                                + stringDayTime.substring
                                (stringDayTime.lastIndexOf("/") - 1,
                                        stringDayTime.lastIndexOf("/"))
                                + stringDayTime.substring
                                (stringDayTime.lastIndexOf("/") + 1)));
            }
        }
    }

    private void setData(String date){
        Forecast forecast = getCurrentForecast(forecasts, date);
        insertDataInTable(forecast);
    }

    private Forecast getCurrentForecast(ArrayList<Forecast> forecastArrayList, String date){
        for (Forecast forecastsInArray : forecastArrayList) {
            if (forecastsInArray.returnDate().equals(date)) {
                return  forecastsInArray;
            }
        }
        return null;
    }

    private void insertDataInTable(Forecast forecast){
        LinkedHashMap<String, ForecastDuringOneDay> hashMap = forecast.getHashMapTemperatureDuringDay();
        for(int i=0; i<4; i++){
            data[0][i] = hashMap.get(headers[i].toString()).getForecast();
            data[1][i] = hashMap.get(headers[i].toString()).getPhotoURL();
            data[2][i] = hashMap.get(headers[i].toString()).getTemperature();
        }
    }

    public void changeData(String date){
        for(int i=2; i>=0; i--)
            model.removeRow(i);
        setData(date);
        for(int i=0; i<data.length; i++){
            model.insertRow(i, data[i]);
        }
        weatherTable.setModel(model);
        Forecast forecast = getCurrentForecast(forecasts, date);
        for(int i=0; i<4; i++){
            if(isGismeteo) {
                String stringDayTime = hashMapRelationsGismeteoSinoptik.get(forecast.getHashMapTemperatureDuringDay().
                        get(headers[i].toString()).getPhotoURL().
                        substring(forecast.getHashMapTemperatureDuringDay().
                                get(headers[i].toString()).getPhotoURL().indexOf(".")) + "Small");
                weatherTable.getColumnModel().getColumn(i).setCellRenderer(
                        new ImageRenderer(data, "pictures\\"
                                + stringDayTime.substring(stringDayTime.lastIndexOf("/") - 1,
                                stringDayTime.lastIndexOf("/"))
                                + stringDayTime.substring(stringDayTime.lastIndexOf("/") + 1)));
            }
            else {
                String stringDayTime = forecast.getHashMapTemperatureDuringDay().get(headers[i].toString()).
                        getPhotoURL();
                weatherTable.getColumnModel().getColumn(i).setCellRenderer(
                        new ImageRenderer(data, "pictures\\"
                                + stringDayTime.substring
                                (stringDayTime.lastIndexOf("/") - 1,
                                        stringDayTime.lastIndexOf("/"))
                                + stringDayTime.substring
                                (stringDayTime.lastIndexOf("/") + 1)));
            }
        }
    }

    public JTable getWeatherTable() {
        return weatherTable;
    }

}
