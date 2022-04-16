// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public enum Modes {
        competition,
        test, 
        sim
    }
    public enum TurnDir {
        left, 
        right
    }

    // sets the robot mode, and debug outputs
    public static final class ModeConstants {
        public static final Modes mode = Modes.competition;
        public static final boolean odsDebug = false;
        public static final boolean navxDebug = true;
        public static final boolean driveDebug = false;
        public static final boolean limelightDebug = false;
        public static final boolean intakeRollerDebug = false;
        public static final boolean intakeCenterDebug = false;
        public static final boolean intakeActuateDebug = false;
        public static final boolean shootKickerDebug = false;
        public static final boolean shootFlywheelDebug = false;
        public static final boolean shootHoodDebug = true;
        public static final boolean hangActuateDebug = false;
        public static final boolean hangWinchDebug = false;
    }

    // Constants for the intake
    public static final class IntakeConstants {
        // MOTOR PORTS
        public static final int kIntakeRollerMotorPort = 14;
        public static final int kLeftIntakeArmMotorPort = 19;
        public static final int kRightIntakeArmMotorPort = 5;
        public static final int kCentererLeftMotorPort = 13;
        public static final int kCentererRightMotorPort = 4;
        // LIMIT SWITCH PORTS
        public static final int intakeActuateUpLeftLimitPort = 6;
        public static final int intakeActuateUpRightLimitPort = 7;
        public static final int intakeActuateDownLeftLimitPort = 8;
        public static final int intakeActuateDownRightLimitPort = 9;
        // DEFAULT POWER SETTINGS
        public static final double defIntakePower = 0.3;
        // INVERSION SETTINGS
        public static final boolean intakeRollerInverted = false;
        public static final boolean intakeLeftArmInverted = true;
        public static final boolean intakeRightArmInverted = false;
        public static final boolean intakeCentererLeftInverted = true;
        public static final boolean intakeCentererRightInverted = false;

    }

    // Constants for the shooter
    public static final class ShooterConstants {
        public static final double defShotRPM = 2500;
        public static final double defKickerOutRPM = -1500;
        public static final double defKickerInRPM = 1000;
        public static final double defHoodRPM = 1000;

        public static final double ODSlimit = 200;
        // MOTOR PORTS
        public static final int kBackShooterMotorPort = 1;
        public static final int kFrontShooterMotorPort = 10;
        public static final int kKickerMotorPort = 12;
        public static final int kHoodMotorPort = 11;

        public static final int highODSPort = 0;
        public static final int lowODSPort = 5;
        public static final int hoodDownLimitPort = 3;
        public static final int hoodUpLimitPort = 2;

        public static final double hoodMaxEncVal = 45;

        // INVERSION SETTINGS
        public static final boolean frontShooterInverted = true;
        public static final boolean backShooterInverted = true;
        public static final boolean kickerInverted = true;
        public static final boolean hoodInverted = true;
    }

    // Constants for the shooter
    public static final class HangConstants {
        public static final int kWinchLeft = 15;
        public static final int kWinchRight = 9;
        public static final int kActuateLeft = 18;
        public static final int kActuateRight = 6;

        public static final int hangActuateUpLeftLimitPort = 0;
        public static final int hangActuateUpRightLimitPort = 1;
        // public static final int hangActuateDownLeftLimitPort = 2;
        // public static final int hangActuateDownRightLimitPort = 3;
        public static final int hangWinchLeftLimitPort = 4;
        public static final int hangWinchRightLimitPort = 5;

        // INVERSION SETTINGS
        public static final boolean winchLeftInverted = false;
        public static final boolean winchRightInverted = true;
        public static final boolean actuateLeftInverted = false;
        public static final boolean actuateRightInverted = true;
    } 

    // Constants for the drivetrain
    public static final class DriveConstants {
        public static final double inchToClickScaler = 0.67/*89655*/;
        // DRIVE CONSTANTS SHOULD DEFAULT TO COMP ROBOT SETTINGS
        // Motor CAN port
        public static final int kLeftFrontMotorPort = 16;
        public static final int kLeftBackMotorPort = 17;
        public static final int kRightFrontMotorPort = 8;
        public static final int kRightBackMotorPort = 7;

        // Motor grouping for encoders
        public static final int[] kLeftEncoderPorts = new int[] {kLeftFrontMotorPort, kLeftBackMotorPort};
        public static final int[] kRightEncoderPorts = new int[] {kRightFrontMotorPort, kRightBackMotorPort};

        // Motor inverted?
        public static final boolean rightInverted = true;
        public static final boolean leftInverted = false;

        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;

        public static final double kMaxTurnRateDegPerS = 100;
        public static final double kMaxTurnAccelerationDegPerSSquared = 300;

        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
        

        // Gyro PID Constants 
        /* The following PID Controller coefficients will need to be tuned */
        /* to match the dynamics of your drive system.  Note that the      */
        /* SmartDashboard in Test mode has support for helping you tune    */
        /* controllers by displaying a form where you can enter new P, I,  */
        /* and D constants and test the mechanism.                         */
        
        public static final double kP = 0.03;
        public static final double kI = 0.00;
        public static final double kD = 0.00;
        public static final double kF = 0.00;

        /* This tuning parameter indicates how close to "on target" the    */
        /* PID Controller will attempt to get.                             */

        static final double kToleranceDegrees = 2.0f;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kGunnerControllerPort = 1;
    }

    public double degToRad (double deg) {
        return deg * (3.14159 / 180.0);
    }
}
