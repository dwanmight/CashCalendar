package com.might.dwan.cashcalendar.archs.presenters;

import android.support.annotation.CallSuper;

import java.lang.ref.WeakReference;

/**
 * Created by Ilya on 06.06.2018.
 */

public abstract class BasePresenter<V, M> {
    private WeakReference<V> view;
    protected M model;

    public BasePresenter(V v) {
        this(v, null);
    }

    public BasePresenter(V v, M m) {
        this.view = new WeakReference<V>(v);
        this.model = m;
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
        view = null;
    }

    public void release() {
        unbindView();
        model = null;
    }
}
