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
public class Inimigos {
    
    protected NaveInimiga[][] inimigos;
    
    protected int dir = Personagem.DIREITA;
    protected int colunas;
    protected int linhas;
    
    public Inimigos(String srcImagem, int colunas, int linhas) {
        inimigos = new NaveInimiga[linhas][colunas];
        this.colunas = colunas;
        this.linhas = linhas;
        
        for (int l = 0; l < linhas; l++){
            for(int c =0; c < colunas; c++){
                try {
                    inimigos[l][c] = new NaveInimiga(srcImagem);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        }
    }

    public int getColunas() {
        return colunas;
    }

    public int getLinhas() {
        return linhas;
    }
    
    
    public boolean moverMatriz(int direcao){
        for (int l = 0; l < inimigos.length; l++){
            for(int c =0; c < inimigos[0].length; c++){
                inimigos[l][c].mover(dir);
               // inimigos[l][c].mover(direcao);
            }
        }
        return true;
    }
    
    public NaveInimiga[][] getInimigos() {
        return inimigos;
    }
    
}
