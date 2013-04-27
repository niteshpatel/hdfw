package com.herodevelop.hdfw.tasks.transitions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;


public final class FadeOutTask extends Task {

    private final int paintIndex;
    private final Task task;
    private boolean done;
    private float alpha;

    public FadeOutTask(ScreenEx screen, int paintIndex, Task task) {
        super(screen);

        this.paintIndex = paintIndex;
        this.task = task;
        this.done = false;
        this.alpha = 0;
    }

    @Override
    public final void update(float delta) {
        if (alpha < 1) {
            alpha += 0.2;
            alpha = Math.min(alpha, 1);
        }
        if (alpha == 1 && !done) {
            screen.addTask(task);
            done = true;
        }
    }

    @Override
    public final boolean isDone() {
        return task.isDone();
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
