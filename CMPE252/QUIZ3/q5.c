#include <stdio.h>
#include <string.h>

int main()
{
    enum weekday {monday, tuesday, wednesday, thursday, friday};
    enum weekday d1 = wednesday;
    d1 = d1 + 2;
    printf("%d", d1);
    return 0;
}