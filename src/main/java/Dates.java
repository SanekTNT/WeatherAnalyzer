import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Dates {

    public ArrayList<String> datesForForecast(int days) {
        ArrayList<String> dates = new ArrayList<String>();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(new Date());
        for(int i=0; i<days; i++){
            dates.add(calendarToday.get(Calendar.YEAR) + "-"
                    + changeFormOfNumber(calendarToday.get(Calendar.MONTH), true) + "-"
                    + changeFormOfNumber(calendarToday.get(Calendar.DAY_OF_MONTH), false));
            calendarToday.set(Calendar.DAY_OF_YEAR, calendarToday.get(Calendar.DAY_OF_YEAR) + 1);
        }
        return dates;
    }

    private String changeFormOfNumber(int value, boolean isMonth){
        if(isMonth)
            value++;
        String str = String.valueOf(value);
        if(str.length() == 1)
            str = "0" + str;
        return str;
    }
}
