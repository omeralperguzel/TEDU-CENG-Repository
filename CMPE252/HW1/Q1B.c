#include <stdio.h>

int main() {

    char filename[100];

    FILE *fp;
    printf("Enter txt file name:\n");
    scanf("%s", filename);
    fp = fopen(filename, "r"); // open the input file for reading
    if (fp == NULL) {
        printf("Error opening file.");
        return 1;
    }

    int team_id, points, i, j;
    //int scores[1000] = {0}; //scores for all teams initialized to 0
    char result[10];
    //int id;

    while (fscanf(fp, "%d %s", &team_id, result) != EOF) {
        points = 0;
        for (i = 0; result[i] != '\0'; i++) {
            if (result[i] == 'W') {
                points += 3;
            } else if (result[i] == 'D') {
                points += 1;
            }
        }
        //scores[id] += points;
        printf("There are %d teams in total.\n",team_id);
        printf("Team %d: %d\n", team_id, points);
    }

    fclose(fp); // close the input file
    return 0;
}
