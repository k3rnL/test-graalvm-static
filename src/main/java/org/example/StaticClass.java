package org.example;

import com.oracle.svm.core.c.function.CEntryPointOptions;
import com.oracle.svm.core.c.function.CEntryPointSetup;
import org.graalvm.nativeimage.c.function.CEntryPoint;

public class StaticClass {

//        public static final CGlobalData<CEntryPointNativeFunctions.IsolateThreadPointer> threadIsolate =
//                CGlobalDataFactory.createWord();
//    private static final CGlobalData<PointerBase> globalIsolatePtrAddress =
//            CGlobalDataFactory.createWord();

//    public static final IsolateThread isolateThread = Isolates.createIsolate(new Isolates.CreateIsolateParameters.Builder().build());

    static String staticField;

    @CEntryPoint
    @CEntryPointOptions(prologue = AgentIsolate.Prologue.class,
            epilogue = CEntryPointSetup.LeaveEpilogue.class)
    public static void testStaticAccess() {
        System.out.println("Static field value: " + staticField);
    }

}
