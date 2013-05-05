package com.herodevelop.hdfw;

import com.herodevelop.hdlibgdx.*;

import java.util.HashMap;

public abstract class GameEx extends Game {

    public App app;
    public Files file;
    public Graphics graphics;
    public Input input;
    protected HashMap<ScreenId, ScreenEx> screens;

    public void create() {
        app = new App("hdfw");
        file = new Files();
        graphics = new Graphics();
        input = new Input();
    }

    public abstract void createEx();

    public final ScreenEx getScreenEx(ScreenId screenId) {
        return screens.get(screenId);
    }

    public final void setScreenEx(ScreenId screenId) {
        setScreen(getScreenEx(screenId));
    }
}
