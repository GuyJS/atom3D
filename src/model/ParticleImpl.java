package model;

public abstract class ParticleImpl implements Particle {


    private final double mass;
    private final double charge;
    private final double radius;
    
    public ParticleImpl(double mass, double charge, double radius){
        this.mass = mass;
        this.charge = charge;
        this.radius = radius;
    }


    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public double getCharge() {
        return charge;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
