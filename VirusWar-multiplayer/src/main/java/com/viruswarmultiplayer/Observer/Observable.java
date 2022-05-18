package com.viruswarmultiplayer.Observer;

public interface Observable {
    public void reg(Observer o);
    public void notifySubscribers(Integer x, Integer y, Character cell);
}
