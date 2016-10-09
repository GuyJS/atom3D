package animation;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private Set<KeyCode> keyDownSet;

    public KeyEventHandler(){
        keyDownSet = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent event) {
        EventType<KeyEvent> type = event.getEventType();
        if(type.equals(KeyEvent.KEY_PRESSED)){
            keyDownSet.add(event.getCode());
        } else if (type.equals(KeyEvent.KEY_RELEASED)) {
            keyDownSet.remove(event.getCode());
        }
    }

    public boolean isKeyDown(KeyCode keyCode){
        return keyDownSet.contains(keyCode);

    }

}
