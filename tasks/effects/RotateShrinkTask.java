package com.herodevelop.hdfw.tasks.effects;

import com.herodevelop.hdfw.Drawable;
import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;

public final class RotateShrinkTask extends Task {

    private final Drawable obj;
    private final int paintIndex;
    private int timer;
    private int angle;
    private float scale;

    public RotateShrinkTask(ScreenEx screen, Drawable obj, int paintIndex) {
        super(screen);

        this.obj = obj;
        this.paintIndex = paintIndex;

        // Initialise timer
        this.timer = 0;

        // Set the image config
        this.angle = 0;
        this.scale = 1;
    }

    @Override
    public final void update(float delta) {
        timer += 40;
        angle += 20;
        scale /= 1.125;
    }

    @Override
    public final boolean isDone() {
        return timer > 1000;
    }

    @Override
    public final void paint(float delta) {
        game.graphics.drawImage(obj.getImage(), obj.getPosX(), obj.getPosY(), scale, angle);
    }

    @Override
    public final int getPaintIndex() {
        return paintIndex;
    }
}
