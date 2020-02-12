package frc.robot.Mapping;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Autonomous.TurnToAngle2;
import frc.robot.Drivetrain.Drive;

import frc.robot.Subsystems.Collector;
import frc.robot.Subsystems.Shooter;

import static frc.robot.Mapping.Constants.*;

public class RobotCommands{

    public final static Drive  m_robotDrive = new Drive();
    public final Shooter m_shooter = new Shooter();
    public final Collector m_collector = new Collector();


    // === Drive === //
    public final StartEndCommand halfSpeed = new StartEndCommand(
        () -> m_robotDrive.setMaxOutput(0.5), 
        () -> m_robotDrive.setMaxOutput(1), 
        m_robotDrive);


    // === Collector === //
    public final StartEndCommand groundEject = new StartEndCommand(
      //Sets the collector motor to reverse
        () -> m_collector.groundEject(0.5),
        () -> m_collector.groundEject(0),
        m_collector
    );
    public final StartEndCommand collectorReverse = new StartEndCommand (
      //Sets both the collector and conveyor motors to reverse
        () -> m_collector.collectorReverse(0.5),
        () -> m_collector.collectorReverse(0),
        m_collector
    );
    public final StartEndCommand groundCollect = new StartEndCommand (
      //Sets both the collector and conveyor motors to take in balls
        () -> m_collector.groundCollect(0.5),
        () -> m_collector.groundCollect(0.0),
        m_collector
    );
    public final StartEndCommand humanCollect = new StartEndCommand(
      () -> m_collector.humanCollect(0.5),
      () -> m_collector.humanCollect(0),
      m_collector
    );

    public final StartEndCommand reverseInjector = new StartEndCommand(
      () -> m_collector.reverseInjector(0.3),
      () -> m_collector.reverseInjector(0.0)

    );

    public final StartEndCommand deployCollector = new StartEndCommand(
      //Deploys the collector
        () -> m_collector.deployCollector(), () -> m_collector.retractCollector(), 
        m_collector
    );
    
    // === Shooter === //
    
    public final InstantCommand shooterFar = new InstantCommand(
        () -> m_shooter.shooterPositon(1),
        m_shooter
    );
    public final InstantCommand shooterNear = new InstantCommand(
        () -> m_shooter.shooterPositon(0),
        m_shooter
    );

    public final StartEndCommand shooterRev = new StartEndCommand(
    () -> m_shooter.shooterRev(0.35, 0.35),
    () -> m_shooter.shooterRev(0, 0),
    m_shooter

);

public final StartEndCommand shoot = new StartEndCommand(
    () -> m_shooter.shoot(0.35, 0.35, 0.9),
    () -> m_shooter.shoot(0, 0, 0),
    m_shooter

);

    // public final ConditionalCommand shoot = new ConditionalCommand(
    //     Shooter.shoot, shooterOn, null //Set last term to check weather the shooter is up to speed, if so it will run the first command
    // );


    // === Climber === //
}

