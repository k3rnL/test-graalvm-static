package org.example;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.Isolates;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;

public class Main {

    public static final CEntryPointLiteral<TestFunction> testFunction =  CEntryPointLiteral.create(StaticClass.class, "testStaticAccess");

    public interface TestFunction extends CFunctionPointer {
        @InvokeCFunctionPointer
        void invoke();
    }

    public static void main(String[] args) {
        System.out.println("Test without changing the value of the static field");

        testFunction.getFunctionPointer().invoke();

        System.out.println("Test after changing the value of the static field");

        StaticClass.staticField = "New value";

        testFunction.getFunctionPointer().invoke();

        System.out.println("Test after changing the value inside the isolate");
        IsolateThread isolateThread = Isolates.createIsolate(new Isolates.CreateIsolateParameters.Builder().build());
        AgentIsolate.setGlobalIsolate(Isolates.getIsolate(isolateThread));

        StaticClass.staticField = "Isolate value";

        testFunction.getFunctionPointer().invoke();

    }
}