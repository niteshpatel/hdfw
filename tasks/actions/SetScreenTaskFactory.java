package com.herodevelop.hdfw.tasks.actions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.ScreenId;
import com.herodevelop.hdfw.tasks.Task;
import com.herodevelop.hdfw.tasks.TaskFactory;

public final class SetScreenTaskFactory extends TaskFactory {

    private final ScreenEx screen;
    private final ScreenId screenId;
    private final Float delay;

    public SetScreenTaskFactory(ScreenEx screen, ScreenId screenId, Float delay) {
        this.screen = screen;
        this.screenId = screenId;
        this.delay = delay;
    }

    @Override
    public final Task newTask() {
        return new SetScreenTask(screen, screenId, delay);
    }
}
