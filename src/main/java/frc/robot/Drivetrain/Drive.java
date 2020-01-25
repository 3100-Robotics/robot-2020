/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Mapping.Constants;

import static frc.robot.Mapping.Constants.*;

public class Drive extends SubsystemBase {

    private double limitSpeed = 0;
    private double limitRotate = 0;
    private double scaleSpeed;
    private double scaleRotate;
    private final double moveAccelerationLimit = 0.05;
    private final double rotateAccelerationLimit = 0.08; // Velocity - Tune for different drivetrain, if it's too
                                                         // low/sluggish

    public Drive() {

        zeroHeading();
        Robot.m_gyro.zeroYaw();
        resetEncoders();

    }

    // Arcade Drive, one Joystick controls forwards/backwards, the other controls

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
        double bSpeed = moveSpeed - limitSpeed;
        if (bSpeed > moveAccelerationLimit) {
            limitSpeed += moveAccelerationLimit;

        } else if (bSpeed < -moveAccelerationLimit) {
            limitSpeed -= moveAccelerationLimit;

        } else if (bSpeed <= moveAccelerationLimit) {
            limitSpeed = moveSpeed;

        }

        // R O T A T E
        double bRotate = rotateSpeed - limitRotate;
        if (bRotate > rotateAccelerationLimit) {
            limitRotate += rotateAccelerationLimit;

        } else if (bRotate < -rotateAccelerationLimit) {
            limitRotate -= rotateAccelerationLimit;

        } else if (bRotate <= rotateAccelerationLimit) {

            limitRotate = rotateSpeed;
        }

        frontLeft.set(ControlMode.PercentOutput, -limitRotate, DemandType.ArbitraryFeedForward, limitSpeed);
        frontRight.set(ControlMode.PercentOutput, +limitRotate, DemandType.ArbitraryFeedForward, limitSpeed);

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
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (frontLeft.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition()) / 2.0;
    }

    public double getLeftDistance() {
        return ((frontLeft.getSelectedSensorPosition() * 2 * Math.PI * 6) / 512);// * 0.0001;
    }

    public double getRightDistance() {
        return frontRight.getSelectedSensorPosition();// * 0.001;
    }

    public void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
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
        Robot.m_gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(Robot.m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return Robot.m_gyro.getRate() * (kGyroReversed ? -1.0 : 1.0);
    }

    /*
     * 
     * Prevents the robot from moving with a small amount of input
     * 
     */
    private static double deadband(final double input) {
        if (Math.abs(input) < 0.2) {
            return 0;
        } else {
            return input;
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        // System.out.println(m_gyro.isRotating());
        // System.out.println(Robot.m_gyro.getAngle());
        // System.out.println(getHeading());
        // System.out.println("Left");
        System.out.println(getLeftDistance());
        // System.out.println("Right");
        // System.out.println(getRightDistance());
    }

}
