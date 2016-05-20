package bl;

import vo.IndexVO;
import vo.StockVO;
import vo.TheIndexVO;

/**
 * Created by zcy on 2016/4/13.
 * 计算股票的各种指标
 */
public class CalculateIndex {
    TheIndexVO theIndexVO;

    public TheIndexVO getTheIndex(StockVO stockVO){
        theIndexVO = new TheIndexVO();
        theIndexVO.setBias(calculateBias(stockVO));
        theIndexVO.setRSI(calculateRSI(stockVO));
        theIndexVO.setWM(calculateWM(stockVO));
        theIndexVO.setAR(calculateAR(stockVO));
        theIndexVO.setBR(calculateBR(stockVO));
        theIndexVO.setVR(calculateVR(stockVO));
        return theIndexVO;
    }

    public TheIndexVO getTheIndex(IndexVO indexVO){
        theIndexVO = new TheIndexVO();
        theIndexVO.setBias(calculateBias(indexVO));
        theIndexVO.setRSI(calculateRSI(indexVO));
        theIndexVO.setWM(calculateWM(indexVO));
        theIndexVO.setAR(calculateAR(indexVO));
        theIndexVO.setBR(calculateBR(indexVO));
        theIndexVO.setVR(calculateVR(indexVO));
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
        for(int i=length-1;i>length-22;i--){
            if(stockVO.getHigh()[i-1]>stockVO.getHigh()[k]){
                k = i-1;
            }
        }
        double h = stockVO.getHigh()[k]; //30天内的最高价

        k = length-1;
        for(int i=length-1;i>length-22;i--){
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
        for(int i=length-1;i>length-22;i--){
            if(indexVO.getHigh()[i-1]>indexVO.getHigh()[k]){
                k = i-1;
            }
        }
        double h = indexVO.getHigh()[k]; //30天内的最高价

        k = length-1;
        for(int i=length-1;i>length-22;i--){
            if(indexVO.getLow()[i-1]<indexVO.getLow()[k]){
                k = i-1;
            }
        }
        double l = indexVO.getLow()[k]; //30天内的最低价
        double result = (h-close)/(h-l)*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateAR(StockVO stockVO){
        int length = stockVO.getDate().length;
        double[] opens = new double[20];
        double[] highs = new double[20];
        double[] lows = new double[20];
        for(int i=length-1;i>=length-20;i--){
            opens[length-1-i] = stockVO.getOpen()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            highs[length-1-i] = stockVO.getHigh()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            lows[length-1-i] = stockVO.getLow()[i];
        }
        double sum1 = 0;
        for(int i=0;i<opens.length;i++){
            sum1+=(highs[i]-opens[i]);
        }
        double sum2 = 0;
        for(int i=0;i<opens.length;i++){
            sum2+=(opens[i]-lows[i]);
        }
        double result = sum1/sum2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateAR(IndexVO indexVO){
        int length = indexVO.getDate().length;
        double[] opens = new double[20];
        double[] highs = new double[20];
        double[] lows = new double[20];
        for(int i=length-1;i>=length-20;i--){
            opens[length-1-i] = indexVO.getOpen()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            highs[length-1-i] = indexVO.getHigh()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            lows[length-1-i] = indexVO.getLow()[i];
        }
        double sum1 = 0;
        for(int i=0;i<opens.length;i++){
            sum1+=(highs[i]-opens[i]);
        }
        double sum2 = 0;
        for(int i=0;i<opens.length;i++){
            sum2+=(opens[i]-lows[i]);
        }
        double result = sum1/sum2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateBR(StockVO stockVO){
        int length = stockVO.getDate().length;
        double[] closes = new double[20];
        double[] highs = new double[20];
        double[] lows = new double[20];
        for(int i=length-2;i>=length-21;i--){
            closes[length-2-i] = stockVO.getClose()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            highs[length-1-i] = stockVO.getHigh()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            lows[length-1-i] = stockVO.getLow()[i];
        }
        double sum1 = 0;
        for(int i=0;i<closes.length;i++){
            sum1+=(highs[i]-closes[i]);
        }
        double sum2 = 0;
        for(int i=0;i<closes.length;i++){
            sum2+=(closes[i]-lows[i]);
        }
        double result = sum1/sum2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateBR(IndexVO indexVO){
        int length = indexVO.getDate().length;
        double[] closes = new double[20];
        double[] highs = new double[20];
        double[] lows = new double[20];
        for(int i=length-2;i>=length-21;i--){
            closes[length-2-i] = indexVO.getClose()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            highs[length-1-i] = indexVO.getHigh()[i];
        }
        for(int i=length-1;i>=length-20;i--){
            lows[length-1-i] = indexVO.getLow()[i];
        }
        double sum1 = 0;
        for(int i=0;i<closes.length;i++){
            sum1+=(highs[i]-closes[i]);
        }
        double sum2 = 0;
        for(int i=0;i<closes.length;i++){
            sum2+=(closes[i]-lows[i]);
        }
        double result = sum1/sum2*100;
        return ((double)Math.round(result*100))/100;
    }

    private double calculateVR(IndexVO indexVO){
        int AVS,BVS,CVS;
        AVS=0;
        BVS=0;
        CVS=0;
        double VR = 0;
        int length = indexVO.getDate().length;
        double[] closes = new double[20];
        double[] opens = new double[20];
        double[] volume = new double[20];
        for(int i=length-2;i>=length-21;i--){
            closes[length-2-i] = indexVO.getClose()[i];
            opens[length-2-i] = indexVO.getOpen()[i];
            volume[length-2-i] = indexVO.getVolume()[i];
        }
        for(int i = 0;i<opens.length;i++){
            if(closes[i]-opens[i]>0){
                AVS+=volume[i];
            }else if(closes[i]-opens[i]<0){
                BVS+=volume[i];
            }else{
                CVS+=volume[i];
            }
        }
        VR = ((AVS+CVS*0.5)/(BVS+CVS*0.5));
        return ((double)Math.round(VR*100))/100;
    }

    private double calculateVR(StockVO stockVO){
        int AVS,BVS,CVS;
        AVS=0;
        BVS=0;
        CVS=0;
        double VR = 0;
        int length = stockVO.getDate().length;
        double[] closes = new double[20];
        double[] opens = new double[20];
        double[] volume = new double[20];
        for(int i=length-2;i>=length-21;i--){
            closes[length-2-i] = stockVO.getClose()[i];
            opens[length-2-i] = stockVO.getOpen()[i];
            volume[length-2-i] = stockVO.getVolume()[i];
        }
        for(int i = 0;i<opens.length;i++){
            if(closes[i]-opens[i]>0){
                AVS+=volume[i];
            }else if(closes[i]-opens[i]<0){
                BVS+=volume[i];
            }else{
                CVS+=volume[i];
            }
        }
        VR = ((AVS+CVS*0.5)/(BVS+CVS*0.5));
        return ((double)Math.round(VR*100))/100;
    }
}
