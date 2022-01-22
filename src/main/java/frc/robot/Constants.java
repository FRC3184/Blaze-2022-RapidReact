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
    // Mode Setting
    public enum Modes {
        competition,
        test, 
        sim
    }

    public static final class ModeConstants {

        public static final Modes mode = Modes.test;
    }

    // Constants for the Programming ROBOT
    public static final class DriveConstants {
        // DRIVE CONSTANTS SHOULD DEFAULT TO COMP ROBOT SETTINGS
        // Motor CAN port
        public static final int kLeftFrontMotorPort = 3;
        public static final int kLeftBackMotorPort = 4;
        public static final int kRightFrontMotorPort = 1;
        public static final int kRightBackMotorPort = 2;

        // Motor grouping for encoders
        public static final int[] kLeftEncoderPorts = new int[] {kLeftFrontMotorPort, kLeftBackMotorPort};
        public static final int[] kRightEncoderPorts = new int[] {kRightFrontMotorPort, kRightBackMotorPort};

        // Motor inverted?
        public static final boolean rightInverted = false;
        public static final boolean leftInverted = true;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
    }
}
