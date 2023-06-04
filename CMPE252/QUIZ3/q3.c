#include <stdio.h>
#include <string.h>

int g(){
    int x = 0;
    x++;
    printf("%d ", x);
}

int f(){
    static int x;
    x++;
    printf("%d ", x);
}

int main()
{
    g();
    f();
    g();
    f();
    return 0;
}