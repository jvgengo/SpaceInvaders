/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cotuca.spaceinvaders.personagens;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author u12176
 */
public class NaveAliada extends Nave{

    public NaveAliada() {
        super();
    }
    
    public NaveAliada(String src) throws IOException {
        this.imagem = Image.createImage(src);
        this.sprite = new Sprite(imagem,16,16);
    }

    public Tiro atirar() {
        super.atirar(); 
        return null;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void mover(int direcao) {
        super.mover(direcao); 
        
        //sprite.nextFrame();
        sprite.setPosition(x, y);
    }
    
    
    
    
    
    
    
    
}
