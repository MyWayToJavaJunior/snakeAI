package ru.anton.snakeAI.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.anton.snakeAI.model.Field;
import ru.anton.snakeAI.model.Snake;

public class GameView {
    private Field field;
    private Snake snake;
    private GraphicsContext gc;
    private int canvasSize;
    private int snakeElemSize;

    public GameView(Field field, Snake snake, GraphicsContext gc, int canvasSize) {
        this.field = field;
        this.snake = snake;
        this.gc = gc;
        this.canvasSize = canvasSize;
        snakeElemSize = canvasSize/field.getSize();
    }

    public void draw(){
        gc.clearRect(0,0, canvasSize, canvasSize);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLUE);
        gc.setLineWidth(1);
        drawSnakeElem(snake.getHead());
        gc.setFill(Color.YELLOW);
        for (int i = 1; i < snake.getElems().size(); i++) {
            drawSnakeElem(snake.getElems().get(i));
        }
    }

    private void drawSnakeElem(int[] coords){
        System.out.println(coords[0]);
        gc.fillRect(coords[0]*snakeElemSize, coords[1]*snakeElemSize, snakeElemSize, snakeElemSize);
        gc.strokeRect(coords[0]*snakeElemSize, coords[1]*snakeElemSize, snakeElemSize, snakeElemSize);
    }
}
