#include	<stdlib.h>
#include	<stdio.h>
#include	<string.h>

#define TEXT_LEN 256

typedef struct node {
	char * word;
	struct node * next;
}node;

struct node *head = NULL;

void deleteList(struct node** head)
{  
  struct node* current = *head;
  struct node* next;
  while (current != NULL) {
    next = current->next;
    free(current);
    current = next;
  }
  *head = NULL;
}

// print out the linked list in reverse
void printReverse(struct node* head)
{
  if (head == NULL) return;
  printReverse(head->next);
  printf("\"%s\"\n", head->word);
}

// add a node to the start of the linked list
void insert(char* word) {
  node *new_node = (struct node*) malloc(sizeof(struct node));
  new_node->word = word;
  new_node->next = head;
  head = new_node;
}


int		main		()
{

  char text[TEXT_LEN];
  char result[TEXT_LEN];
  char *found;
  size_t length;
  
  printf("Please enter a list of words with comma's inbetween: ");
  fgets(text,TEXT_LEN,stdin);
  size_t ln = strlen(text)-1;
  if (text[ln] == '\n') text[ln] = '\0';
    
  while(1)
  {
    if (strchr(text, ',') == NULL)
    {
      // use strdup to copy and null terminate string
      // since strchr in previous calls doesn't null terminate    
      insert(strdup(text));
      break;
    }
    // use strchr to find position of comma ','
    found = strchr(text, ',');
    // find the total length of chars leading up to position of comma
    length = found - text;
    // copy the first "length" characters of "text" and put in result array
    strncpy(result, text,length);
    // since strncpy doesn't null terminate, do it now
    result[length] = '\0';
    // strdup used to properly null terminate string and insert into LL
    insert(strdup(result));
    // copy from +1 position past prev identified
    // comma position to the end of array and start the loop over
    //strncpy(text, found+1, sizeof(text)/sizeof(text[length]));
    strncpy(text, found+1, sizeof(text));
  }
    
  printReverse(head);
  deleteList(&head);
  return 0;
}
