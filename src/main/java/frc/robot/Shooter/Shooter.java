package frc.robot.Shooter;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    public Shooter(){
        super("Shooter");
    }

    public void run() {


    }





    protected void initDefaultCommand() {

        setDefaultCommand(new ShooterRun());

    }
}