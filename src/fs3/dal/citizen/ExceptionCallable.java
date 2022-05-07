package fs3.dal.citizen;

import java.util.concurrent.Callable;

public abstract class ExceptionCallable implements Callable<Exception> {
    @Override
    public Exception call() {
        try {
            doTask();
        }
        catch (Exception e) {
            return e;
        }
        return null;
    }

    abstract void doTask() throws Exception;
}
