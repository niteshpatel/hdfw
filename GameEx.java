package com.herodevelop.hdfw;

import com.herodevelop.hdlibgdx.*;

import java.util.HashMap;

public abstract class GameEx extends Game {

    public App app;
    public Audio audio;
    public Files file;
    public Graphics graphics;
    public Input input;
    public HashMap<String, String> data;
    protected HashMap<ScreenId, ScreenEx> screens;

    protected abstract int getVirtualWidth();

    protected abstract int getVirtualHeight();

    public void create() {
        app = new App("hdfw");
        audio = new Audio();
        file = new Files();
        graphics = new Graphics(getVirtualWidth(), getVirtualHeight());
        input = new Input();
        data = new HashMap<String, String>();
    }

    public abstract void createEx();

    public final ScreenEx getScreenEx(ScreenId screenId) {
        return screens.get(screenId);
    }

    public final void setScreenEx(ScreenId screenId) {
        setScreen(getScreenEx(screenId));
    }
}
