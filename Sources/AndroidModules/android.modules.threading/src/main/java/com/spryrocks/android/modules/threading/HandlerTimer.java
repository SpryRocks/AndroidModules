package com.spryrocks.android.modules.threading;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handler Timer is the class, that simplifies using timer in ui thread.
 * Created by maxim_000 on 31.12.2014.
 */
public class HandlerTimer {

    private final Timer timer;

    private final Handler handler;

    private boolean isCancelled;

    public HandlerTimer() {
        this.handler = new Handler();

        this.timer = new Timer();
    }

    public void scheduleAtFixedRate(final Runnable task, int delay, int period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tickAsync(task);
            }
        }, delay, period);
    }

    public void schedule(final Runnable task, int delay) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tickAsync(task);
            }
        }, delay);
    }

    private void tickAsync(final Runnable task) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                tickSync(task);
            }

        });
    }

    private void tickSync(final Runnable task) {
        if (isCancelled)
            return;

        task.run();
    }

    public void cancel() {
        this.timer.cancel();
        isCancelled = true;
    }
}