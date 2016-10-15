package animation;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Box;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeMovementCalculatorTest {
    
    private static final double DELTA = 1E-8;
    private NodeMovementCalculator sut;
    private Node node;
    private static final double AMOUNT = 30;
    
    
    @Before
    public void setUp(){
        node = new Box(2, 2, 2);
        node.setRotationAxis(new Point3D(0, -1, 0)); //along the upward y-axis
        node.setTranslateX(0.0);
        node.setTranslateY(0.0);
        node.setTranslateZ(0.0);
        sut = new NodeMovementCalculator(node);
    }
    
    @Test
    public void shouldMoveForward(){
        sut.moveForward(AMOUNT);
        assertEquals(AMOUNT, node.getTranslateZ(), DELTA);
        assertEquals(0, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateY(), DELTA);
    }
    
    @Test
    public void shouldMoveBackward(){
        sut.moveBack(AMOUNT);
        assertEquals(-AMOUNT, node.getTranslateZ(), DELTA);
        assertEquals(0, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateY(), DELTA);
    }
    
    @Test
    public void shouldMoveLeft(){
        sut.strafeLeft(AMOUNT);
        assertEquals(-AMOUNT, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateZ(), DELTA);
        assertEquals(0, node.getTranslateY(), DELTA);
    }
    
    @Test
    public void shouldMoveRight(){
        sut.strafeRight(AMOUNT);
        assertEquals(AMOUNT, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateZ(), DELTA);
        assertEquals(0, node.getTranslateY(), DELTA);
    }

    @Test
    public void shouldRotateClockwise(){
        sut.rotateCW(AMOUNT);
        assertEquals(360 - AMOUNT, node.getRotate(), DELTA);
    }

    @Test
    public void shouldRotateAntiClockwise(){
        sut.rotateACW(AMOUNT);
        assertEquals(AMOUNT, node.getRotate(), DELTA);
    }

    @Test
    public void shouldMoveCorrectlyAfterRotating(){
        //rotate 30 and then move forward 30.
        sut.rotateACW(AMOUNT);
        sut.moveForward(AMOUNT);

        assertEquals(-AMOUNT * Math.sin(Math.PI/6), node.getTranslateX(), DELTA);
        assertEquals(AMOUNT * Math.cos(Math.PI/6), node.getTranslateZ(), DELTA);

        sut.moveBack(AMOUNT);

        assertEquals(0, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateZ(), DELTA);
    }

    @Test
    public void shouldStrafeCorrectlyAfterRotate(){
        sut.rotateACW(AMOUNT);
        sut.strafeRight(AMOUNT);

        assertEquals(AMOUNT * Math.sin(Math.PI/6), node.getTranslateZ(), DELTA);
        assertEquals(AMOUNT * Math.cos(Math.PI/6), node.getTranslateX(), DELTA);

        sut.strafeLeft(AMOUNT);

        assertEquals(0, node.getTranslateX(), DELTA);
        assertEquals(0, node.getTranslateZ(), DELTA);
    }
}