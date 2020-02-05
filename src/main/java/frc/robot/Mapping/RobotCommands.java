package frc.robot.Mapping;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Autonomous.TurnToAngle2;
import frc.robot.Drivetrain.Drive;
import frc.robot.Shooter.Shooter;
import frc.robot.Subsystems.Collector;

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
    public final InstantCommand groundEject = new InstantCommand(
      //  () -> frontLeft.set(1)
    );
    public final InstantCommand collectorReverse = new InstantCommand(
       // () -> frontLeft.set(1)
    );
    public final InstantCommand groundCollect = new InstantCommand(
      //  () -> frontLeft.set(1)
    );
    public final InstantCommand humanCollect = new InstantCommand(
      //  () -> frontLeft.set(1)
    );
    public final InstantCommand collectorUpDown = new InstantCommand(
        //Pneumatic Piston
      //  () -> frontLeft.set(1)
    );

    
    // === Shooter === //
    
    public final InstantCommand shooterFar = new InstantCommand(
     //   () -> frontLeft.set(1)
    );
    public final InstantCommand shooterNear = new InstantCommand(
       // () -> frontLeft.set(1)
    );

    public final InstantCommand shooterOn = new InstantCommand(
   // () -> frontLeft.set(1)
);

    // public final ConditionalCommand shoot = new ConditionalCommand(
    //     Shooter.shoot, shooterOn, null //Set last term to check weather the shooter is up to speed, if so it will run the first command
    // );


    // === Climber === //
}

