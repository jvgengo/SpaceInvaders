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

    protected NaveInimiga[][] inimigos;
    protected int dir = Personagem.BAIXO;
    protected int colunas;
    protected int linhas;
    protected int x;
    protected int y;

    public Inimigos(String srcImagem, int colunas, int linhas, int x, int y) {
        inimigos = new NaveInimiga[linhas][colunas];
        this.colunas = colunas;
        this.linhas = linhas;
        this.x = x;
        this.y = y;
        int stepParaLinha = 0;
        int stepParaColuna = 0;

        for (int indiceDaLinha = 0; indiceDaLinha < linhas; indiceDaLinha++) {
            for (int indiceDaColuna = 0; indiceDaColuna < colunas; indiceDaColuna++) {
                try {
                    inimigos[indiceDaLinha][indiceDaColuna] = new NaveInimiga(srcImagem, stepParaColuna + SpaceInvaders.lSprite, stepParaLinha + SpaceInvaders.aSprite);
                    stepParaColuna += 30;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }

            }
            stepParaColuna = 0;
            stepParaLinha += 30;

        }
    }

    public int getColunas() {
        return colunas;
    }

    public int getLinhas() {
        return linhas;
    }

    public boolean moverMatriz(Tela tela) {

        for (int l = 0; l < linhas; l++) {
            for (int i = 0; i < colunas; i++) {
                NaveInimiga n = inimigos[l][i];
                n.mover(dir);

            }
            // inimigos[l][c].mover(direcao);
        }
        return true;
    }

    public NaveInimiga[] getInimigos() {

        NaveInimiga[] retorno = new NaveInimiga[linhas * colunas];
        int indiceDoRetorno = 0;
        for (int l = 0; l < linhas; l++) {
            for (int i = 0; i < colunas; i++) {
                NaveInimiga n = inimigos[l][i];
                retorno[indiceDoRetorno] = n;
                indiceDoRetorno++;
            }
        }
        return retorno;
    }
}
