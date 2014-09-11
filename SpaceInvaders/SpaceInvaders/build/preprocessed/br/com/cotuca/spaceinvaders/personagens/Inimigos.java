/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.personagens;

import br.com.cotuca.spaceinvaders.jogo.SpaceInvaders;
import br.com.cotuca.spaceinvaders.jogo.Tela;
import java.io.IOException;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author u12176
 *
 */
public class Inimigos {

    protected NaveInimiga[][] inimigos;
    protected int direcao = Personagem.DIREITA;
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

        int larguraDaTela = tela.getWidth();

        NaveInimiga naveMaisProximaDaDireita = null;
        NaveInimiga naveMaisProximaDaEsquerda = null;

        int maiorX = 0;
        int menorX = larguraDaTela;

        for (int l = 0; l < linhas; l++) {
            for (int i = 0; i < colunas; i++) {
                NaveInimiga naveAtual = inimigos[l][i];
                Sprite spriteDaNaveAtual = naveAtual.getSprite();
                int posicaoXDaNaveAtual = spriteDaNaveAtual.getX();
                if (naveAtual.isVisivel()) {
                    if (posicaoXDaNaveAtual > maiorX) {
                        maiorX = posicaoXDaNaveAtual;
                        naveMaisProximaDaDireita = naveAtual;
                    }

                    if (posicaoXDaNaveAtual < menorX) {
                        menorX = posicaoXDaNaveAtual;
                        naveMaisProximaDaEsquerda = naveAtual;

                    }
                }
            }
        }


        boolean posicaoMenorQueLimiteDaDireita = naveMaisProximaDaDireita.getSprite().getX()
                + naveMaisProximaDaDireita.getSprite().getWidth() < larguraDaTela;

        boolean posicaoMenorQueLimeteDaEsquerda = naveMaisProximaDaEsquerda.getSprite().getX() > 0;

        if (posicaoMenorQueLimiteDaDireita && posicaoMenorQueLimeteDaEsquerda) {

            mover(direcao);

        } else if (!posicaoMenorQueLimiteDaDireita) {

            direcao = Personagem.BAIXO;
            mover(direcao);

            direcao = Personagem.ESQUERDA;
            mover(direcao);

        } else if (!posicaoMenorQueLimeteDaEsquerda) {
            direcao = Personagem.BAIXO;
            mover(direcao);

            direcao = Personagem.DIREITA;
            mover(direcao);
        }

        return true;
    }

    private void mover(int direcaoParaSerMovido) {
        for (int l = 0; l < linhas; l++) {
            for (int i = 0; i < colunas; i++) {

                inimigos[l][i].mover(direcaoParaSerMovido);

            }
        }

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
