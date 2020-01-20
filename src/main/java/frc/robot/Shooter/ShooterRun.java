package frc.robot.Shooter;

import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import frc.robot.Mapping.SpeedControllerSetUp;

public class ShooterRun extends Command {

    CANCoder m_leftEncoder = new CANCoder(3);


    public ShooterRun() {
        super("ShooterRun");
      //  requires(Shooter);

    }

 


    protected void initialize() {


    }


    protected void execute() {

        //System.out.println("Test");

         //   OI.m_robotShooter.run();
            
  
        

    }


    protected boolean isFinished() {
    
        return false;
    }

 
    protected void end() {

    }


    protected void interrupted() {
       
    }
}
