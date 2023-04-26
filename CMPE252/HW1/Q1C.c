#include <stdio.h>

int main() {
    FILE *fp;
    fp = fopen("team3.txt", "r"); // open the input file for reading
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
        printf("Team %d: %d points\n", team_id, points);
        num_teams++;
    }

    printf("Number of teams: %d\n", num_teams);

    fclose(fp); // close the input file
    return 0;
}