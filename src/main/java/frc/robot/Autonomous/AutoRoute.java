package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;

public class AutoRoute extends SequentialCommandGroup {

    public AutoRoute(Drive drive) {

        super(new DriveForward(1, 0.5, drive)

        );

    }

}