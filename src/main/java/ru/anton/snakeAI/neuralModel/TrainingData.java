package ru.anton.snakeAI.neuralModel;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;


public class TrainingData {
    private int[][] field;
    private KeyCode currentButton;

    public TrainingData(Snake snake, Field field, KeyCode currentButton) {
        this.currentButton = currentButton;
        this.field = prepareData(snake, field);
    }

    public static int[][] prepareData(Snake snake, Field field) {
        int[][] result = new int[field.getSize()][field.getSize()];
        int[] food = field.getFood();
        result[food[0]][food[1]] = 3;
        snake.getElems().forEach(e -> result[e[0]][e[1]] = 1);
        result[snake.getHead()[0]][snake.getHead()[1]] = 2;
        return result;
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
        builder.append(getButtonDataSet(currentButton));
        return builder.toString();
    }

    public int getSetSize() {
        return field.length * field[0].length;
    }

    public static String getButtonDataSet(KeyCode keyCode) {
        String result = "1 0 0 0";
        switch (keyCode) {
            case UP:
                result = "1 0 0 0";
                break;
            case RIGHT:
                result = "0 1 0 0";
                break;
            case DOWN:
                result = "0 0 1 0";
                break;
            case LEFT:
                result = "0 0 0 1";
        }
        return result;
    }

    public static KeyCode getKeyCode(int maxPosition) {
        KeyCode result = KeyCode.UP;
        switch (maxPosition) {
            case 0:
                result = KeyCode.UP;
                break;
            case 1:
                result = KeyCode.RIGHT;
                break;
            case 2:
                result = KeyCode.DOWN;
                break;
            case 3:
                result = KeyCode.LEFT;
        }
        return result;
    }

}
