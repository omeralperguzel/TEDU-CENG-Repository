#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define SIZE 250
float myAtof(char *string, char *error);

int main()
{
    char string[SIZE];            // Array declaration.
    float fnum1;
    char errorState=0;

    printf("Enter a number:\n");
    gets(string);

    fnum1=myAtof(string,&errorState);

    if (errorState==0){
        printf("Your number is: %.2f \n", fnum1);
    }else if (errorState==1){
        printf("Error has been occurred due to inappropriate input!\n");
    }

    return 0;
}

float myAtof(char* string, char* error) {
    float result = 0.0;
    int decimal = 0;
    int sign = 1;
    int i = 0;

    // Check for negative sign
    if (string[0] == '-') {
        sign = -1;
        i++;
    }

    // Process digits before decimal point
    while (string[i] != '\0' && string[i] != '.') {
        if (string[i] >= '0' && string[i] <= '9') {
            result = result * 10.0 + (string[i] - '0');
        } else {
            *error = 1;
            return 0.0;
        }
        i++;
    }

    // Process digits after decimal point
    if (string[i] == '.') {
        decimal = 1;
        i++;
    }
    while (string[i] != '\0') {
        if (string[i] >= '0' && string[i] <= '9') {
            result = result * 10.0 + (string[i] - '0');
            decimal *= 10;
        } else {
            *error = 1;
            return 0.0;
        }
        i++;
    }

    // Calculate final result
    result = sign * result / decimal;

    return result;
}