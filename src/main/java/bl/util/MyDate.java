package bl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zcy on 2016/5/17.
 * 与日期相关的操作
 */
public class MyDate {

    /**
     * @return String
     * 得到今天的日期
     */
    public static String getDate_Today(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * @return String
     * 得到30天前的数据
     */
    public static String getDate_OneMonthAgo(){
        return getDate_NDaysAgo(30);
    }

    /**
     * @return String
     * 得到n天前的数据
     */
    public static String getDate_NDaysAgo(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-n);
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * @param date1
     * @param date2
     * @return int
     * 如果date1小于等于date2，返回0
     * 如果date1大于等于date2，返回1
     */
    public static int compareDate(String date1,String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(date1);
            d2 = df.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d1.getTime()<=d2.getTime()){
            return 0;
        }
        else if(d1.getTime()>=d2.getTime()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
