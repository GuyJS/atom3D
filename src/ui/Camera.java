package ui;

import animation.Animateable;
import animation.KeyEventHandler;
import animation.NodeMovementCalculator;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Context;

public class Camera extends Group implements Animateable {

    private static final int MOVEMENT_SPEED = 5;
    private static final int ROTATION_SPEED = 2;
    public static final double DEGREES_TO_RADIANS = Math.PI / 180;
    public static final int DEGREES_IN_CIRCLE = 360;
    private int nearClip;
    private int farClip;
    private int fov;
    private AnimationTimer animationTimer;
    private Context context;
    private KeyEventHandler keyHandler;
    private NodeMovementCalculator movement;

    PerspectiveCamera camera;

    public Camera(int near, int far, int fov, Context context){
        nearClip = near;
        farClip = far;
        this.fov = fov;
        this.context = context;
        this.keyHandler = context.getKeyHandler();
        initialiseAnimationTimer();
        camera = setUpCamera();
        this.getChildren().add(camera);
        movement = new NodeMovementCalculator(camera);
    }

    private void initialiseAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate(now/1000000);
            }
        };
    }

    private PerspectiveCamera setUpCamera(){
        PerspectiveCamera cam = new PerspectiveCamera(true);
        cam.setNearClip(nearClip);
        cam.setFarClip(farClip);
        cam.setFieldOfView(fov);
        cam.setRotationAxis(new Point3D(0, -1, 0));
        return cam;
    }

    public PerspectiveCamera getCamera(){
        return camera;
    }

    @Override
    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    @Override
    public void animate(long t) {
        if( keyHandler.isKeyDown(KeyCode.UP) ){
            movement.moveForward(MOVEMENT_SPEED);
        }
        if( keyHandler.isKeyDown(KeyCode.DOWN) ){
            movement.moveBack(MOVEMENT_SPEED);
        }
        if( keyHandler.isKeyDown(KeyCode.LEFT)){
            movement.rotateACW(ROTATION_SPEED);
        }
        if( keyHandler.isKeyDown(KeyCode.RIGHT)){
            movement.rotateCW(ROTATION_SPEED);
        }
    }
}
