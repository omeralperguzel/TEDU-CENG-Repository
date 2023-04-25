#include <stdio.h>
#include <math.h>

int main() {
    float a, b, c;
    float x, xn_prev, xn_next;
    int min_xn, max_xn;
    
    // Read input values
    printf("Enter value of a: ");
    scanf("%f", &a);
    printf("Enter value of b: ");
    scanf("%f", &b);
    printf("Enter value of c: ");
    scanf("%f", &c);
    printf("Enter minimum value of x_n: ");
    scanf("%d", &min_xn);
    printf("Enter maximum value of x_n: ");
    scanf("%d", &max_xn);
    
    // Initialize x_n_0 and x_n_1
    xn_prev = x = 10;
    //xn_next = a * x * x + b * x + c;
    xn_next = x * b + (-b + sqrt(b * b - 4 * a * c)) / (2 * a);

    // Print x values within range
    for (int n = 0; n <= max_xn; n++) {
        if (n >= min_xn) {
            printf("x_%d: %.2f\n", n, x);
        }
        x = xn_next;
        //xn_next = a * x * x + b * x + c;
        xn_next = x * b + (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
    }
    
    return 0;
}
