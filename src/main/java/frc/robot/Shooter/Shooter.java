package frc.robot.Shooter;



import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mapping.RobotMap;


public class Shooter extends Subsystem{

    public Shooter(){
        super("Shooter");
    }

    public void run() {

        //RobotMap.driveControls.getRightTrigger() * 22000

       // RobotMap.driveControls.getRightTrigger();
     //   RobotMap.rightDriveMotor.set(ControlMode.Velocity, 11000); //22000 is maximum, 100% power

     System.out.println(RobotMap.leftFrontDriveMotor.getSensorCollection());

    }

    protected void initDefaultCommand() {

        setDefaultCommand(new ShooterRun());

    }
}