package timidinrobot;

import robocode.BulletHitEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Estat2 extends Estat {
    
    private boolean canTurn = true;

    public Estat2(TimidinRobot rob) {
        super(rob);
    }

    @Override
    public void run() {
        if (canTurn){
            robot.turnGunLeft(30);
        }
        else{
            robot.turnRadarLeft(1);
            robot.turnRadarRight(1);

        }
        robot.execute();
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        canTurn = false;
        double angleToEnemy = robot.getHeading() - robot.getGunHeading() + event.getBearing();
        robot.setTurnGunRight(normalizeAngle(angleToEnemy)); 
        double firePower = Math.min(3, 400 / event.getDistance());
        robot.setFire(firePower);  

        robot.execute();
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {}

    @Override
    public void onHitWall(HitWallEvent event) {}

    @Override
    public void onBulletHit(BulletHitEvent event) {
        if (event.getEnergy() <= 0) {
            canTurn = true;
        }
    }

    private double normalizeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}


