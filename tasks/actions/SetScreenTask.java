package com.herodevelop.hdfw.tasks.actions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.ScreenId;
import com.herodevelop.hdfw.tasks.Task;

public final class SetScreenTask extends Task {

    private final ScreenId screenId;
    private float delay;
    private boolean done;

    public SetScreenTask(ScreenEx screen, ScreenId screenId, Float delay) {
        super(screen);

        this.screenId = screenId;
        this.delay = delay;
        this.done = false;
    }

    @Override
    public final void update(float delta) {
        delay -= delta;
        if (delay <= 0) {
            game.setScreenEx(screenId);
            done = true;
        }
    }

    @Override
    public final boolean isDone() {
        return done;
    }
}
