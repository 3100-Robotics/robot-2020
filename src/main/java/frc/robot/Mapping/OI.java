
package frc.robot.Mapping;

import java.lang.module.ModuleDescriptor.Requires;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.XBoxStates;
import frc.robot.Mapping.Constants.DriveConstants;
import frc.robot.Robot;
import frc.robot.Commands.TurnToAngle;

public class OI {

    // The robot's subsystems
  

  // The driver's controller
  public final static XBoxStates m_driveController = new XBoxStates(0);
  public final static XBoxStates m_techController = new XBoxStates(1);

  public static JoystickButton turn = new JoystickButton(OI.m_driveController, RobotMap.xButtonChannel);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public OI() {
    // Configure the button bindings
    configureButtonBindings();

    //Configure default commands
   // Set the default drive command to split-stick arcade drive
  //   Robot.drive.setDefaultCommand(
    
  //        // A split-stick arcade command, with forward/backward controlled by the left
  //       // hand, and turning controlled by the right.
  //       new RunCommand(() -> Robot.drive
  //           .arcadeDrive(m_driveController.getLeftStickY(),
  //                        m_driveController.getRightStickX())));
                        

    }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   // Drive at half speed when the right bumper is held
    new JoystickButton(m_driveController, RobotMap.leftBumperChannel)
        .whenPressed(() -> Robot.drive.setMaxOutput(0.5))
        .whenReleased(() -> Robot.drive.setMaxOutput(1));

    // Stabilize robot to drive straight with gyro when left bumper is held
    new JoystickButton(m_driveController, RobotMap.rightBumperChannel).whenHeld(new PIDCommand(
        new PIDController(DriveConstants.kStabilizationP, DriveConstants.kStabilizationI,
                          DriveConstants.kStabilizationD),
        // Close the loop on the turn rate
        Robot.drive::getTurnRate,
        // Setpoint is 0
        0,
        // Pipe the output to the turning controls
        output -> Robot.drive.arcadeDrive(m_driveController.getLeftStickY(), output),
        // Require the robot drive
        Robot.drive));

    // Turn to 90 degrees when the 'X' button is pressed, with a 5 second timeout
    new JoystickButton(m_driveController, RobotMap.xButtonChannel)
        .whenPressed(new TurnToAngle(90, Robot.drive).withTimeout(5));

   
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    // no auto
    return new InstantCommand();
  }


}