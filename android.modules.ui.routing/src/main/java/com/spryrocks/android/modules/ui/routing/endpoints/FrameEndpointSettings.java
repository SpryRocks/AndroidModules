package com.spryrocks.android.modules.ui.routing.endpoints;

public class FrameEndpointSettings {
    boolean clearBackStack;

    FrameEndpointSettings() {
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
    public FrameEndpointSettings clearBackStack(boolean clearBackStack) {
        this.clearBackStack = clearBackStack;
        return this;
    }
}
