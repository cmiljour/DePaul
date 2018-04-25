#include	"safeBustersHeaders.h"

int continueRunning = 1;
int safeOpened = 0;
int comboNum1;
int comboNum2;
int comboNum3;

void sigIntHandler (int sigNum
		   )
{
	continueRunning = 0;
}

int		computeResponse	(int*		countPtr
				)
{
  int	toReturn;

  sleep(1);
  (*countPtr)--;

  if  (*countPtr == 0)
  {
    printf("Safe \"Click!\"\n");
    toReturn	= SIG_RIGHT_DIGIT;
  }
  else
  {
    printf("Safe \"Nope.\"\n");
    toReturn	= SIG_WRONG_DIGIT;
  }

  return(toReturn);
}

void sigTryNextDigitHandler (int sigNum, siginfo_t* infoPtr, void* dataPtr
			    )
{
	if (comboNum1 > 0)
	{
		kill(infoPtr->si_pid,computeResponse(&comboNum1));
	}
	else if (comboNum2 > 0)
	{
		kill(infoPtr->si_pid,computeResponse(&comboNum2));
	}
	else if(comboNum3 > 0)
	{
		if ((computeResponse(&comboNum3)) == SIG_RIGHT_DIGIT)
		{
			safeOpened = 1;
			continueRunning = 0;
            kill(infoPtr->si_pid,SIG_RIGHT_DIGIT);

		}
		else kill(infoPtr->si_pid,SIG_WRONG_DIGIT);
			}
}

int main () {
	
	srand(getpid());
	struct sigaction	act;
    memset(&act,"\0",sizeof(act));

	act.sa_flags = SA_SIGINFO;
    act.sa_sigaction = sigTryNextDigitHandler;
	sigaction(SIG_TRY_NEXT_DIGIT, &act, NULL);
	signal(SIG_QUIT, sigIntHandler);

	comboNum1 = (rand() % 16) + 1;
	comboNum2 = (rand() % 16) + 1;
	comboNum3 = (rand() % 16) + 1;
	printf("Combo: %d-%d-%d \n", comboNum1, comboNum2, comboNum3);

	while (continueRunning)
		sleep(1);

	if (safeOpened == 1)
	{
		printf("Safe:  I am no match for the thief's skill! \n");
	}
	else 
	{
		printf("Safe:  Go back to safe cracking school, thief! \n");
	}
	return(EXIT_SUCCESS);
}
