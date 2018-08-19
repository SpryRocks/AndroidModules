package com.spryrocks.android.modules.ui.routing.endpoints;

public class FrameEndpointSettings {
    boolean clearBackStack;
    boolean hideKeyboard;

    FrameEndpointSettings() {
    }

    @SuppressWarnings("unused")
    public FrameEndpointSettings clearBackStack(boolean clearBackStack) {
        this.clearBackStack = clearBackStack;
        return this;
    }

    @SuppressWarnings("unused")
    public FrameEndpointSettings hideKeyboard(boolean hideKeyboard) {
        this.hideKeyboard = hideKeyboard;
        return this;
    }
}
