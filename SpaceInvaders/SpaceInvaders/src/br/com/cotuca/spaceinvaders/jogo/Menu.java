/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author u12165
 */
public class Menu extends GameCanvas implements Runnable{

    private final String[] opcoes = {"Jogar", "Instrucoes", "Sair"};
    private final int JOGAR = 0;
    private final int INSTRUCOES = 1;
    private final int SAIR = 2;
    private Image fundo;
    private int numOpcoes, altura, largura;
    private Thread thread;
    private boolean saiMenu;
    private int indiceOpcao;
    private Tela telaJogo;

    public Menu() {
        super(true);
        indiceOpcao = 0;
        numOpcoes = opcoes.length;
        altura = getHeight();
        largura = getWidth();
        
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
        if (tecla == GameCanvas.DOWN_PRESSED && indiceOpcao > 0) {
            indiceOpcao++;
        }
        //tecla para cima
        if (tecla == GameCanvas.UP_PRESSED && indiceOpcao < numOpcoes) {
            indiceOpcao--;
        }
        
        if (tecla == GameCanvas.FIRE_PRESSED){
            saiMenu = true;
            if (indiceOpcao == this.JOGAR){
                telaJogo = new Tela();
            }
            
        }
    }
    
    
    public void iniciar(){
        thread.start();
    }

    private void desenhar(Graphics g) {
        g.drawImage(fundo, 0, 0, 0);
        g.setColor(255,255,255);
        for (int i = 0; i < numOpcoes; i++){
            g.drawString(opcoes[i], 
                         largura/2-opcoes[i].length(), 
                         altura/2+i*20,
                         0);
        }
        //opcaoSelecionada
        g.setColor(0,255,0);
        g.drawString(opcoes[indiceOpcao], 
                     largura/2-opcoes[indiceOpcao].length(), 
                     altura/2+indiceOpcao*20,
                     0);
        
        flushGraphics();
    }

    public void run() {
        Graphics g = getGraphics();
        while (saiMenu){
            acoesTeclado();
            desenhar(g);
        }
    }

}
