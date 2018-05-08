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

void printReverse(struct node* head)
{
	if (head == NULL) return;

	printReverse(head->next);
	printf("\"%s\"\n", head->word);
}

void insert(char* word) {
   //create a link
    node *new_node = (struct node*) malloc(sizeof(struct node));

   //link->key = key;
      new_node->word = word;

   //point it to old first node
   new_node->next = head;
 
   //point first to new first node
   head = new_node;
}


int		main		()
{

char text[TEXT_LEN];
char result[TEXT_LEN];

	printf("Please enter a list of words with comma's inbetween: ");
	fgets(text,TEXT_LEN,stdin);
	size_t ln = strlen(text)-1;
	if (text[ln] == '\n') text[ln] = '\0';
    char *found;
    size_t length;
    
    while(1)
    {
    if (strchr(text, ',') == NULL)
    {
    insert(strdup(text));
    break;
    }
   
    found = strchr(text, ',');
    length = found - text;
    strncpy(result, text,length);
    result[length] = '\0';
    insert(strdup(result));
    strncpy(text, found+1, 100);
    }
    
    printReverse(head);
    deleteList(&head);
    return 0;
}
