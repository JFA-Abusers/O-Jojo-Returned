package com.jfa.view.entities;

import com.jfa.view.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidade {

    protected BufferedImage sprite;
    protected int width, height;
    public double  x, y;
    protected int maskx, masky,mwidth, mheight;
//testando o github jdsadjsajlsa

    public Entidade(int x, int y, int width, int height, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.maskx = 0;
        this.masky = 0;
        this.mwidth =width;
        this.mheight = height;
    }

    public void setMask(int maskx, int masky, int mheight, int mwidth){
        this.maskx = maskx;
        this.masky = masky;
        this.mheight = mheight;
        this.mwidth = mwidth;
    }
    /// mudança

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return (int)this.x;
    }

    public int getY() {
        return (int)this.y;
    }

    public void render(Graphics g){
        g.drawImage(sprite, this.getX() - Camera.x, this.getY()- Camera.y, null);
    }

    public void tick() {}

    public static boolean taBatendo(Entidade e1, Entidade e2){
        Rectangle e1Mask = new Rectangle(e1.getX()+ e1.maskx, e1.getY()+e1.masky,20,20);
        Rectangle e2Mask = new Rectangle(e2.getX()+ e2.maskx, e2.getY()+e2.masky,20,20);

        return e1Mask.intersects(e2Mask);
    }
}
