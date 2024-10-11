
package timidinrobot;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;



public abstract class Estat{
    public TimidinRobot robot;
    
    public Estat(TimidinRobot rob){
        robot = rob;
    }
    
    abstract void run();
    abstract void onScannedRobot(ScannedRobotEvent event);
    abstract void onHitRobot(HitRobotEvent event);
}
