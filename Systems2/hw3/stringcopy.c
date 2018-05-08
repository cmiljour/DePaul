#include	<stdlib.h>
#include	<stdio.h>
#include	<string.h>

#define TEXT_LEN 256

int		main		()
{

char text[TEXT_LEN];

printf("Please enter a list of words with comma's inbetween: ");
fgets(text,TEXT_LEN,stdin);
size_t ln = strlen(text)-1;
if (text[ln] == '\n') text[ln] = '\0';

char comma = ',';
char *found;
size_t length;

found = strchr(text, comma);
length = found - text;
strncpy(text, found+1, 100);

while(1)
{
if (strchr(text, comma) == NULL)
{
printf("The last word now is: %s\n", text);
break;
}
found = strchr(text, comma);
length = found - text;

strncpy(text, found+1, 100);
printf("The length is: %d\n", length);
printf("The text now is: %s\n", text);
}




return 0;



}
