#include <stdio.h>
#include <string.h>

int main()
{
    char name[25] = "CMPE252";
    char day[25] = "WEDNESDAY";
    strncat(name, day+6, 6);
    printf("%s", name);
    return 0;
}