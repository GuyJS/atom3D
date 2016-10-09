package model;

public interface Atom {

    int getProtonNumber();

    int getNeutronNumber();

    int getElectronNumber();

    default int getCharge(){
        return getProtonNumber() - getElectronNumber();
    }

    default boolean isIon(){
        return getCharge() != 0;
    }

}
