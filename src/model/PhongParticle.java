package model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import ui.PhongSphere;
import ui.RandomUtilities;

import java.util.Random;

public class PhongParticle extends PhongSphere {

    private Context context;
    private Point3D velocity;

    public PhongParticle(Particle particle, int radius, Color diffuse, Color specular, Context context) {
        super(radius, diffuse, specular,
                RandomUtilities.nextDouble(100) - 50,
                RandomUtilities.nextDouble(-100),
                RandomUtilities.nextDouble(100) - 50);
        this.context = context;
        velocity = new Point3D(0, 0, 0);
    }

    @Override
    public void animate(long t) {
        velocity = velocity.multiply(0.995);
        setX((getTranslateX() + velocity.getX()));
        setY((getTranslateY() + velocity.getY()));
        setZ((getTranslateZ() + velocity.getZ()));
    }

    public void addVelocity(Point3D delta){
        velocity = velocity.add(delta);
    }

    public Point3D getVelocity(){
        return new Point3D(velocity.getX(), velocity.getY(), velocity.getZ());
    }

}
