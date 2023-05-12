#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define String_Length 20
#define Max_Element 10

void findUnion(char *Group_one[], char *Group_two[], int Number1, int Number2, char *Union_Sett[], int *Union_count_pointer)
{
    int Union_Count = 0;

    for (int i = 0; i < Number1; i++) {
        Union_Sett[Union_Count] = Group_one[i];
        Union_Count++;
    }
    
    for (int i = 0; i < Number2; i++) {
        int found = 0;
        for (int j = 0; j < Union_Count; j++) {
            if (strcmp(Group_two[i], Union_Sett[j]) == 0) {
                found = 1;
                break;
            }
        }
        if (!found) {
            Union_Sett[Union_Count] = Group_two[i];
            Union_Count++;
        }
    }

    *Union_count_pointer = Union_Count;
}

void Print_Car_Info(char *Union_Sett[], int Union_Count)
{
    char Temporary_String[String_Length];
    int age;
    for (int i = 0; i < Union_Count; i++) {
        strcpy(Temporary_String, Union_Sett[i]);
        char* token = strtok(Temporary_String, "_");
        printf("%s ", token);
        while (token != NULL) {
            token = strtok(NULL, "_");
            if (token != NULL) {
                printf("%s ", token);
            }
        }
        sscanf(token, "%d", &age);
        printf("%d\n", 2023 - age);
    }
}
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

    printf("Number of elements in car Group_one: ");
    scanf("%d", &Number1);
    printf("Entries in car Group_one: ");
    for (i = 0; i < Number1; i++)
    {
        scanf("%d", &ind);
        Group_one[i] = list[ind];
    }

    printf("\nNumber of elements in car Group_two: ");
    scanf("%d",&Number2);
    printf("Entries in car Group_two: ");
    for (i = 0; i < Number2; i++)
    {
        scanf("%d", &ind);
        Group_two[i] = list[ind];
    }

    char *Union_Sett[2*Max_Element];
    int Union_Count;

    findUnion(Group_one, Group_two, Number1, Number2, Union_Sett, &Union_Count);

    printf("\nGroup_one:\n");
    for (int i = 0; i < Number1; i++)
    {
        printf("%s\n",Group_one[i]);
    }

    printf("\nGroup_two:\n");
    for (int i = 0; i < Number2; i++)
    {
        printf("%s\n",Group_two[i]);
    }

    printf("\nunion of Group_one and Group_two:\n");
    for (int i = 0; i < Union_Count; i++)
    {
        printf("%s\n",Union_Sett[i]);
    }

    printf("\nDo you want to print the union set in NAME, max speed and age format (1/0)? ");
    int response;

    scanf("%d", &response);

    if (response == 1)
    {
        printf("\nunion of Group_one and Group_two in NAME, max speed and age format:\n");
        Print_Car_Info(Union_Sett, Union_Count);

        printf("\nunion of Group_one and Group_two:\n");
        for (int i = 0; i < Union_Count; i++)
        {
            printf("%s\n",Union_Sett[i]);
        }
    }

    return 0;
}
