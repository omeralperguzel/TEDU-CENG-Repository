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

    int team_id, points, i, j, num_teams = 0;
    char result[10];

    while (fscanf(fp, "%d %s", &team_id, result) != EOF) {
        points = 0;
        for (i = 0; result[i] != '\0'; i++) {
            if (result[i] == 'W') {
                points += 3;
            } else if (result[i] == 'D') {
                points += 1;
            }
        }
        printf("Team %d: %d\n", team_id, points);
        num_teams++;
    }

    printf("There are %d teams in total.\n",num_teams);

    /*
    for (int i = 1; i <= team_id; i++) {
        if (result[i] > 0) {
            printf("Team %d: %d\n", i, result[i]);
        }
    }*/
    return 0;

    fclose(fp); // close the input file
    return 0;
}
