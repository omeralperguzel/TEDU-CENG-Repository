#include <stdio.h>
#include <stdlib.h>

int main() {

    char fname[100];

    printf("Enter txt file name:\n");
    scanf("%s", fname);
    FILE* file = fopen(fname, "r");

    if (file == NULL) {
        printf("Error.\n");
        return 1;
    }

//Program does not print all values of input file. Only prints the last value. Can you rewrite the program?


    int scores[1000] = {0}; //scores for all teams initialized to 0
    int id;
    char results[100];//character array

    while (fscanf(file, "%d %s", &id, results) == 2) {
        int s = 0;
        for (int i = 0; results[i] != '\0'; i++) {
            if (results[i] == 'W') {
                s=s+3;
            } else if (results[i] == 'D') {
                s=s+1;
            }
        }
        scores[id] += s;
    }

    fclose(file);
    printf("There are %d teams in total.\n",id);
    
    for (int i = 1; i <= id; i++) {
        if (scores[i] > 0) {
            printf("Team %d: %d\n", i, scores[i]);
        }
    }
    return 0;
}
