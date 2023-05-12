#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define String_Length 20
#define Max_Element 10

// This function finds the union of two groups.
void findUnion(char *Group_one[], char *Group_two[], int Number1, int Number2, char *Union_Sett[], int *Union_count_pointer)
{
    int Union_Count = 0;

    // First, we add all the elements of the first group to the union set.
    for (int i = 0; i < Number1; i++) {
        Union_Sett[Union_Count] = Group_one[i];
        Union_Count++;
    }
    
    // Then, we add the elements of the second group to the union set if they are not already in the union set.
    for (int i = 0; i < Number2; i++) {
        int found = 0;
        for (int j = 0; j < Union_Count; j++) {
            if (strcmp(Group_two[i], Union_Sett[j]) == 0) {
                found = 1;
                break;
            }
        }
        // If the element is not in the union set, we add it to the union set.
        if (!found) {
            Union_Sett[Union_Count] = Group_two[i];
            Union_Count++;
        }
    }
    // We update the union count.
    *Union_count_pointer = Union_Count;
}

// This function prints the car information.
void Print_Car_Info(char *Union_Sett[], int Union_Count)
{
    const int New_String_Length = 50;
    // We create new strings to store the car name, max speed, year, and age.
    char Car_Name[New_String_Length], Max_Speed[New_String_Length], Year[New_String_Length], Age[New_String_Length];
    int age;
    // This loop is for each car in the union set.
    for (int i = 0; i < Union_Count; i++) {
        strcpy(Car_Name, Union_Sett[i]);
        char* token = strtok(Car_Name, "_");
        // We convert the car name to uppercase.
        while (token != NULL) {
            for (int j = 0; j < strlen(token); j++) {
                Car_Name[j] = toupper(token[j]);
            }
            token = strtok(NULL, "_");
            if (token != NULL) {
                strcpy(Max_Speed, token);
                token = strtok(NULL, "_");
                strcpy(Year, token);
                break;
            }
        }
        int Car_Age = 2023 - atoi(Year);
        // We print the car information.
        printf("%s %s %d\n", Car_Name, Max_Speed, Car_Age);
    }
}

// This is the main function.
int main()
{
    char list[Max_Element][String_Length] = {"", "citroen_250_2010", "honda_200_2005",
    "mercedes_300_2020", "nissan_190_2014", "peugeot_210_2000",
    "opel_210_2011", "volkswagen_200_2003", "suzuki_240_2022", "bmw_260_2021"};

    char *Group_one[Max_Element];
    int Number1;

    char *Group_two[Max_Element];
    int Number2;

    int i;
    int ind;

    printf("Number of elements in car group1: ");
    scanf("%d", &Number1);
    printf("Entries in car group1: ");
    for (i = 0; i < Number1; i++)
    {
        scanf("%d", &ind);
        Group_one[i] = list[ind];
    }

    printf("\nNumber of elements in car group2: ");
    scanf("%d",&Number2);
    printf("Entries in car group2: ");
    for (i = 0; i < Number2; i++)
    {
        scanf("%d", &ind);
        Group_two[i] = list[ind];
    }

    char *Union_Sett[2*Max_Element];
    int Union_Count;

    findUnion(Group_one, Group_two, Number1, Number2, Union_Sett, &Union_Count);

    printf("\ngroup1:\n");
    for (int i = 0; i < Number1; i++)
    {
        printf("%s\n",Group_one[i]);
    }

    printf("\ngroup2:\n");
    for (int i = 0; i < Number2; i++)
    {
        printf("%s\n",Group_two[i]);
    }

    printf("\nunion of group1 and group2:\n");
    for (int i = 0; i < Union_Count; i++)
    {
        printf("%s\n",Union_Sett[i]);
    }

    printf("\nDo you want to print the union set in NAME, max speed and age format (1/0)? ");
    int response;

    scanf("%d", &response);

    if (response == 1)
    {
        printf("\nunion of group1 and group2 in NAME, max speed and age format:\n");
        Print_Car_Info(Union_Sett, Union_Count);

        printf("\nunion of group1 and group2:\n");
        for (int i = 0; i < Union_Count; i++)
        {
            printf("%s\n",Union_Sett[i]);
        }
    }
    
    return 0;
}
