package ui;

import animation.KeyEventHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Context;

public class Launcher extends Application {

    private KeyEventHandler keyHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context context = new Context();
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.DARKBLUE);
        Camera camera = new Camera(0, 1000, 70, context);
        scene.setCamera(camera.getCamera());

        PhongSphere sphere = new PhongSphere(5, Color.RED, Color.BLUE);


        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateZ(-1);
        light.setTranslateX(-2);

        GridFloor floor = new GridFloor(20, 20, 100, Color.WHITE, Color.BLACK);

        root.getChildren().addAll(sphere, light);
        root.getChildren().addAll(floor.getGrid());

        primaryStage.addEventHandler(KeyEvent.ANY, context.getKeyHandler());

        primaryStage.setScene(scene);
        primaryStage.show();

        sphere.startAnimating();
        camera.startAnimating();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
