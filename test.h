// globals.h
#ifndef GLOBALS_H
#define GLOBALS_H


// Declare a global variable to hold the isolate pointer
void  *global_isolate_ptr ;

void set_global_isolate_ptr(void *ptr) {
    global_isolate_ptr = ptr;
}

void *get_global_isolate_ptr() {
    return global_isolate_ptr;
}

void trick2();
void trick1() {
    get_global_isolate_ptr();
    set_global_isolate_ptr((void*) 0);
    trick2();
}

void trick2() {
    trick1();
}

#endif // GLOBALS_H