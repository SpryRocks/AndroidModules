package com.example.android.modules.ui.v7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.modules.ui.lifecycle.ActivityLifecycleListenersCollection;
import com.example.android.modules.ui.lifecycle.ILifecycleListener;
import com.example.android.modules.ui.lifecycle.ILifecycleListenersCollection;

public class BaseActivity extends AppCompatActivity implements ILifecycleListenersCollection {
    private final ActivityLifecycleListenersCollection lifecycleListenersCollection;

    public BaseActivity() {
        lifecycleListenersCollection = new ActivityLifecycleListenersCollection();
    }


    /* lifecycle */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleListenersCollection.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        lifecycleListenersCollection.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        lifecycleListenersCollection.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        lifecycleListenersCollection.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();

        lifecycleListenersCollection.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        lifecycleListenersCollection.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        lifecycleListenersCollection.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(lifecycleListenersCollection.onActivityResult(requestCode, resultCode, data))
            return;

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lifecycleListenersCollection.onRestoreInstanceState(savedInstanceState);
    }


    /* public methods */

    @Override
    public void registerListener(ILifecycleListener listener) {
        lifecycleListenersCollection.registerListener(listener);
    }
}
