#include <stdio.h>
#include <stdlib.h>

// Define a struct to represent a rating node
typedef struct rating {
    int user_id;
    int item_id;
    float value;
    struct rating *next;
} RatingNode;

// Function to insert a new rating into the linked list
void insert_rating(RatingNode **head, int user_id, int item_id, float value) {
    RatingNode *node = *head;
    RatingNode *prev = NULL;
    
    // Look for a node with matching user and item IDs
    while (node != NULL && (node->user_id != user_id || node->item_id != item_id)) {
        prev = node;
        node = node->next;
    }
    
    // If a matching node is found, update the rating value
    if (node != NULL) {
        node->value = value;
        printf("Customer rating (%d, %d) is updated\n", user_id, item_id);
    }
    // Otherwise, create a new node and add it to the list
    else {
        node = (RatingNode *) malloc(sizeof(RatingNode));
        node->user_id = user_id;
        node->item_id = item_id;
        node->value = value;
        node->next = NULL;
        
        if (*head == NULL) {
            *head = node;
        } else {
            prev->next = node;
        }
        
        printf("Customer rating (%d, %d) is added successful\n", user_id, item_id);
    }
}

// Function to remove a rating from the linked list
void remove_rating(RatingNode **head, int user_id, int item_id) {
    RatingNode *node = *head;
    RatingNode *prev = NULL;
    
    // Look for a node with matching user and item IDs
    while (node != NULL && (node->user_id != user_id || node->item_id != item_id)) {
        prev = node;
        node = node->next;
    }
    
    // If a matching node is found, remove it from the list
    if (node != NULL) {
        if (prev == NULL) {
            *head = node->next;
        } else {
            prev->next = node->next;
        }
        
        free(node);
        printf("Customer rating (%d, %d) is removed successful\n", user_id, item_id);
    } else {
        printf("Customer rating (%d, %d) does not exit\n", user_id, item_id);
    }
}

// Function to get the rating value for a specific user and item
float get_rating(RatingNode *head, int user_id, int item_id) {
    RatingNode *node = head;
    
    // Look for a node with matching user and item IDs
    while (node != NULL && (node->user_id != user_id || node->item_id != item_id)) {
        node = node->next;
    }
    
    // If a matching node is found, return the rating value
    if (node != NULL) {
        return node->value;
    } else {
        return 0.0;
    }
}

// Function to compute the average rating for a specific item
float compute_average_rating(RatingNode *head, int item_id) {
    RatingNode *node = head;
    int count = 0;
    float total = 0.0;
    
    // Traverse the list and add up the rating values for the given item
    while (node != NULL) {
        if (node->item_id == item_id) {
            total += node->value;
            count++;
        }
        node = node->next;
    }
    if (count > 0) {
        return total / count;
    } else {
        return 0.0;
    }
}

// Function to compute the average rating for a specific user
/*float compute_user_average_rating(RatingNode *head, int user_id) {
    RatingNode *node = head;
    int count = 0;
    float total = 0.0;
    
    // Traverse the list and add up the rating values for the given user
    while (node != NULL) {
        if (node->user_id == user_id) {
            total += node->value;
            count++;
        }
        node = node->next;
    }
    if (count > 0) {
        return total / count;
    } else {
        return 0.0;
    }
}*/

int main() {
    RatingNode* head = NULL;
    char command[10];
    int user_id, item_id;
    float rating;
    while (1) {
        scanf("%s", command);
        if (command[0] == 'I') {
            scanf("%d %d %f", &user_id, &item_id, &rating);
            insert(&head, user_id, item_id, rating);
        } else if (command[0] == 'R') {
            scanf("%d %d", &user_id, &item_id);
            remove_rating(&head, user_id, item_id);
        } else if (command[0] == 'A') {
            scanf("%d", &item_id);
            printf("%.2f\n", compute_average_rating(head, item_id));
        } else if (command[0] == 'U') {
            scanf("%d", &user_id);
            printf("%.2f\n", compute_user_average_rating(head, user_id));
        } else if (command[0] == 'Q') {
            break;
        }
    }
    return 0;
}



        
