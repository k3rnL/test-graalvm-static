package org.example;

import com.oracle.svm.core.c.CGlobalData;
import com.oracle.svm.core.c.CGlobalDataFactory;
import com.oracle.svm.core.c.function.CEntryPointNativeFunctions;

//@CContext(GlobalIsolateRegistry.Directives.class)
public class GlobalIsolateRegistry {

//    static class Directives implements CContext.Directives {
//        @Override
//        public List<String> getHeaderFiles() {
//            return List.of("\"/home/edaniel/IdeaProjects/untitled3/test.h\"");
//        }
//    }

//    public static final CGlobalData<CEntryPointNativeFunctions.IsolateThreadPointer> globalIsolatePtr =
//            CGlobalDataFactory.createWord();


    // Declare native methods
//    @CFunction
//    static native void set_global_isolate_ptr(long ptr);
//
//    @CFunction
//    static native long get_global_isolate_ptr();
//
//    // Java wrapper to set the isolate
//    public static void setIsolate(IsolateThread thread) {
//        long address = thread.rawValue();
//        set_global_isolate_ptr(address);
//    }
//
//    // Java wrapper to get the isolate
//    public static IsolateThread getIsolate() {
//        long address = get_global_isolate_ptr();
//        return WordFactory.pointer(address);
//    }
}