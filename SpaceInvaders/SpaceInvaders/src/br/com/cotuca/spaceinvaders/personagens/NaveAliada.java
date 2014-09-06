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
public class NaveAliada extends Nave {

    public NaveAliada(String src) throws IOException {
        super(src);
    }

    public Tiro atirar() {
        return null;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
