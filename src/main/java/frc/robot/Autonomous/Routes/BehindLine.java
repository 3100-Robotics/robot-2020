package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class BehindLine extends SequentialCommandGroup {

    public BehindLine(Drive drive) {

        
        super(
            new RobotCommands().shooterFar,
            new RobotCommands().retractCollector2,
            new AutoRev(3, 0.35, 0.3),
            new AutoShoot(3, 0.35, 0.3),
            new RobotCommands().deployCollector2,
            new RobotCommands().shooterNear,
            new DriveForward(4 * Constants.moveConstant, -0.8, drive),
            new TurnToAngle2(75, 0.8, drive),
            new DriveForward(4 * Constants.moveConstant, 0.8, drive),
            new TurnToAngle2(60, 0.8, drive),
            new RobotCommands().retractCollector2
            
            
            

        );

    }

}