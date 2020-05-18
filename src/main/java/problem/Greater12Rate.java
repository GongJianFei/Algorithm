package problem;

import java.util.ArrayList;
import java.util.List;

public class Greater12Rate {


    private static double calculateRate(int result,List<Integer> list){
        int num = list.size();
        System.out.println(num);
        int count = 0;
        int rightCount = 0;
        for (int i=1;i<=6;i++){
            list.set(0,i);
            for (int j=1;j<=6;j++){
                list.set(1,j);
                for (int k=1;k<=6;k++){
                    list.set(2,k);
                    for (int n=1;n<=6;n++) {
                        list.set(3,n);
                        System.out.println((list.get(0) + list.get(1) + list.get(2) + list.get(3))+":第"+count+"次");
                        if ((list.get(0) + list.get(1) + list.get(2) + list.get(3)) > result) {
                            rightCount++;
                        }

                        count++;
                    }

                }
            }
        }
        System.out.println();
    return (double) rightCount/(double) count*100;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        double rate = calculateRate(12,list);
        System.out.println(rate +"%");
    }
}
