package com.jfa.world;

import com.jfa.entities.Inimigo;
import com.jfa.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Mundo {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;

    public Mundo(String caminho){
        try {
            BufferedImage mapa= ImageIO.read(getClass().getResource(caminho));
            int[] pixels= new int[mapa.getWidth()* mapa.getHeight()];
            WIDTH= mapa.getWidth();
            HEIGHT= mapa.getHeight();
            tiles = new Tile[mapa.getWidth()* mapa.getHeight()];
            mapa.getRGB(0,0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
            for(int xx = 0; xx < mapa.getWidth(); xx++) {
                for (int yy = 0; yy < mapa.getHeight(); yy++) {
                    int pixelAtual = pixels[xx + (yy * mapa.getWidth())];
                    tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64,yy*64,Tile.TILE_CHAO);
                    if (pixelAtual == 0xFF000000) {
                        //chao
                        tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64,yy*64,Tile.TILE_CHAO);
                    } else if (pixelAtual == 0xFFFF3DD1) {
                        //paredes laterais
                        tiles[xx+ (yy*WIDTH)]= new ParedeVerticalTile(xx*64,yy*64,Tile.TILE_PAREDE_VERTICAL);
                    } else if (pixelAtual == 0xFFFF8377) {
                        //canto superior esquerdo
                        tiles[xx+ (yy*WIDTH)]= new CantoEsquerdaTile(xx*64,yy*64, Tile.TILE_CANTO_ESQUERDA);
                    } else if (pixelAtual == 0xFFBDFF3A) {
                        //canto superior direito
                        tiles[xx+ (yy*WIDTH)]= new CantoDireitaTIle(xx*64, yy*64, Tile.TILE_CANTO_DIREITA);
                    } else if (pixelAtual == 0xFFFFFFFF) {
                        //parede de cima
                        tiles[xx+ (yy*WIDTH)]= new ParedeCimaTile(xx*64, yy*64, Tile.TILE_PAREDE_CIMA);
                    } else if (pixelAtual == 0xFF444444) {
                        //lapides
                        tiles[xx+ (yy*WIDTH)]= new LapideTile(xx*64, yy*64, Tile.TILE_LAPIDE);
                    }else if(pixelAtual== 0xFF0C5DFF) {
                        Game.jogador.setX(xx*64);
                        Game.jogador.setY(yy*64);
                    }else if(pixelAtual==0xFFFF0000){
                        Game.entidades.add(new Inimigo(xx*64,yy*64,64,64,Game.spritesheet.pegaSprite(0,0,64,64)));
                    }else {
                        tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64,yy*64,Tile.TILE_CHAO);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        int x_inicial = (Camera.x>>6);
        int y_inicial = (Camera.y>>6);
        int x_final = x_inicial +(Game.WIDTH>>6);
        int y_final = y_inicial +(Game.HEIGHT>>6)+12;
        for(int xx = x_inicial; xx <= x_final; xx++) {
            for (int yy = y_inicial; yy <= y_final; yy++) {
                if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
                    continue;
                Tile tile = tiles[xx+(yy*WIDTH)];
                tile.render(g);

            }
        }
    }

}
