package ru.anton.snakeAI.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import ru.anton.snakeAI.Game;
import ru.anton.snakeAI.model.Directions;
import ru.anton.snakeAI.view.GameView;

public class MainController {
    private Game game;
    private GameView gameView;

    @FXML
    private Canvas canvas;

    @FXML
    private Label gameTypeLabel;

    @FXML
    private Label scoresLabel;

    @FXML
    void createBehaviorGame(ActionEvent event) {

    }

    @FXML
    void createHumanGame(ActionEvent event) {

    }

    @FXML
    void createNeuroGame(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) {

    }

    public void initialize(){
        game = new Game(50, 5, 1);
        gameView = new GameView(game.getField(), game.getSnake(), canvas.getGraphicsContext2D(), (int) canvas.getWidth());
        gameView.draw();

        canvas.getParent().getParent().getParent().addEventFilter(KeyEvent.KEY_PRESSED,  event -> {
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
        });

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(game.getSpeed()),
                        ae -> {
                            game.tick();
                            gameView.draw();
                        }
                )
        );

        timeline.setCycleCount(5); //Ограничим число повторений
        timeline.play();

    }



}
