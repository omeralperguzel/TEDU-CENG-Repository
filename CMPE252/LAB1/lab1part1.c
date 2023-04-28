#include <stdio.h>
#include <math.h>

int takePow(int numArg, int dArg, int returnVal)
{
    float result = pow(numArg,dArg);
    return (int)result;
}

int main()
{
    int num;
    int d;
    int p;

    printf("Enter number> ");
    scanf("%d", &num);
    printf("Enter power> ");
    scanf("%d", &d);
    int ans = takePow(num, d,1);
    printf("%d power of: %d is %d",d, num,ans);
    return 0;
}
