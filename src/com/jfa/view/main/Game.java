package com.jfa.view.main;

import com.jfa.controller.JogadorController;
import com.jfa.view.entities.Magia;
import com.jfa.view.graphics.Waves;
import com.jfa.view.world.Mundo;
import com.jfa.view.entities.Entidade;
import com.jfa.view.entities.Inimigo;
import com.jfa.view.entities.Jogador;
import com.jfa.view.graphics.GameOver;
import com.jfa.view.graphics.Spritesheet;
import com.jfa.view.graphics.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Canvas implements Runnable, KeyListener {

    //___________________________CONSTANTES DO JOGO___________________________________________________
    private boolean taRodando = true;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    public static final int SCALE = 2;
    public static String ESTADO_DO_JOGO= "CUTSCENE";
    public static boolean resetar =false;
    public static boolean temJogador = false;
    //_________________________CLASSES INVOCADAS
    public static JFrame frame;
    private Thread thread;
    private BufferedImage image;
    public static Spritesheet spritesheet;
    public static Random rand;
    public UI ui;
    public GameOver gg;
    //___________________________VARIÁVEIS ÚNICAS_________________________________________________________
    public static List<Entidade> entidades;
    public static List<Inimigo> inimigos;
    public static List<Magia> magias;
    public static Jogador jogador;
    public static Mundo mundo;
    public Waves wave;
    public Menu menu;
    public JogadorController jogadorController;
    public MenuCadastrarPlayer menuCadastrarPlayer;
    public static int waves =0;


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
        wave = new Waves();
        rand= new Random();
        gg = new GameOver();
        menu = new Menu();
        jogadorController= new JogadorController();
        menuCadastrarPlayer= new MenuCadastrarPlayer();
        //_______________________________________________________________________________________
        magias = new ArrayList<>();
        entidades = new ArrayList<Entidade>();
        inimigos = new ArrayList<Inimigo>();
        spritesheet = new Spritesheet("/Spritesheet.png");
        jogador = new Jogador(0,0,64,64,spritesheet.pegaSprite(0,0,64,64));
        entidades.add(jogador);
        ui= new UI();
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

    public void tick() {
        if(!temJogador){
                ESTADO_DO_JOGO = "MENU CADASTRAR PLAYER";
        }
        if(ESTADO_DO_JOGO =="MENU CADASTRAR PLAYER"){



        }else if (ESTADO_DO_JOGO == "NORMAL" || ESTADO_DO_JOGO == "CUTSCENE") {
            for (int i = 0; i < Game.inimigos.size(); i++) {

                Game.inimigos.get(i).tick();
            }
            if (ESTADO_DO_JOGO == "CUTSCENE") {
                jogador.tickCutscene();
            } else if (ESTADO_DO_JOGO == "NORMAL") {
                jogador.tick();
                for (int i = 0; i < Game.magias.size(); i++) {
                    Game.magias.get(i).tick();
                }
            }
        } else if (ESTADO_DO_JOGO == "PERDEU") {
            if (Game.resetar) {
                reiniciar();
            }
        } else if(ESTADO_DO_JOGO =="MENU"){
            menu.tick();
            if (Game.resetar) {
                reiniciar();
            }
            Game.waves=0;
        }
        if (Game.inimigos.size() <= 0) {
            Game.waves++;
            int xInimigo;
            int yInimigo;

            for (int i = 0; i < Game.waves * 8; i++) {
                Inimigo inimigo;
                do {
                    int x = rand.nextInt(600);
                    int y = rand.nextInt(600);
                    xInimigo = x + Game.jogador.getX();
                    yInimigo = y + Game.jogador.getY();
                    inimigo = new Inimigo((xInimigo), (yInimigo), 64, 64, Game.spritesheet.pegaSprite(0, 0, 64, 64), 10 + Game.waves * 1.5);
                } while ((!Mundo.taLivre(xInimigo, yInimigo)
                        ||!Mundo.taLivre(xInimigo+100, yInimigo)
                        ||!Mundo.taLivre(xInimigo-100, yInimigo)
                        ||!Mundo.taLivre(xInimigo, yInimigo+100)
                        ||!Mundo.taLivre(xInimigo, yInimigo-100)
                        ||!Mundo.taLivre(xInimigo+100, yInimigo+100)
                        ||!Mundo.taLivre(xInimigo-100, yInimigo-100)
                        ||!Mundo.taLivre(xInimigo+100, yInimigo-100)
                        ||!Mundo.taLivre(xInimigo-100, yInimigo+100)
                    ) && (
                        inimigo.taBatendoNele(xInimigo,yInimigo)
                        ||inimigo.taBatendoNele(xInimigo+100,yInimigo)
                        ||inimigo.taBatendoNele(xInimigo-100,yInimigo)
                        ||inimigo.taBatendoNele(xInimigo+100,yInimigo+100)
                        ||inimigo.taBatendoNele(xInimigo-100,yInimigo-100)
                        ||inimigo.taBatendoNele(xInimigo-100,yInimigo+100)
                        ||inimigo.taBatendoNele(xInimigo+100,yInimigo-100)
                        ||inimigo.taBatendoNele(xInimigo,yInimigo-100)
                        ||inimigo.taBatendoNele(xInimigo,yInimigo+100)
                    ));
                Game.entidades.add(inimigo);
                Game.inimigos.add(inimigo);
            }
        }
    }

    public void reiniciar(){
        Game.resetar =false;
        Game.jogador.pontos = 0;
        Game.waves = 0;
        entidades = new ArrayList<Entidade>();
        inimigos = new ArrayList<Inimigo>();
        spritesheet = new Spritesheet("/Spritesheet.png");
        jogador = new Jogador(0, 0, 64, 64, spritesheet.pegaSprite(0, 0, 64, 64));
        entidades.add(jogador);
        Jogador.vida = 100;
        ui = new UI();
        mundo = new Mundo("/MAPA.png");
        //entidades.add(new Inimigo(0,0,64,64,spritesheet.pegaSprite(0,0,64,64)));

        addKeyListener(this);
        ESTADO_DO_JOGO = "CUTSCENE";
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
        for(int i =0; i<Game.entidades.size(); i++){
            Game.entidades.get(i).render(g);
        }
        for(int i =0; i<Game.magias.size(); i++){
            Game.magias.get(i).render(g);
        }
        ui.render(g);
        wave.render(g);
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,WIDTH*SCALE, HEIGHT*SCALE,null);
        if (ESTADO_DO_JOGO=="PERDEU")
            gg.render(g);
        else if(ESTADO_DO_JOGO=="CUTSCENE")
            jogador.renderCutscene(g);
        else if(ESTADO_DO_JOGO=="MENU"){
            menu.render(g);
        }else if(ESTADO_DO_JOGO=="MENU CADASTRAR PLAYER"){
            menuCadastrarPlayer.render(g);
        }
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
    public void keyTyped(KeyEvent e) {
        if(ESTADO_DO_JOGO=="MENU CADASTRAR PLAYER" && e.getKeyCode()!=KeyEvent.VK_ENTER){
            menuCadastrarPlayer.setNome(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(ESTADO_DO_JOGO== "MENU"){
                menu.desce = true;
            }
        }
        if(ESTADO_DO_JOGO=="MENU CADASTRAR PLAYER" && e.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println("eu errei o if");
            temJogador= true;
            ESTADO_DO_JOGO="CUTSCENE";
            jogadorController.RegistrarPlayer(menuCadastrarPlayer.getNome());
            menuCadastrarPlayer.setNome("",1);

        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(ESTADO_DO_JOGO=="NORMAL"){
                ESTADO_DO_JOGO="MENU";
            }else if(ESTADO_DO_JOGO=="MENU"){
                ESTADO_DO_JOGO = "NORMAL";
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(ESTADO_DO_JOGO== "MENU"){
                menu.sobe = true;
            }
        }
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
        if(e.getKeyCode()==KeyEvent.VK_J){
            jogador.atirou = true;
        }
        if(ESTADO_DO_JOGO=="PERDEU" && e.getKeyCode() == KeyEvent.VK_ENTER){
            Game.resetar = true;
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            if(ESTADO_DO_JOGO=="MENU"){
                menu.enter = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(ESTADO_DO_JOGO== "MENU"){
                menu.desce = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(ESTADO_DO_JOGO== "MENU"){
                menu.sobe = false;
            }
        }
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
        if(e.getKeyCode()==KeyEvent.VK_J){
            jogador.atirou = false;
        }
        if(ESTADO_DO_JOGO=="PERDEU" && e.getKeyCode() == KeyEvent.VK_ENTER){
            Game.resetar = false;
        }
    }
}
