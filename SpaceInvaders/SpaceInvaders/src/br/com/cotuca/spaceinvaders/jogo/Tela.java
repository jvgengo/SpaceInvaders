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
    
    //Mudar Pra lista (ou nem?)
    private ArrayList<Personagem> personagens;

    public static final int DELAY = 40;

    public Tela() {
        super(true);
        altura = getHeight();
        largura = getWidth();

        jogando = false;
        thread = new Thread(this);
        personagens = new ArrayList<>();
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
        //Jão seu Zé Mané! E se eu apertar duas ao mesmo tempo? Ele vai dar break e tratar só a primeira, vou mudar pra if
        if ( teclaClicada == GameCanvas.RIGHT_PRESSED){
            naveAliada.mover(Personagem.DIREITA);
        }
        if ( teclaClicada == GameCanvas.LEFT_PRESSED){
            naveAliada.mover(Personagem.ESQUERDA);
        }
        if ( teclaClicada == GameCanvas.FIRE_PRESSED{
            Tiro tiro = naveAliada.atirar();
            //E Adiciona na lista de entidades
            personagens.add(tiro);
            
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
