package timidinrobot;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

public class Estat0 extends Estat {

    private final double RADAR_TURN_ANGLE = 5;

    public Estat0(TimidinRobot rob) {
        super(rob);
    }

    @Override
    public void run() {
        robot.setTurnRadarRight(RADAR_TURN_ANGLE);
        robot.execute();
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double bearing = event.getBearing(); 
        double myHeading = robot.getHeading();
        double angle = Math.toRadians(bearing + myHeading);
        double distance = event.getDistance();
        
        double[] targetCoordinates = calculateTargetCoordinates(angle, distance);
        double[][] corners = getBattleFieldCorners();
        
        int farthestCornerIndex = findFarthestCornerIndex(targetCoordinates, corners);
        robot.e = new Estat1(robot, corners[farthestCornerIndex]); 
    }

    private double[] calculateTargetCoordinates(double angle, double distance) {
        double vX = Math.sin(angle) * distance;
        double vY = Math.cos(angle) * distance;
        return new double[]{robot.getX() + vX, robot.getY() + vY};
    }

    private double[][] getBattleFieldCorners() {
        double width = robot.getBattleFieldWidth();
        double height = robot.getBattleFieldHeight();
        return new double[][]{
            {0, height},
            {width, height},
            {0, 0},
            {width, 0}
        };
    }

    private int findFarthestCornerIndex(double[] targetCoordinates, double[][] corners) {
        double maxDistance = 0;
        int index = 0;
        
        for (int i = 0; i < corners.length; i++) {
            double vdX = targetCoordinates[0] - corners[i][0];
            double vdY = targetCoordinates[1] - corners[i][1];
            double distanceV = Math.sqrt(vdX * vdX + vdY * vdY);
            if (maxDistance < distanceV) {
                maxDistance = distanceV;
                index = i;
            }
        }
        return index;
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) {
        
    }
}

