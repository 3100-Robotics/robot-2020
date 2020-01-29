package frc.robot.Mapping;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Autonomous.TurnToAngle2;
import frc.robot.Drivetrain.Drive;
import frc.robot.Shooter.Shooter;
import frc.robot.Subsystems.Collector;

import static frc.robot.Mapping.Constants.*;

public class RobotCommands{

    public final static Drive m_robotDrive = new Drive();
    public final Shooter m_shooter = new Shooter();
    public final Collector m_collector = new Collector();


   

    // === Collector === //
    public final InstantCommand collectorOn = new InstantCommand(
        () -> m_collector.collectFromGround(), 
        m_collector
    );

    // === Drive === //
    public final StartEndCommand halfSpeed = new StartEndCommand(
        () -> m_robotDrive.setMaxOutput(0.5), 
        () -> m_robotDrive.setMaxOutput(1), 
        m_robotDrive);

}

