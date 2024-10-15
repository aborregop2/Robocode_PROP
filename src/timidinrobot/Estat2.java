package timidinrobot;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;



public class Estat2 extends Estat{
    
   

    public Estat2(TimidinRobot rob) {
        super(rob);
    }
    
    @Override
    public void run() {
        

        robot.execute(); 
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double distance = event.getDistance();
        double firePower = Math.min(300 / distance, 3);
        
        robot.fire(firePower);

    }
    
     private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
     
    @Override
    public void onHitRobot(HitRobotEvent event) {}
    
    @Override
    public void onHitWall(HitWallEvent event) {}
}
