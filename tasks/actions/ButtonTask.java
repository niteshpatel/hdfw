package com.herodevelop.hdfw.tasks.actions;

import com.herodevelop.hdfw.ScreenEx;
import com.herodevelop.hdfw.tasks.Task;
import com.herodevelop.hdfw.tasks.TaskFactory;
import com.herodevelop.hdlibgdx.Graphics.Image;

public final class ButtonTask extends Task {

    private final Image image;
    private final TaskFactory taskFactory;
    // Positioning and bounds
    private final int centerX;
    private final int centerY;
    private final int width;
    private final int height;
    private final int minBoundX;
    private final int maxBoundX;
    private final int minBoundY;
    private final int maxBoundY;
    // Used for scaling the button
    private float scale;
    private boolean pressed;

    public ButtonTask(ScreenEx screen, Image image, int centerX, int centerY, TaskFactory taskFactory) {
        super(screen);

        this.image = image;
        this.centerX = centerX;
        this.centerY = centerY;
        this.taskFactory = taskFactory;

        // Initial button state
        this.pressed = false;
        this.scale = 1;

        // Set the size
        this.width = image.getRegionWidth();
        this.height = image.getRegionHeight();

        // Set the input bounds
        this.minBoundX = (centerX - width / 2) / 2;
        this.maxBoundX = (centerX + width / 2) / 2;
        this.minBoundY = (centerY - height / 2) / 2;
        this.maxBoundY = (centerY + height / 2) / 2;
    }

    @Override
    public final void update(float delta) {
        int posX = screen.game.input.getX();
        int posY = screen.game.input.getY();

        // Check if are in the button
        if (posX >= minBoundX && posY >= minBoundY && posX < maxBoundX && posY < maxBoundY) {

            // Increase the button size while the button is pressed
            if (screen.game.input.isTouched()) {
                pressed = true;
                if (scale < 1.07) {
                    scale += 0.014;
                }
            }

            // If the button is released when we are over it, then
            // execute the button task
            else if (pressed) {
                pressed = false;
                screen.addTask(taskFactory.newTask());
            } else if (scale > 1) {
                scale -= 0.02;
            }
        }

        // If the input is out of bounds, return the button to
        // the normal state
        else {
            pressed = false;
            if (scale > 1) {
                scale -= 0.02;
            }
        }
    }

    @Override
    public final void paint(float delta) {
        // Draw the scaled button
        game.graphics.drawImage(
                image,
                centerX - (width / 2),
                centerY - (height / 2),
                scale, 0);
    }
}