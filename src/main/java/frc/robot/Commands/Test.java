package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Mapping.Constants;

public class Test extends CommandBase {

    public Test() {

    }

    public void initialize() {

        System.out.println("High");
        System.out.println(Constants.lightSensorHigh.getValue());
        System.out.println("Low");
        System.out.println(Constants.lightSensorLow.getValue());


    }

    public void execute() {

    }
    public boolean isFinished(){
        return true;

    }

}