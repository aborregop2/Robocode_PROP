package timidinrobot;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Estat1 extends Estat {

    private final double[] targetCoordinates;
    private static final double SAFE_DISTANCE = 50;
    private boolean robotDetected = false;
    private boolean robotHit = false;
    private boolean hitWall = false;
    private double wallAngle;
    private double robotAngle;
    private double radarAngle;
    
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
            
            robot.ahead(Math.hypot(deltaX, deltaY) - 60);
            
            if (Math.hypot(deltaX, deltaY) <= 60){
                robot.e = new Estat2(robot);
            }
            
        }
        
        if (robotDetected && !robotHit && !hitWall) {
            robot.fire(1);
            if (radarAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
            robot.ahead(100);
            robotDetected = false;
        } 
        
        if (robotHit) {
            robot.back(100);
            if (robotAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
            robot.ahead(40);
            robotHit = false;
        }
        
        if (hitWall) {
            robot.back(100); 
            if (wallAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
            
            robot.ahead(40);

            hitWall = false;
        }

        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        radarAngle = event.getBearing();
        if (event.getDistance() <= SAFE_DISTANCE) {
            robotDetected = true;
        }
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) {
        robotAngle = event.getBearing();
        robotHit = true;
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        wallAngle = event.getBearing();
        hitWall = true;
    }

    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}




