#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
        printf("Customer rating (%d, %d) does not exist\n", user_id, item_id);
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
    float average = 0.0;
    
    // Traverse the list and add up the rating values for the given item
    while (node != NULL) {
        if (node->item_id == item_id) {
            count++;
            total += node->value;
        }
        node = node->next;
    }
    if (count > 0) {
        average = total / count;
        return average;
    } else {
        return 0.0;
    }
}   

// Function to free the memory used by the linked list
void clearList(RatingNode **head) {
    RatingNode *node = *head;
    RatingNode *next;
    
    while (node != NULL) {
        next = node->next;
        free(node);
        node = next;
    }
    
    *head = NULL;
}


int main() {
    RatingNode *head = NULL;
    char command[10];
    int userID, itemID;
    float rating;

    while ((scanf("%s %d %d", command, &userID, &itemID) == 3) || (scanf("%s %d", command, &itemID) == 2)) {
        if (strcmp(command, "INSERT") == 0) {
            scanf("%f", &rating);
            insert_rating(&head, userID, itemID, rating);
        } 
        else if (strcmp(command, "REMOVE") == 0) {
            remove_rating(&head, userID, itemID);
        } 
        else if (strcmp(command, "RATING") == 0) {
            printf("Customer rating (%d, %d) is: %.1f\n", userID, itemID, get_rating(head, userID, itemID));
        } 
        else if (strcmp(command, "AVERAGE") == 0) {
            printf("Average rating (%d) is: %.1f\n", itemID, compute_average_rating(head, itemID));
        }
    }

    /*while (scanf("%s %d", command, &itemID) == 2) {
        if (strcmp(command, "AVERAGE") == 0) {
            printf("Average rating (%d) is: %.1f\n", itemID, compute_average_rating(head, itemID));
        }
    }*/

    // free memory used by the linked list
    clearList(&head);

    return 0;
}
