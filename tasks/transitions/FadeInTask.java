package com.herodevelop.hdfw.tasks.transitions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;

public final class FadeInTask extends Task {

    private final int paintIndex;
    private float alpha;

    public FadeInTask(ScreenEx screen, int paintIndex) {
        super(screen);

        this.paintIndex = paintIndex;
        this.alpha = 1;
    }

    @Override
    public final void update(float delta) {
        alpha -= 0.2;
        alpha = Math.max(alpha, 0);
    }

    @Override
    public final boolean isDone() {
        return alpha <= 0;
    }

    @Override
    public final void paint(float delta) {
        // Colour screen
        game.graphics.drawRect(0, 0, game.graphics.getWidth(), game.graphics.getHeight(), 0, 0, 0, alpha);
    }

    @Override
    public final int getPaintIndex() {
        return paintIndex;
    }
}
