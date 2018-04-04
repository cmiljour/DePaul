#include <stdio.h>
#include <stdlib.h>

extern char begin;
extern char end;

#define SIZE 64

void enterBeginEnd(){
	char array[SIZE];
	printf("Enter first character");
	fgets(array, SIZE, stdin);
	begin = array[0];

	printf("Enter some more");
	fgets(array, SIZE, stdin);
	end = array[9];

}
