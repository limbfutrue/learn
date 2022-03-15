
#include <stdio.h>

// C语言不支持函数重载。所以声明的函数不需要写参数

/**
 * 交换a和b的值
 * @param a
 * @param b
 */
void changeValue(int * a, int * b){
    // *a 取出a地址所对应的值
    int temp = *a;
    *a = *b;
    *b = temp;
}
//先声明 后调用，  或者写在调用它的函数的前面。因为是面向过程的。
void change();

//C语言不支持函数重载。所以声明的函数不需要写参数
void add(int *a);

int main2(){
    int a = 100;
    int b = 200;
//& 代表取地址
    changeValue(&a,&b);
    printf("%d,%d\n",a,b);
    change();
    add(&a);
    return 0;
}

void change(){

}

void add(int *a){
    //*取值
    printf("%d\n",*a);
    //&取地址
    printf("%p",&a);
}

