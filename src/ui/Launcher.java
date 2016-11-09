package ui;

import animation.Animateable;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Context;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Context context = new Context(20, 10);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 600, true);
        Camera camera = new Camera(1, 3000, 70, context);
        Group group3D = new Group();
        SubScene subScene = new SubScene(group3D, 600, 600);
        subScene.setFill(Color.DARKBLUE);
        subScene.setCamera(camera.getCamera());

        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateZ(-1);
        light.setTranslateX(-2);
        light.setTranslateY(-5);

        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.setTranslateY(-5);
        ambientLight.setTranslateX(150);
        ambientLight.setTranslateZ(150);

        GridFloor floor = new GridFloor(10, 10, 200, Color.WHITE, Color.BLACK);

        group3D.getChildren().addAll(floor.getGrid());
        group3D.getChildren().addAll(light, ambientLight);
        context.getPhysics().getAllParticles().forEach(particle -> group3D.getChildren().add(particle));

        primaryStage.addEventHandler(KeyEvent.ANY, context.getKeyHandler());

        VBox menu = new VBox(5.0);
        menu.setPrefSize(200, 600);
        menu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        borderPane.setCenter(subScene);
        borderPane.setLeft(menu);
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
