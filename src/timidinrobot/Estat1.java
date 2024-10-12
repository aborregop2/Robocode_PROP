package timidinrobot;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Estat1 extends Estat {

    private final double[] targetCoordinates;
    private static final double SAFE_DISTANCE = 100;
    private boolean robotDetected = false;
    private boolean robotHit = false;
    private boolean hitWall = false;

    public Estat1(TimidinRobot rob, double corner[]) {
        super(rob);
        targetCoordinates = corner;
    }

    @Override
    public void run() {
        
        if (!robotDetected && !robotHit && !hitWall) {
            double deltaX = targetCoordinates[0] - robot.getX();
            double deltaY = targetCoordinates[1] - robot.getY();
        
            double angleToTarget = Math.atan2(deltaX, deltaY);
            double targetAngle = Math.toDegrees(angleToTarget);
            robot.turnRight(normalizeAngle(targetAngle - robot.getHeading()));
            robot.turnRadarRight(normalizeAngle(targetAngle - robot.getRadarHeading()));
            
            robot.ahead(Math.hypot(deltaX, deltaY));
        }
        
        if (robotDetected) {
            robot.fire(1);
            robot.turnRight(90);
            robot.ahead(50);
            System.out.println("hola robot detectat");
            robotDetected = false;
        } 
        
        if (robotHit) {
            robot.back(50);
            System.out.println("hola xoc detectat");
            robotHit = false;
        }
        
        if (hitWall) {
            robot.back(50);            
            System.out.println("hola pared detectada");
            hitWall = false;
        }

        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        if (event.getDistance() <= SAFE_DISTANCE) {
            System.out.println("Robot detectado a " + event.getDistance() + " unidades");
            robotDetected = true;
        }
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) {
        //Aqui no entra
        robotHit = true;
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        hitWall = true;
    }

    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}




