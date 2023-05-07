#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct rating {
    int user_id;
    int item_id;
    double rating_value;
};

struct node {
    struct rating *rating_ptr;
    struct node *next;
};

void insert_rating(struct node **head_ptr, int user_id, int item_id, double rating_value) {
    struct rating *new_rating_ptr = malloc(sizeof(struct rating));
    new_rating_ptr->user_id = user_id;
    new_rating_ptr->item_id = item_id;
    new_rating_ptr->rating_value = rating_value;
    
    struct node *new_node_ptr = malloc(sizeof(struct node));
    new_node_ptr->rating_ptr = new_rating_ptr;
    new_node_ptr->next = *head_ptr;
    *head_ptr = new_node_ptr;
}

struct rating *find_rating(struct node *head, int user_id, int item_id) {
    struct node *curr_node = head;
    while (curr_node != NULL) {
        if (curr_node->rating_ptr->user_id == user_id && curr_node->rating_ptr->item_id == item_id) {
            return curr_node->rating_ptr;
        }
        curr_node = curr_node->next;
    }
    return NULL;
}

double calculate_average_rating(struct node *head, int item_id) {
    int num_ratings = 0;
    double total_rating_value = 0;
    struct node *curr_node = head;
    while (curr_node != NULL) {
        if (curr_node->rating_ptr->item_id == item_id) {
            num_ratings++;
            total_rating_value += curr_node->rating_ptr->rating_value;
        }
        curr_node = curr_node->next;
    }
    if (num_ratings > 0) {
        return total_rating_value / num_ratings;
    } else {
        return 0.0;
    }
}

void print_highest_rated_items(struct node *head) {
    int highest_rating_value = -1;
    struct node *curr_node = head;
    while (curr_node != NULL) {
        if (curr_node->rating_ptr->rating_value > highest_rating_value) {
            highest_rating_value = curr_node->rating_ptr->rating_value;
        }
        curr_node = curr_node->next;
    }
    curr_node = head;
    while (curr_node != NULL) {
        if (curr_node->rating_ptr->rating_value == highest_rating_value) {
            printf("Item %d has a rating of %d\n", curr_node->rating_ptr->item_id, highest_rating_value);
        }
        curr_node = curr_node->next;
    }
}

int main() {
    struct node *head = NULL;
    char input_line[50];
    
    while (1) {
        fgets(input_line, 50, stdin);
        if (strcmp(input_line, "break\n") == 0) {
            break;
        }
        char *command = strtok(input_line, " ");
        if (strcmp(command, "INSERT") == 0) {
            int user_id, item_id;
            double rating_value;
            sscanf(strtok(NULL, " "), "%d", &user_id);
            sscanf(strtok(NULL, " "), "%d", &item_id);
            sscanf(strtok(NULL, " "), "%lf", &rating_value);
            insert_rating(&head, user_id, item_id, rating_value);
        } else if (strcmp(command, "RATING") == 0) {
            int user_id, item_id;
            sscanf(strtok(NULL, " "), "%d", &user_id);
            sscanf(strtok(NULL, " "), "%d", &item_id);
            struct rating *rating_ptr = find_rating(head, user_id, item_id);
            if (rating_ptr == NULL) {
                printf("Rating not found.\n");
            } else {
                printf("Rating of item %d by user %d: %.1f\n", item_id, user_id, rating_ptr->rating_value);
            }
        } else if (strcmp(command, "AVERAGE") == 0) {
            int item_id;
            sscanf(strtok(NULL, " "), "%d", &item_id);
            double average_rating = calculate_average_rating(head, item_id);
            if (average_rating == 0.0) {
                printf("Item not rated.\n");
            } else {
                printf("Average rating of item %d: %.1f\n", item_id, average_rating);
            }
        }
    }
    
    printf("Highest rated items:\n");
    print_highest_rated_items(head);
    
    return 0;
}
