package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public final class Constants {

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

   // DRIVE MOTORS
   public static final int frontLeftDriveMotor = 3;
   public static final int frontRightDriveMotor = 4;
   public static final int backLeftDriveMotor = 1;
   public static final int backRightDriveMotor = 2;

   
   public static final WPI_TalonFX frontLeft = new WPI_TalonFX(frontLeftDriveMotor);
   public static final WPI_TalonFX backLeft = new WPI_TalonFX(backLeftDriveMotor);
   public static final WPI_TalonFX frontRight = new WPI_TalonFX(frontRightDriveMotor);
   public static final WPI_TalonFX backRight = new WPI_TalonFX(backRightDriveMotor);

   // ENCODER 
   public static final int leftEncoderPort = 0;
   public static final int rightEncoderPort = 2;
   

	public static final class DriveConstants{

    public static final int[] kLeftEncoderPorts = new int[]{0, 1};
    public static final int[] kRightEncoderPorts = new int[]{2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;

    public static final boolean kGyroReversed = false;

    public static final double kStabilizationP = 1;
    public static final double kStabilizationI = 0.5;
    public static final double kStabilizationD = 0;

    //
    public static final double kTurnP = 0.3;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;

    public static final double kMaxTurnRateDegPerS = 100;
    public static final double kMaxTurnAccelerationDegPerSSquared = 300;

    public static final double kTurnToleranceDeg = 5;
    public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 1;
  }
}
