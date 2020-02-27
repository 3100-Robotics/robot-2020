package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class DriveForward extends CommandBase {
    public final Drive drive;
    private final double goal, speed;

    public DriveForward(final double goalDistance, final double driveSpeed, final Drive drive) {
        this.drive = drive;
        this.goal = goalDistance;
        this.speed = driveSpeed;

        addRequirements(drive);
    }

    public void initialize() {
        Drive.resetEncoders();
        drive.arcadeDrive(speed, 0);
    }

    public void execute() {
        // double error = drive.getLeftDistance() - drive.getRightDistance();

        // System.out.println("Error");
        // System.out.println(error);
        // System.out.println(drive.getAverageEncoderDistance());

        drive.arcadeDrive(speed, 0);

    }

    public boolean isFinished() {
        return (drive.getAverageEncoderDistance() >= goal);
    }

    public void end(boolean interrupted) {

        drive.arcadeDrive(0, 0);

    }

}