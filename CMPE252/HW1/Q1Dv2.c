#include <stdio.h>
#include <string.h>

int main() {

    char filename[100];

    FILE *fp;
    printf("Enter txt file name:\n");
    scanf("%s", filename);
    fp = fopen(filename, "r"); // open the input file for reading
    
    if (fp == NULL) {
        printf("There are 0 teams in total.");
        return 1;
    }

    char line[101];
    int team_id, num_teams = 0;
    char str[101];

    while (fgets(line, sizeof(line), fp) != NULL) {

        sscanf(line, "%d %s", &team_id, str);
        num_teams++;
    }
    printf("There are %d teams in total.\n", num_teams);
    fclose(fp);

    fp = fopen(filename, "r");

    if (fp == NULL) {
        return 1;
    }

    while (fgets(line, sizeof(line), fp) != NULL) {
            
            sscanf(line, "%d %s", &team_id, str);
            int length = strlen(str);
            int points = 0;
            for (int i = 0; i < length; i++)
            {
                if (str[i] == 'W') 
                {
                    points += 3; 
                } 
                else if (str[i] == 'D') 
                {
                    points += 1;
                } 
                else if (str[i] == 'L') {} 
            }
            printf("Team %d: %d\n", team_id, points);
            //num_teams++;
        }

    fclose(fp); // close the input file
    return 0;
}
