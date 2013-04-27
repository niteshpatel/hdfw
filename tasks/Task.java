package com.herodevelop.hdfw.tasks;

import com.herodevelop.hdfw.GameEx;
import com.herodevelop.hdfw.ScreenEx;

public abstract class Task {

    protected final ScreenEx screen;
    protected final GameEx game;

    public Task(ScreenEx screen) {
        this.screen = screen;
        this.game = screen.game;
    }

    public void handleInput(float delta) {
    }

    public void update(float delta) {
    }

    public int getUpdateIndex() {
        return 0;
    }

    public void paint(float delta) {
    }

    public int getPaintIndex() {
        return 0;
    }

    public boolean isDone() {
        return false;
    }
}
