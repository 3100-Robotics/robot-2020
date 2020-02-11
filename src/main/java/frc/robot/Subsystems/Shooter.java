package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mapping.Constants;

public class Shooter extends SubsystemBase {

public BooleanSupplier isOn;

	public Shooter() {
        
    }

    public void shooterPositon(int position){

        // if(position == 1){
        //     Constants.angleSolenoid.set(true);
        // }else{
        //     Constants.angleSolenoid.set(false);
        // }

    }

    public void shooterRev(double speedTop, double speedBottom){

        Constants.shooterTop.set(ControlMode.PercentOutput, -speedTop);
        Constants.shooterBottom.set(ControlMode.PercentOutput, speedBottom);
 
    }

    public void shoot(double speedTop, double speedBottom, double injectorSpeed){

        Constants.shooterTop.set(ControlMode.PercentOutput, -speedTop);
        Constants.shooterBottom.set(ControlMode.PercentOutput, speedBottom);
        Constants.conveyor.set(ControlMode.PercentOutput, injectorSpeed);
        Constants.injector.set(ControlMode.PercentOutput, -injectorSpeed);

    }
}