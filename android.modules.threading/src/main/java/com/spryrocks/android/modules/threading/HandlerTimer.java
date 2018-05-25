/*
 * Copyright 2017 Spry Rocks, Inc
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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