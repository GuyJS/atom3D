package animation;

import javafx.scene.Node;

public class NodeMovementCalculator {
    
    private static final double DEGREES_TO_RADIANS = Math.PI/180.0;
    private static final double DEGREES_IN_CIRCLE = 360.0;
    private Node node;
    
    public NodeMovementCalculator(Node node){
        this.node = node;
    }
    
    public void moveForward(double amount){
        //0 is along z, 270 is along x
        double facingAngle = node.getRotate() * DEGREES_TO_RADIANS;
        double zComp = Math.cos(facingAngle); //"forwards"
        double xComp = -Math.sin(facingAngle); //because z is at 270 degrees.

        node.setTranslateX(node.getTranslateX() + (amount * xComp));
        node.setTranslateZ(node.getTranslateZ() + (amount * zComp));
    }

    public void moveBack(double amount){
        //0 is along z, 270 is along x
        double facingAngle = node.getRotate() * DEGREES_TO_RADIANS;
        double zComp = Math.cos(facingAngle); //"forwards"
        double xComp = -Math.sin(facingAngle); //because z is at 270 degrees.

        node.setTranslateX(node.getTranslateX() - (amount * xComp));
        node.setTranslateZ(node.getTranslateZ() - (amount * zComp));
    }

    public void strafeLeft(double amount){
        double facingAngle = node.getRotate() * DEGREES_TO_RADIANS;
        double xComp = -Math.cos(facingAngle); //"forwards"
        double zComp = -Math.sin(facingAngle); //because z is at 270 degrees.

        node.setTranslateX(node.getTranslateX() + (amount * xComp));
        node.setTranslateZ(node.getTranslateZ() + (amount * zComp));
    }

    public void strafeRight(double amount){
        double facingAngle = node.getRotate() * DEGREES_TO_RADIANS;
        double xComp = -Math.cos(facingAngle); //"forwards"
        double zComp = -Math.sin(facingAngle); //because z is at 270 degrees.

        node.setTranslateX(node.getTranslateX() - (amount * xComp));
        node.setTranslateZ(node.getTranslateZ() - (amount * zComp));
    }
    
    public void rotateACW(double deg){
        double rotation = node.getRotate();
        rotation = (rotation + deg) % DEGREES_IN_CIRCLE;
        node.setRotate(rotation);
    }
    
    public void rotateCW(double deg){
        double rotation = node.getRotate();
        rotation = (rotation + DEGREES_IN_CIRCLE - deg) % DEGREES_IN_CIRCLE; //to prevent negative values.
        node.setRotate(rotation);
    }
}
