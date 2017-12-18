package ru.anton.snakeAI.neuralModel;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;


public class TrainingData {
    private int[][] field;
    private KeyCode currentButton;
    private String data;
    private String direction;

    public TrainingData(Snake snake, Field field, KeyCode currentButton) {
        this.currentButton = currentButton;
        this.field = prepareData(snake, field);
        direction = getButtonDataSet(currentButton, snake.getDirection());
        data = toString();
    }

    public static int[][] prepareData(Snake snake, Field field) {
        /*int[][] result = new int[field.getSize()][field.getSize()];
        int[] food = field.getFood();
        result[food[0]][food[1]] = 3;
        snake.getElems().forEach(e -> result[e[0]][e[1]] = 1);
        result[snake.getHead()[0]][snake.getHead()[1]] = 2;*/

        int [][] result = new int[snake.getElems().size()+1][2];

        for (int i = 0; i < snake.getElems().size(); i++) {
            result[i] = snake.getElems().get(i);
        }

        result[result.length-1] = field.getFood();

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
        builder.append(direction);
        return builder.toString();
    }

    public String getData() {
        return data;
    }

    public int getSetSize() {
        return field.length * field[0].length;
    }

    public static String getButtonDataSet(KeyCode keyCode, Directions direction) {
        String result = "0 0 0";
        switch (direction) {
            case NORTH:
                switch (keyCode) {
                    case UP:
                        result = "0 1 0";
                        break;
                    case RIGHT:
                        result = "0 0 1";
                        break;
                    case DOWN:
                        result = "0 1 0";
                        break;
                    case LEFT:
                        result = "1 0 0";
                }
                break;
            case EAST:
                switch (keyCode) {
                    case UP:
                        result = "1 0 0";
                        break;
                    case RIGHT:
                        result = "0 1 0";
                        break;
                    case DOWN:
                        result = "0 0 1";
                        break;
                    case LEFT:
                        result = "0 1 0";
                }
                break;
            case SOUTH:
                switch (keyCode) {
                    case UP:
                        result = "0 1 0";
                        break;
                    case RIGHT:
                        result = "1 0 0";
                        break;
                    case DOWN:
                        result = "0 1 0";
                        break;
                    case LEFT:
                        result = "0 0 1";
                }
                break;
            case WEST:
                switch (keyCode) {
                    case UP:
                        result = "0 0 1";
                        break;
                    case RIGHT:
                        result = "0 1 0";
                        break;
                    case DOWN:
                        result = "1 0 0";
                        break;
                    case LEFT:
                        result = "0 1 0";
                }
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
        System.out.println(result);
        return result;
    }

}
