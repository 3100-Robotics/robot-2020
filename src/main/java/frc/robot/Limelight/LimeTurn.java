package frc.robot.Limelight;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class LimeTurn extends CommandBase{

    private final Drive m_drive;
    final double STEER_K = 0.15;
    private boolean stop1 = false;
    private double x;
    private double v;

    public LimeTurn(Drive subsystem){

        m_drive = subsystem;
        addRequirements(m_drive);
    }

    public void initialize(){

        x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        v = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

    }

    public void execute(){

        // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        // NetworkTableEntry tx = table.getEntry("tx");
        // NetworkTableEntry tv = table.getEntry("tv");
        // x = tx.getDouble(0.0);
        // v = tv.getDouble(0.0);
        
      //TODO: CREATE LOGIC TO TURN AT ONE SPEED TILL CLOSE, THEN A CONSTANT VALUE TILL IT GETS TO 0*

  
        if (v < 1.0)
        {
          return;
        }
        else {
            while(Math.abs(x) > 3){

            x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            v = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
                m_drive.arcadeDrive(0.0, 0.68 * Math.signum(x));


            }
            stop1 = true;
      
        }

        stop1 = true;
    }
    public boolean isFinished(){

        return stop1;
    }

}