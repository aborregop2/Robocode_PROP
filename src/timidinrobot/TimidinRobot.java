
package timidinrobot;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class TimidinRobot extends AdvancedRobot {

    public Estat e;
    
    @Override
    public void run() {
        setAdjustGunForRobotTurn(false);
        setAdjustRadarForRobotTurn(false);
        setAdjustRadarForGunTurn(false);
        
        e = new Estat0(this);
        
        while(true){
            e.run();
        }
    }
    
    
    @Override
    public void onScannedRobot(ScannedRobotEvent event){
        e.onScannedRobot(event);
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) {
        e.onHitRobot(event);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        e.onHitWall(event);
    }
}
