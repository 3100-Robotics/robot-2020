
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Drivetrain.Drive;
import frc.robot.Shooter.Shooter;
import frc.robot.Drivetrain.DefaultDrive;
//import frc.robot.Shooter.Shooter;
import frc.robot.Robot;
import frc.robot.Commands.*;

public class RobotContainer {
  
  private final Drive m_robotDrive = new Drive();
  public final static Shooter m_shooter = new Shooter();

  //public static AHRS ahrs = new AHRS(SPI.Port.kMXP); 
  
 

  // DRIVE CONTROLLER
  public final static XboxController m_driveController = new XboxController(Constants.DriveControllerPort);
  // TECH CONTROLLER
  public final static XboxController m_techController = new XboxController(Constants.TechControllerPort);


  private final JoystickButton turnToAngle = new JoystickButton(m_driveController, Constants.xButtonChannel);
  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);
  private final JoystickButton halfSpeed = new JoystickButton(m_driveController, Constants.rightBumperChannel);
  private final JoystickButton test = new JoystickButton(m_driveController, Constants.yButtonChannel);



  /**
   * The container for the robot.  Contains subsystems, RobotContainer devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive,
            () -> -m_driveController.getY(GenericHID.Hand.kLeft),
            () -> -m_driveController.getX(GenericHID.Hand.kRight)));
   
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    turnToAngle.whenPressed(new TurnToAngle(45, m_robotDrive).withTimeout(5));
    reset.whenPressed(new Reset());
    halfSpeed.whenPressed(() -> m_robotDrive.setMaxOutput(0.5));
    halfSpeed.whenReleased(() -> m_robotDrive.setMaxOutput(1));
    test.toggleWhenPressed(new Test());

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