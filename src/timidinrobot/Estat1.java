package timidinrobot;

import robocode.BulletHitEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Estat1 extends Estat {

    private final double[] targetCoordinates;
    private static final double SAFE_DISTANCE = 120;
    private boolean robotDetected = false;
    private boolean robotHit = false;
    private boolean hitWall = false;
    private double wallAngle;
    private double robotAngle;
    private double radarAngle;
    private boolean radarDirectionRight = true;
    
    public Estat1(TimidinRobot rob, double corner[]) {
        super(rob);
        targetCoordinates = corner;
    }

    @Override
    public void run() {
        
        if (robotDetected) {     
            robotDetected = false;
        } 
        
        if (robotHit) {
            robot.back(30);
            if (robotAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
            robot.ahead(30);
            robotHit = false;
        }
        
        if (hitWall) {
            robot.back(30); 
            if (wallAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
            
            robot.ahead(30);

            hitWall = false;
        }
        
        
        if (!robotDetected && !robotHit && !hitWall) {
            
           
            double deltaX = targetCoordinates[0] - robot.getX();
            double deltaY = targetCoordinates[1] - robot.getY();
        
            double angleToTarget = Math.atan2(deltaX, deltaY);
            double targetAngle = Math.toDegrees(angleToTarget);
            robot.turnRight(normalizeAngle(targetAngle - robot.getHeading()));
            robot.turnRadarRight(normalizeAngle(targetAngle - robot.getRadarHeading()));
            
            
            robot.ahead(Math.hypot(deltaX, deltaY) - 60);
            
            if (Math.hypot(deltaX, deltaY) <= 60){    
                robot.setEstat(new Estat2(robot));
            }   
            
        }
        

        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        radarAngle = event.getBearing();
        if (event.getDistance() <= SAFE_DISTANCE) {
            robot.fire(1);
            if (radarAngle < 0) {
                robot.turnRight(90);
            }
            else {
                robot.turnLeft(90);
            }
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
    
    @Override
    public void onBulletHit(BulletHitEvent event) {}
}




