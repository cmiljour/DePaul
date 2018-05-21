/*-------------------------------------------------------------------------*
 *---									---*
 *---		babyFTPServer.cpp					---*
 *---									---*
 *---	    This file defines a very simple file-transfer server	---*
 *---	program.							---*
 *---									---*
 *---	----	----	----	----	----	----	----	----	---*
 *---									---*
 *---	Version 1.0		2012 August 8		Joseph Phillips	---*
 *---									---*
 *---	Version 1.1		2013 August 15		Joseph Phillips	---*
 *---									---*
 *---	    Adapted to do more than just list remote files as the	---*
 *---	previous remoteFileListingServer.cpp.				---*
 *---									---*
 *-------------------------------------------------------------------------*/

//  Compile with:
//    $ g++ babyFTPServer.cpp -o babyFTPServer

#include	"babyFTPHeader.h"
#include	<sys/types.h>	// for open()
#include	<sys/stat.h>	// for open(), stat()
#include	<fcntl.h>	// for open()
#include	<dirent.h>	// for opendir(), readdir(), closedir()
#include	<wait.h>	// for wait()
#include	<signal.h>	// for sigaction()

using namespace std;

enum
{
  PORT_NUM_CMDLINE_INDEX			= 1,
  PASSWD_FILENAME_CMDLINE_INDEX
};

const int	MAX_NUM_QUEUING_CLIENTS		= 5;

#define		DIR_NAME			"."

#define		DEFAULT_PASSWORD_FILENAME	".babyFTPrc"


//  PURPOSE:  To serve as the SIGCHLD handler.  Ignores 'sig'.  No return
//	value.
void	sigchld_handler		(int	sig
				)
{
  int	status;
  pid_t	pid;

  //  YOUR CODE
  while( (pid = waitpid(-1,&status, WNOHANG)) > 0)
  {
  //  YOUR CODE
   if (status < 0) 
   {
	  printf("Process %d crashed.\n",pid);
   }
   else 
   {
	  printf("Process %d finished with return value %d\n",
          pid,
          status
          );
   }
  } 
	

  fflush(stdout);
} 


//  PURPOSE:  To replace the first '\n' char appearing in the first 'maxLength'
//	chars of 'cPtr' with a '\0'.  If neither a '\n' nor a '\0' is found
//	in the first 'maxLength' chars then 'cPtr[maxLength-1]' is set to '\0'.
//	Returns 'cPtr', or NULL if 'maxLength==0'.
char*  	removeEndingNewline	(char*	cPtr,
				 uint	maxLength
				)
{
  //  I.  Applicability validity check:
  if  ( (cPtr == NULL) || (maxLength == 0) )
    return(NULL);

  //  II.  Remove ending newline char:
  for  (uint i = 0;  i < maxLength;  i++)
  {
    if  (cPtr[i] == '\0')
      return(cPtr);

    if  (cPtr[i] == '\n')
    {
      cPtr[i] = '\0';
      return(cPtr);
    }

  }

  //  III.  '\n' not found, end string and return:
  cPtr[maxLength-1] = '\0';
  return(cPtr);
}


//  PURPOSE:  To get the port number to use either from
//stati	'argv[PORT_NUM_CMDLINE_INDEX]' (if there are sufficient arguments
//	according to 'argc') or by asking the user.  Then to attempt to
//	open a listening socket on that port.  Returns file descriptor of
//	listening socket on success or '-1' otherwise.
int	createListeningSocket	(int		argc,
				 const char*	argv[]
				)
{
  //  I.  Applicability validity check:

  //  II.  Create listening socket:
  //  II.A.  Get desired port number:
  int 	     port;

  if  (argc > PORT_NUM_CMDLINE_INDEX)
    port = strtol(argv[PORT_NUM_CMDLINE_INDEX],NULL,0);
  else
  {
    char	text[MAX_STRING];

    printf("Desired port number? ");
    fgets(text,MAX_STRING,stdin);
    port = strtol(text,NULL,0);
  }

  //  II.B.  Create listening socket:
  int socketDescriptor;

  //  YOUR CODE HERE
  socketDescriptor = socket(AF_INET, SOCK_STREAM,0);

  struct sockaddr_in socketInfo;
  memset(&socketInfo, '\0',sizeof(socketInfo));
  socketInfo.sin_family = AF_INET;
  socketInfo.sin_port = htons(port);
  socketInfo.sin_addr.s_addr = INADDR_ANY;

  int status = bind(socketDescriptor, (struct sockaddr*)&socketInfo, sizeof(socketInfo));
  
  if (status < 0)
  {
	  fprintf(stderr,"Cound not bind to port %d\n", port);
	  exit(status);
  }

  listen(socketDescriptor, 20);
  //  III.  Finished:
  return(socketDescriptor);
}


