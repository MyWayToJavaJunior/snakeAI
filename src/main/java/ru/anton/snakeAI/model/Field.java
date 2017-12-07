package ru.anton.snakeAI.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field implements GameElement{
    private final static int foodLive = 30;
    private int size;
    private int[][] cells;
    private int[] food;
    private int currentFoodLive;
    private Snake snake;

    public Field(int size, Snake snake){
        this.size = size;
        cells = new int[size][size];
        this.snake = snake;
        food = new int[2];
        currentFoodLive = foodLive;
        addFood();
    }

    @Override
    public void tick() {
        if (currentFoodLive == 0) {
            addFood();
            currentFoodLive = foodLive;
        } else currentFoodLive--;

    }

    private void addFood() {
        food[0] = (int) (Math.random()*size);
        food[1] = (int) (Math.random()*size);
        //System.out.println(Arrays.toString(food));
        if (snake.getElems().stream().anyMatch(e-> e[0]==food[0] && e[1]==food[1])) addFood();
    }

    public void eatFood(){
        currentFoodLive = 0;
    }

    public int[] getFood(){
        return food;
    }

    public int getSize() {
        return size;
    }


}
