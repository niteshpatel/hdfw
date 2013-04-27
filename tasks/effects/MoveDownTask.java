package com.herodevelop.hdfw.tasks.effects;

import com.herodevelop.hdfw.Drawable;
import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;

public final class MoveDownTask extends Task {

    private final Drawable obj;
    private final int speed;
    private int count;

    public MoveDownTask(ScreenEx screen, Drawable obj, int count, int speed) {
        super(screen);

        this.obj = obj;
        this.count = count;
        this.speed = speed;
    }

    @Override
    public final void update(float delta) {
        int change = Math.min(speed, count);
        count -= change;
        obj.setPosY(obj.getPosY() - change);
    }

    @Override
    public final boolean isDone() {
        return count <= 0;
    }
}
