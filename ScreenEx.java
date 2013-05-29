package com.herodevelop.hdfw;

import com.herodevelop.hdfw.tasks.Task;
import com.herodevelop.hdlibgdx.Screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class ScreenEx extends Screen {

    public final GameEx game;
    private ArrayList<Task> tasks;
    private ArrayList<Task> tasksQ;
    private Boolean suspended;

    protected ScreenEx(GameEx game) {
        game.app.log(((Object) this).getClass().getName() + "()");
        this.game = game;
        this.tasks = new ArrayList<Task>();
        this.tasksQ = new ArrayList<Task>();
        this.suspended = false;
    }

    private void updateTasks(float delta) {
        // Update all tasks
        Collections.sort(tasks, new UpdateIndexComparator());
        for (Task t : tasks) {

            // Inputs must be handled before update, otherwise you risk user
            // input modifying something they have not yet become aware of
            // (skip inputs for one iteration, usually when another task
            // is overriding the inputs)
            if (!t.isSkippingInput()) {
                t.handleInput(delta);
            } else {
                t.setSkippingInput(false);
            }
            t.update(delta);
        }
    }

    private void paintTasks(float delta) {
        // Clear screen and paint all tasks
        game.graphics.clear();
        Collections.sort(tasks, new PaintIndexComparator());
        for (Task t : tasks) {
            t.paint(delta);
        }
    }

    private void purgeTasks() {
        ArrayList<Task> filtered = new ArrayList<Task>();
        for (Task t : tasks) {
            if (!t.isDone()) {
                filtered.add(t);
            }
        }
        tasks = filtered;
    }

    private void clearTasks() {
        tasks = new ArrayList<Task>();
    }

    final void queueTasks() {
        // Add list of queued tasks to tasks for next update
        for (Task t : tasksQ) {
            tasks.add(t);
        }
        tasksQ = new ArrayList<Task>();
    }

    public final void addTask(Task t) {
        tasksQ.add(t);
    }

    @Override
    public final void render(float delta) {
        updateTasks(delta);

        // Frame skip at 20fps
        if (delta > 0.05)
            return;

        paintTasks(delta);
        purgeTasks();
        queueTasks();
    }

    @Override
    public final void show() {
        game.app.log(((Object) this).getClass().getName() + ".show");
        if (!this.suspended) {
            game.app.log(((Object) this).getClass().getName() + ".onStart");
            this.onStart();
        }
        game.app.log(((Object) this).getClass().getName() + ".onThaw");
        this.onThaw();
        this.suspended = false;
    }

    @Override
    public final void hide() {
        game.app.log(((Object) this).getClass().getName() + ".onFreeze");
        this.onFreeze();
        if (!this.suspended) {
            game.app.log(((Object) this).getClass().getName() + ".onStop");
            this.onStop();
            clearTasks();
        }
        game.app.log(((Object) this).getClass().getName() + ".hide");
    }

    protected final void suspend() {
        this.suspended = true;
    }

    protected void onStart() {
    }

    void onStop() {
    }

    void onFreeze() {
    }

    void onThaw() {
    }

    public final void stop() {
        game.app.log(((Object) this).getClass().getName() + ".onStop");
        this.suspended = false;
        this.onStop();
        clearTasks();
    }

    @Override
    public void pause() {
        game.app.log(((Object) this).getClass().getName() + ".pause");
    }

    @Override
    public void resume() {
        game.app.log(((Object) this).getClass().getName() + ".resume");
    }

    @Override
    public void dispose() {
        game.app.log(((Object) this).getClass().getName() + ".dispose");
    }

    @Override
    public void resize(int width, int height) {
        //game.app.log(((Object) this).getClass().getName() + ".resize");
    }

    private class UpdateIndexComparator implements Comparator<Task> {
        @Override
        public int compare(Task lhs, Task rhs) {
            int lhsIndex = lhs.getUpdateIndex();
            int rhsIndex = rhs.getUpdateIndex();
            return (lhsIndex < rhsIndex ? -1 : (lhsIndex == rhsIndex ? 0 : 1));
        }
    }

    private class PaintIndexComparator implements Comparator<Task> {
        @Override
        public int compare(Task lhs, Task rhs) {
            int lhsIndex = lhs.getPaintIndex();
            int rhsIndex = rhs.getPaintIndex();
            return (lhsIndex < rhsIndex ? -1 : (lhsIndex == rhsIndex ? 0 : 1));
        }
    }
}
