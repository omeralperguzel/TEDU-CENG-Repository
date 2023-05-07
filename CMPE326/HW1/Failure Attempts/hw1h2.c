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
float compute_user_average_rating(RatingNode *head, int user_id) {
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
}

int main() {
    //LinkedList list;
    RatingNode* head = NULL;
    char cmd[10];
    int user_id, item_id;
    float rating;    
    while (1) {
        scanf("%s", cmd);
        if (strcmp(cmd, "INSERT") == 0) {
            scanf("%d %d %f", &user_id, &item_id, &rating);
            if (list.insert(user_id, item_id, rating)) {
                printf("Customer rating (%d, %d) is added successful\n", user_id, item_id);
            } else {
                printf("Customer rating (%d, %d) is updated\n", user_id, item_id);
            }
        } else if (strcmp(cmd, "REMOVE") == 0) {
            scanf("%d %d", &user_id, &item_id);
            if (list.remove(user_id, item_id)) {
                printf("Customer rating (%d, %d) is removed successful\n", user_id, item_id);
            } else {
                printf("Customer rating (%d, %d) does not exist\n", user_id, item_id);
            }
        } else if (strcmp(cmd, "RATING") == 0) {
            scanf("%d %d", &user_id, &item_id);
            float rating = list.getRating(user_id, item_id);
            printf("Customer rating (%d, %d) is: %.1f\n", user_id, item_id, rating);
        } else if (strcmp(cmd, "AVERAGE") == 0) {
            scanf("%d", &item_id);
            float avgRating = list.getAverageRating(item_id);
            printf("Average rating (%d) is: %.1f\n", item_id, avgRating);
        } else if (strcmp(cmd, "HIGHEST") == 0) {
            vector<int> itemIDs = list.getHighestRatedItems();
            printf("Highest rated item(s): ");
            for (int i = 0; i < itemIDs.size(); i++) {
                printf("%d ", itemIDs[i]);
            }
            printf("\n");
        } else if (strcmp(cmd, "QUIT") == 0) {
            break;
        } else {
            printf("Invalid command. Please try again.\n");
        }
    }
    
    return 0;
}