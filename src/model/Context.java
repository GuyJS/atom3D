package model;

import animation.KeyEventHandler;

public class Context {

    private KeyEventHandler keyHandler;
    private PhysicsEngine physics;

    public Context(int protons, int neutrons){
        keyHandler = new KeyEventHandler();
        physics = new PhysicsEngine(protons, neutrons, this);
    }


    public KeyEventHandler getKeyHandler() {
        return keyHandler;
    }

    public PhysicsEngine getPhysics(){
        return physics;
    }

}
