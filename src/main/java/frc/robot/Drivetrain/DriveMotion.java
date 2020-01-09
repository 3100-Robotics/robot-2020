package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Mapping.RobotMap;


//DriveMotion is a command that is used to run the drivetrain
public class DriveMotion extends Command {


    public DriveMotion() {
        super("DriveMotion");
        requires(Robot.drive);

    }


    protected void initialize() {


    }

 
    protected void execute() {

        //Checks to see the mode set in Robot, then sets the controls for the Robot

            //Arcade Drive
        if (Robot.mode == 1){

            Robot.drive.arcadeDrive(-RobotMap.driveControls.getLeftStickY(), -RobotMap.driveControls.getRightStickX());

        }else if (Robot.mode == 2) {
            Robot.drive.tankDrive(RobotMap.driveControls.getLeftStickY(), -RobotMap.driveControls.getRightStickY());
        }

    }



    protected boolean isFinished() {
       
        return false;
    }

    protected void end() {

    }


    protected void interrupted() {
        super.interrupted();
    }
}


