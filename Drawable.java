package com.herodevelop.hdfw;

import com.herodevelop.hdlibgdx.Graphics.Image;

public interface Drawable {

    public Image getImage();

    public void setImage(Image image);

    public int getPosX();

    public void setPosX(int posX);

    public int getPosY();

    public void setPosY(int posY);

    public int getWidth();

    public int getHeight();
}
