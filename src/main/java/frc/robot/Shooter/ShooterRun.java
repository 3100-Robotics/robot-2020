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

        //System.out.println("Test");

            Robot.shooter.run();
            
        SmartDashboard.putNumber("Speed", RobotMap.rightFrontDriveMotor.getSelectedSensorVelocity(0));

    }


    protected boolean isFinished() {
    
        return false;
    }

 
    protected void end() {

    }


    protected void interrupted() {
       
    }
}
