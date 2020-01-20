package frc.robot.Commands;

import java.util.Set;


import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Mapping.RobotContainer;

public class Test extends CommandBase{

    public Test() {

        RobotContainer.ahrs.reset();

    }

    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }





}