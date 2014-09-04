/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import br.com.cotuca.spaceinvaders.personagens.Inimigos;
import br.com.cotuca.spaceinvaders.personagens.NaveAliada;
import br.com.cotuca.spaceinvaders.personagens.Personagem;
import br.com.cotuca.spaceinvaders.personagens.Tiro;
import java.io.IOException;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.LayerManager;

/**
 *
 * @author u12176
 */
public class Tela extends GameCanvas implements Runnable {

//    variavel de controle de jogo
    private boolean jogando;
    private Thread thread;

    private LayerManager lmng;

    private int altura;
    private int largura;

//    Personagens da tela 
    private Inimigos inimigos;
    private NaveAliada naveAliada;
    private Image fundo;
    
    private Personagem[] personagens;

    public static final int DELAY = 40;

    public Tela() {
        super(true);
        altura = getHeight();
        largura = getWidth();

        jogando = false;
        thread = new Thread(this);
        try {
            naveAliada = new NaveAliada(Imagens.NAVE_ALIADA);
            inimigos = new Inimigos(Imagens.NAVE_INIMIGA, 8, 4);
            fundo = Image.createImage(Imagens.FUNDO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lmng = new LayerManager();
        lmng.append(naveAliada.getSprite());

    }

    private void acoesDoTeclado(Graphics g) {

        int teclaClicada = getKeyStates();

//        Verificacao de cada acao nessa parte
        switch (teclaClicada) {
            case GameCanvas.RIGHT_PRESSED:
                naveAliada.mover(Personagem.DIREITA);
                break;
            case GameCanvas.LEFT_PRESSED:
                naveAliada.mover(Personagem.ESQUERDA);
                break;
            case GameCanvas.FIRE_PRESSED:
                Tiro tiro = naveAliada.atirar();
                break;
        }

    }

    private void desenhar(Graphics g) {
       g.drawImage(fundo, 0, 0,0);
       lmng.paint(g,80, 0);
       flushGraphics();
    }

    public void inicarJogo() {
        jogando = true;
        thread.start(); 
    }

    public void pararJogo() {
        jogando = false;
    }

    public void run() {
        Graphics g = getGraphics();

        while (jogando) {

            acoesDoTeclado(g);
            desenhar(g);

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                System.err.println("Erro no delay da thread");
            }

        }
    }
}
