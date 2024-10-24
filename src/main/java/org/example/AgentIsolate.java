package org.example;

import com.oracle.svm.core.Uninterruptible;
import com.oracle.svm.core.c.CGlobalData;
import com.oracle.svm.core.c.CGlobalDataFactory;
import com.oracle.svm.core.c.function.CEntryPointActions;
import com.oracle.svm.core.c.function.CEntryPointErrors;
import com.oracle.svm.core.c.function.CEntryPointOptions;
import com.oracle.svm.core.util.VMError;
import org.graalvm.nativeimage.Isolate;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.WordPointer;

import static org.graalvm.word.WordFactory.nullPointer;

/**
 * A utility class for managing isolate.
 */
public final class AgentIsolate {
    static final CGlobalData<WordPointer> GLOBAL_ISOLATE = CGlobalDataFactory.createWord();

    public static final class Prologue implements CEntryPointOptions.Prologue {
        private static final CGlobalData<CCharPointer> errorMessage = CGlobalDataFactory.createCString(
                        "Failed to enter (or attach to) the global isolate in the current thread.");

        @Uninterruptible(reason = "prologue")
        static void enter() {
            int code = CEntryPointActions.enterAttachThread(GLOBAL_ISOLATE.get().read(), false, true);
            if (code != CEntryPointErrors.NO_ERROR) {
                CEntryPointActions.failFatally(code, errorMessage.get());
            }
        }
    }

    public static final class EnterOrBailoutPrologue implements CEntryPointOptions.Prologue {
        @Uninterruptible(reason = "prologue")
        static int enter() {
            Isolate global = GLOBAL_ISOLATE.get().read();
            if (global.isNull()) {
                return CEntryPointErrors.UNINITIALIZED_ISOLATE;
            }
            return CEntryPointActions.enterByIsolate(global);
        }
    }

    public static void setGlobalIsolate(Isolate isolate) {
        VMError.guarantee(nullPointer().equal(GLOBAL_ISOLATE.get().read()), "Global isolate must be set exactly once");
        GLOBAL_ISOLATE.get().write(isolate);
    }

    public static void resetGlobalIsolate() {
        VMError.guarantee(nullPointer().notEqual(GLOBAL_ISOLATE.get().read()), "Global isolate must be set");
        GLOBAL_ISOLATE.get().write(nullPointer());
    }

    private AgentIsolate() {
    }
}