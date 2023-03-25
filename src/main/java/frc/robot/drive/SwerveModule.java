package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
    WPI_TalonFX turn, drive;
    String smkey;

    public SwerveModule (WPI_TalonFX turn, WPI_TalonFX drive, String key) {
        this.turn = turn;
        this.drive = drive;
        this.smkey = key;

        this.turn.config_kP(0, 0.01);
        this.turn.config_kI(0, 0.0);
        this.turn.config_kD(0, 0.005);

        this.drive.config_kP(0, 0.01);
        this.drive.config_kI(0, 0.0);
        this.drive.config_kD(0, 0.005);
    }

    public double getOptimisedAngle (double current, double angle) {
        double delta = angle - current;

        if(Math.abs(delta) > 180) {
            delta = 360 + delta;
        }

        return delta;
    }

    public void update(double angle, double speed) {
        double currentAngle = this.turn.getSelectedSensorPosition() / (2048 / 360);

        double targetAngle = this.getOptimisedAngle(currentAngle, angle) * (2048 / 360) + this.turn.getSelectedSensorPosition();
        
        SmartDashboard.putNumber(smkey+"_turn", targetAngle / (2048 / 360));
        SmartDashboard.putNumber(smkey+"_drive", speed);
        SmartDashboard.updateValues();
        
        this.turn.set(ControlMode.Position, targetAngle);

        this.drive.set(ControlMode.Velocity, speed);
    }
}
