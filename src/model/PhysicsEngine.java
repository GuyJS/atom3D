package model;

import animation.Animateable;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhysicsEngine implements Animateable {

    public static final double DAMPING_FACTOR = 0.995;
    public static final int MINIMUM_SPEED = 5;
    private int protonNum;
    private int neutronNum;
    private Context context;

    private PhongParticle[] protons;
    private PhongParticle[] neutrons;

    private AnimationTimer animationTimer;

    public PhysicsEngine(int protons, int neutrons, Context context){
        this.neutronNum = neutrons;
        this.protonNum = protons;
        this.context = context;
        initialiseAtom();
    }

    private void initialiseAtom(){
        protons = new PhongParticle[protonNum];
        neutrons = new PhongParticle[neutronNum];

        for(int i = 0 ; i < protonNum ; i++){
            PhongParticle proton = new PhongParticle(new Proton(), MINIMUM_SPEED, Color.RED, Color.ORANGE, context);
            proton.setDampingValues(DAMPING_FACTOR, MINIMUM_SPEED);
            protons[i] = proton;
        }
        for(int i = 0 ; i < neutronNum ; i++){
            PhongParticle neutron = new PhongParticle(new Neutron(), MINIMUM_SPEED, Color.BLUE, Color.LIGHTBLUE, context);
            neutron.setDampingValues(DAMPING_FACTOR, MINIMUM_SPEED);
            neutrons[i] = neutron;
        }
    }

    public List<PhongParticle> getAllParticles(){
        List<PhongParticle> particles = new ArrayList<>();
        particles.addAll(Arrays.asList(protons));
        particles.addAll(Arrays.asList(neutrons));
        return particles;
    }


    @Override
    public AnimationTimer getAnimationTimer() {
        if(animationTimer == null) {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    animate(now / 1000000);
                }
            };
        }
        return animationTimer;
    }

    @Override
    public void animate(long t) {
        for (PhongParticle proton : protons) {
            getAllParticles().forEach(particle -> proton.addVelocity(calculateDeltaV(proton, particle)));
        }
        for (PhongParticle neutron : neutrons) {
            getAllParticles().forEach(particle -> neutron.addVelocity(calculateDeltaV(neutron, particle)));
        }
    }

    public Point3D calculateDeltaV(PhongParticle subject, PhongParticle other){
        double separation = subject.getPos().distance(other.getPos());
        if(Math.abs(separation) == 0){
            return new Point3D(0, 0, 0);
        }
        boolean boost = false;
        if(Math.abs(separation) < 1){
            boost = true;
        }

        separation -= 20;
        double straightLineDeltaV;
        if(separation > 0){
            straightLineDeltaV = -1 * Math.sqrt(separation);
        } else {
            straightLineDeltaV = Math.sqrt(Math.abs(separation));
        }
        Point3D deltaV = subject.getPos().subtract(other.getPos());
        deltaV = deltaV.normalize();
        deltaV = deltaV.multiply(straightLineDeltaV/100);
        if(boost){
            deltaV = deltaV.multiply(100);
        }
        return deltaV;
    }
}
