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
 * @author u12176
 */
public class SpaceInvaders extends MIDlet implements CommandListener{

    private Display display;
    private Command cmdExit;
    private Menu coreGame;
    public static final int lSprite = 16;
    public static final int aSprite = 8;
    
    SpaceInvaders(){
        display = Display.getDisplay(this);
        
        coreGame = new Menu(this.display);
        cmdExit = new Command("Sair", Command.EXIT, 0);
        coreGame.addCommand(cmdExit);
        coreGame.setCommandListener(this);
    }
    
    public void startApp() {
        if (coreGame != null){
            display.setCurrent(coreGame);
             coreGame.iniciar();
        }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit){
            System.gc();
            destroyApp(false);
            notifyDestroyed();
        }
    }
}