//  PURPOSE:  To get the password, either from the file specified in
//	'argv[PASSWD_FILENAME_CMDLINE_INDEX]' if there are sufficient
//	command line arguments according to 'argc', or from file
//	'DEFAULT_PASSWORD_FILENAME', or by asking user.  Up to 'maxLength-1'
//	chars of password written to 'password'.  Returns 'password'.
char*	obtainPassword	(int		argc,
			 const char*	argv[],
			 char* 		password,
			 uint		maxLength
			)
{
  //  I.  Applicability validity check:

  //  II.  Obtain password:
  const char*	passwordFilename= (argc > PASSWD_FILENAME_CMDLINE_INDEX)
  		 		  ? argv[PASSWD_FILENAME_CMDLINE_INDEX]
				  : DEFAULT_PASSWORD_FILENAME;

  FILE* passwordFilePtr;

  char text[256];

  passwordFilePtr = fopen(DEFAULT_PASSWORD_FILENAME,"r");  
  //  YOUR CODE HERE

  if  ( passwordFilePtr == NULL// CHANGE THIS
      )
  {
    printf("Couldn't read %s, password for clients? \n",passwordFilename);
    fgets(password,maxLength,stdin);
  }
  else
  {
    //  DEPENDING ON HOW YOU OPEN YOUR FILE, MAYBE SOMETHING HERE
  fscanf(passwordFilePtr,"%s",text);
  //password = text;
  strncpy(password, text, MAX_LINE);
  int passwordSize = strlen(text);
  password[passwordSize] = '\0';

  }

  //  YOUR CODE
 removeEndingNewline(password,maxLength);

  //  III.  Finished:
  return(password);
}


//  PURPOSE:  To send 'GOOD_PASSWORD_RESPONSE' to the client over socket file
//	descriptor 'clientFD' and return 'true' if the password 'read()' from
//	'clientFD' matches 'password', or to send 'BAD_PASSWORD_RESPONSE' to
//	the client and return 'false' otherwise.
bool	didLogin	(int		clientFD,
			 const char*	password
			)
{
  //  I.  Application validity check:
  printf("Process %d authenticating user . . .\n",getpid());
  fflush(stdout);

  //  II.  See if user successfully logged-in:
  //  II.A.  Obtain user's password:
  char	buffer[MAX_LINE];

  //  YOUR CODE HERE
  
  read(clientFD, buffer, MAX_LINE);
  
  //  II.B.  Handle when user's password does NOT match:
  if  ( strncmp(removeEndingNewline(buffer,MAX_LINE),
		password,
		MAX_PASSWORD_LEN
	       )
	!= 0
      )
  {
    //  YOUR CODE HERE
    printf("Process %d bad password.\n",getpid());
    strncpy(buffer, BAD_PASSWORD_RESPONSE, MAX_LINE);
    int badPwRespSize = strlen(BAD_PASSWORD_RESPONSE);
    buffer[badPwRespSize] = '\0';
    write(clientFD, buffer, MAX_LINE);
    return(false);
  }

  //  II.C.  If get here then user's password does match:
  //  YOUR CODE HERE
  printf("Process %d good password.\n",getpid());
  int goodPwRespSize = strlen(GOOD_PASSWORD_RESPONSE);
  strncpy(buffer, GOOD_PASSWORD_RESPONSE, MAX_LINE);
  buffer[goodPwRespSize] = '\0';
  write(clientFD, buffer, MAX_LINE);
  //  III.  Finished:
  return(true);
}


