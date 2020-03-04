package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class FrontLine extends SequentialCommandGroup {

    public FrontLine(Drive drive) {

        
        super(
            new RobotCommands().shooterFar,
            new RobotCommands().retractCollector2,
            new AutoRev(3, 0.34, 0.29),
            new AutoShoot(3, 0.34, 0.29),
            new DriveForward(1 * Constants.moveConstant, 0.8, drive)
            // new TurnToAngle2(75, 0.8, drive)
            
            
            

        );

    }

}