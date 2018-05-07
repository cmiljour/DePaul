#include	<stdlib.h>
#include	<stdio.h>
#include	<string.h>

#define TEXT_LEN 256

struct node {
	char * word;
	struct node *next;
};

struct node *head = NULL;
struct node *current = NULL;

//display the list
void printList() {

   //struct node *ptr = head;
   printListAux(head);
   //printf("\n[head] =>");
   //start from the beginning
  // while(ptr != NULL) {
     // printf("\"%s\"\n",ptr->word);
     // ptr = ptr->next;
   //}

   //printf(" [null]\n");
}

void  printListAux(struct node* head)
{
	if (head == NULL)
		return;
	printListAux(head->next);
	printf("\"%s\"\n",head->word);
}

void insert(char* word) {
   //create a link
   struct node *link = (struct node*) malloc(sizeof(struct node));

   //link->key = key;
   link->word = word;

   //point it to old first node
   link->next = head;

   //point first to new first node
   head = link;
}


int		main		()
{

char text[TEXT_LEN];

	printf("Please enter a list of words with comma's inbetween: ");
	fgets(text,TEXT_LEN,stdin);
	size_t ln = strlen(text)-1;
	if (text[ln] == '\n') text[ln] = '\0';
	char *textPtr;
	char *templine;

	textPtr = text;
	//textPtr = strsep(&textPtr, ",");
	while((templine = strsep(&textPtr, ",")) != NULL)
        {
		insert(templine);
		printf("\"%s\"\n",templine);
        }	

//	printList();
	return 0;



}
