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
public class Nave extends Personagem {

    protected Sprite sprite;
    protected static final int VELOCIDADE = 5;
    protected int divisor = 1; //variavel que dividi o sprite
    
    
    public Nave(String src, int x,int y, int div) throws IOException {
        super(src,x,y);
        divisor = div;
        //arrumar a dimnesao dos sprites, ta uma bosta pq n temos os sprites certos ainda
        this.sprite = new Sprite(this.imagem,this.imagem.getWidth()/divisor,this.imagem.getHeight());
        this.sprite.setPosition(x, y);

    }

    public boolean mover(int direcao) {
        switch (direcao) {
            case Personagem.DIREITA:
                this.sprite.move(VELOCIDADE, 0);
                this.sprite.nextFrame();
                return true;
            case Personagem.ESQUERDA:
                this.sprite.move(-VELOCIDADE, 0);
                this.sprite.nextFrame();
                return true;
        }
        return false;
    }
    
    public Sprite getSprite() {
        return sprite;
    }

}
