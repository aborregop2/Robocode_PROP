package timidinrobot;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;



public class Estat2 extends Estat{
    
    private final double RADAR_TURN_ANGLE = 5;

    public Estat2(TimidinRobot rob) {
        super(rob);
    }
    
    @Override
    public void run() {
        //robot.setTurnRadarRight(RADAR_TURN_ANGLE); 
        

        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double distance = event.getDistance();
        double firePower = Math.min(300 / distance, 3);
        
        double bearing = event.getBearing();
        robot.setTurnRadarRight(normalizeAngle(bearing - robot.getRadarHeading()));
        
        robot.fire(firePower);

    }
    
     private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
     
    @Override
    public void onHitRobot(HitRobotEvent event) {
    }
}
