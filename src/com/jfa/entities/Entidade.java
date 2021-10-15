package com.jfa.entities;

import com.jfa.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.BitSet;

public class Entidade {

    protected BufferedImage sprite;
    protected int width, height;
    public double  x, y;
//testando o github jdsadjsajlsa

    public Entidade(int x, int y, int width, int height, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
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
}
