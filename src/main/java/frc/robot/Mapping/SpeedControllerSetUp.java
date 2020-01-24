
package frc.robot.Mapping;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import static frc.robot.Mapping.Constants.*;

public class SpeedControllerSetUp {


    public void configure(){

        frontLeft.configFactoryDefault();
        backLeft.configFactoryDefault();
        frontRight.configFactoryDefault();
        backRight.configFactoryDefault();

        frontLeft.setInverted(false);
        backLeft.setInverted(false);
        frontRight.setInverted(true);
        backRight.setInverted(true);

        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        // RobotMap.rightFrontDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
        // RobotMap.rightFrontDriveMotor.config_kP(0, 0.06);
        // RobotMap.rightFrontDriveMotor.config_kD(0, 0.0001 );
        // RobotMap.rightFrontDriveMotor.config_kI(0, 0); //DON'T CHANGE
        // RobotMap.rightFrontDriveMotor.config_kF(0, 1023.0/22425.0);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


    }

}