//  PURPOSE:  To list the files in the server's current directory to the client
//	using file descriptor 'clientFD', followed by 'END_RESPONSE,MAX_LINE'.
//	No return value.
void	listDir		(int		clientFD
			)
{
 
  //  I.  Application validity check:
  printf("Process %d starting listing current directory.\n",getpid());
  fflush(stdout);

  //  II.  List files:
  //  II.A.  Attempt to open the directory:
  char	buffer[MAX_LINE];
  //  YOUR CODE HERE
  const char* dirName = ".";
  DIR*        dirPtr = opendir(dirName);
  
  if (dirPtr == NULL)
  {
    fprintf(stderr,"Cannot open directory %s\n", dirName);
    exit(EXIT_FAILURE);
  }

  struct dirent*    entryPtr;
  while ( (entryPtr = readdir(dirPtr)) != NULL)
  {
    char        entryPath[MAX_LINE];	  
    struct stat statBuffer;
    
    snprintf(entryPath,MAX_LINE,"%s/%s",dirName,entryPtr->d_name);
    stat(entryPath, &statBuffer);
    
    if( S_ISREG(statBuffer.st_mode) )
    {
      snprintf(buffer,MAX_LINE,"%30s (%5d)",entryPtr->d_name,statBuffer.st_size);
      write(clientFD, buffer, MAX_LINE);
    }
    else if( S_ISDIR(statBuffer.st_mode) )
    {
      snprintf(buffer,MAX_LINE,"%30s ( dir )",entryPtr->d_name);
      write(clientFD, buffer, MAX_LINE);
    } 
    else 
    {
      snprintf(buffer,MAX_LINE,"%30s (other)",entryPtr->d_name);   
      write(clientFD, buffer, MAX_LINE);
    }
  }
  
  closedir(dirPtr);
  strncpy(buffer, END_RESPONSE, MAX_LINE);
  int respSize = strlen(END_RESPONSE);
  buffer[respSize] = '\0';
  write(clientFD, buffer, MAX_LINE);
 

  //  III.  Finished:
  printf("Process %d finished listing current directory.\n",getpid());
  fflush(stdout);
}


//  PURPOSE:  To send to the client using file descriptor 'clientFD' the file
//	whose name is 'read()' from 'clientFD'.  No return value.
void	getFile		(int		clientFD
			)
{
  //  I.  Applicability validity check:
  printf("Process %d attempting to read file.\n",getpid());
  fflush(stdout);

  //  II.  Attempt to get the file:
  //  II.A.  Attempt to 'read()' the filename from 'clientFD':
  char	buffer[MAX_LINE];
  char	filename[MAX_LINE];

  //  YOUR CODE HERE
  read(clientFD, filename, MAX_LINE);

  //  II.B.  Attempt to open file:
  //  YOUR CODE HERE
  int fd = open(filename,O_RDONLY,0);
  if (fd < 0)
  {
	  int toSend = htonl(fd);
	  write(clientFD,&toSend,sizeof(toSend));
	  return;
  }
  //  II.C.  Attempt to get file's size:
  //  YOUR CODE HERE
  char statRes[MAX_LINE];
  struct stat statBuffer;
  const char* dirName = ".";

  snprintf(statRes,MAX_LINE,"%s/%s",dirName,filename);

  stat(statRes, &statBuffer);
  int fileSize = statBuffer.st_size;
  
  //  II.D.  Tell client about the file:
  printf("Process %d getting %s.\n",getpid(),filename);
  fflush(stdout);

  //char * yourFileNameVar = filename;
  //  II.D.1.  Tell client how many bytes the file has:
  //  YOUR CODE HERE
  int toSend = htonl(fileSize);
  write(clientFD,&toSend,sizeof(toSend));
  //  II.D.2.  Tell client the chars of the file:
  //  YOUR CODE HERE
  char newBuffer[fileSize];
  read(fd,newBuffer,fileSize);
  write(clientFD,newBuffer,fileSize);

  //  II.E.  Close file:
  //  YOUR CODE HERE
  close(fd);
  //  III.  Finished:
  printf("Process %d read file %s.\n",getpid(), filename);
  fflush(stdout);
}


