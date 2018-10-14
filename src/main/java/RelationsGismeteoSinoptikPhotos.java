import java.io.File;
import java.util.HashMap;

public class RelationsGismeteoSinoptikPhotos {

    private HashMap<String, String> hashMap;

    RelationsGismeteoSinoptikPhotos(){
        hashMap = new HashMap<>();
        for(int i=0; i<=4; i++){
            if(i==0) {
                hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.png",
                        "//sinst.fwdcdn.com/img/weatherImg/m/d000.gif"); // sunny weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d000.gif",
                        false);
                hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.pngSmall",
                        "//sinst.fwdcdn.com/img/weatherImg/s/d000.gif"); // small sunny weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/d000.gif",
                        true);
                hashMap.put(".gismeteo.ua/static/images/icons/new/n.moon.pngSmall",
                        "//sinst.fwdcdn.com/img/weatherImg/s/n000.gif"); // small night weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n000.gif",
                        true);
            }
            else {
                hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.c" + i + ".png",
                        "//sinst.fwdcdn.com/img/weatherImg/m/d" + i + "00.gif"); // sunny weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d" + i + "00.gif",
                        false);
                hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.c" + i + ".pngSmall",
                        "//sinst.fwdcdn.com/img/weatherImg/s/d" + i + "00.gif"); // small sunny weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/d" + i + "00.gif",
                        true);
                hashMap.put(".gismeteo.ua/static/images/icons/new/n.moon.c" + i + ".pngSmall",
                        "//sinst.fwdcdn.com/img/weatherImg/s/n" + i + "00.gif"); // small night sunny weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n" + i + "00.gif",
                        true);
                hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.c" + i + ".s" + i + ".png",
                        "//sinst.fwdcdn.com/img/weatherImg/m/d" + i + i + "2.gif"); // snowy weather
                getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d" + i + i + "2.gif",
                        false);
                for(int j=1; j<=4; j++){
                    hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.c" + i + ".r" + j +".png",
                            "//sinst.fwdcdn.com/img/weatherImg/m/d" + i + j +"0.gif"); // rainy weather
                    getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d" + i + j +"0.gif",
                            false);
                    hashMap.put(".gismeteo.ua/static/images/icons/new/d.sun.c" + i + ".r" + j +".pngSmall",
                            "//sinst.fwdcdn.com/img/weatherImg/s/d" + i + j +"0.gif"); // small rainy weather
                    getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/d" + i + j +"0.gif",
                            true);
                    hashMap.put(".gismeteo.ua/static/images/icons/new/n.moon.c" + i + ".r" + j +".pngSmall",
                            "//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"0.gif"); // night rainy weather
                    getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"0.gif",
                            true);
                    hashMap.put(".gismeteo.ua/static/images/icons/new/n.moon.c" + i + ".r" + j +".pngSmall",
                            "//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"0.gif"); // small night rainy weather
                    getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"0.gif",
                            true);
                    hashMap.put(".gismeteo.ua/static/images/icons/new/n.moon.c" + i + ".s" + j + ".png",
                            "//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"2.gif"); // night snowy weather
                    getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n" + i + j +"2.gif",
                            false);
                }
            }
        }
        getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d500.gif", false);
        getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/m/d600.gif", false);
        getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n500.gif", true);
        getPictureFromWeatherSite("//sinst.fwdcdn.com/img/weatherImg/s/n600.gif", true);
    }

    private void getPictureFromWeatherSite(String string, boolean isSmall){
        String fileName = "";
        if(isSmall)
            fileName = "C:\\Users\\alexandr\\Desktop\\Java\\weather\\src\\main\\java\\pictures\\" + "s" +
                    string.substring(string.lastIndexOf("/") + 1);
        else
            fileName = "C:\\Users\\alexandr\\Desktop\\Java\\weather\\src\\main\\java\\pictures\\" +
                    string.substring(string.lastIndexOf("/"));
        if (!(new File(fileName)).exists()) {
            new Downloader("http:"+ string, fileName);
        }
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }
}
