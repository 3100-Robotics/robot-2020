package frc.robot.Drivetrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Mapping.OI;
import frc.robot.Mapping.RobotMap;
import frc.robot.Drivetrain.Drive;


//DriveMotion is a command that is used to run the drivetrain
public class DriveMotion extends Command {


    public DriveMotion() {
        super("DriveMotion");
    

    }


    

    protected void initialize() {


    }

 
    protected void execute() {

        //Checks to see the mode set in Robot, then sets the controls for the Robot

            //Arcade Drive
        if (Robot.mode == 1){

            
            Robot.drive.arcadeDrive(-OI.m_driveController.getLeftStickY(), -OI.m_driveController.getRightStickX());
        

        }else if (Robot.mode == 2) {
            Robot.drive.tankDrive(OI.m_driveController.getLeftStickY(), -OI.m_driveController.getRightStickY());
            
            
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