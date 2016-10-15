package ui;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GridFloor {

    private int rows;
    private int cols;
    private int cellSize;
    private Color colour1;
    private Color colour2;

    Node[] grid;

    public GridFloor(int rows, int cols, int cellSize, Color colour1, Color colour2){
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.colour1 = colour1;
        this.colour2 = colour2;
        grid = new Node[rows*cols];
        initialise();
    }

    private void initialise(){
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                Rectangle rectangle = new Rectangle(cellSize, cellSize);
                rectangle.setRotationAxis(new Point3D(1, 0, 0));
                rectangle.setRotate(rectangle.getRotate()+90);
                rectangle.setFill(Paint.valueOf((i + j) % 2 == 0 ? colour1.toString() : colour2.toString()));
                rectangle.setTranslateX(i * cellSize - (rows * cellSize / 2));
                rectangle.setTranslateZ(j * cellSize - (cols * cellSize / 2));
                rectangle.setTranslateY(10);
                grid[i * cols + j] = rectangle;
            }
        }
    }

    public Node[] getGrid(){
        return grid;
    }




}
