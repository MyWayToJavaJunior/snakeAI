package ru.anton.snakeAI.model;

public enum Directions {
    NORTH(0,-1, 0), SOUTH(0, 1, 2), EAST(1, 0, 1), WEST(-1, 0, 3);

    public int xDiff;
    public int yDiff;
    public int absoluteDir;

    Directions(int xDiff, int yDiff, int absoluteDir){
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.absoluteDir = absoluteDir;
    }
}
