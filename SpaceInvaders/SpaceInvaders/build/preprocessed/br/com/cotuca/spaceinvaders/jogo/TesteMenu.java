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
import javax.microedition.midlet.*;

/**
 * @author u12165
 */
public class TesteMenu extends MIDlet {

    private Display display;
    private Command cmdExit;
    private Menu menu;
    
    public TesteMenu(){
        display = Display.getDisplay(this);
        menu = new Menu();
        cmdExit = new Command("Sair", Command.EXIT, 0);
        menu.addCommand(cmdExit);
        menu.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                if (c == cmdExit){
                    System.gc();
                    destroyApp(false);
                    notifyDestroyed();
                }
            }
        });
    }
    
    public void startApp() {
        if (menu != null){
            display.setCurrent(menu);
            menu.iniciar();
        }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
