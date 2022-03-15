//
// Created by dada on 15/3/2022.
//
#include <stdio.h>
int main5(){
    int arr[4];
    // sizeof 所有元素占用的大小，一个int型的元素占4个字节，4个就是16个
    printf("%d\n",sizeof arr);
    int j = 0;
    //给数组赋值
    for (j = 0; j < sizeof(arr) / sizeof (int); ++j) {//   16/4 = 4
        //(arr +i)取出地址，通过移动指针，获取下一个元素的地址     *（arr+i)代表取出该地址的值
        *(arr + j) = j + 1000;//赋值
    }

    int i = 0;
    for (i = 0; i < sizeof(arr) / sizeof (int); ++i) {
//        printf("%d\n",arr[i]);
        printf("%d\n",*(arr +i));
    }
    return 0;
}
