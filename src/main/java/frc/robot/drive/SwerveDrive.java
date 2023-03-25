package frc.robot.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.math.Vector2;

public class SwerveDrive {
    SwerveModule front_left, front_right, back_left, back_right;
    double max_speed = 5000;
    public SwerveDrive(SwerveModule fl, SwerveModule fr, SwerveModule bl, SwerveModule br) {
        this.front_left = fl;
        this.front_right = fr;
        this.back_left = bl;
        this.back_right = br;
    }

    public void update (double drive, double strafe, double turn) {
        Vector2 direction;
        double aoff = 90;
        
        if(Math.abs(drive) < .1)
            drive=0.00001;
        if(Math.abs(strafe) < .1)
            strafe=0;

        if(new Vector2(strafe, drive).length()< 0.1 && Math.abs(turn) < 0.1) {
            direction = new Vector2(0, 0);
            aoff = 0;
        }else
            direction = new Vector2(strafe, drive);
        Vector2 turnTransformDirection1 = new Vector2(direction.y, -direction.x).norm().times(new Vector2(-turn, -turn));
        Vector2 turnTransformDirection2 = new Vector2(direction.y, -direction.x).norm().times(new Vector2(turn, turn));
        Vector2 toTarget1 = direction.plus(turnTransformDirection1);
        Vector2 toTarget2 = direction.plus(turnTransformDirection2);
        double angle1 = aoff + Math.atan2(toTarget1.y, toTarget1.x) * (180 / 3.14);
        double angle2 = aoff + Math.atan2(toTarget2.y, toTarget2.x) * (180 / 3.14);
        SmartDashboard.putNumber("a1", angle1);
        SmartDashboard.putNumber("a2", angle2);
        double mag = toTarget1.length() * this.max_speed;

        if(Math.abs(strafe) <= Math.abs(drive)) {
            this.front_left.update(angle1, mag);
            this.front_right.update(angle1, mag);
            this.back_left.update(angle2, mag);
            this.back_right.update(angle2, mag);
        }else{
            this.front_left.update(angle1, mag);
            this.front_right.update(angle2, mag);
            this.back_left.update(angle1, mag);
            this.back_right.update(angle2, mag);
        }
    }
}