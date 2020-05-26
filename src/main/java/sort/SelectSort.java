package sort;

import java.lang.reflect.Array;

public class SelectSort {

    public static Integer[] selectSort(Integer[] array){
        Integer[] newArray = new Integer[array.length];


        for (int i = 0;i<array.length-1;i++){
            int k = i+1;
            Integer tempMax = 0;
            Integer index = 0;
            for (int j = 0;j<k;j++){
                if (compareNum(array[j],tempMax)){
                    tempMax = array[j];
                    index = j;
                }
            }
            switchNum(tempMax,index,array,i);

        }
        return array;
    }

    public static boolean compareNum(Integer a,Integer b){
        if (a>b){
            return true;
        }else{
            return false;
        }
    }
    public static void switchNum(Integer a,Integer index,Integer[] array,Integer arrayIndex){
        int temp = a;
        array[index] = array[arrayIndex];
        array[arrayIndex] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = {2,1,6,4,5,8,9};
        selectSort(array);
        for (int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
}
