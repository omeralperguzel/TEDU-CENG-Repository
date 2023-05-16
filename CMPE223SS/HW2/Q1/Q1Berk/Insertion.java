package CMPE223SS.HW2.Q1.Q1Berk;

public class Insertion {

    public void Insertion_sort(Integer[] array) {

        // we need to length of array for our for loop
        int n = array.length;


        for (int i = 1; i < n; i++) {
            // We declared i = 1 for compare first item and right items and we
            // increased i for compare left side elements with right side elements
            int number = array[i];

            int j = i - 1;
            // j always have to come before i so we want compare
            while (j >= 0 && array[j] > number) {
                //so if left hand side bigger than right we are changing ascending order
                // but if we have 2 integer bigger than right side our while loop working for twice and
                // replacing item 2 step left
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = number;
        }


    }

    public void Insertion_sort_DO(Integer[] array) {

        int n = array.length;

        for (int i = (n - 2); i >= 0; i--) {

            int number = array[i];

            int j = i + 1;

            while (j <= (n - 1) && array[j] > number) {

                array[j - 1] = array[j];

                j = j + 1;
            }
            array[j-1] = number;


        }
    }

    public void Insertion_sort_DV(Double[] array){
            int n = array.length;

            for(int i=1;i<n;i++){
                double number = array[i];

                int j = i-1;
                while(j>=0 && array[j]>number){
                    array[j+1] = array[j];

                    j = j-1;
                }
                array[j+1] = number;

            }
    }
}
