
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Drivetrain.Drive;
import frc.robot.Subsystems.Collector;
import frc.robot.Drivetrain.DefaultDrive;
import frc.robot.Robot;
import frc.robot.Autonomous.AutoRoute;
import frc.robot.Autonomous.DriveForward;
import frc.robot.Autonomous.TurnToAngle2;
import frc.robot.Commands.*;
import static frc.robot.Mapping.RobotCommands.*;

import java.util.List;

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
  private final AxisButton halfSpeed = new AxisButton(m_driveController, Constants.rightTriggerChannel);
  
  
  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);

  // === Collector === //
  private final AxisButton groundEject = new AxisButton(m_techController, Constants.leftTriggerChannel);
  private final JoystickButton collectorReverse = new JoystickButton(m_techController, Constants.leftBumperChannel);
  private final AxisButton groundCollect = new AxisButton(m_techController, Constants.rightTriggerChannel);
  private final JoystickButton humanCollect = new JoystickButton(m_techController, Constants.rightBumperChannel);
  private final JoystickButton deployCollector = new JoystickButton(m_techController, Constants.aButtonChannel);

  // === Shooter === //
  private final JoystickButton shoot = new JoystickButton(m_driveController, Constants.leftBumperChannel);
  private final JoystickButton shooterRev = new JoystickButton(m_techController, Constants.backButtonChannel);
  private final POVButton shooterFar = new POVButton(m_techController, Constants.POVU);
  private final POVButton shooterNear = new POVButton(m_techController, Constants.POVD);

  // === Climber === //
  private final JoystickButton climberControl = new JoystickButton(m_techController, Constants.leftYAxisChannel);


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
            () -> -m_driveController.getX(GenericHID.Hand.kRight)));

    m_chooser.addOption("Auto", AutoRoute);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  private void configureButtonBindings() {

    // turnToAngle.whenPressed(new TurnToAngle(90, m_robotDrive).withTimeout(5));
    turnToAngle.whileHeld(new TurnToAngle2(90, 0.4, m_robotDrive));
    driveForward.whileHeld(new DriveForward(60, 0.4, m_robotDrive));
    reset.whileHeld(new Reset());
    halfSpeed.toggleWhenActive(robotCommands.halfSpeed);
   
    groundEject.whileActiveContinuous(robotCommands.groundEject);
    collectorReverse.whileHeld(robotCommands.collectorReverse);
    groundCollect.whileActiveContinuous(robotCommands.groundCollect);
    groundCollect.whenInactive(robotCommands.reverseInjector.withTimeout(1));
    humanCollect.whileHeld(robotCommands.humanCollect);
    deployCollector.toggleWhenPressed(robotCommands.deployCollector);
    
    // shoot.whenPressed(robotCommands.shoot);
    shooterRev.toggleWhenPressed(robotCommands.shooterRev);
    shooterFar.toggleWhenPressed(robotCommands.shooterFar);
    shooterNear.toggleWhenPressed(robotCommands.shooterNear);

    shoot.whileHeld(robotCommands.shoot);
    

  }

  
public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.ksVolts,
                                       Constants.kvVoltSecondsPerMeter,
                                       Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
                             Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_robotDrive::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts,
                                   Constants.kvVoltSecondsPerMeter,
                                   Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        m_robotDrive::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0.0, 0.0),
        new PIDController(Constants.kPDriveVel, 0.0, 0.0),
        // RamseteCommand passes volts to the callback
        m_robotDrive::tankDriveVolts,
        m_robotDrive
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  }
 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   return m_chooser.getSelected();
  // }

}