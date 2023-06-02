#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
    unsigned int id; // gamer id
    char name[20]; // gamer name
	char surname[20]; // gamer surname
    char genre[20]; // genre of gamer
    int followerCount; // follower Count of the gamer
} gamer;

void showRecords(FILE *filePtr);

int modifyFollowerCount(FILE *filePtr, unsigned int id, int multiplyFollower);
int insertGamer(FILE *filePtr, unsigned int id, char name[], char surname[], char genre[], int followerCount);
int removeGamer(FILE *filePtr, unsigned int id);
int viewGenreFollows(FILE *filePtr, char genre[], int exactFollower);

int main()
{
    unsigned int id;
    int multiplyFollower;
    int status;

    char name[20];
    char surname[20];
	char genre[20];
    int followerCount;

    int count;
	int exactFollower;

// It opens the file for reading and writing.
    FILE *filePtr;
    filePtr = fopen("gamer.bin","rb+");
    if (filePtr == NULL)
    {
        printf("Could not open gamer.bin");
        return 0;
    }

    showRecords(filePtr);

    int choice;

// It asks the user to choose an operation.
    printf("\nWhich operation do you choose?\n");
    printf("1 : Update Follower Count\n");
    printf("2 : Add Gamer\n");
    printf("3 : Delete Gamer\n");
    printf("4 : View Genre Info with Follower\n");
    printf("> ");
    scanf("%d",&choice);

    switch (choice)
    {
    // In case 1 it asks the user to enter the gamer id and the value to multiply the follower count. Then it multiplies the follower count of the gamer with the given id.
    case 1:
        printf("\nGamer id: ");
        scanf("%d",&id);
        printf("Multiplication value for follower count: ");
        scanf("%d",&multiplyFollower);
        status = modifyFollowerCount(filePtr, id, multiplyFollower);
        if (status == 1)
            showRecords(filePtr);
        else
            printf("No gamer with id %d\n", id);
        break;
    // In case 2 it asks the user to enter the gamer id, name, surname, genre and follower count. Then it adds the gamer to the file.
    case 2:
        printf("\nGamer id: ");
        scanf("%d",&id);
        printf("Name: ");
        scanf("%s",name);
		printf("Surname: ");
        scanf("%s",surname);
        printf("Genre: ");
        scanf("%s",genre);
        printf("Follower Count: ");
        scanf("%d",&followerCount);
        status = insertGamer(filePtr, id, name, surname, genre, followerCount);
        if (status == 1)
            showRecords(filePtr);
        else
            printf("There is already a gamer with id %d\n", id);
        break;
    // In case 3 it asks the user to enter the gamer id then deletes the gamer with the given id.
    case 3:
        printf("\nGamer id: ");
        scanf("%d",&id);
        status = removeGamer(filePtr, id);
        if (status == 1)
            showRecords(filePtr);
        else
            printf("No gamer with id %d\n", id);
        break;
    // In case 4 it asks the user to enter the genre and the exact follower count. Then it prints the number of gamers in the given genre with the given follower count.
    case 4:
        printf("\nGenre: ");
        scanf("%s",genre);
		printf("\nExact follower count: ");
        scanf("%d",&exactFollower);
        count = viewGenreFollows(filePtr, genre, exactFollower);
        if (count == 0)
            printf("No gamer in genre %s with follower count == %d\n", genre, exactFollower);
        else
            printf("There are %d gamers in genre %s with follower count == %d\n", count, genre, exactFollower);
        break;
    }

    fclose(filePtr);
    return 0;
}

// It prints the records in the file. Then it sets the file pointer to the beginning of the file.
void showRecords(FILE *filePtr)
{
    fseek(filePtr, 0, SEEK_SET);

    printf("\n%-3s %-20s %-20s %-20s %s\n",
                   "ID",
                   "Name",
				   "Surname",
                   "Genre",
                   "Follower Count");

    while (!feof(filePtr))
    {
        gamer g;
        int result = fread(&g, sizeof(gamer), 1, filePtr);
        if (result != 0 && g.id != 0)
        {
            printf("%-3d %-20s %-20s %-20s %d\n",
                   g.id,
                   g.name,
				   g.surname,
                   g.genre,
                   g.followerCount);
        }
    }
}

// It multiplies the follower count of the gamer with the given id with the given value. Then it sets the file pointer to the beginning of the file.
int modifyFollowerCount(FILE *filePtr, unsigned int id, int multiplyFollower)
{
    fseek(filePtr, (id - 1) * sizeof(gamer), SEEK_SET);
    gamer g;
    int result = fread(&g, sizeof(gamer), 1, filePtr);
    if (result != 0 && g.id == id)
    {
        g.followerCount *= multiplyFollower;
        fseek(filePtr, -sizeof(gamer), SEEK_CUR);
        fwrite(&g, sizeof(gamer), 1, filePtr);
        return 1;
    }
    else
    {
        return 0;
    }
}

// It adds the gamer with the given id, name, surname, genre and follower count to the file. Then it sets the file pointer to the beginning of the file.
int insertGamer(FILE *filePtr, unsigned int id, char name[], char surname[], char genre[], int followerCount)
{
    fseek(filePtr, (id - 1) * sizeof(gamer), SEEK_SET);
    gamer g;
    int result = fread(&g, sizeof(gamer), 1, filePtr);
    if (result != 0 && g.id == id)
    {
        return 0;
    }
    else
    {
        strcpy(g.name, name);
        strcpy(g.surname, surname);
        strcpy(g.genre, genre);
        g.followerCount = followerCount;
        g.id = id;
        fseek(filePtr, (id - 1) * sizeof(gamer), SEEK_SET);
        fwrite(&g, sizeof(gamer), 1, filePtr);
        return 1;
    }
}

// It deletes the gamer with the given id from the file. Then it sets the file pointer to the beginning of the file.
int removeGamer(FILE *filePtr, unsigned int id)
{
    fseek(filePtr, (id - 1) * sizeof(gamer), SEEK_SET);
    gamer g;
    int result = fread(&g, sizeof(gamer), 1, filePtr);
    if (result != 0 && g.id == id)
    {
        g.id = 0;
        fseek(filePtr, -sizeof(gamer), SEEK_CUR);
        fwrite(&g, sizeof(gamer), 1, filePtr);
        return 1;
    }
    else
    {
        return 0;
    }
}

// It returns the number of gamers in the given genre with the given follower count. Then it sets the file pointer to the beginning of the file.
int viewGenreFollows(FILE *filePtr, char genre[], int exactFollower)
{
    fseek(filePtr, 0, SEEK_SET);
    int count = 0;
    while (!feof(filePtr))
    {
        gamer g;
        int result = fread(&g, sizeof(gamer), 1, filePtr);
        if (result != 0 && g.id != 0 && strcmp(g.genre, genre) == 0 && g.followerCount == exactFollower)
        {
            count++;
        }
    }
    return count;
}