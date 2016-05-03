package database;

import data.GetStockData;
import po.StockPO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zcy on 2016/5/3.
 */
public class Initial {

    public void initialTable_StockInfo(){
        GetStockData getStockData = new GetStockData();
        List<StockPO> list = new ArrayList<StockPO>();
        try {
            String stocks = getID_bankStock();
            String[] names = stocks.split(" ");
            for (int i = 0; i < names.length; i++) {
                list.add(getStockData.getStockData_name(names[i], "2005-01-01", getToday_date()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getDate()[0]);
        }
    }

    public static void main(String[] args){
        Initial initial = new Initial();
        initial.initialTable_StockInfo();
    }

    /**
     * @return String
     * 得到所有银行股的ID
     */
    private String getID_bankStock(){
        String stocks = "";
        String temp;
        try {
            File file = new File("client/src/main/resources/bank_stock.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while((temp = bufferedReader.readLine())!=null){
                String[] co = temp.split(" ");
                stocks+=co[0]+" ";
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks.trim();
    }

    private String getToday_date(){
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = simpleDateFormat.format(d);
        return s;
    }
}
