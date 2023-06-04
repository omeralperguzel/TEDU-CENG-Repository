#include <stdio.h>
#include <string.h>

typedef struct {
    int id;
    int age;
} student;

void print_student_info(student *ptr){
    printf("%d", ptr->id);
}

int main()
{
    student s1 = {123, 20};
    print_student_info(&s1);
    return 0;
}