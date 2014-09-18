/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import java.io.IOException;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;

/**
 *
 * @author u12165
 */
public class Menu extends GameCanvas implements Runnable {

    private final String[] opcoes = {"Jogar", "Instrucoes", "Sair"};
    private GameCanvas[] telas;
    private final int JOGAR = 0;
    private final int INSTRUCOES = 1;
    private final int SAIR = 2;
    private final int DELAY = 40;
    private Image fundo;
    private int numOpcoes, altura, largura;
    private Thread thread;
    private boolean saiMenu;
    private int indiceOpcao;
    private Tela telaJogo;
    private LayerManager lm;
    private Display display;

    public Menu(Display d) {
        super(true);
        this.display = d;
        indiceOpcao = 0;
        numOpcoes = opcoes.length;
        altura = getHeight();
        largura = getWidth();
        telas = new GameCanvas[2];//instruções e tela do jogo

        lm = new LayerManager();
        saiMenu = false;
        thread = new Thread(this);
        try {
            fundo = Image.createImage(Imagens.FUNDO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void acoesTeclado() {
        int tecla = getKeyStates();
        //tecla para baixo
        if (tecla == GameCanvas.DOWN_PRESSED && indiceOpcao < numOpcoes - 1) {
            indiceOpcao++;
        }
        //tecla para cima
        if (tecla == GameCanvas.UP_PRESSED && indiceOpcao > 0) {
            indiceOpcao--;
        }

        if (tecla == GameCanvas.FIRE_PRESSED) {
            saiMenu = true;
            if (indiceOpcao == this.JOGAR) {
                telaJogo = new Tela(this);
                display.setCurrent(telaJogo);
                telaJogo.inicarJogo();
                
            }

        }
        try {
            Thread.sleep(DELAY*2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void iniciar() {
        saiMenu = false;
        thread.start();
    }

    private void desenhar(Graphics g) {
        g.drawImage(fundo, 0, 0, 0);
        g.setColor(255, 255, 255);
        for (int i = 0; i < numOpcoes; i++) {
            g.drawString(opcoes[i],
                    largura / 2 - opcoes[i].length()*3,
                    altura / 2 + i * 20,
                    0);
        }
        //opcaoSelecionada
        g.setColor(0, 255, 0);
        g.drawString(opcoes[indiceOpcao],
                largura / 2 - opcoes[indiceOpcao].length()*3,
                altura / 2 + indiceOpcao * 20,
                0);

        lm.paint(g, 0, 0);
        flushGraphics();
    }
    
    public void voltaMenu(){
        display.setCurrent(this);
        thread = new Thread(this);
        iniciar();
    }

    public void run() {
        Graphics g = getGraphics();
        while (!saiMenu) {
            acoesTeclado();
            desenhar(g);
        }

    }

}
