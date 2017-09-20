package com.mycoding.designpatterns.observer.subpackage;

import com.mycoding.designpatterns.observer.obspackage.Observer;

/**
 * 主题
 */
public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
