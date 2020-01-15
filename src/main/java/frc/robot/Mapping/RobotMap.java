package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class RobotMap {

    //Joystick

    public static int driveControlsChannel = 0;
    public static int techControlsChannel = 1;


    //     D R I V E R   B U T T O N S
    //X B O X
    public static final int rightXAxisChannel = 4;
    public static final int rightYAxisChannel = 5;
    public static final int leftXAxisChannel = 0;
    public static final int leftYAxisChannel = 1;

    public static final int aButtonChannel = 1;
    public static final int bButtonChannel = 2;
    public static final int xButtonChannel = 3;
    public static final int yButtonChannel = 4;

    public static final int rightBumperChannel = 6;
    public static final int leftBumperChannel = 5;

    public static final int startButtonChannel = 8;
    public static final int backButtonChannel = 7;

    public static final int rightTriggerChannel = 3;
    public static final int leftTriggerChannel = 2;

    //Sparks
    public static final int driveFrontLeftChannel = 3;
    public static final int driveFrontRightChannel = 4;
    public static final int driveBackLeftChannel = 1;
    public static final int driveBackRightChannel = 2;
    public static final int auxMotorChannel = 3;
    public static final int auxMotorChannel2 = 4;


    //Solenoids


   // private static int PCM1Channel = 41; //Manually set Pneumatics if they don't work
    private static int pistonChannel1 = 4;
    private static int pistonChannel2 = 7;


    //INIT

    

    public static XBoxStates driveControls = new XBoxStates(driveControlsChannel);
    public static XBoxStates techControls = new XBoxStates(techControlsChannel);

    //

    public static TalonFX leftFrontDriveMotor = new TalonFX(driveFrontLeftChannel);
    public static TalonFX rightFrontDriveMotor = new TalonFX(driveFrontRightChannel);
    public static TalonFX leftBackDriveMotor = new TalonFX(driveBackLeftChannel);
    public static TalonFX rightBackDriveMotor = new TalonFX(driveBackRightChannel);

    public static Spark auxMotor = new Spark(auxMotorChannel);
    public static Spark auxMotor2 = new Spark(auxMotorChannel2);

    public static Solenoid wallPiston = new Solenoid(41, pistonChannel1);
    public static Solenoid testPiston = new Solenoid(41,6);
    public static Solenoid secondPiston = new Solenoid(41, pistonChannel2);
    public static Solenoid test1Piston = new Solenoid(41,5);

}