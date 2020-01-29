package frc.robot.Autonomous;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle2 extends CommandBase {
  private final Drive m_drive;
  private final double target, speed;

  /**
   * Creates a new TurnToAngle2.
   * 
   * @param targetDegrees the target degrees to turn to
   * @param moveSpeed     how fast you want it to turn
   * @param subsystem     what subsystem it should require
   */
  public TurnToAngle2(double targetDegrees, double moveSpeed, Drive subsystem) {
    m_drive = subsystem;
    target = targetDegrees;
    speed = moveSpeed;

    addRequirements(m_drive);
  }

  public void initialize() {
    m_drive.zeroHeading();
  }

  @Override
  public void execute() {

    m_drive.arcadeDrive(0, speed);

  }

  public boolean isFinished() {

    boolean isFinished = false;

    if (m_drive.getHeading() <= target) {
      isFinished = false;
    } else {
      isFinished = true;
    }

    return isFinished;
  }

  public void end(boolean interrupted) {

    m_drive.arcadeDrive(0, 0);

  }
}