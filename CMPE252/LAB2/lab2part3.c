#include <stdio.h>
#include <stdlib.h>

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
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void countSmallerArray(const int arr[], int n, int csmallerArr[]) {
    for (int i = 0; i < n; i++) {
        int count = 0;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[i]) {
                count++;
            }
        }
        csmallerArr[i] = count;
    }
}

int main() {
        int arr[SIZE];
        int n;
        int csmallerArr[SIZE];
        readInput(arr, &n);
        printNumbers(arr, n);
		countSmallerArray(arr, n, csmallerArr);
		printf("Count Smaller ");
		printNumbers(csmallerArr, n);

        return 0;
}
