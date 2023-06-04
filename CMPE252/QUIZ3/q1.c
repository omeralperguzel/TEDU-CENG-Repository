/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>
#include <string.h>

#include <stdio.h>
#include <string.h>

int main()
{
    char months[4][10] = {"february", "april", "june", "september"};
    char *arr[4];
    int i;
    char *temp;
    for (i = 0; i < 4; i++) arr[i] = months[i];
    if (strcmp(arr[0],arr[1]) > 0){
        temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }

    if (strcmp(arr[2],arr[3]) > 0){
        temp = arr[2];
        arr[2] = arr[3];
        arr[3] = temp;
    }

    for (i = 0; i < 4; i++) printf("%s ", arr[i]);

    return 0;
}