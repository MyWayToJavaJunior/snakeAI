package ru.anton.snakeAI.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import ru.anton.snakeAI.Game;
import ru.anton.snakeAI.gamers.Gamer;
import ru.anton.snakeAI.gamers.HumanGamer;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.neuralModel.NeuralNetwork;
import ru.anton.snakeAI.neuralModel.TrainingData;
import ru.anton.snakeAI.neuralModel.TrainingManager;
import ru.anton.snakeAI.view.GameView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TrainingController {
    private TrainingManager trainingManager;
    Canvas canvas;
    Game game;
    GameView gameView;
    Gamer gamer;
    boolean isRecording = false;
    ObjectProperty<Path> pathToSetProperty = new SimpleObjectProperty<>();
    NeuralNetwork neuralNetwork;

    @FXML
    private Label recordStatusLabel;

    @FXML
    private Label buttonLabel;

    @FXML
    private Label networkPathLabel;

    @FXML
    private Label learnLabel;

    @FXML
    private Label setLabel;

    @FXML
    private Label recordsLabel;

    @FXML
    void saveNetwork() {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Neural Network");//Заголовок диалога

        File file = fileChooser.showSaveDialog(buttonLabel.getScene().getWindow());
        if (file != null) {
            neuralNetwork.saveNetwork(file);

            networkPathLabel.setText(file.getName());
        }
    }


    @FXML
    void createNetwork(ActionEvent event) {
        //int size = (int) (canvas.getWidth() / 10 * canvas.getWidth() / 10);
        //neuralNetwork.createNetwork(size, size * 2, 4);
        int size = 4;
        neuralNetwork.createNetwork(size, size * 5, 3); //FIXME hardcode
        networkPathLabel.setText("New Network");
    }

    @FXML
    void learnNetwork(ActionEvent event) {
        neuralNetwork.trainingNetwork(pathToSetProperty.get().toFile());
    }

    @FXML
    void loadNetwork(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Load Neural Network");//Заголовок диалога

        File file = fileChooser.showOpenDialog(buttonLabel.getScene().getWindow());//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            neuralNetwork.loadNetwork(file.toString());
        }
    }

    @FXML
    void startRecord(ActionEvent event) {
        isRecording = true;
        game = new Game((int) (canvas.getWidth() / 10), 5, 7);
        gameView = new GameView(game.getField(), game.getSnake(), canvas.getGraphicsContext2D(), (int) canvas.getWidth());
        gameView.draw();

        gamer = new HumanGamer(game.getField(), game.getSnake(), recordsLabel.getParent().getParent());
        gamer.init();

        recordStatusLabel.setText("Recording");

        recordsLabel.textProperty().bindBidirectional(trainingManager.getRecordsCountProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object);
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        buttonLabel.textProperty().bindBidirectional(((HumanGamer) gamer).getKeyCodeProperty(), new StringConverter<KeyCode>() {
            @Override
            public String toString(KeyCode object) {
                return object.toString();
            }

            @Override
            public KeyCode fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    void stopRecord(ActionEvent event) {
        buttonLabel.textProperty().unbindBidirectional(((HumanGamer) gamer).getKeyCodeProperty());
        isRecording = false;
        recordStatusLabel.setText("Stop recording");
    }

    @FXML
    void saveTick() {
        if (isRecording) {
            KeyCode code = gamer.game();
            TrainingData data = new TrainingData(game.getSnake(), game.getField(), gamer.game());

            trainingManager.addData(data);
            switch (code) {
                case UP:
                    game.getSnake().setDirection(Directions.NORTH);
                    break;
                case DOWN:
                    game.getSnake().setDirection(Directions.SOUTH);
                    break;
                case LEFT:
                    game.getSnake().setDirection(Directions.WEST);
                    break;
                case RIGHT:
                    game.getSnake().setDirection(Directions.EAST);

            }

            game.tick();
            gameView.draw();
        }
    }

    @FXML
    void nextTick(){
        if (isRecording) {
            KeyCode code = gamer.game();
            switch (code) {
                case UP:
                    game.getSnake().setDirection(Directions.NORTH);
                    break;
                case DOWN:
                    game.getSnake().setDirection(Directions.SOUTH);
                    break;
                case LEFT:
                    game.getSnake().setDirection(Directions.WEST);
                    break;
                case RIGHT:
                    game.getSnake().setDirection(Directions.EAST);

            }
            game.tick();
            gameView.draw();
        }
    }

    @FXML
    void saveSet() {
        System.out.println(trainingManager.getDataListString());
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Training Set");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("data files (*.data)", "*.data");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonLabel.getScene().getWindow());
        if (file != null) {
            try {
                Files.write(file.toPath(), trainingManager.getDataListString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            setLabel.setText(file.getName());
        }
    }

    @FXML
    void loadSet() {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Load Training Set");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("data files (*.data)", "*.data");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(buttonLabel.getScene().getWindow());//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            pathToSetProperty.setValue(file.toPath());
        }
    }

    public void initialize() {
        trainingManager = new TrainingManager();
        neuralNetwork = new NeuralNetwork();
        setLabel.textProperty().bindBidirectional(pathToSetProperty, new StringConverter<Path>() {
            @Override
            public String toString(Path object) {
                if (object == null) return "";
                return object.getFileName().toString();
            }

            @Override
            public Path fromString(String string) {
                return null;
            }
        });
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
