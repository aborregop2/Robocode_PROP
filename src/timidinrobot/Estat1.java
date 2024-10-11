package timidinrobot;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

public class Estat1 extends Estat {

    private final double[] targetCoordinates;
    private static final double SAFE_DISTANCE = 100;
    private boolean canFirstTurn = true;
    //private static final double ATTACK_DISTANCE = 0;

    public Estat1(TimidinRobot rob, double corner[]) {
        super(rob);
        targetCoordinates = corner;
    }

    @Override
    public void run() {
      double deltaX = targetCoordinates[0] - robot.getX();
      double deltaY = targetCoordinates[1] - robot.getY();
      if(canFirstTurn){
        double angleToTarget = Math.atan2(deltaX, deltaY);
        double targetAngle = Math.toDegrees(angleToTarget);

        robot.turnRight(normalizeAngle(targetAngle - robot.getHeading()));
        robot.turnRadarRight(normalizeAngle(targetAngle - robot.getRadarHeading()));
        canFirstTurn = false;
      }

        
        robot.ahead(Math.hypot(deltaX, deltaY));
        



        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        if (event.getDistance() <= SAFE_DISTANCE) {
            robot.fire(1); 
            robot.turnRight(90);
            
            robot.ahead(60);
            
              double deltaX = targetCoordinates[0] - robot.getX();
        double deltaY = targetCoordinates[1] - robot.getY();
        double angleToTarget = Math.atan2(deltaX, deltaY);
        double targetAngle = Math.toDegrees(angleToTarget);

        robot.turnRight(normalizeAngle(targetAngle - robot.getHeading()));
        robot.turnRadarRight(normalizeAngle(targetAngle - robot.getRadarHeading()));

        }
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) {
        robot.back(50);
    }


    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}



