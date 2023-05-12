#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define STR_LEN 20
#define MAX_ELEM 10

void findUnion(char *group1[], char *group2[], int n1, int n2, char *unionSet[], int *unionCountPtr) {
    int unionCount = 0;

    // First, we copy all elements of group1 to the unionSet array.
    for (int i = 0; i < n1; i++) {
        unionSet[unionCount] = group1[i];
        unionCount++;
    }

    // Then, we compare each element of group2 with each element of unionSet.
    for (int i = 0; i < n2; i++) {
        int found = 0;
        for (int j = 0; j < unionCount; j++) {
            // If the element of group2 is already in unionSet, we skip it.
            if (strcmp(group2[i], unionSet[j]) == 0) {
                found = 1;
                break;
            }
        }
        // If the element of group2 is not in unionSet, we add it.
        if (!found) {
            unionSet[unionCount] = group2[i];
            unionCount++;
        }
    }

    // Finally, we update the value of unionCountPtr.
    *unionCountPtr = unionCount;
}

void printCarInfo(char *unionSet[], int unionCount) {
    const int NEW_STR_LEN = 50;
    char carName[NEW_STR_LEN], maxSpeed[NEW_STR_LEN], year[NEW_STR_LEN], age[NEW_STR_LEN];
    for (int i = 0; i < unionCount; i++) {
        char *token;
        strcpy(carName, unionSet[i]);
        token = strtok(carName, "_");
        while (token != NULL) {
            for (int j = 0; j < strlen(token); j++) {
                carName[j] = toupper(token[j]);
            }
            token = strtok(NULL, "_");
            if (token != NULL) {
                strcpy(maxSpeed, token);
                token = strtok(NULL, "_");
                strcpy(year, token);
                break;
            }
        }
        int carAge = 2023 - atoi(year);
        //printf("%s %s %s %d\n", carName, maxSpeed, year, carAge);
        printf("%s %s %d\n", carName, maxSpeed, carAge);
    }
}

int main() {
    char list[MAX_ELEM][STR_LEN] = {"", "citroen_250_2010", "honda_200_2005",
    "mercedes_300_2020", "nissan_190_2014", "peugeot_210_2000",
    "opel_210_2011", "volkswagen_200_2003", "suzuki_240_2022", "bmw_260_2021"};

    char *group1[MAX_ELEM];
    int n1;

    char *group2[MAX_ELEM];
    int n2;

    int i;
    int ind;

    printf("Number of elements in car group1: ");
    scanf("%d", &n1);
    printf("Entries in car group1: ");
    for (i = 0; i < n1; i++) {
        scanf("%d", &ind);
        group1[i] = list[ind];
    }

    printf("\nNumber of elements in car group2: ");
    scanf("%d",&n2);
    printf("Entries in car group2: ");
    for (i = 0; i < n2; i++) {
        scanf("%d", &ind);
        group2[i] = list[ind];
    }

    char *unionSet[2*MAX_ELEM];
    int unionCount;

    findUnion(group1, group2, n1, n2, unionSet, &unionCount);

    printf("\ngroup1:\n");
    for (int i = 0; i < n1; i++) {
        printf("%s\n",group1[i]);
    }

    printf("\ngroup2:\n");
    for (int i = 0; i < n2; i++) {
        printf("%s\n",group2[i]);
    }

    printf("\nunion of group1 and group2:\n");
    for (int i = 0; i < unionCount; i++) {
        printf("%s\n",unionSet[i]);
    }

    printf("\nDo you want to print the union set in NAME, max speed and age format (1/0)? ");
    int response;

    scanf("%d", &response);

    if (response == 1) {
        printf("\nunion of group1 and group2 in NAME, max speed and age format:\n");
        printCarInfo(unionSet, unionCount);

        printf("\nunion of group1 and group2:\n");
        for (int i = 0; i < unionCount; i++) {
            printf("%s\n",unionSet[i]);
        }
    }

    return 0;
}