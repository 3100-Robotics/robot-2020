
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

        conveyor.configFactoryDefault();
        collector.configFactoryDefault();
        injector.configFactoryDefault();

        shooterTop.configFactoryDefault();
        shooterBottom.configFactoryDefault();
        /*
         * 
         * conveyor. collector. injector.
         * 
         * shooterTop. shooterBottom.
         * 
         */

        frontLeft.setInverted(true);
        backLeft.setInverted(true);
        frontRight.setInverted(true);
        backRight.setInverted(true);
        shooterTop.setInverted(false);

        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);
        backLeft.setNeutralMode(NeutralMode.Coast);
        backRight.setNeutralMode(NeutralMode.Coast);

        conveyor.setNeutralMode(NeutralMode.Brake);
        collector.setNeutralMode(NeutralMode.Brake);
        injector.setNeutralMode(NeutralMode.Brake);

        shooterTop.setNeutralMode(NeutralMode.Coast);
        shooterBottom.setNeutralMode(NeutralMode.Coast);

        shooterTop.enableVoltageCompensation(false);
        shooterBottom.enableVoltageCompensation(false);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        // RobotMap.rightFrontDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
        // 0, 30);
        // RobotMap.rightFrontDriveMotor.config_kP(0, 0.06);
        // RobotMap.rightFrontDriveMotor.config_kD(0, 0.0001 );
        // RobotMap.rightFrontDriveMotor.config_kI(0, 0); //DON'T CHANGE
        // RobotMap.rightFrontDriveMotor.config_kF(0, 1023.0/22425.0);

        // frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        // frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        // frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);

        frontLeft.setSensorPhase(false);
        frontRight.setSensorPhase(false);

        frontLeft.configNominalOutputForward(0);
        frontLeft.configNominalOutputReverse(0);
        frontLeft.configPeakOutputForward(1);
        frontLeft.configPeakOutputReverse(-1);

        frontRight.configNominalOutputForward(0);
        frontRight.configNominalOutputReverse(0);
        frontRight.configPeakOutputForward(1);
        frontRight.configPeakOutputReverse(-1);

        frontLeft.config_kF(0, 0.4995117188);
        frontLeft.config_kP(0, 0.01455192034);
        frontLeft.config_kI(0, 0);
        frontLeft.config_kD(0, 0);

        frontRight.config_kF(0, 0.4995117188);
        frontRight.config_kP(0, 0.01455192034);
        frontRight.config_kI(0, 0);
        frontRight.config_kD(0, 0);

        shooterTop.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
        shooterBottom.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);

        shooterTop.setSensorPhase(false);
        shooterBottom.setSensorPhase(false);

        shooterTop.configNominalOutputForward(0);
        shooterTop.configNominalOutputReverse(0);
        shooterTop.configPeakOutputForward(1);
        shooterTop.configPeakOutputReverse(-1);

        shooterBottom.configNominalOutputForward(0);
        shooterBottom.configNominalOutputReverse(0);
        shooterBottom.configPeakOutputForward(1);
        shooterBottom.configPeakOutputReverse(-1);

        shooterTop.config_kF(0, 0.4995117188);
        shooterTop.config_kP(0, 0.01455192034);
        shooterTop.config_kI(0, 0);
        shooterTop.config_kD(0, 0);

        shooterBottom.config_kF(0, 0.4995117188);
        shooterBottom.config_kP(0, 0.018);
        shooterBottom.config_kI(0, 0);
        shooterBottom.config_kD(0, 0);

        // Possibly use current stuff to get shooting motor to speed
        // TalonSRX talon = new TalonSRX(0);
        // talon.configPeakCurrentLimit(30); // don't activate current limit until
        // current exceeds 30 A ...
        // talon.configPeakCurrentDuration(100); // ... for at least 100 ms
        // talon.configContinuousCurrentLimit(20); // once current-limiting is actived,
        // hold at 20A
        // talon.enableCurrentLimit(true);

    }

}