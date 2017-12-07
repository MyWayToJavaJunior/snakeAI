package ru.anton.snakeAI.gamers;

import javafx.scene.input.KeyCode;
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
        float[] result = new float[field.getSize()*field.getSize()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                result[j+i*cells.length] = cells[i][j];
            }
        }
        float[] answer = neuralNetwork.getFann().run(result);
        System.out.println("result "+Arrays.toString(answer));
        int maxPos = getMaxPosition(answer);


        return  TrainingData.getKeyCode(maxPos);
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


}
