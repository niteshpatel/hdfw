package com.herodevelop.hdfw.tasks.transitions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;
import com.herodevelop.hdfw.tasks.TaskFactory;

public final class FadeOutTaskFactory extends TaskFactory {

    private final ScreenEx screen;
    private final int paintIndex;
    private final Task task;

    public FadeOutTaskFactory(ScreenEx screen, int paintIndex, Task task) {
        this.screen = screen;
        this.paintIndex = paintIndex;
        this.task = task;
    }

    @Override
    public final Task newTask() {
        return new FadeOutTask(screen, paintIndex, task);
    }
}
