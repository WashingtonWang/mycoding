package com.mycoding.designpatterns.observer.subpackage;

public interface Subject {
    void registerObserver();

    void removeObserver();

    void notfyObservers();
}
