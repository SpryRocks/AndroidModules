package com.spryrocks.android.modules.ui.routing.endpoints;

public class FrameEndpointSettings {
    boolean clearBackStack;
    boolean hideKeyboard;

    FrameEndpointSettings() {
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
    public FrameEndpointSettings clearBackStack(boolean clearBackStack, boolean hideKeyboard) {
        this.clearBackStack = clearBackStack;
        this.hideKeyboard = hideKeyboard;
        return this;
    }
}
