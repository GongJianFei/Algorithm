package sort;

public class InsertSort {
    public static Integer[] insertSort(Integer[] array){


        for (int i = 1;i<array.length;i++){
          Integer temp = array[i];
          while (i>=1 && array[i-1]>temp){
              array[i] = array[i-1];
              i--;
          }
          array[i] = temp;

        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] newArray = {9,5,4,3,6,1,8,2};
        insertSort(newArray);
        for (Integer num:newArray){
            System.out.println(num);
        }

    }
}
