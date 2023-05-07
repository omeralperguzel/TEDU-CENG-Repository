#include <stdio.h>
#include <stdlib.h>

struct rating {
    int user_id;
    int item_id;
    int rating_value;
};

struct node {
    struct rating *rating_ptr;
    struct node *next;
};

void insert_rating(struct node **head_ptr, int user_id, int item_id, int rating_value) {
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
    int total_rating_value = 0;
    struct node *curr_node = head;
    while (curr_node != NULL) {
        if (curr_node->rating_ptr->item_id == item_id) {
            num_ratings++;
            total_rating_value += curr_node->rating_ptr->rating_value;
        }
        curr_node = curr_node->next;
    }
    if (num_ratings > 0) {
        return (double) total_rating_value / num_ratings;
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
    
    // insert some example ratings
    insert_rating(&head, 1, 1, 3);
    insert_rating(&head, 1, 2, 4);
    insert_rating(&head, 2, 1, 2);
    insert_rating(&head, 2, 2, 5);
    
    // find a rating
    struct rating *found_rating = find_rating(head, 1, 2);
    if (found_rating != NULL) {
        printf("User 1 gave item 2 a rating of %d\n", found_rating->rating_value);
    } else {
        printf("Rating not found\n");
    }

    // calculate average rating for an item
    double avg_rating = calculate_average_rating(head, 1);
    printf("Average rating for item 1 is %f\n", avg_rating);

    // print highest rated items
    print_highest_rated_items(head);

    return 0;
}
