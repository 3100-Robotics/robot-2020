
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Drivetrain.Drive;
import frc.robot.Shooter.Shooter;
import frc.robot.Subsystems.Collector;
import frc.robot.Drivetrain.DefaultDrive;
import frc.robot.Robot;
import frc.robot.Autonomous.AutoRoute;
import frc.robot.Autonomous.DriveForward;
import frc.robot.Autonomous.TurnToAngle2;
import frc.robot.Commands.*;
import static frc.robot.Mapping.RobotCommands.*;

public class RobotContainer {

  private final RobotCommands robotCommands = new RobotCommands();

  

  // public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

  // DRIVE CONTROLLER
  public final static XboxController m_driveController = new XboxController(Constants.DriveControllerPort);
  // TECH CONTROLLER
  public final static XboxController m_techController = new XboxController(Constants.TechControllerPort);

  private final JoystickButton turnToAngle = new JoystickButton(m_driveController, Constants.xButtonChannel);
  private final JoystickButton driveForward = new JoystickButton(m_driveController, Constants.yButtonChannel);
  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);
  private final JoystickButton halfSpeed = new JoystickButton(m_driveController, Constants.rightBumperChannel);
  private final JoystickButton collectorOn = new JoystickButton(m_techController, Constants.xButtonChannel);
  private final JoystickButton collectorReverse = new JoystickButton(m_techController, Constants.leftBumperChannel);
  private final JoystickButton groundEject = new JoystickButton(m_techController, Constants.leftTriggerChannel);
  private final JoystickButton humanCollect = new JoystickButton(m_techController, Constants.rightBumperChannel);
  private final JoystickButton groundCollect = new JoystickButton(m_techController, Constants.rightTriggerChannel);
  private final JoystickButton collectorUpDown = new JoystickButton(m_techController, Constants.aButtonChannel);
  private final POVButton shooterFar = new POVButton(m_techController, Constants.POVU);
  private final POVButton shooterNear = new POVButton(m_techController, Constants.POVD);

  private final POVButton test = new POVButton(m_driveController, Constants.POVU);

  private final Command AutoRoute = new AutoRoute(m_robotDrive);
   // A chooser for autonomous commands
 SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, RobotContainer devices, and
   * commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft),
            () -> m_driveController.getX(GenericHID.Hand.kRight)));

    m_chooser.addOption("Auto", AutoRoute);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // turnToAngle.whenPressed(new TurnToAngle(90, m_robotDrive).withTimeout(5));
    turnToAngle.whenPressed(new TurnToAngle2(90, 0.4, m_robotDrive));
    driveForward.whenPressed(new DriveForward(60, 0.4, m_robotDrive));
    reset.whenPressed(new Reset());
    test.whenPressed(new Test());
    halfSpeed.toggleWhenPressed(robotCommands.halfSpeed);
    collectorOn.whileHeld(robotCommands.collectorOn);

  }

 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

}