package ru.anton.snakeAI.gamers;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

public class NeuralGamer extends AbstractGamer{

    public NeuralGamer(Field field, Snake snake) {
        super(field, snake);
    }

    @Override
    public void init() {

    }

    @Override
    public KeyCode game() {
        return null;
    }
}
