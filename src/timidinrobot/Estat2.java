package timidinrobot;

import robocode.BulletHitEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Estat2 extends Estat {
    
    private boolean enemyDead = true;

    public Estat2(TimidinRobot rob) {
        super(rob);
    }

    @Override
    public void run() {
        if (enemyDead) robot.turnGunLeft(30);
        robot.execute();
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double angleToEnemy = robot.getHeading() - robot.getGunHeading() + event.getBearing();
        robot.setTurnGunRight(normalizeAngle(angleToEnemy)); 
        
        robot.setFire(1);  

        robot.execute();
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {}

    @Override
    public void onHitWall(HitWallEvent event) {}

    @Override
    public void onBulletHit(BulletHitEvent event) {
        if (event.getEnergy() <= 0) {
            enemyDead = true;
        }
        else {
            enemyDead = false;
        }
    }

    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}


