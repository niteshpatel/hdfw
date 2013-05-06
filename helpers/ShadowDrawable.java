package com.herodevelop.hdfw.helpers;

import com.herodevelop.hdfw.Drawable;
import com.herodevelop.hdlibgdx.Graphics.Image;

public final class ShadowDrawable implements Drawable {

    private final int posX;
    private final int posY;
    private final int width;
    private final int height;
    private final Image image;

    public ShadowDrawable(Drawable drawable, Image image) {
        this.posX = drawable.getPosX();
        this.posY = drawable.getPosY();
        this.width = drawable.getWidth();
        this.height = drawable.getHeight();
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosY(int posY) {
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}