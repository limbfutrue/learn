//
// Created by dada on 14/3/2022.
//
#include <stdio.h>
int main3(){
    int num = 999;
    int *num_p = &num;
    int ** num_pp = &num_p;
    printf("num_pµÄÄÚ´æµØÖ·%p,%p,\n",num_p,**num_pp);
    return 0;
}
