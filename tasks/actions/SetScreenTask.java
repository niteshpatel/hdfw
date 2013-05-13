package com.herodevelop.hdfw.tasks.actions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.ScreenId;
import com.herodevelop.hdfw.tasks.ReuseTask;

public class SetScreenTask extends ReuseTask {

    private final ScreenId screenId;
    private final float initialDelay;
    private float delay;
    private boolean done;

    public SetScreenTask(ScreenEx screen, ScreenId screenId, Float delay) {
        super(screen);

        this.screenId = screenId;
        this.initialDelay = delay;
        reset();
    }

    @Override
    public void reset() {
        delay = initialDelay;
        done = false;
    }

    @Override
    public void update(float delta) {
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
