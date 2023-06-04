#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ATTENDEES 500

struct Attendee {
    char name[50];
    char surname[50];
    float duration;
};

// This is the function that will be used by qsort to compare two attendees
int compareAttendees(const void* a, const void* b) {
    struct Attendee* attendeeA = (struct Attendee*)a;
    struct Attendee* attendeeB = (struct Attendee*)b;
    int surnameCompare = strcmp(attendeeA->surname, attendeeB->surname);
    if (surnameCompare != 0) {
        return surnameCompare;
    }
    return strcmp(attendeeA->name, attendeeB->name);
}


// This is the main function
int main() {
    char inputFileName[100];
    printf("Enter the input file name: ");
    scanf("%s", inputFileName);

    // This is the question that asks the user if the results should be sorted or not
    char sortedAnswer[4];
    printf("Sort the results? (yes/no): ");
    scanf("%s", sortedAnswer);

    // This is the file pointer that will be used to read the input file
    FILE* inputFile = fopen(inputFileName, "r");
    if (inputFile == NULL) {
        printf("Error: Could not open input file.\n");
        return 1;
    }

    // This is the array that will be used to store the attendees
    struct Attendee attendees[MAX_ATTENDEES];
    int numAttendees = 0;

    // This is the loop that reads the input file line by line
    char line[200];
    fgets(line, sizeof(line), inputFile); // skip header line
    while (fgets(line, sizeof(line), inputFile) != NULL) {
        char* joinTime = strtok(line, ",");
        char* leaveTime = strtok(NULL, ",");
        char* durationStr = strtok(NULL, ",");
        char* name = strtok(NULL, ",");
        char* email = strtok(NULL, ",");

        // Parse name and surname
        char* surname = strrchr(name, ' ');
        if (surname == NULL) {
            printf("Error: Invalid input file format.\n");
            return 1;
        }
        *surname = '\0';
        surname++;
        while (*surname == ' ') {
            surname++;
        }
        char* firstName = name;
        while (*firstName == ' ') {
            firstName++;
        }

        // Parse duration
        float duration = atof(durationStr);

        // Find attendee in array
        int attendeeIndex = -1;
        for (int i = 0; i < numAttendees; i++) {
            if (strcasecmp(attendees[i].name, firstName) == 0 && strcasecmp(attendees[i].surname, surname) == 0) {
                attendeeIndex = i;
                break;
            }
        }

        // Update or add attendee
        if (attendeeIndex == -1) {
            struct Attendee attendee = { .duration = duration };
            strcpy(attendee.name, firstName);
            strcpy(attendee.surname, surname);
            attendees[numAttendees] = attendee;
            numAttendees++;
        } else {
            attendees[attendeeIndex].duration += duration;
        }
    }

    fclose(inputFile);

    // Sort attendees if necessary
    if (strcasecmp(sortedAnswer, "yes") == 0) {
        qsort(attendees, numAttendees, sizeof(struct Attendee), compareAttendees);
    }

    for (int i = 0; i < numAttendees; i++) {
        printf("%s %s %.2f\n", attendees[i].surname, attendees[i].name, attendees[i].duration);
    }

    return 0;
}