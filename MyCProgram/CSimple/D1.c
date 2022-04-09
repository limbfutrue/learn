#include <stdio.h>
int main1() {
    printf("哈喽,世界！  Hello, World!\n");
    int i = 0;
    long l = 10;
    double d = 100;

    float f = 11;
    short s = 1;
    char c ='a';

    char * str = "libaoming";

    printf("i的值：%d\n",i);
    printf("l的值：%ld\n",l);
    printf("d的值：%lf\n",d);

    printf("f的值：%f\n",f);
    printf("s的值：%d\n",s);
    printf("c的值：%c\n",c);
    printf("str的值：%s\n",str);

    return 0;
}
