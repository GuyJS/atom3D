package ui;

import animation.Animateable;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Context;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context context = new Context(30, 20);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.DARKBLUE);
        Camera camera = new Camera(0, 1000, 70, context);
        scene.setCamera(camera.getCamera());

        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateZ(-1);
        light.setTranslateX(-2);
        light.setTranslateY(-5);

        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.setTranslateY(-5);
        ambientLight.setTranslateX(150);
        ambientLight.setTranslateZ(150);

//        GridFloor floor = new GridFloor(20, 20, 100, Color.WHITE, Color.BLACK);

//        root.getChildren().addAll(floor.getGrid());
        root.getChildren().addAll(light, ambientLight);
        context.getPhysics().getAllParticles().forEach(particle -> root.getChildren().add(particle));

        primaryStage.addEventHandler(KeyEvent.ANY, context.getKeyHandler());

        primaryStage.setScene(scene);
        primaryStage.show();

        camera.startAnimating();
        context.getPhysics().startAnimating();
        context.getPhysics().getAllParticles().forEach(Animateable::startAnimating);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
