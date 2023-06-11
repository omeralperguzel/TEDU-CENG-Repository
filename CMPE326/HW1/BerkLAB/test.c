#include <stdio.h>
#include <math.h>

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

int main()
{
    shape_t shapes[MAX_SHAPES];
    int numOfShapes = loadShapes(shapes);

    printf("\nShapes 3D:\n");
    for (int i = 0; i < numOfShapes; i++)
        printShape(&shapes[i]);

    return 0;
}

int scanShape(FILE *filep, shape_t *objp) {
    char type[20];
    int read = fscanf(filep, "%s", type);

    if (read != 1)
        return 0;

    if (strcmp(type, "cube") == 0) {
        objp->type = CUBE;
        read = fscanf(filep, "%lf %lf %lf %lf", &objp->shape.cube.center.x, &objp->shape.cube.center.y, &objp->shape.cube.center.z, &objp->shape.cube.side);
        return (read == 4);
    } else if (strcmp(type, "square_prism") == 0) {
        objp->type = SQUARE_PRISM;
        read = fscanf(filep, "%lf %lf %lf %lf %lf", &objp->shape.square_prism.center.x, &objp->shape.square_prism.center.y, &objp->shape.square_prism.center.z, &objp->shape.square_prism.base_side_length, &objp->shape.square_prism.height);
        return (read == 5);
    } else if (strcmp(type, "sphere") == 0) {
        objp->type = SPHERE;
        read = fscanf(filep, "%lf %lf %lf %lf", &objp->shape.sphere.center.x, &objp->shape.sphere.center.y, &objp->shape.sphere.center.z, &objp->shape.sphere.radius);
        return (read == 4);
    } else {
        return 0; // Invalid shape type
    }
}

int loadShapes(shape_t shapes[]) {
    char filename[50];
    printf("Enter the file name to read: ");
    scanf("%s", filename);

    FILE *filep = fopen(filename, "r");
    if (filep == NULL) {
        printf("Failed to open %s\n", filename);
        return 0;
    }

    printf("Opening %s\n", filename);

    int count = 0;
    while (count < MAX_SHAPES && scanShape(filep, &shapes[count]))
        count++;

    printf("Loading complete\n");
    fclose(filep);
    printf("Closing %s\n", filename);

    return count;
}

void printShape(const shape_t *objp) {
    switch (objp->type) {
        case CUBE:
            printf("Cube: <%0.2lf %0.2lf %0.2lf> <%0.2lf> <%0.2lf> <%0.2lf>\n",
                   objp->shape.cube.center.x, objp->shape.cube.center.y, objp->shape.cube.center.z,
                   objp->shape.cube.side,
                   6 * pow(objp->shape.cube.side, 2),
                   pow(objp->shape.cube.side, 3));
            break;
        case SQUARE_PRISM:
            printf("Square_prism: <%0.2lf %0.2lf %0.2lf> <%0.2lf %0.2lf> <%0.2lf> <%0.2lf>\n",
                   objp->shape.square_prism.center.x, objp->shape.square_prism.center.y, objp->shape.square_prism.center.z,
                   objp->shape.square_prism.base_side_length, objp->shape.square_prism.height,
                   4 * objp->shape.square_prism.base_side_length * objp->shape.square_prism.height,
                   pow(objp->shape.square_prism.base_side_length, 2) * objp->shape.square_prism.height);
            break;
        case SPHERE:
            printf("Sphere: <%0.2lf %0.2lf %0.2lf> <%0.2lf> <%0.2lf> <%0.2lf>\n",
                   objp->shape.sphere.center.x, objp->shape.sphere.center.y, objp->shape.sphere.center.z,
                   objp->shape.sphere.radius,
                   4 * M_PI * pow(objp->shape.sphere.radius, 2),
                   (4.0 / 3.0) * M_PI * pow(objp->shape.sphere.radius, 3));
            break;
        default:
            printf("Invalid shape type\n");
    }
}
