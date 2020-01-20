/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import static frc.robot.Mapping.Constants.*;

import frc.robot.Mapping.Constants.DriveConstants;

public class Drive extends SubsystemBase {

    PIDController turnController;
    private double limitSpeed = 0;
    private double limitRotate = 0;

    private double scaleSpeed;
    private double scaleRotate;

    private final double moveAccelerationLimit = 0.05;
    private final double rotateAccelerationLimit = 0.08; // Velocity - Tune for different drivetrain, if it's too low,
                                                         // sluggish

                                                        


    static final double Kp = 1.00;
    static final double Ki = 0.00;
    static final double Kd = 0.00;
    double rotateToAngleRate;
    AHRS m_gyro = new AHRS(SPI.Port.kMXP);
    Gyro test;
    private Encoder m_leftEncoder, m_rightEncoder;

    static final double kToleranceDegrees = 2.0f;
    static final double kTargetAngleDegrees = 90.0f;

    public Drive() {

    // Since encoders return pulses, set the proper distance using wheel diameter.
    m_leftEncoder = new Encoder(leftEncoderPort, leftEncoderPort + 1);
    m_leftEncoder.setDistancePerPulse(2048);
    m_rightEncoder = new Encoder(rightEncoderPort, rightEncoderPort + 1);
    m_rightEncoder.setDistancePerPulse(2048);

    }

    // Arcade Drive, one Joystick controls forwards/backwards, the other controls
    // turning
    public void arcadeDrive(double moveSpeed, double rotateSpeed) {

        // Limits for speed, using quadratics and max/min
        moveSpeed = deadband(moveSpeed);
        rotateSpeed = deadband(rotateSpeed);

        scaleSpeed = moveSpeed < 0 ? -0.8 : 0.8;
        scaleRotate = rotateSpeed < 0 ? -0.6 : 0.6;

        moveSpeed *= scaleSpeed * moveSpeed;
        rotateSpeed *= scaleRotate * rotateSpeed;

        // Acceleration Curve, takes the difference of the input value and a limited
        // value
        // Checks to see if it's greater than the limit for acceleration

        // M O V E
        if (moveSpeed - limitSpeed > moveAccelerationLimit) {
            limitSpeed += moveAccelerationLimit;
        } else if (moveSpeed - limitSpeed < -moveAccelerationLimit) {
            limitSpeed -= moveAccelerationLimit;
        } else if (moveSpeed - limitSpeed <= moveAccelerationLimit) {
            limitSpeed = moveSpeed;
        }

        // R O T A T E
        if (rotateSpeed - limitRotate > rotateAccelerationLimit) {
            limitRotate += rotateAccelerationLimit;
        } else if (rotateSpeed - limitRotate < -rotateAccelerationLimit) {
            limitRotate -= rotateAccelerationLimit;
        } else if (rotateSpeed - limitRotate <= rotateAccelerationLimit) {
            limitRotate = rotateSpeed;
        }


        frontLeft.set(ControlMode.PercentOutput, -limitRotate, DemandType.ArbitraryFeedForward,
                limitSpeed);
        frontRight.set(ControlMode.PercentOutput, +limitRotate, DemandType.ArbitraryFeedForward,
                limitSpeed);

    }

    // Tank Drive, one Joystick controls the left, one controls the right.
    public void tankDrive(double leftSpeed, double rightSpeed) {

        // Limits for speed, using quadratics and max/min
        leftSpeed = deadband(leftSpeed);
        rightSpeed = deadband(rightSpeed);

        limitSpeed = leftSpeed < 0 ? -0.8 : 0.8;
        limitSpeed = rightSpeed < 0 ? -0.8 : 0.8;

        leftSpeed *= limitSpeed * leftSpeed;
        rightSpeed *= limitSpeed * rightSpeed;

        // Tells the program to run the driveTank
        // differentialDrive.tankDrive(leftSpeed, rightSpeed);
         frontRight.set(ControlMode.PercentOutput,rightSpeed);
         frontLeft.set(ControlMode.PercentOutput,leftSpeed);
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
    }

    public double getLeftDistance() {
        return m_leftEncoder.getDistance();
      }
    
      public double getRightDistance() {
        return m_rightEncoder.getDistance();
      }
    
      public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
      }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(final double maxOutput) {
         setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        m_gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    }

    // // The command won't work if you only bump the stick a little, a deadband in
    // the
    // // middle
    private double deadband(final double input) {
        if (Math.abs(input) < 0.2) {
            return 0;
        } else {
            return input;
        }
    }
 
 @Override
  public void periodic() {
    // This method will be called once per scheduler run

    System.out.println(m_gyro.isRotating());
  //  System.out.println(m_gyro.getPitch());
  //  System.out.println(m_gyro.getRate());
  //  System.out.println(m_gyro.getRoll());

  }

}
