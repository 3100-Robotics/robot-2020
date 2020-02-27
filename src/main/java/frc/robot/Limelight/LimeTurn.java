package frc.robot.Limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Drivetrain.Drive;

public class LimeTurn extends CommandBase{

    private final Drive m_drive;
    final double STEER_K = 0.15;

    public LimeTurn(Drive subsystem){

        m_drive = subsystem;
        addRequirements(m_drive);
    }


    public void execute(){

        //TODO: CREATE LOGIC TO TURN AT ONE SPEED TILL CLOSE, THEN A CONSTANT VALUE TILL IT GETS TO 0*


        if (Robot.v < 1.0)
        {
          return;
        }
        else {
            while(Math.abs(Robot.x) > 0.1){

                m_drive.arcadeDrive(0.0, 0.3 * Math.signum(Robot.x));

            }
      
        }

    }

}