package com.jfa.main;

import com.jfa.entities.Entidade;
import com.jfa.entities.Inimigo;
import com.jfa.entities.Jogador;
import com.jfa.graphics.Spritesheet;
import com.jfa.world.Mundo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static JFrame frame;
    private Thread thread;
    private boolean taRodando = true;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    private final int SCALE = 2;
    private BufferedImage image;
    public static Spritesheet spritesheet;


    public static List<Entidade> entidades;
    public static Jogador jogador;
    public static Mundo mundo;


    public static void main(String[] args) {

        Game game = new Game();
        game.start();

    }

    public synchronized void start(){
        thread = new Thread(this);
        taRodando = true;
        thread.start();

    }

    public synchronized void stop(){
        taRodando = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public Game(){
        //__________________inicializando a janela_____________________________
        //setando o tamanho preferido desta classe
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        inicializarFrame();
        image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        //_______________________________________________________________________________________

        entidades = new ArrayList<Entidade>();
        spritesheet = new Spritesheet("/Spritesheet.png");
        jogador = new Jogador(0,0,64,64,spritesheet.pegaSprite(0,0,64,64));
        entidades.add(jogador);
        mundo= new Mundo("/MAPA.png");
        //entidades.add(new Inimigo(0,0,64,64,spritesheet.pegaSprite(0,0,64,64)));

        addKeyListener(this);

    }

    public void inicializarFrame(){
        //Inicializando o jframe
        frame= new JFrame("O jojo: returned");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);//pra aparecer no meio
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// pra terminar a aplicacao caso feche a janela
        frame.setVisible(true);// pra aparecer a tela
    }

    public void tick(){
        for(Entidade e : entidades){

            e.tick();
        }
    }

    public void render(){
        BufferStrategy bs= this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;

        }

        Graphics g = image.getGraphics();
        g.setColor(new Color(19,19,255));
        g.fillRect(0,0,WIDTH, HEIGHT);
        //Graphics2D g2 = (Graphics2D) g;
        mundo.render(g);
        for(Entidade e : entidades){
            e.render(g);
        }
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,WIDTH*SCALE, HEIGHT*SCALE,null);
        bs.show();
    }

    @Override
    public void run() {
        long antes = System.nanoTime();
        double tantoDeTicks = 60.0;
        double ms = 1000000000/tantoDeTicks;
        double diferenca = 0;
        int frames= 0;
        double timer = System.currentTimeMillis();
        requestFocus();
        while(taRodando){
            //System.out.println("O jogo esta rodando");
            long agora = System.nanoTime();
            diferenca+= (agora-antes)/ms;
            antes = agora;
            if(diferenca>=1){
                tick();
                render();
                frames++;
                diferenca--;
            }
            if(System.currentTimeMillis()- timer>=1000){
                System.out.println("FPS: "+ frames);
                frames=0;
                timer = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_D){
            //System.out.printf("Direita");
            jogador.direita=true;
        }else if(e.getKeyCode()== KeyEvent.VK_A){
            //System.out.println("Esquerda");
            jogador.esquerda=true;
        }
        if(e.getKeyCode()== KeyEvent.VK_W){
           //System.out.println("cima");
            jogador.cima=true;
        }else if(e.getKeyCode()== KeyEvent.VK_S){
            //System.out.println("embaixo");
            jogador.embaixo= true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_D){
            //System.out.printf("Direita");
            jogador.direita=false;
        }else if(e.getKeyCode()== KeyEvent.VK_A){
            //System.out.println("Esquerda");
            jogador.esquerda=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_W){
            //System.out.println("cima");
            jogador.cima=false;
        }else if(e.getKeyCode()== KeyEvent.VK_S){
            //System.out.println("embaixo");
            jogador.embaixo= false;
        }
    }
}
