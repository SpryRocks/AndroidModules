package com.spryrocks.android.modules.ui.routing.endpoints;

public class ScreenEndpointSettings {
    boolean restartTask;
    boolean newTask;

    ScreenEndpointSettings() {
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
    public ScreenEndpointSettings restartTask(boolean restartTask) {
        this.restartTask = restartTask;
        return this;
    }

    @SuppressWarnings({"SameParameterValue", "unused"})
    public void newTask(boolean newTask) {
        this.newTask = newTask;
    }
}
