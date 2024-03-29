package CMPE223SS.HW2.Q2.Try2;

public static void smallestPairwiseDifference(int[] arr, String sortAlgorithm) {
    switch (sortAlgorithm) {
        case "Selection":
            selectionSort(arr);
            break;
        case "Insertion":
            insertionSort(arr);
            break;
        case "Merge":
            mergeSort(arr);
            break;
        case "Quick":
            quickSort(arr);
            break;
        case "noSort":
            break;
        default:
            System.out.println("Invalid sorting algorithm.");
            return;
    }
    int minDiff = Integer.MAX_VALUE;
    int minSum = Integer.MAX_VALUE;
    int n = arr.length;
    int num1 = 0, num2 = 0;
    for (int i = 0; i < n - 1; i++) {
        int diff = Math.abs(arr[i] - arr[i + 1]);
        if (diff < minDiff) {
            minDiff = diff;
            num1 = arr[i];
            num2 = arr[i + 1];
            minSum = num1 + num2;
        } else if (diff == minDiff) {
            int sum = arr[i] + arr[i + 1];
            if (sum < minSum) {
                num1 = arr[i];
                num2 = arr[i + 1];
                minSum = sum;
            }
        }
    }
    System.out.println("ABS:" + minDiff + ", Min=" + num1 + ", Max=" + num2);
}