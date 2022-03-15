//
// Created by dada on 15/3/2022.
//
#include <stdio.h>

void add(int num1,int num2){
    printf("num1 + num2 = %d\n",num1 + num2);
}

void mins(int num1,int num2){
    printf("num1 - num2 = %d\n",num1 - num2);
}

/**
 * 函数回调
 * void (*method)(int,int)   void方法的返回值类型，(*method)函数名 (int,int)参数
 *
 * @param method  方法名称
 * @param num1
 * @param num2
 */
void opreate(void (*method)(int,int ),int num1,int num2){
    method(num1,num2);
}
int main(){

//    void (*call)(int,int);//定义一个临时函数指针变量call
//    call = add;
    opreate(add,10,10);
    opreate(mins,100,10);
    char c = 'a';
    char *c1 = "limb";//char * 其实代表字符串

    printf("char c = %c\n",c);
    printf("char *c1 = %s",c1);
}
