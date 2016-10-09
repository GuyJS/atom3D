package model;

import animation.KeyEventHandler;

public class Context {

    private KeyEventHandler keyHandler;

    public Context(){
        keyHandler = new KeyEventHandler();
    }


    public KeyEventHandler getKeyHandler() {
        return keyHandler;
    }

}
