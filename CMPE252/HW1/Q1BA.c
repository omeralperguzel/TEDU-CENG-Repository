#include <stdio.h>
#include <string.h>




int main() {

    printf("Enter txt file name:");
    char file_name[100];
    scanf ("%s", file_name);

    FILE *input_file = fopen(file_name, "r");

    if (input_file == NULL) {
        printf("There are 0 teams in total.");
        return 0;
    }

    char line[101];
    int num, count = 0;
    char str[101];

    while (fgets(line, sizeof(line), input_file) != NULL) {

        sscanf(line, "%d %s", &num, str);
        count++;
    }
    printf("There are %d teams in total.", count);
    fclose(input_file);


    input_file = fopen(file_name, "r");

    if (input_file == NULL) {
        return 1;
    }

    while (fgets(line, sizeof(line), input_file) != NULL) {

        sscanf(line, "%d %s", &num, str);
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
        printf("Team %d: %d\n", num, points);
    }

    fclose(input_file);
    return 0;
}