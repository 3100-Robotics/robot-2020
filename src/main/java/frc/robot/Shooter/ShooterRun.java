package frc.robot.Shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Mapping.RobotMap;

public class ShooterRun extends Command {


    public ShooterRun() {
        super("ShooterRun");
        requires(Robot.shooter);

    }

 


    protected void initialize() {


    }


    protected void execute() {

            Robot.shooter.run();
            RobotMap.leftDriveMotor.set(ControlMode.Velocity, 0.9 * 500.0 * 4096.0 / 600.0);
        SmartDashboard.putNumber("Speed", RobotMap.leftDriveMotor.getSelectedSensorVelocity(0));

    }


    protected boolean isFinished() {
    
        return false;
    }

 
    protected void end() {

    }


    protected void interrupted() {
       
    }
}
