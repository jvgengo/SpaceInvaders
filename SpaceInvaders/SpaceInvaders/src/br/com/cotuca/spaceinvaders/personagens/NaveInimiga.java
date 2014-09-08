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

    protected static final int VELOCIADADE_BAIXO = 1;
    
    public NaveInimiga(String src,int x,int y) throws IOException {
        super(src,x,y,2);
    }

    public boolean mover(int direcao) {
        if(super.mover(direcao)){
            return true;
        }
        if(direcao == Personagem.BAIXO){
            this.sprite.move(0, VELOCIADADE_BAIXO);
            return true;
        }
        return false;
    }
    
    

}
