// Declare the required header file

#include<stdio.h>
#include<math.h>

// Define the Pi

#define PI 3.14159

// Create a g(x) fucntion

double gx(double x) {
  // return g(x) = x^2sin(x)
  return pow(x, 2) * sin(x);

}

// Create h(x) function

double hx(double x) {
  // return h(x)= sqrt(4-x^2)
  return sqrt(4 - pow(x, 2));
}

// Write a function trap with input parameters

// a, b, n, and f

double trap(int n, double a, double b, char F){
  // Compute the interval
  double h = (b - a) / n;
  // Declare the required variables
  double trapsum = 0;

double trapaproximation;

double i;

// Check when F is equal to g

if(F=='g'){
  // for loop iterate the trapezoidal
  for (i = a + h; i <= b - h; i += h)
    // call the fucntion g(x)
    trapsum += gx(i);
  // copute the area area_approximation
  trapaproximation = (h / 2) * (gx(a) + gx(b) + 2 * trapsum);
  // return the area_approximation
  return trapaproximation;

}

// otherwise

else {
  // for loop iterate the trapezoidal
  for (i = a + h; i <= b - h; i += h)
    // call the function h(x)
    trapsum += hx(i);
  // // compute the area area_approximation
  trapaproximation = (h / 2) * (hx(a) + hx(b) + 2 * trapsum);
  // return the area_approximation
  return trapaproximation;
}

}

// Create a main function of the program

int main(int argc, char *argv[])

{

// Declare the required variables

int n;

// Display the g(x) result

printf("TRAPEZOIDAL RULE g(x) = x^2sin(x), a = 0, b = PI(3.14159)\n");

// for loop to display the result from 2 to 128

for (n = 2; n <= 128; n *= 2){
  // Display the trapezoidal result
  // for n of 2 to 128
  printf("for n = %d, area is approximately %.4f\n", n, trap(n, 0, PI, 'g'));
}

printf("\n");

// Display the result for h(x)
printf("TRAPEZOIDAL RULE h(x) = sqrt(4 - x^2), a = -2, b = 2\n");

for (n = 2; n <= 128; n *= 2){
  // Display the result from n of 2 t0 128
  printf("for n = %d, area is approximately %.4f\n", n, trap(n, -2, 2, 'h'));
}

  return 0;

}
