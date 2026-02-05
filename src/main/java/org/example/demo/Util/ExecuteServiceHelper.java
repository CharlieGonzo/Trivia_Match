package org.example.demo.Util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteServiceHelper {
    private static ExecuteServiceHelper instance;
    private ExecutorService executor;

    public static ExecuteServiceHelper getInstance(){
        if(instance == null){
            instance = new ExecuteServiceHelper();
            instance.executor = Executors.newFixedThreadPool(10);
        }
        return instance;
    }

    private ExecuteServiceHelper(){}

    public void runTask(Runnable runnable){
        executor.execute(runnable);
    }

    public <T> T runTask(Callable<T> callable) throws Exception {
        return callable.call();
    }


}
