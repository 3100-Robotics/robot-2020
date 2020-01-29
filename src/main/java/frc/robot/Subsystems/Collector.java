package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {

private boolean isDeployed;

public Collector(){

}

public void deployCollector(){

    //Set Piston to extend/retract

}

public void collectFromGround(){

    //Set Two Motors to run

}

public void collectFromPlayer(){

    //Conveyor motor on, and ground collector in reverse

}

public void groundDispense(){

    //Reverse Conveyor and collector

}

public void reverseGround(){

    //Reverses ground collector

}


}