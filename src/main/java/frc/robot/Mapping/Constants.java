package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Encoder;

public final class Constants {

  // === XBOX CHANNELS === //
  // AXES
  public static final int leftXAxisChannel = 0;
  public static final int leftYAxisChannel = 1;
  public static final int leftTriggerChannel = 2;
  public static final int rightTriggerChannel = 3;
  public static final int rightXAxisChannel = 4;
  public static final int rightYAxisChannel = 5;

  // BUTTONS
  public static final int aButtonChannel = 1;
  public static final int bButtonChannel = 2;
  public static final int xButtonChannel = 3;
  public static final int yButtonChannel = 4;

  public static final int leftBumperChannel = 5;
  public static final int rightBumperChannel = 6;

  public static final int backButtonChannel = 7;
  public static final int startButtonChannel = 8;

  public static final int POVU = 0;
  public static final int POVR = 90;
  public static final int POVD = 180;
  public static final int POVL = 270;

  // === DRIVE MOTORS === //
  public static final int frontLeftDriveMotor = 3;
  public static final int backLeftDriveMotor = 1;
  public static final int frontRightDriveMotor = 4;
  public static final int backRightDriveMotor = 2;

  public static final WPI_TalonFX frontLeft = new WPI_TalonFX(frontLeftDriveMotor);
  public static final WPI_TalonFX backLeft = new WPI_TalonFX(backLeftDriveMotor);
  public static final WPI_TalonFX frontRight = new WPI_TalonFX(frontRightDriveMotor);
  public static final WPI_TalonFX backRight = new WPI_TalonFX(backRightDriveMotor);

  // === ENCODER === //
  public static final double MM_TO_IN = 0.0393701;
  public static final double WHEEL_TO_WHEEL_DIAMETER_INCHES = 320 * MM_TO_IN;
  public static final double WHEEL_DIAMETER_INCHES = 5.5;
  public static final double PULSES_PER_ROTATION = 256;
  public static final int leftEncoderPort = 3;
  public static final int rightEncoderPort = 4;
  public static final int kEncoderCPR = 1024;

  public static final double kWheelDiameterInches = 0; // TODO: Find out diameter of wheel inches
  public static final double kEncoderDistancePerPulse = (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;

   // leftEncoder.setDistancePerPulse((2 * Math.PI * WHEEL_DIAMETER_INCHES) / PULSES_PER_ROTATION);
    

  // === GYRO === //
  public static final boolean kGyroReversed = false;

  // === TURN PID === //
  // USED IN TURNTOANGLE
  public static final double kTurnP = 0.4;
  public static final double kTurnI = 0;
  public static final double kTurnD = 0;

  public static final double kMaxTurnRateDegPerS = 100;
  public static final double kMaxTurnAccelerationDegPerSSquared = 300;

  public static final double kTurnToleranceDeg = 5;
  public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

  // === JOYSTICK PORTS === //
  public static final int DriveControllerPort = 0;
  public static final int TechControllerPort = 1;

}
