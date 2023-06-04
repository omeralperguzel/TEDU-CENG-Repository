#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ATTENDEES 500

struct Attendee {
    char name[50];
    char surname[50];
    float duration;
};

int compareAttendees(const void* a, const void* b) {
    struct Attendee* attendeeA = (struct Attendee*)a;
    struct Attendee* attendeeB = (struct Attendee*)b;
    int surnameCompare = strcmp(attendeeA->surname, attendeeB->surname);
    if (surnameCompare != 0) {
        return surnameCompare;
    }
    return strcmp(attendeeA->name, attendeeB->name);
}

int main() {
    char inputFileName[100];
    printf("Enter the input file name: ");
    scanf("%s", inputFileName);

    char sortedAnswer[4];
    printf("Sort the results? (yes/no): ");
    scanf("%s", sortedAnswer);

    FILE* inputFile = fopen(inputFileName, "r");
    if (inputFile == NULL) {
        printf("Error opening input file.\n");
        return 1;
    }

    struct Attendee attendees[MAX_ATTENDEES];
    int numAttendees = 0;

    char line[200];
    fgets(line, sizeof(line), inputFile); // skip header line
    while (fgets(line, sizeof(line), inputFile)) {
        char* joinTime = strtok(line, ",");
        char* leaveTime = strtok(NULL, ",");
        char* durationStr = strtok(NULL, ",");
        char* name = strtok(NULL, ",");
        char* email = strtok(NULL, ",");

        // parse name and surname
        char* surname = strrchr(name, ' ') + 1;
        int surnameLength = strlen(surname);
        int nameLength = strlen(name) - surnameLength - 1;
        char* attendeeName = (char*)malloc((nameLength + 1) * sizeof(char));
        strncpy(attendeeName, name, nameLength);
        attendeeName[nameLength] = '\0';

        // parse duration
        float duration = atof(durationStr);

        // check if attendee already exists
        int attendeeIndex = -1;
        for (int i = 0; i < numAttendees; i++) {
            if (strcasecmp(attendees[i].name, attendeeName) == 0 &&
                strcasecmp(attendees[i].surname, surname) == 0) {
                attendeeIndex = i;
                break;
            }
        }

        // update duration if attendee already exists, otherwise add new attendee
        if (attendeeIndex != -1) {
            attendees[attendeeIndex].duration += duration;
        } else {
            struct Attendee attendee;
            strcpy(attendee.name, attendeeName);
            strcpy(attendee.surname, surname);
            attendee.duration = duration;
            attendees[numAttendees] = attendee;
            numAttendees++;
        }

        free(attendeeName);
    }

    fclose(inputFile);

    // sort attendees if requested
    if (strcasecmp(sortedAnswer, "yes") == 0) {
        qsort(attendees, numAttendees, sizeof(struct Attendee), compareAttendees);
    }

    // write results to output file
    FILE* outputFile = fopen("output.txt", "w");
    if (outputFile == NULL) {
        printf("Error opening output file.\n");
        return 1;
    }

    for (int i = 0; i < numAttendees; i++) {
        fprintf(outputFile, "%s %s %.2f\n", attendees[i].surname, attendees[i].name, attendees[i].duration);
    }

    fclose(outputFile);

    return 0;
}