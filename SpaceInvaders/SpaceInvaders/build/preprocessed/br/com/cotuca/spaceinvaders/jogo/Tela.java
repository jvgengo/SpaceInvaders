/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import br.com.cotuca.spaceinvaders.personagens.*;
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
    private Tiro[] tiros;
    //Controle Lógico
    private int qtosPers = 0;
    private int qtosTiros = 0;
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
        tiros = new Tiro[5];
        try {
            naveAliada = new NaveAliada(Imagens.NAVE_ALIADA, largura / 2, altura - 60);
            inimigos = new Inimigos(Imagens.NAVE_INIMIGA, 8, 4, 0, 0);
            fundo = Image.createImage(Imagens.FUNDO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lmng = new LayerManager();
        lmng.insert(naveAliada.getSprite(), iLm);


        //capone n tenho certeza se eh a melhor forma de ser feito isso
        //preciso add cada nave no lmng
        NaveInimiga[][] navesInimigas = inimigos.getInimigos();

        for (int i = 0; i < inimigos.getLinhas(); i++) {
            for (int j = 0; j < inimigos.getColunas(); j++) {
                iLm += 1;
                lmng.insert(navesInimigas[i][j].getSprite(), iLm);
            }
        }

    }

    private void acoesDoTeclado(Graphics g) {

        int teclaClicada = getKeyStates();

//        Verificacao de cada acao nessa parte
        if (teclaClicada == GameCanvas.RIGHT_PRESSED) {
            naveAliada.mover(Personagem.DIREITA);
        }
        if (teclaClicada == GameCanvas.LEFT_PRESSED) {
            naveAliada.mover(Personagem.ESQUERDA);
        }
        if (teclaClicada == GameCanvas.UP_PRESSED) {
            Tiro tiro = null;

            //verificar o limite de tiros na tela
            if (qtosTiros < 5) {

                try {
                    tiro = naveAliada.atirar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lmng.insert(tiro.getTiro(), iLm);
                tiros[qtosTiros++] = tiro;
                               
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
            inimigos.moverMatriz(Personagem.BAIXO);
            for (int i = 0 ; i < qtosTiros; i++) {
               Tiro tAtual = tiros[i];
               tAtual.mover(Personagem.CIMA);
            }
            desenhar(g);

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                System.err.println("Erro no delay da thread");
            }

        }
    }
}
