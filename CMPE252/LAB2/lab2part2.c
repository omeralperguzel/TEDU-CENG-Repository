#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define SIZE 1000


void readInput(int arr[], int *nPtr) {
    printf("Enter the number of elements:\n");
    scanf("%d", nPtr);
    printf("Enter %d elements:\n", *nPtr);
    for (int i = 0; i < *nPtr; i++) {
        scanf("%d", &arr[i]);
    }
}

void printNumbers(const int arr[], int n) {
    printf("Array elements: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}


void findMeanVar(const int arr[], int n, double *meanPtr, double *varPtr) {
    double sum = 0.0, mean, variance = 0.0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    mean = sum / n;
    for (int i = 0; i < n; i++) {
        variance += ((arr[i] - mean) * (arr[i] - mean));
    }
    variance /= n;
    *meanPtr = mean;
    *varPtr = variance;
}

int main() {
        int arr[SIZE];
        int n;
        double mean, var;

        readInput(arr, &n);
        printNumbers(arr, n);
		findMeanVar(arr, n, &mean, &var);
		printf("Mean of all elements = %.2lf\n", mean);
		printf("Variance of all elements = %.2lf\n", var);

        return 0;
}
