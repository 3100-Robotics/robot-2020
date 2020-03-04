
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Limelight.LimeTurn;
import frc.robot.Drivetrain.DefaultDrive;
import frc.robot.Commands.*;
import static frc.robot.Mapping.RobotCommands.*;

public class RobotContainer {

  private final RobotCommands robotCommands = new RobotCommands();

  // DRIVE CONTROLLER
  public final static XboxController m_driveController = new XboxController(Constants.DriveControllerPort);
  // TECH CONTROLLER
  public final static XboxController m_techController = new XboxController(Constants.TechControllerPort);

  // === Drive === //
  private final AxisButton halfSpeed = new AxisButton(m_driveController, Constants.rightTriggerChannel);
  private final JoystickButton limeTurn = new JoystickButton(m_driveController, Constants.xButtonChannel);

  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);
  private final JoystickButton test = new JoystickButton(m_driveController, Constants.bButtonChannel);
  private final JoystickButton beam = new JoystickButton(m_driveController, Constants.yButtonChannel);

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

  private final SequentialCommandGroup BehindLine = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.BehindLine(m_robotDrive));
  private final SequentialCommandGroup FrontLine = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.FrontLine(m_robotDrive));
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
        // // A split-stick arcade command, with forward/backward controlled by the left
        // // hand, and turning controlled by the right.
        new DefaultDrive(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft),
            () -> m_driveController.getX(GenericHID.Hand.kRight)));
    m_climber.setDefaultCommand(

        new Climb(m_climber, () -> m_techController.getY(GenericHID.Hand.kLeft),
            () -> m_techController.getY(GenericHID.Hand.kRight)));

    m_chooser.addOption("Behind", BehindLine);
    m_chooser.addOption("In-Front", FrontLine);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  private void configureButtonBindings() {

    reset.whenPressed(new Reset());
    test.whileHeld(new Test());
    // beam.whenPressed(robotCommands.beam);

    halfSpeed.whileActiveContinuous(new HalfSpeed(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft),
        () -> m_driveController.getX(GenericHID.Hand.kRight),
        () -> m_driveController.getRawAxis(Constants.rightTriggerChannel)));
    limeTurn.whileHeld(new LimeTurn(m_robotDrive));

    groundEject.whileActiveContinuous(robotCommands.groundEject);
    collectorReverse.whileHeld(robotCommands.collectorReverse);
    // collectorReverse.whenReleased(robotCommands.beam.withTimeout(1));
    groundCollect.whileActiveContinuous(robotCommands.groundCollect);
    // groundCollect.whenInactive(robotCommands.beam.withTimeout(1));
    humanCollect.whileHeld(robotCommands.humanCollect);
    // humanCollect.whenReleased(robotCommands.beam.withTimeout(1));
    deployCollector.toggleWhenPressed(robotCommands.collector);

    shooterRev.toggleWhenPressed(robotCommands.shooterRev);
    shooterFar.toggleWhenPressed(robotCommands.shooterFar);
    shooterNear.toggleWhenPressed(robotCommands.shooterNear);
    shoot.whileHeld(robotCommands.shoot);

  }

  // public Command getAutonomousCommand() {

  // // Create a voltage constraint to ensure we don't accelerate too fast
  // var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new
  // SimpleMotorFeedforward(Constants.ksVolts,
  // Constants.kvVoltSecondsPerMeter, Constants.kaVoltSecondsSquaredPerMeter),
  // Constants.kDriveKinematics, 10);

  // // S-Spline Test
  // var TestStart = new Pose2d(Units.feetToMeters(0.0), Units.feetToMeters(0.0),
  // Rotation2d.fromDegrees(0));
  // var TestEnd = new Pose2d(Units.feetToMeters(5.0), Units.feetToMeters(0.0),
  // Rotation2d.fromDegrees(0));

  // var interiorWaypoints = new ArrayList<Translation2d>();
  // // interiorWaypoints.add(new Translation2d(Units.feetToMeters(0.2),
  // // Units.feetToMeters(0.0)));
  // // interiorWaypoints.add(new Translation2d(Units.feetToMeters(1.3),
  // // Units.feetToMeters(0.0)));
  // // TODO: TRY DELETING ^^^

  // TrajectoryConfig config = new
  // TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
  // Constants.kMaxAccelerationMetersPerSecondSquared);
  // // config.setReversed(false);

  // Trajectory exampleTrajectory =
  // TrajectoryGenerator.generateTrajectory(TestStart, interiorWaypoints, TestEnd,
  // config);

  // // Create config for trajectory
  // // TrajectoryConfig config =
  // // new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
  // // Constants.kMaxAccelerationMetersPerSecondSquared)
  // // // Add kinematics to ensure max speed is actually obeyed
  // // .setKinematics(Constants.kDriveKinematics)
  // // // Apply the voltage constraint
  // // .addConstraint(autoVoltageConstraint);

  // // // An example trajectory to follow. All units in meters.
  // // Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
  // // // Start at the origin facing the +X direction
  // // new Pose2d(0, 0, new Rotation2d(0)),
  // // // Pass through these two interior waypoints, making an 's' curve path
  // // List.of(
  // // new Translation2d(1, 1),
  // // new Translation2d(2, -1)
  // // ),
  // // // End 3 meters straight ahead of where we started, facing forward
  // // new Pose2d(3, 0, new Rotation2d(0)),
  // // // Pass config
  // // config
  // // );

  // RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory,
  // m_robotDrive::getPose,
  // new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
  // new SimpleMotorFeedforward(Constants.ksVolts,
  // Constants.kvVoltSecondsPerMeter,
  // Constants.kaVoltSecondsSquaredPerMeter),
  // Constants.kDriveKinematics, m_robotDrive::getWheelSpeeds, new
  // PIDController(Constants.kPDriveVel, 0.0, 0.0),
  // new PIDController(Constants.kPDriveVel, 0.0, 0.0),
  // // RamseteCommand passes volts to the callback
  // m_robotDrive::tankDriveVolts, m_robotDrive);

  // // Run path following command, then stop at the end.
  // return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

}