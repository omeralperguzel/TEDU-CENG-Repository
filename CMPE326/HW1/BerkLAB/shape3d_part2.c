#include <stdio.h>
#include <math.h>
#include <string.h>

#define MAX_SHAPES 50

typedef struct {
    double x;
    double y;
    double z;
} point_t;

typedef struct {
    point_t center;
    double radius;
} sphere_t;

typedef struct {
    point_t center;
    double side;
} cube_t;

typedef struct {
    point_t center;
    double base_side_length;
    double height;
} square_prism_t;

typedef union {
    cube_t cube;
    square_prism_t square_prism;
    sphere_t sphere;
} shape3d_data_t;

typedef enum {
    CUBE,
    SQUARE_PRISM,
    SPHERE
} class_t;

typedef struct {
    class_t type;
    shape3d_data_t shape;
} shape_t;

int scanShape(FILE *filep, shape_t *objp);
int loadShapes(shape_t shapes[]);
void printShape(const shape_t *objp);
int isLineFit(double lengthOfLine, const shape_t *objp);

int main()
{
    shape_t shapes[MAX_SHAPES];
    int numOfShapes = loadShapes(shapes);

    printf("\nShapes 3D:\n");
    for (int i = 0; i < numOfShapes; i++)
        printShape(&shapes[i]);

    double lengthOfLine;
    printf("Enter length of the line segment: ");
    scanf("%lf", &lengthOfLine);

    printf("The given line with length %.2lf can fit the following shapes:\n", lengthOfLine);
    for (int i = 0; i < numOfShapes; i++) {
        if (isLineFit(lengthOfLine, &shapes[i]))
            printShape(&shapes[i]);
    }

    return 0;
}

int scanShape(FILE *filep, shape_t *objp)
{
    char shapeType[20];
    if (fscanf(filep, "%s", shapeType) != 1)
        return 0;

    if (strcmp(shapeType, "cube") == 0) {
        objp->type = CUBE;
        if (fscanf(filep, "%lf %lf %lf %lf",
                   &objp->shape.cube.center.x,
                   &objp->shape.cube.center.y,
                   &objp->shape.cube.center.z,
                   &objp->shape.cube.side) != 4)
            return 0;
    } else if (strcmp(shapeType, "square_prism") == 0) {
        objp->type = SQUARE_PRISM;
        if (fscanf(filep, "%lf %lf %lf %lf %lf",
                   &objp->shape.square_prism.center.x,
                   &objp->shape.square_prism.center.y,
                   &objp->shape.square_prism.center.z,
                   &objp->shape.square_prism.base_side_length,
                   &objp->shape.square_prism.height) != 5)
            return 0;
    } else if (strcmp(shapeType, "sphere") == 0) {
        objp->type = SPHERE;
        if (fscanf(filep, "%lf %lf %lf %lf",
                   &objp->shape.sphere.center.x,
                   &objp->shape.sphere.center.y,
                   &objp->shape.sphere.center.z,
                   &objp->shape.sphere.radius) != 4)
            return 0;
    } else {
        return 0;  // Invalid shape type
    }

    return 1;
}

int loadShapes(shape_t shapes[])
{
    char fileName[100];
    printf("Enter the file name to read: ");
    scanf("%s", fileName);

    FILE *filep = fopen(fileName, "r");
    if (filep == NULL) {
        printf("Unable to open %s\n", fileName);
        return 0;
    }

    printf("Opening %s\n", fileName);

    int numOfShapes = 0;
    while (numOfShapes < MAX_SHAPES && scanShape(filep, &shapes[numOfShapes]))
        numOfShapes++;

    printf("Loading complete\n");
    fclose(filep);
    printf("Closing %s\n", fileName);

    return numOfShapes;
}

void printShape(const shape_t *objp)
{
    switch (objp->type) {
        case CUBE:
            printf("Cube: <%.2lf %.2lf %.2lf> <%.2lf> <%.2lf> <%.2lf>\n",
                   objp->shape.cube.center.x,
                   objp->shape.cube.center.y,
                   objp->shape.cube.center.z,
                   objp->shape.cube.side,
                   6 * pow(objp->shape.cube.side, 2),
                   pow(objp->shape.cube.side, 3));
            break;
        case SQUARE_PRISM:
            printf("Square_prism: <%.2lf %.2lf %.2lf> <%.2lf %.2lf> <%.2lf> <%.2lf>\n",
                   objp->shape.square_prism.center.x,
                   objp->shape.square_prism.center.y,
                   objp->shape.square_prism.center.z,
                   objp->shape.square_prism.base_side_length,
                   objp->shape.square_prism.height,
                   4 * objp->shape.square_prism.base_side_length * objp->shape.square_prism.height,
                   pow(objp->shape.square_prism.base_side_length, 2) * objp->shape.square_prism.height);
            break;
        case SPHERE:
            printf("Sphere: <%.2lf %.2lf %.2lf> <%.2lf> <%.2lf> <%.2lf>\n",
                   objp->shape.sphere.center.x,
                   objp->shape.sphere.center.y,
                   objp->shape.sphere.center.z,
                   objp->shape.sphere.radius,
                   4 * M_PI * pow(objp->shape.sphere.radius, 2),
                   (4.0 / 3.0) * M_PI * pow(objp->shape.sphere.radius, 3));
            break;
        default:
            printf("Invalid shape\n");
    }
}

int isLineFit(double lengthOfLine, const shape_t *objp)
{
    switch (objp->type) {
        case CUBE:
            if (lengthOfLine <= sqrt(3) * objp->shape.cube.side)
                return 1;
            break;
        case SQUARE_PRISM:
            if (lengthOfLine <= sqrt(2) * sqrt(pow(objp->shape.square_prism.base_side_length, 2) + pow(objp->shape.square_prism.height, 2)))
                return 1;
            break;
        case SPHERE:
            if (lengthOfLine <= 2 * objp->shape.sphere.radius)
                return 1;
            break;
    }
    return 0;
}
