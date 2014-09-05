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
public class Nave extends Personagem{

    protected Sprite sprite;

    public Nave(String src) throws IOException {
        super(src);
        this.sprite = new Sprite(this.imagem, 16, 16);
    }


    public void mover(int direcao) {
            switch (direcao) {
            case Personagem.DIREITA:
                x += 5;
                break;
            case Personagem.ESQUERDA:
                x -= 5;
                break;
        }
    }
   
      
    
}