//  PURPOSE:  To handle the client whom talking to over socket file descriptor
//	'clientFD' and that should authenticate with password 'password'.
//	Returns 'EXIT_SUCCESS' if session went well or 'EXIT_FAILURE'
//	otherwise.
int	handleClient	(int		clientFD,
			 const char*	password
			)
{

//    I.  Application validity check:
  if  ( !didLogin(clientFD,password) )
  {
    close(clientFD);
    return(EXIT_FAILURE);
  }

  //  II.  Handle client:
  int	status	=	EXIT_SUCCESS;
  char	buffer[MAX_LINE];

  //  II.A.  Each iteration attempts to handle another
  //  	     command coming from the client:
  while  (true)
  {
    //  II.A.1.  'read()' command from client:
    int	numChars	= read(clientFD,buffer,MAX_LINE);

    if  (numChars < 0)
    {
      status = EXIT_FAILURE;
      break;
    }

    //  II.A.2.  Handle various commands:
    if  (strncmp(buffer,QUIT_CMD,QUIT_CMD_LEN) == 0)
    {
      printf("Process %d quitting.\n",getpid());
      fflush(stdout);
      break;
    }
    else
    if  (strncmp(buffer,LIST_CMD,LIST_CMD_LEN) == 0)
    {
      listDir(clientFD);
    }
    else
    if  (strncmp(buffer,GET_CMD,GET_CMD_LEN) == 0)
    {
      getFile(clientFD);
    }

  }

  //  III.  Finished:
  return(status);
}


//  PURPOSE:  To listen on socket 'listeningSocketId' for clients, let them
//	attempt to authenticate with password 'passwordSizeword', and to send list
//	of current directory entries to those that are successful.  No return
//	value.
void	doServer	(int		listeningSocketId,
			 const char*	password
			)
{
  //  I.  Applicability validity check:

  //  II.  Serve:

  //  II.A.  Each iteration serves one client
  bool	shouldServe = true;

  while  (shouldServe)
  {
    //  II.A.1.  Open communication with client:
    int		clientDescriptor	= accept(listeningSocketId,NULL,NULL);

    //  II.A.2.  Handle client:
    if  (fork() == 0)
    {
      exit(handleClient(clientDescriptor,password));
    }
    //  II.A.3.  Go back to listen for another client:
    close(clientDescriptor);
  }

  //  III.  Finished:
}


//  PURPOSE:  To get a port and function as a server on that port that sends
//	to clients a listing of the entries in the current directory.
//	First command line argument (if present) taken as port to use.
//	Second command line argument (if present) taken as filename containing
//	password to authenticate users.
int	main		(int		argc,
 			 const char*	argv[]
			)
{
  //  I.  Applicability validity check:

  //  II.  Do server:
  //  II.A.  Setup for server:
  char		password[MAX_PASSWORD_LEN];
  int		listeningSocketId;

  if  ( (listeningSocketId = createListeningSocket(argc,argv)) < 0 )
    return(EXIT_FAILURE);

  obtainPassword(argc,argv,password,MAX_PASSWORD_LEN);
  //  YOUR CODE HERE to make sigchld_handler() the handler for SIGCHLD
  
  struct sigaction act;
  memset(&act,'\0',sizeof(struct sigaction));
  sigemptyset(&act.sa_mask);
  act.sa_flags = SA_NOCLDSTOP | SA_RESTART;
  act.sa_handler = sigchld_handler;
  sigaction(SIGCHLD,&act,NULL);

  //  II.B.  Do server:
  doServer(listeningSocketId,password);

  //  III.  Finished:
  close(listeningSocketId);
  return(EXIT_SUCCESS);
}
