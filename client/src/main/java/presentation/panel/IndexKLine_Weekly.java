package presentation.panel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2016/3/29.
 */
public class IndexKLine_Weekly {

    private String getLatestFriday(){
        Calendar calendar = Calendar.getInstance();
        Date d = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String s = simpleDateFormat.format(d);
        while(!s.equals("星期五")){
            calendar.add(calendar.DATE,-1);
            d = calendar.getTime();
            s = simpleDateFormat.format(d);
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        s = simpleDateFormat.format(d);
        System.out.println(s);
        return s;
    }
    public static void main(String[] args){
        IndexKLine_Weekly indexKLine_weekly = new IndexKLine_Weekly();
        indexKLine_weekly.getLatestFriday();
    }
}
