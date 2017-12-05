package ru.anton.snakeAI.model;

public enum Directions {
    NORTH(0,-1), SOUTH(0, 1), EAST(1,0), WEST(-1, 0);

    int xDiff;
    int yDiff;

    Directions(int xDiff, int yDiff){
        this.xDiff = xDiff;
        this.yDiff = yDiff;
    }
}
