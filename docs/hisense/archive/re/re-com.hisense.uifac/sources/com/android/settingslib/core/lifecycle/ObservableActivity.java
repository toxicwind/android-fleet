package com.android.settingslib.core.lifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes.dex */
public class ObservableActivity extends FragmentActivity implements LifecycleOwner {
    private final Lifecycle mLifecycle = new Lifecycle(this);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.mLifecycle.onAttach(this);
        this.mLifecycle.onCreate(bundle);
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_CREATE);
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        this.mLifecycle.onAttach(this);
        this.mLifecycle.onCreate(bundle);
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_CREATE);
        super.onCreate(bundle, persistableBundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_START);
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_RESUME);
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_PAUSE);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_STOP);
        super.onStop();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mLifecycle.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!super.onCreateOptionsMenu(menu)) {
            return false;
        }
        this.mLifecycle.onCreateOptionsMenu(menu, null);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        this.mLifecycle.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean zOnOptionsItemSelected = this.mLifecycle.onOptionsItemSelected(menuItem);
        return !zOnOptionsItemSelected ? super.onOptionsItemSelected(menuItem) : zOnOptionsItemSelected;
    }
}
