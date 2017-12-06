package ru.anton.snakeAI.neuralModel;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

import java.util.Arrays;

public class TrainingData {
    private int[][] field;
    private KeyCode currentButton;

    public TrainingData(Snake snake, Field field, KeyCode currentButton){
        this.currentButton = currentButton;
        prepareData(snake, field);
    }

    private void prepareData(Snake snake, Field field){
        this.field = new int[field.getSize()][field.getSize()];
        int[] food = field.getFood();
        this.field[food[0]][food[1]] = 3;
        snake.getElems().forEach(e-> this.field[e[0]][e[1]] = 1);
        this.field[snake.getHead()[0]][snake.getHead()[1]] = 2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] aField : field) {
            for (int anAField : aField) {
                builder.append(anAField);
                builder.append(" ");
            }
        }
        builder.append(System.lineSeparator());
        builder.append(currentButton.ordinal());
        return builder.toString();
    }

    public int getSetSize(){
        return field.length*field[0].length;
    }

}
