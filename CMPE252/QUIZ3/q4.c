#include <stdio.h>
#include <string.h>

int main()
{
    int a[5] = {1, 2, 3, 4, 5};
    int b[7] = {1, 2, 3, 4, 5};
    const int *p1 = a;
    int * const p2 = a;
    const int * const p3 = a;
    int m = 24;
    return 0;
}