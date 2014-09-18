/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import java.io.IOException;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author u12176
 */
public class Tiro extends Personagem {

    private Sprite tiro;
    public static final int VELOCIDADE = 10;
    
    
    public Tiro(String src,int x,int y) throws IOException {
        super(src,x,y);
        this.tiro = new Sprite(this.imagem, this.imagem.getWidth()/8, this.imagem.getHeight());
        this.tiro.setPosition(x, y);
    } 

    public boolean mover(int direcao) {
        if(direcao == Personagem.CIMA){
            tiro.move(0, -VELOCIDADE);
            tiro.nextFrame();
            return true;
        }
        return false;
    }

    public Sprite getTiro() {
        return tiro;
    }
    
    
}

