package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Intent;
import android.os.Bundle;

public interface ILifecycleListener {
    void onCreate(Bundle savedInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
