package ru.anton.snakeAI.gamers;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

public class HumanGamer extends AbstractGamer {
    private Node node;
    private KeyCode currentKeyCode;

    public HumanGamer(Field field, Snake snake, Node node) {
        super(field, snake);
        this.node = node;
        currentKeyCode = KeyCode.DOWN;
    }

    @Override
    public void init(){
        node.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            currentKeyCode = event.getCode();
        });
    }

    @Override
    public KeyCode game() {
        return currentKeyCode;
    }
}
