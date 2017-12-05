package ru.anton.snakeAI.model;

public class Field implements GameElement{
    private int size;
    private int[][] cells;
    private int[] food;
    private int foodLive = 10;
    private Snake snake;

    public Field(int size, Snake snake){
        this.size = size;
        cells = new int[size][size];
        this.snake = snake;
        food = new int[2];
        addFood();
    }

    @Override
    public void tick() {
        if (foodLive == 0) {
            addFood();
        } else foodLive--;

    }

    private void addFood() {
        food[0] = (int) (Math.random()*size);
        food[1] = (int) (Math.random()*size);
        if (snake.getElems().stream().anyMatch(e-> e[0]==food[0] && e[1]==food[1])) addFood();
    }

    public int[] getFood(){
        return food;
    }

    public int getSize() {
        return size;
    }
}
