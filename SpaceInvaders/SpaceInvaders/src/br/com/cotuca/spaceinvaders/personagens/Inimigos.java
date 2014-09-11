/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import br.com.cotuca.spaceinvaders.jogo.SpaceInvaders;
import br.com.cotuca.spaceinvaders.jogo.Tela;
import java.io.IOException;

/**
 *
 * @author u12176
 *
 */
public class Inimigos {

    protected NaveInimiga[] inimigos;

    protected int dir = Personagem.BAIXO;
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
        int step = 0;
        for (int l = 0; l < linhas * colunas; l++) {
            try {
                //arrumar posicao dos inimigos que esta errado ainda
                System.out.println("Instancia" + (l));
                
                //Capone o erro acho que esta na criacao das posicoes
                //Teste com um cara apenas na 10 10 e ta funcionando normal
                
//                inimigos[l] = new NaveInimiga(srcImagem, ((l % colunas) * SpaceInvaders.lSprite) + x,
//                        (l * SpaceInvaders.aSprite) + y);
                inimigos[l] = new NaveInimiga(srcImagem,step + SpaceInvaders.lSprite , SpaceInvaders.aSprite);
                step += 20;
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

        }
    }

    public int getColunas() {
        return colunas;
    }

    public int getLinhas() {
        return linhas;
    }

    public boolean moverMatriz(Tela tela) {

        for (int l = 0; l < linhas * colunas; l++) {

            NaveInimiga n = inimigos[l];
            n.mover(dir);
            // inimigos[l][c].mover(direcao);
        }
        return true;
    }

    public NaveInimiga[] getInimigos() {
        return inimigos;
    }

}
