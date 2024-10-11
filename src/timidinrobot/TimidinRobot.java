
package timidinrobot;

import robocode.AdvancedRobot;
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
}
