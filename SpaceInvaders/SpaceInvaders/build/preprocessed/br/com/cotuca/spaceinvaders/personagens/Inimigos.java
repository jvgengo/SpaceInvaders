/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import br.com.cotuca.spaceinvaders.jogo.SpaceInvaders;
<<<<<<< HEAD
=======
import br.com.cotuca.spaceinvaders.jogo.Tela;
>>>>>>> vgcapone
import java.io.IOException;

/**
 *
 * @author u12176
<<<<<<< HEAD
 * 
 */
public class Inimigos {
    
    protected NaveInimiga[] inimigos;
    
    protected int dir = Personagem.DIREITA;
    protected int colunas;
    protected int linhas;
    
    public Inimigos(String srcImagem, int colunas, int linhas,int x, int y) {
        inimigos = new NaveInimiga[linhas*colunas];
        this.colunas = colunas;
        this.linhas = linhas;
        
        for (int l = 0; l < linhas; l++){
            for(int c =0; c < colunas; c++){
                try {
                    //arrumar posicao dos inimigos que esta errado ainda
                    inimigos[l+c] = new NaveInimiga(srcImagem,(l*SpaceInvaders.lSprite)+x,
                            (c*SpaceInvaders.aSprite)+y);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
=======
 *
 */
public class Inimigos {

    protected NaveInimiga[] inimigos;

    protected int dir = Personagem.DIREITA;
    protected int colunas;
    protected int linhas;
    protected int x;
    protected int y;

    public Inimigos(String srcImagem, int colunas, int linhas, int x, int y) {
        inimigos = new NaveInimiga[linhas * colunas];
        this.colunas = colunas;
        this.linhas = linhas;
        this.x = x;
        this.y = y;
        for (int l = 0; l < linhas * colunas; l++) {
            try {
                //arrumar posicao dos inimigos que esta errado ainda
                System.out.println("Instancia" + (l));
                inimigos[l] = new NaveInimiga(srcImagem, ((l % colunas) * SpaceInvaders.lSprite) + x,
                        (l * SpaceInvaders.aSprite) + y);
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
>>>>>>> vgcapone
            }

        }
    }

    public int getColunas() {
        return colunas;
    }

    public int getLinhas() {
        return linhas;
    }
<<<<<<< HEAD
    
    
    public boolean moverMatriz(int direcao){
        for (int l = 0; l < linhas; l++){
            for(int c =0; c < colunas; c++){
                inimigos[l+c].mover(dir);
               // inimigos[l][c].mover(direcao);
            }
        }
        return true;
    }
    
=======

    public boolean moverMatriz(Tela tela) {

        for (int l = 0; l < linhas * colunas; l++) {

            NaveInimiga n = inimigos[l];
            n.mover(dir);
            // inimigos[l][c].mover(direcao);
        }
        return true;
    }

>>>>>>> vgcapone
    public NaveInimiga[] getInimigos() {
        return inimigos;
    }

}
