
package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import static frc.robot.Mapping.Constants.*;

public class SpeedControllerSetUp {

    public void configure() {

        frontLeft.configFactoryDefault();
        backLeft.configFactoryDefault();
        frontRight.configFactoryDefault();
        backRight.configFactoryDefault();

        frontLeft.setInverted(true);
        backLeft.setInverted(true);
        frontRight.setInverted(false);
        backRight.setInverted(false);

        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        // RobotMap.rightFrontDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
        // 0, 30);
        // RobotMap.rightFrontDriveMotor.config_kP(0, 0.06);
        // RobotMap.rightFrontDriveMotor.config_kD(0, 0.0001 );
        // RobotMap.rightFrontDriveMotor.config_kI(0, 0); //DON'T CHANGE
        // RobotMap.rightFrontDriveMotor.config_kF(0, 1023.0/22425.0);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        // Possibly use currnt stuff to get shooting motor to speed
        // TalonSRX talon = new TalonSRX(0);
        // talon.configPeakCurrentLimit(30); // don't activate current limit until
        // current exceeds 30 A ...
        // talon.configPeakCurrentDuration(100); // ... for at least 100 ms
        // talon.configContinuousCurrentLimit(20); // once current-limiting is actived,
        // hold at 20A
        // talon.enableCurrentLimit(true);

    }

}