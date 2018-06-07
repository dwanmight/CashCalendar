package com.might.dwan.cashcalendar.archs.presenters;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import java.lang.ref.WeakReference;

/**
 * Created by Ilya on 06.06.2018.
 */

public abstract class BasePresenter<V, M> {
    private WeakReference<V> view;
    protected M model;

    public BasePresenter(V v) {
        this.view = new WeakReference<>(v);
        performUpdateView();
    }

    private void performUpdateView() {
        if (isValidView() && model != null)
            updateView();
    }

    public void bindModel(M model) {
        this.model = model;
        performUpdateView();
    }

    private boolean isValidView() {
        return (view != null && view.get() != null);
    }

    public abstract void updateView();

    public V view() {
        try {
            if (!isValidView()) {
                throw new NullPointerException("View is invalid");
            }
        } catch (Exception e) {e.printStackTrace();}
        return view.get();
    }



    @CallSuper
    public void unbindView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    @CallSuper
    public void release() {
        onRelease();
        unbindView();
        model = null;
    }

    /**
     * Called before release data
     */
    public void onRelease() {}


    /**
     * Called when window save current state
     * @param bundle
     */
    public void onSaveState(Bundle bundle) {}
}
