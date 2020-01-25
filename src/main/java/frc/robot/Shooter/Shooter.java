package frc.robot.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public Shooter() {
        super();
    }

    public void run() {

        // TODO: This is what I can use to test PID Tuning
        // RobotMap.driveControls.getRightTrigger() * 22000

        // RobotMap.driveControls.getRightTrigger();
        // RobotMap.rightDriveMotor.set(ControlMode.Velocity, 11000); //22000 is
        // maximum, 100% power

    }

}