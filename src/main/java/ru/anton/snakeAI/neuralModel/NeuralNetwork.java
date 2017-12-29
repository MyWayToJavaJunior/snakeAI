package ru.anton.snakeAI.neuralModel;

import com.googlecode.fannj.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor slonikmak on 06.12.2017.
 */
public class NeuralNetwork {
    private Fann fann;

    public NeuralNetwork(){
        System.setProperty("jna.library.path", "C:\\Users\\Oceanos\\Downloads\\FANN-2.2.0-Source\\FANN-2.2.0-Source\\bin\\fannfloat.dll");

        System.out.println( System.getProperty("jna.library.path") );

        File file = new File(System.getProperty("jna.library.path"));
        System.out.println("Is the dll file there:" + file.exists());
        System.load(file.getAbsolutePath());
    }

    public void createNetwork(int inputLayerSize, int hiddenLayerSize, int outputLayerSize){


        List<Layer> layerList = new ArrayList<>();
        layerList.add(Layer.create(inputLayerSize));
        layerList.add(Layer.create(hiddenLayerSize, ActivationFunction.FANN_SIGMOID, 0.01f));
        layerList.add(Layer.create(hiddenLayerSize, ActivationFunction.FANN_SIGMOID, 0.01f));
        layerList.add(Layer.create(hiddenLayerSize, ActivationFunction.FANN_SIGMOID, 0.01f));
        layerList.add(Layer.create(outputLayerSize, ActivationFunction.FANN_SIGMOID, 0.01f));
        fann = new Fann(layerList);
    }

    public void loadNetwork(String file){
        fann = new Fann(file);
    }

    public void trainingNetwork(File trainingSetFile){
        System.out.println("Start training");
        Trainer trainer = new Trainer(fann);
        trainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_RPROP);
        /* Проведем обучение взяв уроки из файла, с максимальным колличеством
           циклов 100000, показывая отчет каждую 100ю итерацию и добиваемся
        ошибки меньше 0.0001 */
        trainer.train(trainingSetFile.getAbsolutePath(), 100000, 100, 0.000001f);
        System.out.println("stop training");
    }

    public void saveNetwork(File destinationFile){
        fann.save(destinationFile.toString());
    }

    public Fann getFann(){
        return fann;
    }
}
