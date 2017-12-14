package ru.anton.snakeAI.gamers;

import javafx.scene.input.KeyCode;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;
import ru.anton.snakeAI.neuralModel.NeuralNetwork;
import ru.anton.snakeAI.neuralModel.TrainingData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralGamer extends AbstractGamer{
    NeuralNetwork neuralNetwork;

    public NeuralGamer(Field field, Snake snake) {
        super(field, snake);
    }

    @Override
    public void init() {

    }

    @Override
    public KeyCode game() {
        int[][] cells = TrainingData.prepareData(snake, field);
        float[] result = new float[12]; //FIXME hardcode
        /*for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                result[j+i*cells.length] = cells[i][j];
            }
        }*/
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                list.add((float) cells[i][j]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);   //FIXME list to array?
        }
        System.out.println(Arrays.toString(result));
        float[] answer = neuralNetwork.getFann().run(result);
        System.out.println("result "+Arrays.toString(answer));
        int maxPos = getMaxPosition(answer);

        LocalDirection localDirection = LocalDirection.FORWARD;

        switch (maxPos) {
            case 0: localDirection = LocalDirection.LEFT;
                break;
            case 1 : localDirection = LocalDirection.FORWARD;
                break;
            case 2: localDirection = LocalDirection.RIGHT;
        }


        return  getKeyCode(snake.getDirection(), localDirection);
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork){
        this.neuralNetwork = neuralNetwork;
    }

    private int getMaxPosition(float[] array){
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]>array[result]) result = i;
        }
        return result;
    }

    /**
     *
     * @param currenDdirection текущее направление
     * @param localDirection изменение направления
     * @return код клавиши
     */
    private KeyCode getKeyCode(Directions currenDdirection, LocalDirection localDirection){
        int result = currenDdirection.absoluteDir+localDirection.diff;
        if (result<0) result = 3;
        if (result>3) result = 0;
        return TrainingData.getKeyCode(result);
    }

    public static enum LocalDirection{
        RIGHT(1), FORWARD(0), LEFT(-1);
        int diff;

        LocalDirection(int diff){
            this.diff = diff;
        }
    }


}
