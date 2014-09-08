/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import br.com.cotuca.spaceinvaders.Recursos.Imagens;
import java.io.IOException;

/**
 *
 * @author u12176
 */
public class NaveAliada extends Nave {

    public NaveAliada(String src, int x, int y) throws IOException {
        super(src,x,y,8);
    }

    public Tiro atirar() throws IOException {
        
        Tiro tiro = new Tiro(Imagens.TIRO, getSprite().getX(), getSprite().getY() - this.imagem.getHeight() - 20);
        
        
        return tiro;
    }

}
