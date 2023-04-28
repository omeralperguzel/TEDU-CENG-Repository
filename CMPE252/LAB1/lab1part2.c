#include <stdio.h>
#include <math.h>

void isInCircle(/*double *distance, */int *result, double *centerX, double *centerY) {
    double x, y, x_circle, y_circle, radius, sumX = 0, sumY = 0;
    int count = 0;
    scanf("%lf %lf %lf", &x, &y, &radius); // Reads circle coordinates and radius
    x_circle = x;
    y_circle = y;
    while (scanf("%lf %lf", &x, &y) != EOF) { // Read point coordinates until EOF
        sumX += x;
        sumY += y;
        count++;
    }
    *centerX = sumX / count; // Calculate center coordinate
    *centerY = sumY / count;
    /***/double distance = pow(*centerX - x_circle, 2) + pow(*centerY - y_circle, 2); // Calculate the distance of a circle 
    if (/***/distance < pow(radius, 2)) { // Check if point is inside of the circle or not
        *result = 1;
    } 
    else if (/***/distance == pow(radius, 2)) { // Check if point is on circle or not
        *result = 0;
    } 
    else { // Check if point is outside of the circle or not
        *result = -1;
    }
}

int main()
{
    int result;
    double distance, centerX, centerY;

    isInCircle(/* &distance, */&result, &centerX, &centerY);

    /*printf("%.2f", distance);*/
    if (result == 1)
        printf("center of points is inside circle, centerX: %.1f , centerY: %.1f",centerX,centerY );
    else if (result == 0)
        printf("center of points is on circle, centerX: %.1f , centerY: %.1f",centerX,centerY );
    else
        printf("center of points is outside circle, centerX: %.1f , centerY: %.1f",centerX,centerY);
    return 0;
}
