package com.github.xy02.ritp;

class WindowState{
    private int windowSize;
    private int increment;
    private int decrement;

    public WindowState(int windowSize, int increment, int decrement) {
        this.windowSize = windowSize;
        this.increment = increment;
        this.decrement = decrement;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public int getIncrement() {
        return increment;
    }

    public int getDecrement() {
        return decrement;
    }
}
