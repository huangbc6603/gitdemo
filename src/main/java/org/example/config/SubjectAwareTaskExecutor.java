package org.example.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Component
public class SubjectAwareTaskExecutor extends ThreadPoolTaskExecutor {


    @Override
    public boolean prefersShortLivedTasks() {
        return false;
    }

    @Override
    public void execute(Runnable aTask) {
        final Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            super.execute(currentSubject.associateWith(aTask));
        } else {
            super.execute(aTask);
        }
    }


    @Override
    public void execute(Runnable task, long startTimeout) {
        Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            super.execute(currentSubject.associateWith(task), startTimeout);
        } else {
            super.execute(task, startTimeout);
        }

    }


    @Override
    public Future<?> submit(Runnable task) {
        Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            return super.submit(currentSubject.associateWith(task));
        } else {
            return super.submit(task);
        }


    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            return super.submit(currentSubject.associateWith(task));
        } else {
            return super.submit(task);
        }
    }


    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            return super.submitListenable(currentSubject.associateWith(task));
        } else {
            return super.submitListenable(task);
        }


    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        Subject currentSubject = ThreadContext.getSubject();
        if (currentSubject != null) {
            return super.submitListenable(currentSubject.associateWith(task));
        } else {
            return super.submitListenable(task);
        }
    }

}