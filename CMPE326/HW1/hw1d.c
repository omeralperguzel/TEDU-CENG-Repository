#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct rating {
    int user_id;
    int item_id;
    double rating_value;
    struct rating *next;
} Rating;

Rating *add_rating(Rating *head, int user_id, int item_id, double rating_value) {
    Rating *current = head;
    while (current != NULL) {
        if (current->user_id == user_id && current->item_id == item_id) {
            current->rating_value = rating_value;
            printf("Customer rating (%d, %d) is updated\n", user_id, item_id);
            return head;
        }
        current = current->next;
    }
    Rating *new_rating = malloc(sizeof(Rating));
    new_rating->user_id = user_id;
    new_rating->item_id = item_id;
    new_rating->rating_value = rating_value;
    new_rating->next = head;
    printf("Customer rating (%d, %d) is added successful\n", user_id, item_id);
    return new_rating;
}

double get_rating_by_user_and_item(Rating *head, int user_id, int item_id) {
    Rating *current = head;
    while (current != NULL) {
        if (current->user_id == user_id && current->item_id == item_id) {
            return current->rating_value;
        }
        current = current->next;
    }
    return 0.0;
}

double get_average_rating_by_item(Rating *head, int item_id) {
    Rating *current = head;
    double sum = 0.0;
    int count = 0;
    while (current != NULL) {
        if (current->item_id == item_id) {
            sum += current->rating_value;
            count++;
        }
        current = current->next;
    }
    if (count == 0) {
        return 0.0;
    } else {
        return sum / count;
    }
}

void print_highest_rated_items(Rating *head) {
    double max_rating = 0.0;
    int item_ids[1000] = {0};
    int num_items = 0;
    Rating *current = head;
    while (current != NULL) {
        if (current->rating_value > max_rating) {
            max_rating = current->rating_value;
            item_ids[0] = current->item_id;
            num_items = 1;
        } else if (current->rating_value == max_rating) {
            item_ids[num_items] = current->item_id;
            num_items++;
        }
        current = current->next;
    }
    printf("Highest rated items:");
    for (int i = 0; i < num_items; i++) {
        printf(" %d", item_ids[i]);
    }
    printf("\n");
}

int main() {
    FILE *input_file = fopen("input.txt", "r");
    Rating *head = NULL;
    char query[10];
    int user_id, item_id;
    double rating_value;

    while (fscanf(input_file, "%s", query) != EOF) {
        if (strcmp(query, "INSERT") == 0) {
            fscanf(input_file, "%d %d %lf", &user_id, &item_id, &rating_value);
            head = add_rating(head, user_id, item_id, rating_value);
        } 
        else if (strcmp(query, "RATING") == 0){
            fscanf(input_file, "%d %d", &user_id, &item_id);
            double rating = get_rating_by_user_and_item(head, user_id, item_id);
            printf("Customer rating (%d, %d) is: %.1lf\n", user_id, item_id, rating);
        } 
        else if (strcmp(query, "AVERAGE") == 0) {
            fscanf(input_file, "%d", &item_id);
            double average = get_average_rating_by_item(head, item_id);
            printf("Average rating (%d) is: %.1lf\n", item_id, average);
        } 
        else if (strcmp(query, "HIGHEST") == 0) {
            print_highest_rated_items(head);
        } 
        else if (strcmp(query, "break") == 0) {
            break;
        }
    }

    fclose(input_file);
    return 0;
}
