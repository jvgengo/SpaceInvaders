/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import br.com.cotuca.spaceinvaders.personagens.*;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

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
    private Tiro[] tiros;
    private NaveInimiga[] nInimigas;
    //Controle Lógico
    private int qtosPers = 0;
    private int qtosTiros = 0;
    private int qtosInimigos = 0;
    public static final int DELAY = 40;
    //indice do LayerManger
    private int iLm = 0;

    public Tela() {
        super(true);
        altura = getHeight();
        largura = getWidth();
        
        jogando = false;
        thread = new Thread(this);
        //Aumentar o tamanho Max
        personagens = new Personagem[20];
        tiros = new Tiro[3];
        try {
            naveAliada = new NaveAliada(Imagens.NAVE_ALIADA, largura / 2, altura - 60);
            inimigos = new Inimigos(Imagens.NAVE_INIMIGA, 8,4, 0, 0);
            fundo = Image.createImage(Imagens.FUNDO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lmng = new LayerManager();
        lmng.insert(naveAliada.getSprite(), iLm);


        //capone n tenho certeza se eh a melhor forma de ser feito isso
        //preciso add cada nave no lmng
        nInimigas = inimigos.getInimigos();
        qtosInimigos = nInimigas.length;

        for (int i = 0; i < inimigos.getLinhas(); i++) {
            iLm += 1;
            lmng.insert(nInimigas[i].getSprite(), iLm);
        }

    }

    private void acoesDoTeclado(Graphics g) {

        int teclaClicada = getKeyStates();
        int xNave = naveAliada.getSprite().getX();

//        Verificacao de cada acao nessa parte
        if (teclaClicada == GameCanvas.RIGHT_PRESSED) {
            //limite da tela de x ==> 0 ==> largura
            if (xNave < largura - naveAliada.getSprite().getWidth()) {
                naveAliada.mover(Personagem.DIREITA);
            }
        }
        if (teclaClicada == GameCanvas.LEFT_PRESSED) {
            if (xNave > 0) {
                naveAliada.mover(Personagem.ESQUERDA);
            }
        }
        if (teclaClicada == GameCanvas.UP_PRESSED) {
            Tiro tiro = null;

            //verificar o limite de tiros na tela
            if (qtosTiros < 3) {

                try {
                    tiro = naveAliada.atirar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lmng.insert(tiro.getTiro(), iLm);
                tiros[qtosTiros] = tiro;
                qtosTiros++;

                //E Adiciona na lista de entidades

                //Capone vou criar uma lista de tiros, mas depois conversamos sobre essa lista de personagens
                //personagens[qtosPers++] = tiro;
            }

        }

    }

    private void desenhar(Graphics g) {
        g.drawImage(fundo, 0, 0, 0);
        lmng.paint(g, 0, 0);
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
            
            inimigos.moverMatriz(this);
            for (int i = 0; i < qtosTiros; i++) {
                Tiro tAtual = tiros[i];
                
                //for para verificar colisao com os inimigos
                for (int j = 0; j < qtosInimigos; j++) {
                    NaveInimiga nAtual = nInimigas[j];
                    
                    Sprite t = tAtual.getTiro();
                    Sprite n = nAtual.getSprite();
                    
                    //verifica colisao
                    if (t.collidesWith(n, true)) {
                        removerInimigo(nAtual, j);
                        removerTiro(tAtual, i);
                    }
                    
                }
                
                // verificar limite da tela, caso o tiro passe excluir esse tiro
                int yTiro = tAtual.getTiro().getY();

                if (yTiro >= 0) {
                    tAtual.mover(Personagem.CIMA);
                } else {
                    removerTiro(tAtual, i);
                }
            }
            
            //verificar colisao com naveInimiga/naveAliada
            for (int i = 0; i < qtosInimigos; i++) {
                NaveInimiga nAtual = nInimigas[i];
                
                Sprite n = nAtual.getSprite();
                Sprite aliada = naveAliada.getSprite();
                
                if (n.collidesWith(aliada, true)) {
                    //acabou o jogo
                    pararJogo();
                }
            }
            
            desenhar(g);

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                System.err.println("Erro no delay da thread");
            }

        }
    }

    private void removerTiro(Tiro tiro, int i) {
        for (int j = i; j < qtosTiros - 1; j++) {
            tiros[j] = tiros[j + 1];
        }
        qtosTiros--;
        lmng.remove(tiro.getTiro());
    }

    private void removerInimigo(NaveInimiga nave, int i) {
        for (int j = i; j < qtosInimigos - 1; j++) {
            nInimigas[j] = nInimigas[j + 1];
        }
        qtosInimigos--;
        lmng.remove(nave.getSprite());
    }
}
