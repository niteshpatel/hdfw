package com.herodevelop.hdfw.tasks;

import com.herodevelop.hdfw.ScreenEx;

public abstract class ReuseTask extends Task {
    public ReuseTask(ScreenEx screen) {
        super(screen);
    }

    // Reset the task state
    public abstract void reset();
}
