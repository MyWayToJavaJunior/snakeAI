package ru.anton.snakeAI.model;

import java.util.ArrayList;
import java.util.List;

public class Snake implements GameElement{
    private List<int[]> elems;
    private int size;
    private Directions direction;
    private int score = 0;


    public Snake(int initSize){
        size = initSize;
        elems = new ArrayList<>();
        initSnake();
        setDirection(Directions.SOUTH);
    }

    public void setDirection(Directions direction){
        this.direction = direction;
    }

    public void moove(){
        int[] buffCoords = new int[2];
        int[] currBuffCoords = new int[2];

        buffCoords[0] = elems.get(0)[0];
        buffCoords[1] = elems.get(0)[1];

        elems.get(0)[0] = direction.xDiff+buffCoords[0];
        elems.get(0)[1] = direction.yDiff+buffCoords[0];
        for (int i = 1; i < elems.size(); i++) {
            int[] elem = elems.get(i);
            currBuffCoords[0] = elem[0];
            currBuffCoords[1] = elem[1];
            elem[0] = buffCoords[0];
            elem[1] = buffCoords[1];
            buffCoords[0] = currBuffCoords[0];
            buffCoords[1] = currBuffCoords[1];
        }
    }

    public int[] getHead(){
        return elems.get(0);
    }

    public List<int[]> getElems() {
        return elems;
    }

    @Override
    public void tick() {
        moove();
    }

    public void eat() {
        score++;
    }

    private void initSnake(){
        for (int i = 0; i < size; i++) {
            elems.add(new int[2]);
            elems.get(i)[0] = 5+i;
            elems.get(i)[1] = 5;
        }
    }
}
