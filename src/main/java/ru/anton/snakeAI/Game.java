package ru.anton.snakeAI;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.GameElement;
import ru.anton.snakeAI.model.Snake;

import java.util.ArrayList;
import java.util.List;


public class Game implements GameElement{
    private Field field;
    private Snake snake;
    private int speed;
    private boolean gameOver = false;
    private List<GameEventHandler> handlers;
    private IntegerProperty score = new SimpleIntegerProperty();

    public Game(int fieldSize, int minSnakeSize, int speed) {
        snake = new Snake(minSnakeSize);
        field = new Field(fieldSize, snake);
        setSpeed(speed);
        handlers = new ArrayList<>();
        score.setValue(0);
    }

    private void setSpeed(int speed) {
        this.speed = 1000/speed;
    }

    public void start() throws InterruptedException {
        while (!gameOver){
            Thread.sleep(speed);
            tick();
        }
    }

    @Override
    public void tick() {
        field.tick();
        snake.tick();
        if (checkFood()){
            snake.eat();
            field.eatFood();
            score.setValue(score.getValue()+1);
            handlers.forEach(h->h.handle(GameEvent.EAT));
        }
        if (!checkBound() || checkSelf()) {
            gameOver = true;
            return;
        }
        if (snake.getHead()[0]==field.getFood()[0] && snake.getHead()[1]==field.getFood()[1]) snake.eat();
    }

    private boolean checkBound(){
        return snake.getHead()[0] >= 0 && snake.getHead()[0] <= field.getSize() && snake.getHead()[1] >= 0 && snake.getHead()[1] <= field.getSize();
    }

    private boolean checkSelf(){
        boolean result = false;
        for (int i = 1; i < snake.getElems().size(); i++) {
            if (snake.getElems().get(i)[0]==snake.getHead()[0] && snake.getElems().get(i)[1]==snake.getHead()[1]) result = true;
        }
        return result;
    }

    private boolean checkFood(){
        return snake.getHead()[0] == field.getFood()[0] && snake.getHead()[1] == field.getFood()[1];
    }

    public Field getField() {
        return field;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public int getSpeed() {
        return speed;
    }

    public void addEventHandler(GameEventHandler handler){
        handlers.add(handler);
    }

    public IntegerProperty getScoreProperty() {
        return score;
    }

    public interface GameEventHandler{
        void handle(GameEvent event);
    }

    public enum GameEvent {
        EAT, GEME_OVER
    }
}
