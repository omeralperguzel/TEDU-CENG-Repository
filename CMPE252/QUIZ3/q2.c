#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *p = (int *) malloc(3*sizeof(int));
    
    if (p == NULL){
        return 1;
    }
    
    *p = 10;
    *(p+1) = 20;
    *(p+2) = 30;
    
    p = (int*) realloc(p, 2*sizeof(int));
    
    for(int i = 0; i < 3; i++) printf("%d ", p[i]);
    
    free(p);
    
    return 0;
}