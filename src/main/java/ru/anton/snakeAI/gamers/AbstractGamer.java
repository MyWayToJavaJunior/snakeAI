package ru.anton.snakeAI.gamers;

import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

public abstract class AbstractGamer implements Gamer{
    Field field;
    Snake snake;

    public AbstractGamer(Field field, Snake snake){
        this.field = field;
        this.snake = snake;
    }
}
