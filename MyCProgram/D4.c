//
// Created by dada on 15/3/2022.
//
#include <stdio.h>
int main4(){
    int arr[] = {1,2,3,4};

    for (int i = 0; i < 4; ++i) {
        printf("%d\n",arr[i]);
    }

    //ָ��λ��
    int *arrp = arr;
    printf("��һλ��ֵ%d\n",*arrp);
    arrp++;//λ��һλ
    printf("ָ��λ��һλ���ֵΪarr[1]=%d\n",*arrp);
    arrp += 2;//��λ����λ
    printf("ָ����λ����λ���ֵΪarr[3]=%d\n",*arrp);
    return 0;
}
