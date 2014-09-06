/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author u12176
 */
public abstract class Personagem {

    protected int x;
    protected int y;
    protected Image imagem;
    //direcoes
    public static final int DIREITA = 0;
    public static final int ESQUERDA = 1;
    public static final int CIMA = 2;
    public static final int BAIXO = 3;

    public Personagem(String src) throws IOException {
        this.imagem = Image.createImage(src);
    }

    public Personagem(String src,int x, int y) throws IOException {
        this.imagem = Image.createImage(src);
        this.x = x;
        this.y = y;
    }

    public abstract boolean mover(int direcao);
}
