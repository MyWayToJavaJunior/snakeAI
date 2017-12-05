package ru.anton.snakeAI;

import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.GameElement;
import ru.anton.snakeAI.model.Snake;

public class Game implements GameElement{
    private Field field;
    private Snake snake;
    private int speed;
    private boolean gemeOver = false;

    public Game(int fieldSize, int minSnakeSize, int speed) {
        snake = new Snake(minSnakeSize);
        field = new Field(fieldSize, snake);
        setSpeed(speed);
    }

    private void setSpeed(int speed) {
        this.speed = 500/speed;
    }

    public void start() throws InterruptedException {
        while (!gemeOver){
            Thread.sleep(speed);
            tick();
        }
    }

    @Override
    public void tick() {
        field.tick();
        snake.tick();
        if (!checkBound()) {
            gemeOver = true;
            return;
        }
        if (snake.getHead()[0]==field.getFood()[0] && snake.getHead()[1]==field.getFood()[1]) snake.eat();
    }

    private boolean checkBound(){
        if (snake.getHead()[0]<0 || snake.getHead()[0]>field.getSize() || snake.getHead()[1]<0 || snake.getHead()[1]>field.getSize()) return false;
        return true;
    }

    public Field getField() {
        return field;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isGemeOver(){
        return gemeOver;
    }

    public int getSpeed() {
        return speed;
    }
}
