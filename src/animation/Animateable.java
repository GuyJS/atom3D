package animation;

import javafx.animation.AnimationTimer;

public interface Animateable {

    AnimationTimer getAnimationTimer();

    default void startAnimating(){
        getAnimationTimer().start();
    }

    default void stopAnimating(){
        getAnimationTimer().stop();
    }

    void animate(long t);
}
