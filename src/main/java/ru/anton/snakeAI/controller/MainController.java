package ru.anton.snakeAI.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import ru.anton.snakeAI.Game;
import ru.anton.snakeAI.gamers.Gamer;
import ru.anton.snakeAI.gamers.HumanGamer;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.view.GameView;

import java.io.IOException;

public class MainController {
    private Game game;
    private GameView gameView;
    private Timeline timeline;
    private final int canvasSize = 300;
    private Gamer gamer;

    private Canvas canvas;

    @FXML
    private AnchorPane neuralPane;



    @FXML
    private AnchorPane canvasPane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Label gameTypeLabel;

    @FXML
    private Label scoresLabel;

    @FXML
    void goToTraining(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/training.fxml"));
        try {
            Parent parent = loader.load();
            TrainingController controller = loader.getController();
            controller.setCanvas(canvas);
            Scene scene = new Scene(parent, 400, 300);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createBehaviorGame(ActionEvent event) {

    }

    @FXML
    void createHumanGame(ActionEvent event) {
        rightPane.setDisable(false);
        gameTypeLabel.setText("Human game");
        game = new Game(canvasSize/10, 5, 7);
        gamer = new HumanGamer(game.getField(), game.getSnake(),canvas.getParent().getParent().getParent());
        gamer.init();
    }

    @FXML
    void createNeuralGame(ActionEvent event) {
        rightPane.setDisable(false);
        gameTypeLabel.setText("Neural network game");
        neuralPane.setVisible(true);



    }

    @FXML
    void startGame(ActionEvent event) {
        gameView = new GameView(game.getField(), game.getSnake(), canvas.getGraphicsContext2D(), (int) canvas.getWidth());
        gameView.draw();

        scoresLabel.setText("0");
        scoresLabel.textProperty().bindBidirectional(game.getScoreProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object);
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(game.getSpeed()),
                        ae -> {

                            switch (gamer.game()) {
                                case UP: game.getSnake().setDirection(Directions.NORTH);
                                    break;
                                case DOWN: game.getSnake().setDirection(Directions.SOUTH);
                                    break;
                                case LEFT: game.getSnake().setDirection(Directions.WEST);
                                    break;
                                case RIGHT: game.getSnake().setDirection(Directions.EAST);

                            }

                            game.tick();
                            gameView.draw();
                            if (game.isGameOver()){
                                timeline.stop();
                            }
                        }
                )
        );

        timeline.setCycleCount(Animation.INDEFINITE); //Ограничим число повторений
        timeline.play();

    }

    public void initialize(){
        canvas = new Canvas();
        canvas.setHeight(canvasSize);
        canvas.setWidth(canvasSize);

        canvasPane.getChildren().add(canvas);

        /*canvas.getParent().getParent().getParent().addEventFilter(KeyEvent.KEY_PRESSED,  event -> {
                System.out.println(event.getCode());
                switch (event.getCode()) {
                    case UP: game.getSnake().setDirection(Directions.NORTH);
                        break;
                    case DOWN: game.getSnake().setDirection(Directions.SOUTH);
                        break;
                    case LEFT: game.getSnake().setDirection(Directions.WEST);
                        break;
                    case RIGHT: game.getSnake().setDirection(Directions.EAST);

                }
        });*/


    }



}
