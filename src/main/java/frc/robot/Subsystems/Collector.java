package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Mapping.Constants;

public class Collector extends SubsystemBase {

public boolean isDeployed = false;

public final BooleanSupplier isDeployedSupplier = () -> isDeployed;

public Collector(){

    
}

public void deployCollector(){

    //Set Piston to extend/retract
    // Constants.intakeSolenoid.set(true);
    isDeployed = true;

}

public void retractCollector(){

    // Constants.intakeSolenoid.set(false);
    isDeployed = false;

}

public void updateBooleans() {
    BooleanSupplier isDeployedSupplier = () -> isDeployed;
}

public void groundEject(double speed){

    Constants.collector.set(ControlMode.PercentOutput, speed);

}

public void wheelSpeed(double speed){

    //Reverse Conveyor and collector
    Constants.collector.set(ControlMode.PercentOutput, speed);
    Constants.conveyor.set(ControlMode.PercentOutput, speed);

}
public void collectorReverse(double speed){

    //Reverse Conveyor and collector
    Constants.collector.set(ControlMode.PercentOutput, speed);
    Constants.conveyor.set(ControlMode.PercentOutput, -speed);

}


public void humanCollect(double speed){

    Constants.conveyor.set(ControlMode.PercentOutput, speed * 1.5);
    Constants.collector.set(ControlMode.PercentOutput, speed);

    
}
public void groundCollect(double speed){

    //Reverse Conveyor and collector
    Constants.collector.set(ControlMode.PercentOutput, -speed);
    Constants.conveyor.set(ControlMode.PercentOutput, speed);

}

public void reverseInjector(double speed){

    Constants.injector.set(ControlMode.PercentOutput, speed);
    

}

}