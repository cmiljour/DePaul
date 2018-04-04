#include <stdio.h>
#include <stdlib.h>

extern void printFromBeginToEnd();
extern void enterBeginEnd();

char begin;
char end;

int main(){
	enterBeginEnd();
	printFromBeginToEnd();
	return(EXIT_SUCCESS);
}
