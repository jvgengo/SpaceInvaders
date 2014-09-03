/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotuca.spaceinvaders.jogo;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author u12176
 */
public class Main extends MIDlet implements CommandListener {

    private Display display;
    private Command cmdExit;
    private Tela tela;

    Main() throws Exception {
        display = Display.getDisplay(this);
        
        tela = new Tela();
        cmdExit = new Command("sair", Command.EXIT, 0);
        tela.addCommand(cmdExit);
        tela.setCommandListener(this);

    }

    public void startApp() {

        if (tela != null) {
            
            display.setCurrent(tela);
            tela.inicarJogo();
        }

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit) {
            System.gc();  
            destroyApp(false);
            notifyDestroyed();
        }
    }
}
