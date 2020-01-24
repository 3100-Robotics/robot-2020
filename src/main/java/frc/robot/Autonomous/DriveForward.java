package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Drivetrain.Drive;

public class DriveForward extends CommandBase{
    public final Drive drive;
    private final double distance, speed;

    public DriveForward(final double driveDistance, final double driveSpeed, final Drive drive) {
        this.drive = drive;
        this.distance = driveDistance;
        this.speed = driveSpeed;

        addRequirements(drive);
    }

    public void initialize() {
        drive.resetEncoders();

        if (Robot.mode == 1) {
            drive.arcadeDrive(speed, 0);
        }
        else if (Robot.mode == 2 ){
            drive.tankDrive(speed, speed);
        }
    }


    public void execute() {
         double error = drive.getLeftDistance() - drive.getRightDistance();

         if (Robot.mode == 1) {
            drive.arcadeDrive(speed + 1 * error, 0);
        }
        else if (Robot.mode == 2 ){
            drive.tankDrive(speed + 1 * error, speed - 1 * error);
        }
        
        
    }

    public boolean isFinished(){
        return (drive.getAverageEncoderDistance() >= distance);
    }

    public void end(boolean interrupted) {
        if (Robot.mode == 1) {
            drive.arcadeDrive(0, 0);
        }
        else if (Robot.mode == 2 ){
            drive.tankDrive(0, 0);
        }
    }

}