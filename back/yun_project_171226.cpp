#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <time.h>
//referance : http://www.codedata.com.tw/book/java-basic/
//CH.2 CH.3

int main(int argc, char* argv[])
{
	char zipfilename[32] = "test.zip";
	FILE * zipfile;
	FILE * proout;
	char tmp1[512] = {0};
	char tmp2[512] = {0};
	char proname[64];
	char tmpnum[2];
	char *tmpoi1,*tmpoi2;
	int score = 0;

	//1.read zip_file_name from argv

	if(argc >= 2)
		snprintf(zipfilename,sizeof(zipfilename),"%s",argv[1]);
	else
		sprintf(zipfilename,"test.zip");

	//2.get zipped filename and unzip
	//ex:jar -tf zipfile 	//list file names in zipfile
	//ex:jar -xf zipfile 	//unzip

	sprintf(tmp1,"jar -tf %s",zipfilename);
	zipfile = popen(tmp1,"r");
	fscanf(zipfile,"%s",tmp2);
	pclose(zipfile);
	sscanf(tmp2,"%[^.]java",proname);

	sprintf(tmp1,"jar -xf %s",zipfilename);
	system(tmp1);

	//3.compiler and record process and result

	sprintf(tmp1,"javac %s.java",proname);
	proout = popen(tmp1,"r");

	while(!feof(proout))
	{
	    fgets(tmp1,sizeof(tmp1),proout);
        if(strstr(tmp1,"error")=="error")
        {
            printf("score = 0\n");
            return 0;
        }
        else if(strstr(tmp1,"warning"))
        {
            score-=5;
        }
    }
    pclose(proout);

	sprintf(tmp1,"java %s",proname);
	proout = popen(tmp1,"r");
	//4.read process and result to count score
	for(int i = 1;i <= 9;i++)
	   for(int j = 1;j <= 9;j++)
	   {
	       fgets(tmp1,sizeof(tmp1),proout);
	       sprintf(tmp2,"%d",i);
	       tmpoi1 = strstr(tmp1,tmp2);
	       if(tmpoi1)
	       {
      	       sprintf(tmp2,"%d",j);
      	       tmpoi2 = strstr(tmpoi1+1,tmp2);
    	       if(tmpoi2)
    	       {
         	       sprintf(tmp2,"%d",i*j);
        	       if(strstr(tmpoi2+1,tmp2))
        	       {
                        score += 1;
                   }
               }
           }
        }
    score += 19;
    printf("score = %d\n",score);
    pclose(proout);
	//5.kill unzip file and temp files
	sprintf(tmp1,"del %s.java %s.class",proname,proname);
	system(tmp1);
	//ex:del file
	//ex:rmdir dir

	return score;
}
