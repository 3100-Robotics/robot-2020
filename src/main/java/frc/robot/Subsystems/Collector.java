package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mapping.Constants;

public class Collector extends SubsystemBase {

    public static boolean isDeployed = false;

    public final static BooleanSupplier isDeployedSupplier = () -> isDeployed;

    public int lightHigh;
    public int lightLow;

    public Collector() {

    }

    public void lightCheck() {

        // TODO: IF NEEDED ADD BOOLEAN INTERRUPTABLE

        while (lightHigh < 20 || lightLow < 20) {

            if (lightHigh < 20) {

                // Run motor in reverse
                Constants.conveyor.set(ControlMode.PercentOutput, -0.3);

            } else if (lightLow < 20) {

                // Run motor forward
                Constants.conveyor.set(ControlMode.PercentOutput, 0.3);

            }
        }
    }

    public void deployCollector() {

        // Set Piston to extend/retract
        Constants.intakeSolenoid.set(false);
        Constants.intakeSolenoid2.set(true);

        isDeployed = true;

    }

    public void retractCollector() {

        Constants.intakeSolenoid.set(true);
        Constants.intakeSolenoid2.set(false);

        isDeployed = false;

    }

    public void groundEject(double speed) {

        Constants.collector.set(ControlMode.PercentOutput, speed);

    }

    public void wheelSpeed(double collectspeed, double conveyorspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, collectspeed);
        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);

    }

    public void collectorReverse(double collectspeed, double conveyorspeed, double shooterspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, collectspeed);
        Constants.injector.set(ControlMode.PercentOutput, collectspeed * 2);
        Constants.conveyor.set(ControlMode.PercentOutput, -conveyorspeed);
        Constants.shooterTop.set(ControlMode.PercentOutput, -shooterspeed);
        Constants.shooterBottom.set(ControlMode.PercentOutput, -shooterspeed);

    }

    public void humanCollect(double collectspeed, double conveyorspeed) {

        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);
        Constants.collector.set(ControlMode.PercentOutput, collectspeed);

    }

    public void groundCollect(double collectspeed, double conveyorspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, -collectspeed);
        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);

    }

    public void reverseInjector(double speed) {

        Constants.injector.set(ControlMode.PercentOutput, speed);
        Constants.shooterTop.set(ControlMode.PercentOutput, speed * 0.5);
        Constants.shooterBottom.set(ControlMode.PercentOutput, -speed * 0.5);

    }

    public void initialize() {

        lightHigh = Constants.lightSensorHigh.getValue();
        lightLow = Constants.lightSensorHigh.getValue();

    }

    public void execute() {

        lightHigh = Constants.lightSensorHigh.getValue();
        lightLow = Constants.lightSensorHigh.getValue();

    }

}