#include <stdio.h>
#include <math.h>

/*double f(double x) {
    return pow(x, 2) * sin(x); // function g(x) = x^2 * sin(x)
}

double h(double x) {
    return sqrt(4 - pow(x, 2)); // function h(x) = sqrt(4 - x^2)
}

double trap(double a, double b, int n, double (*f)(double)) {
    double h = (b - a) / n;
    double sum = 0.5 * (f(a) + f(b));
    for (int i = 1; i < n; i++) {
        double x = a + i * h;
        sum += f(x);
    }
    return h * sum;
}*/

int main() {
    float a, b, c, d, n; 
    double function;
    //int t;

    // Read input values
    printf("Enter value of a: ");
    scanf("%f", &a);
    printf("Enter value of b: ");
    scanf("%f", &b);
    printf("Enter value of n: ");
    scanf("%f", &n);
    printf("Enter value of c: ");
    scanf("%f", &c);
    printf("Enter value of d: ");
    scanf("%f", &d);
    
    double sumofareas = 0;
    //double T = trap(a, b, n, f);
    int h = (b - a) / n; 

    for (int x = a; x <= b; x++) {
        if ((((int)(x-a) % h) == 0) && (x != a) && (x != b)){
          function = pow(x,c) + 2 * d;
          //printf("f(x): %.2f\n", function);
          sumofareas += function;
          //printf("It really works!");
        }
    }

    double T = ((b - a) / (2 * n)) * ((pow(a,c)+2*d) + (pow(b,c)+2*d) + 2 * sumofareas); // finding the area of the trapezoid
    printf("T: %.2f\n", T);

    return 0;
}