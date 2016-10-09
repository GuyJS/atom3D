package model;

public class AtomImpl implements Atom {

    private int protons;
    private int neutrons;
    private int electrons;

    public AtomImpl(int protons, int electrons, int neutrons){
        this.protons = protons;
        this.electrons = electrons;
        this.neutrons = neutrons;
    }

    @Override
    public int getProtonNumber() {
        return protons;
    }

    @Override
    public int getNeutronNumber() {
        return neutrons;
    }

    @Override
    public int getElectronNumber() {
        return electrons;
    }
}
