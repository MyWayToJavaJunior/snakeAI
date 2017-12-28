package ru.anton.snakeAI.neuralModel;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;


public class TrainingData {
    private KeyCode currentButton;
    private String dataString;
    private float[] data;
    private String direction;

    public TrainingData(Snake snake, Field field, KeyCode currentButton) {
        this.currentButton = currentButton;
        this.data = prepareData(snake, field);
        direction = getButtonDataSet(currentButton, snake.getDirection());
        dataString = toString();
    }

    public static float[] prepareData(Snake snake, Field field) {
        /*int[][] result = new int[field.getSize()][field.getSize()];
        int[] food = field.getFood();
        result[food[0]][food[1]] = 3;
        snake.getElems().forEach(e -> result[e[0]][e[1]] = 1);
        result[snake.getHead()[0]][snake.getHead()[1]] = 2;*/

        /*int [][] result = new int[snake.getElems().size()+1][2];

        for (int i = 0; i < snake.getElems().size(); i++) {
            result[i] = snake.getElems().get(i);
        }

        result[result.length-1] = field.getFood();*/
        float dirX = snake.getHead()[0]+snake.getDirection().xDiff;
        float dirY = snake.getHead()[1]+snake.getDirection().yDiff;
        float[] result = new float[4];
        result[0] = snake.getHead()[0] - dirX;
        result[1] = dirY - snake.getHead()[1];
        result[2] = field.getFood()[0] - dirX;
        result[3] = dirY - field.getFood()[1];
        //snake.getDirection()

        return result;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (float d : data) {
            builder.append(d);
            builder.append(" ");
        }
        builder.append(System.lineSeparator());
        builder.append(direction);
        return builder.toString();
    }

    public String getDataString() {
        return dataString;
    }

    public int getSetSize() {
        return data.length;
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
