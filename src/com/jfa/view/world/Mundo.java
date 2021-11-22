package com.jfa.view.world;

import com.jfa.view.entities.Inimigo;
import com.jfa.view.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Mundo {

    private static Tile[] tiles;
    public static int WIDTH, HEIGHT, TAMANHO_TILE=64;

    public Mundo(String caminho){
        try {
            BufferedImage mapa= ImageIO.read(getClass().getResource(caminho));
            int[] pixels= new int[mapa.getWidth()* mapa.getHeight()];
            WIDTH= mapa.getWidth();
            HEIGHT= mapa.getHeight();
            tiles = new Tile[mapa.getWidth()* mapa.getHeight()];
            mapa.getRGB(0,0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
            criacaoDoMapa(mapa.getWidth(), mapa.getHeight(), pixels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void criacaoDoMapa(int tamanhoX, int tamanhoY, int[]pixels){
        for(int xx = 0; xx < tamanhoX; xx++) {
            for (int yy = 0; yy < tamanhoY; yy++) {
                int pixelAtual = pixels[xx + (yy * tamanhoX)];
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
                } else if (pixelAtual == 0xFFB5B5B5) {
                    //chão chique
                    tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64, yy*64, Tile.TILE_CHAO_CHIQUE);
                } else if (pixelAtual == 0xFFC4CC35) {
                    //chão chiquerrimo
                    tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64, yy*64, Tile.TILE_CHAO_CHIQUERRIMO);
                }else if(pixelAtual==0xFFFFFC5B){
                    tiles[xx+ (yy*WIDTH)]= new LapideTile(xx*64, yy*64, Tile.TILE_CHAO_BORDA);
                }else if(pixelAtual== 0xFF0C5DFF) {
                    Game.jogador.setX(xx*64);
                    Game.jogador.setY(yy*64);
                }else if(pixelAtual==0xFFFF0000){
                    Inimigo inimigo = new Inimigo(xx*64,yy*64,64,64,Game.spritesheet.pegaSprite(0,0,64,64));
                    Game.entidades.add(inimigo);
                    Game.inimigos.add(inimigo);

                }else {
                    tiles[xx+ (yy*WIDTH)]= new ChaoTile(xx*64,yy*64,Tile.TILE_CHAO);
                }
            }
        }
    }

    public static boolean taLivre(int xprox, int yprox){
        //a logica por tras dessas contas de doido
        //é que ce vai validar todas as 8 tiles
        //em volta do nosso player
        //porque agnt não quer ele atravessadno
        //paredes.

        int x1 = (xprox+10)/TAMANHO_TILE;
        int y1 = (yprox+30)/TAMANHO_TILE;

        int x2 = (xprox+30-1)/TAMANHO_TILE;
        int y2 = (yprox+30)/TAMANHO_TILE;

        int x3 = (xprox+30) / TAMANHO_TILE;
        int y3 = (yprox + 30 - 1) / TAMANHO_TILE;

        int x4 = (xprox+30-1)/TAMANHO_TILE;
        int y4 = (yprox+30-1)/TAMANHO_TILE;

        return !(tiles[x1+(y1*Mundo.WIDTH)] instanceof ParedeTile
                ||tiles[x2+(y2*Mundo.WIDTH)] instanceof ParedeTile
                ||tiles[x3+(y3*Mundo.WIDTH)] instanceof ParedeTile
                ||tiles[x4+(y4*Mundo.WIDTH)] instanceof ParedeTile);

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
