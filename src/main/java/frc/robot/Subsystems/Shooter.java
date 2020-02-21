package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotContainer;

public class Shooter extends SubsystemBase {

    public static boolean isOn = false;
    public static boolean isDeployed = false;

    public final static BooleanSupplier isDeployedSupplier = () -> isDeployed;
    public final static BooleanSupplier isOnSupplier = () -> isOn;

    public static double shooterTopSpeed = 0.325;
    public static double shooterBottomSpeed = 0.325;

   // public final static DoubleSupplier shooterSpeedSupplier = () -> shooterSpeed;

    
    public int test = 0;


    public void shooterFar(){

        Constants.angleSolenoid.set(true);
        isDeployed = true;

    }
    public void shooterNear(){

        Constants.angleSolenoid.set(false);
        isDeployed = false;

    }

    public void shooterRev(){

        Constants.shooterTop.set(ControlMode.Velocity, -shooterTopSpeed);
        Constants.shooterBottom.set(ControlMode.Velocity, shooterBottomSpeed);
        isOn = true;
        
 
    }

    public void incrimentUp() {

    //     shooterSpeed = shooterSpeed + 0.01;
    //     System.out.println(shooterSpeed);

    }
    public void incrimentDown(){

    //     shooterSpeed = shooterSpeed - 0.01;
    //     System.out.println(shooterSpeed);

    }


    public void shooterStop(){

        Constants.shooterTop.set(ControlMode.Velocity, 0);
        Constants.shooterBottom.set(ControlMode.Velocity, 0);
        isOn = false;

    }

    public void shoot(double conveyerspeed, double injectorSpeed){

        Constants.shooterTop.set(ControlMode.Velocity, -0.45);
        Constants.shooterBottom.set(ControlMode.Velocity, 0.45);
        Constants.conveyor.set(ControlMode.PercentOutput, injectorSpeed);
        Constants.collector.set(ControlMode.PercentOutput, -injectorSpeed);
        Constants.injector.set(ControlMode.PercentOutput, -injectorSpeed);
      //  isOn = false;

    }
 
public void periodic(){

    SmartDashboard.putBoolean("SHOOTER IS REVED", isOn);

}



}