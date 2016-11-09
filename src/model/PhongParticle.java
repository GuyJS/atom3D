package model;

import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.paint.Color;
import ui.PhongSphere;
import ui.RandomUtilities;

import java.util.Random;

public class PhongParticle extends PhongSphere {

    private Context context;
    private Point3D velocity;
    private double dampingFactor = 1;
    private double minimumDampingSpeed = 1000;

    public PhongParticle(Particle particle, int radius, Color diffuse, Color specular, Context context) {
        super(radius, diffuse, specular,
                RandomUtilities.nextDouble(100) - 50,
                RandomUtilities.nextDouble(-100),
                RandomUtilities.nextDouble(100) - 50);
        this.context = context;
        velocity = new Point3D(0, 0, 0);
        setDepthTest(DepthTest.ENABLE);
    }

    public void setDampingValues(double factor, double minimumSpeed){
        this.dampingFactor = factor;
        this.minimumDampingSpeed = minimumSpeed;
    }

    @Override
    public void animate(long t) {
        damp();
        setX((getTranslateX() + velocity.getX()));
        setY((getTranslateY() + velocity.getY()));
        setZ((getTranslateZ() + velocity.getZ()));
    }

    private void damp(){
        if(velocity.magnitude() > minimumDampingSpeed){
            velocity = velocity.multiply(dampingFactor);
        }
    }


    public void addVelocity(Point3D delta){
        velocity = velocity.add(delta);
    }

    public Point3D getVelocity(){
        return new Point3D(velocity.getX(), velocity.getY(), velocity.getZ());
    }

}
