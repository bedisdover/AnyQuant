package database;

import bl.util.MyDate;
import data.GetIndexData;
import data.GetStockData;
import po.IndexPO;
import po.StockPO;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zcy on 2016/5/3.
 *
 */
public class Initial {

    public void initialTable_StockInfo(){
        GetStockData getStockData = new GetStockData();
        List<StockPO> list = new ArrayList<StockPO>();
        try {
            String stocks = getID_bankStock();
            String[] names = stocks.split(" ");
            for (int i = 0; i < names.length; i++) {
                list.add(getStockData.getStockData_name(names[i], "2005-01-01", MyDate.getDate_Today()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connect co=new Connect();
        String sql="INSERT INTO stockinfo values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=co.getPreparedStatement(sql);

        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).getDate().length;j++){
                try {
                    pstmt.setString(1,list.get(i).getId());
                    pstmt.setLong(2,list.get(i).getVolume()[j]);
                    pstmt.setDouble(3,remain2bit(list.get(i).getPb()[j]));
                    pstmt.setDouble(4,remain2bit(list.get(i).getHigh()[j]));
                    pstmt.setDouble(5,remain2bit(list.get(i).getPe_ttm()[j]));
                    pstmt.setDouble(6,remain2bit(list.get(i).getAdj_price()[j]));
                    pstmt.setDouble(7,remain2bit(list.get(i).getLow()[j]));
                    pstmt.setString(8,list.get(i).getDate()[j]);
                    pstmt.setDouble(9,remain2bit(list.get(i).getClose()[j]));
                    pstmt.setDouble(10,remain2bit(list.get(i).getOpen()[j]));
                    pstmt.setDouble(11,remain2bit(list.get(i).getTurnover()[j]));
                    pstmt.setDouble(12,remain2bit(list.get(i).getIncrease_decreaseRate()[j]));
                    pstmt.setDouble(13,remain2bit(list.get(i).getIncrease_decreaseNum()[j]));
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        co.closeConnection();
    }

    public void initialTable_Indexinfo(){
        GetIndexData getIndexData = new GetIndexData();
        IndexPO po = new IndexPO(1);
        try {
            po = getIndexData.getIndexDataBetween("2005-01-01",MyDate.getDate_Today());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connect co=new Connect();
        String sql="INSERT INTO indexinfo values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=co.getPreparedStatement(sql);

        for(int i=0;i<po.getDate().length;i++){
            try {
                pstmt.setString(1,po.getName());
                pstmt.setLong(2,po.getVolume()[i]);
                pstmt.setDouble(3,remain2bit(po.getHigh()[i]));
                pstmt.setDouble(4,remain2bit(po.getAdj_price()[i]));
                pstmt.setDouble(5,remain2bit(po.getLow()[i]));
                pstmt.setString(6,po.getDate()[i]);
                pstmt.setDouble(7,remain2bit(po.getClose()[i]));
                pstmt.setDouble(8,remain2bit(po.getOpen()[i]));
                pstmt.setDouble(9,remain2bit(po.getIncrease_decreaseRate()[i]));
                pstmt.setDouble(10,remain2bit(po.getIncrease_decreaseNum()[i]));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        co.closeConnection();
    }

    public static void main(String[] args){
        Initial initial = new Initial();
        initial.initialTable_Indexinfo();
    }

    /**
     * @return String
     * 得到所有银行股的ID
     */
    private String getID_bankStock(){
        String stocks = "";
        String temp;
        try {
            File file = new File("src/main/resources/bank_stock.txt");
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

    private static double remain2bit(double a){
        return ((double)Math.round(a*100))/100;
    }

}
