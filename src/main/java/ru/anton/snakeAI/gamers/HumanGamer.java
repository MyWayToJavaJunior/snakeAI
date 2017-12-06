package ru.anton.snakeAI.gamers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

public class HumanGamer extends AbstractGamer {
    private Node node;
    private ObjectProperty<KeyCode> keyCodeProperty = new SimpleObjectProperty<>();

    public HumanGamer(Field field, Snake snake, Node node) {
        super(field, snake);
        this.node = node;
        keyCodeProperty.setValue(KeyCode.DOWN);
    }

    @Override
    public void init(){
        node.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            keyCodeProperty.setValue(event.getCode());
        });
    }

    @Override
    public KeyCode game() {
        return keyCodeProperty.getValue();
    }

    public ObjectProperty<KeyCode> getKeyCodeProperty(){
        return keyCodeProperty;
    }
}
