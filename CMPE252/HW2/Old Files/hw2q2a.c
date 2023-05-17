#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <float.h>
#define SIZE 250
float myAtof(char *string, char *error);

int main() {
    char input[251];
    char* token;
    char* error;
    float result = 0.0;
    float operand1 = 0.0;
    float operand2 = 0.0;
    char operator;

    // Get input from user
    printf("Enter an arithmetic operation (+, -, *, /): ");
    //gets(input);
    scanf("%s", input);

    // Parse input
    token = strtok(input, " ");
    operand1 = myAtof(token, error);
    if (*error) {
        printf("Error has been occurred due to inappropriate input!\n");
        return 0;
    }
    token = strtok(NULL, " ");
    operator = token[0];
    token = strtok(NULL, " ");
    operand2 = myAtof(token, error);
    if (*error) {
        printf("Error has been occurred due to inappropriate input!\n");
        return 0;
    }

    // Perform operation
    switch (operator) {
        case '+':
            result = operand1 + operand2;
            break;
        case '-':
            result = operand1 - operand2;
            break;
        case '*':
            result = operand1 * operand2;
            break;
        case '/':
            if (operand2 == 0.0) {
                printf("Cannot divided into 0.\n");
                return 0;
            }
            result = operand1 / operand2;
            break;
        default:
            printf("Invalid operator type!\n");
            return 0;
    }

    // Print result
    printf("%.2f %c %.2f = %.2f\n", operand1, operator, operand2, result);

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