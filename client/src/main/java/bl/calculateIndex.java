package bl;

import vo.IndexVO;
import vo.StockVO;
import vo.TheIndexVO;

/**
 * Created by zcy on 2016/4/13.
 */
public class CalculateIndex {
    TheIndexVO theIndexVO;

    public TheIndexVO getTheIndex(StockVO stockVO){
        theIndexVO = new TheIndexVO();
        theIndexVO.setBias(calculateBias(stockVO));
        theIndexVO.setRSI(calculateRSI(stockVO));
        theIndexVO.setWM(calculateWM(stockVO));
        return theIndexVO;
    }

    public TheIndexVO getTheIndex(IndexVO indexVO){
        theIndexVO = new TheIndexVO();
        theIndexVO.setBias(calculateBias(indexVO));
        theIndexVO.setRSI(calculateRSI(indexVO));
        theIndexVO.setWM(calculateWM(indexVO));
        return theIndexVO;
    }

    private double calculateBias(StockVO stockVO){
        int length = stockVO.getDate().length;
        double[] closes = new double[5];
        for(int i=length-1;i>=length-5;i--){
            closes[length-1-i] = stockVO.getClose()[i];
        }
        double close = stockVO.getClose()[length-1];
        double sum = 0;
        for(int i = 0;i<5;i++){
            sum+=closes[i];
        }
        double avg = sum/5.0;
        double result = (close-avg)/avg*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateBias(IndexVO indexVO){
        int length = indexVO.getDate().length;
        double[] closes = new double[5];
        for(int i=length-1;i>=length-5;i--){
            closes[length-1-i] = indexVO.getClose()[i];
        }
        double close = indexVO.getClose()[length-1];
        double sum = 0;
        for(int i = 0;i<5;i++){
            sum+=closes[i];
        }
        double avg = sum/5.0;
        double result = (close-avg)/avg*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateRSI(StockVO stockVO){
        int length = stockVO.getDate().length;
        double[] closes = new double[6]; //[0]是最近的收盘价
        for(int i=length-1;i>=length-6;i--){
            closes[length-1-i] = stockVO.getClose()[i];
        }
        double sum1 = 0; //上涨总幅度
        for(int i=0;i<5;i++){
            if(closes[i]>closes[i+1]){
                sum1+=(closes[i]-closes[i+1]);
            }
        }
        double avg1 = sum1/5;
        double sum2 = 0; //上涨总幅度和下跌总幅度
        for(int i=0;i<5;i++){
            sum2+=Math.abs(closes[i]-closes[i+1]);
        }
        double avg2 = sum2/5;
        double result = avg1/avg2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateRSI(IndexVO indexVO){
        int length = indexVO.getDate().length;
        double[] closes = new double[6]; //[0]是最近的收盘价
        for(int i=length-1;i>=length-6;i--){
            closes[length-1-i] = indexVO.getClose()[i];
        }
        double sum1 = 0; //上涨总幅度
        for(int i=0;i<5;i++){
            if(closes[i]>closes[i+1]){
                sum1+=(closes[i]-closes[i+1]);
            }
        }
        double avg1 = sum1/5;
        double sum2 = 0; //上涨总幅度和下跌总幅度
        for(int i=0;i<5;i++){
            sum2+=Math.abs(closes[i]-closes[i+1]);
        }
        double avg2 = sum2/5;
        double result = avg1/avg2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateWM(StockVO stockVO){
        int length = stockVO.getDate().length;
        double close = stockVO.getClose()[length-1];
        int k = length-1;
        for(int i=length-1;i>length-30;i--){
            if(stockVO.getHigh()[i-1]>stockVO.getHigh()[k]){
                k = i-1;
            }
        }
        double h = stockVO.getHigh()[k]; //30天内的最高价

        k = length-1;
        for(int i=length-1;i>length-30;i--){
            if(stockVO.getLow()[i-1]<stockVO.getLow()[k]){
                k = i-1;
            }
        }
        double l = stockVO.getLow()[k]; //30天内的最低价
        double result = (h-close)/(h-l)*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateWM(IndexVO indexVO){
        int length = indexVO.getDate().length;
        double close = indexVO.getClose()[length-1];
        int k = length-1;
        for(int i=length-1;i>length-30;i--){
            if(indexVO.getHigh()[i-1]>indexVO.getHigh()[k]){
                k = i-1;
            }
        }
        double h = indexVO.getHigh()[k]; //30天内的最高价

        k = length-1;
        for(int i=length-1;i>length-30;i--){
            if(indexVO.getLow()[i-1]<indexVO.getLow()[k]){
                k = i-1;
            }
        }
        double l = indexVO.getLow()[k]; //30天内的最低价
        double result = (h-close)/(h-l)*100;
        return ((double)Math.round(result*100))/100;
    }
}
