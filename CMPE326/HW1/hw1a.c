struct Rating {
  int user_id;
  int item_id;
  int rating;
  struct Rating* next;
};

struct Rating* read_ratings(char* filename) {
  FILE* file = fopen(filename, "r");
  if (!file) {
    fprintf(stderr, "Error: could not open file %s\n", filename);
    exit(1);
  }

  struct Rating* head = NULL;
  struct Rating* tail = NULL;

  int user_id, item_id, rating;
  while (fscanf(file, "%d %d %d", &user_id, &item_id, &rating) == 3) {
    struct Rating* node = malloc(sizeof(struct Rating));
    node->user_id = user_id;
    node->item_id = item_id;
    node->rating = rating;
    node->next = NULL;

    if (!head) {
      head = node;
    } else {
      tail->next = node;
    }
    tail = node;
  }

  fclose(file);
  return head;
}

int find_rating(struct Rating* head, int user_id, int item_id) {
  struct Rating* curr = head;
  while (curr) {
    if (curr->user_id == user_id && curr->item_id == item_id) {
      return curr->rating;
    }
    curr = curr->next;
  }
  return -1;
}

double average_rating(struct Rating* head, int item_id) {
  struct Rating* curr = head;
  int count = 0;
  int sum = 0;
  while (curr) {
    if (curr->item_id == item_id) {
      count++;
      sum += curr->rating;
    }
    curr = curr->next;
  }
  if (count == 0) {
    return 0.0;
  } else {
    return (double)sum / count;
  }
}

void highest_rated_items(struct Rating* head, int n) {
  int* counts = calloc(n, sizeof(int));
  int i;
  struct Rating* curr = head;
}

int main() {
  struct Rating* head = read_ratings("ratings.txt");
  printf("Rating for user 1, item 1: %d\n", find_rating(head, 1, 1));
  printf("Rating for user 1, item 2: %d\n", find_rating(head, 1, 2));
  printf("Rating for user 2, item 1: %d\n", find_rating(head, 2, 1));
  printf("Rating for user 2, item 2: %d\n", find_rating(head, 2, 2));
  printf("Average rating for item 1: %.2f\n", average_rating(head, 1));
  printf("Average rating for item 2: %.2f\n", average_rating(head, 2));
  highest_rated_items(head, 2);
  return 0;
}