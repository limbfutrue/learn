//
// Created by dada on 15/3/2022.
//
#include <stdio.h>
int main4(){
    int arr[] = {1,2,3,4};

    for (int i = 0; i < 4; ++i) {
        printf("%d\n",arr[i]);
    }

    //指针位移
    int *arrp = arr;
    printf("第一位的值%d\n",*arrp);
    arrp++;//位移一位
    printf("指针位移一位后的值为arr[1]=%d\n",*arrp);
    arrp += 2;//再位移两位
    printf("指针再位移两位后的值为arr[3]=%d\n",*arrp);
    return 0;
}
