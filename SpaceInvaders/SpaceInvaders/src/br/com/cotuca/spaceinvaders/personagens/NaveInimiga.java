/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cotuca.spaceinvaders.personagens;

import java.io.IOException;

/**
 *
 * @author u12176
 */
public class NaveInimiga extends Nave{

    protected static final int VELOCIDADE_BAIXO = 10;
    private boolean visivel;
    
    public NaveInimiga(String src,int x,int y) throws IOException {
        super(src,x,y,2);
        visivel = true;
    }
    
    public boolean isVisivel(){
        return visivel;
    }
    
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
    public boolean mover(int direcao) {
        if(super.mover(direcao)){
            return true;
        }
        if(direcao == Personagem.BAIXO){
            this.sprite.move(0, VELOCIDADE_BAIXO);
            this.sprite.nextFrame();
            return true;
        }
        return false;
    }
    
    

}
