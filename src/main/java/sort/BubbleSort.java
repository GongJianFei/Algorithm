package sort;

public class BubbleSort {
    public static Integer[] bubbleSort(Integer[] array){
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i]>array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        Integer[] array = {2,1,6,4,5,8,9};
        bubbleSort(array);
        for (int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
