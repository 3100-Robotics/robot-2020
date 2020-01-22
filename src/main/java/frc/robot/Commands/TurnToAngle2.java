package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;


/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle2 extends CommandBase {
    private final Drive m_drive;
    private final double target;
   
  
    /**
     * Creates a new TurnToAngle2.
     *
     * @param subsystem The drive subsystem this command wil run on.
     * @param forward The control input for driving forwards/backwards
     * @param rotation The control input for turning
     */
    public TurnToAngle2(double targetDegrees, Drive subsystem) {
      m_drive = subsystem;
      target = targetDegrees;
      
      addRequirements(m_drive);
    }
  
    @Override
    public void execute() {
      
        if(m_drive.getHeading() < target){

            m_drive.arcadeDrive(0, 0.2);
            System.out.println("Turning");
        }else{

           

        }

    }
  }