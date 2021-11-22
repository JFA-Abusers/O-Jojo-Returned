package com.jfa.view.world;

public class Camera {
    public static int x;
    public static int y;

    public static int Clamp (int xAtual, int xMin, int xMax){
        if(xAtual < xMin){
            xAtual = xMin;
        }
        if(xAtual > xMax){
            xAtual = xMax;

        }
        return xAtual;
    }


}
