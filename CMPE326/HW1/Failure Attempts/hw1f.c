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
    if (new_rating == NULL) {
        printf("Error: Failed to allocate memory for new rating\n");
        exit(EXIT_FAILURE);
    }
    new_rating->user_id = user_id;
    new_rating->item_id = item_id;
    new_rating->rating_value = rating_value;
    new_rating->next = head;
    printf("Customer rating (%d, %d) is added successfully\n", user_id, item_id);
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
    int *item_ids = NULL;
    int num_items = 0;
    Rating *current = head;
    while (current != NULL) {
        if (current->rating_value > max_rating) {
            max_rating = current->rating_value;
            item_ids = realloc(item_ids, sizeof(int));
            if (item_ids == NULL) {
                printf("Error: Failed to allocate memory for item_ids\n");
                exit(EXIT_FAILURE);
            }
            item_ids[0] = current->item_id;
            num_items = 1;
        } else if (current->rating_value == max_rating) {
            item_ids = realloc(item_ids, (num_items + 1) * sizeof(int));
            if (item_ids == NULL) {
                printf("Error: Failed to allocate memory for item_ids\n");
                exit(EXIT_FAILURE);
            }
            item_ids[num_items] = current->
