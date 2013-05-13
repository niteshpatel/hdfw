package com.herodevelop.hdfw.tasks.effects;

import com.herodevelop.hdfw.Drawable;
import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;

public final class FadeTask extends Task {

    private final Drawable obj;
    private final int paintIndex;
    private final float delay;
    private float alpha;

    public FadeTask(ScreenEx screen, Drawable obj, float delay, int paintIndex) {
        super(screen);

        this.obj = obj;
        this.delay = delay;
        this.paintIndex = paintIndex;

        // Set the image config
        this.alpha = 1;
    }

    @Override
    public final void update(float delta) {

        alpha -= (delta / delay);
        if (alpha < 0) alpha = 0;
    }

    @Override
    public final boolean isDone() {
        return alpha <= 0;
    }

    @Override
    public final void paint(float delta) {
        game.graphics.drawImage(obj.getImage(), obj.getPosX(), obj.getPosY(), 1, 0, 1, 1, 1, alpha);
    }

    @Override
    public final int getPaintIndex() {
        return paintIndex;
    }
}
