
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

  // === Drive === //

  private final JoystickButton turnToAngle = new JoystickButton(m_driveController, Constants.xButtonChannel);
  private final JoystickButton driveForward = new JoystickButton(m_driveController, Constants.yButtonChannel);
  private final JoystickButton halfSpeed = new JoystickButton(m_driveController, Constants.rightTriggerChannel);
  
  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);

  // === Collector === //
  private final JoystickButton groundEject = new JoystickButton(m_techController, Constants.leftTriggerChannel);
  private final JoystickButton collectorReverse = new JoystickButton(m_techController, Constants.leftBumperChannel);
  private final JoystickButton groundCollect = new JoystickButton(m_techController, Constants.rightTriggerChannel);
  private final JoystickButton humanCollect = new JoystickButton(m_techController, Constants.rightBumperChannel);
  private final JoystickButton collectorUpDown = new JoystickButton(m_techController, Constants.aButtonChannel);

  // === Shooter === //
  private final JoystickButton shoot = new JoystickButton(m_driveController, Constants.leftBumperChannel);
  private final JoystickButton shooterOn = new JoystickButton(m_techController, Constants.backButtonChannel);
  private final POVButton shooterFar = new POVButton(m_techController, Constants.POVU);
  private final POVButton shooterNear = new POVButton(m_techController, Constants.POVD);

  // === Climber === //
  private final JoystickButton climberControl = new JoystickButton(m_techController, Constants.leftYAxisChannel);

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

  private void configureButtonBindings() {

    // turnToAngle.whenPressed(new TurnToAngle(90, m_robotDrive).withTimeout(5));
    turnToAngle.whenPressed(new TurnToAngle2(90, 0.4, m_robotDrive));
    driveForward.whenPressed(new DriveForward(60, 0.4, m_robotDrive));
    reset.whenPressed(new Reset());
    test.whenPressed(new Test());
    halfSpeed.toggleWhenPressed(robotCommands.halfSpeed);

    groundEject.whileHeld(robotCommands.groundEject);
    collectorReverse.whileHeld(robotCommands.collectorReverse);
    groundCollect.whileHeld(robotCommands.groundCollect);
    humanCollect.whileHeld(robotCommands.humanCollect);
    collectorUpDown.toggleWhenPressed(robotCommands.collectorUpDown);
    
    // shoot.whenPressed(robotCommands.shoot);
    shooterOn.toggleWhenPressed(robotCommands.shooterOn);

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