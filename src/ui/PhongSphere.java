package ui;

import animation.Animateable;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class PhongSphere extends Sphere implements Animateable {

    public static final int ANIMATION_PERIOD = 1500;
    private PhongMaterial sphereMaterial;
    private Color diffuseColour;
    private Color specularColour;
    private double x;
    private double y;
    private double z;
    private AnimationTimer animator;

    public PhongSphere(int radius, Color diffuse, Color specular){
        this(radius, diffuse, specular, 0, 0, 15);
    }

    public PhongSphere(int radius, Color diffuse, Color specular, double x, double y, double z){
        super(radius);
        this.diffuseColour = diffuse;
        this.specularColour = specular;
        this.x = x;
        this.y = y;
        this.z = z;
        sphereMaterial = new PhongMaterial(diffuseColour);
        sphereMaterial.setSpecularColor(specularColour);
        setMaterial(sphereMaterial);
        updatePosition();
        initialiseAnimator();
    }

    private void updatePosition(){
        setTranslateX(x);
        setTranslateY(y);
        setTranslateZ(z);
    }

    public void setX(double x) {
        this.x = x;
        updatePosition();
    }

    public void setY(double y) {
        this.y = y;
        updatePosition();
    }

    public void setZ(double z) {
        this.z = z;
        updatePosition();
    }

    public void setPos(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        updatePosition();
    }

    public Point3D getPos(){
        return new Point3D(x, y, z);
    }

    @Override
    public AnimationTimer getAnimationTimer() {
        if (animator == null) {
            initialiseAnimator();
        }
        return animator;
    }

    private void initialiseAnimator() {
        animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate(now / 1000000);
            }
        };
    }

    public void animate(long t) {
        setPos( 100 * Math.cos((2*Math.PI * (t % ANIMATION_PERIOD)) / ANIMATION_PERIOD),
                0,
                100 * Math.sin((2*Math.PI * (t % ANIMATION_PERIOD)) / ANIMATION_PERIOD)
        );
    }
}
