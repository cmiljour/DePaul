#include <stdio.h>
#include <stdlib.h>

extern void printFromBeginToEnd();
extern void enterBeginEnd();

char begin;
char end;

int main(){
	int x = 2123456789;
	float *p = &x;
	float y = *p;
	printf ("y=%f\n", y);
	return 0;

}
