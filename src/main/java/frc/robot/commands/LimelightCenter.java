// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightCenter extends CommandBase {
  private final Drivetrain m_drive;
  private final Sensor_Limelight m_limelight;
  private double skew, rotate;
  private double driveSpeed;
  private boolean centeringType;    // true == skew, false == rotate
  private double rotateDZ = 1;

  /**
   * Creates a new DriveTime.
   *
   * @param left Are you turning left?
   * @param angle The desired turn angle
   * @param speed The speed of the turn
   */
  public LimelightCenter(double speed, Drivetrain drive, Sensor_Limelight lime, boolean centeringMode) {
    m_drive = drive;
    m_limelight = lime;
    driveSpeed = speed;
    centeringType = centeringMode;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_limelight.turnOnLEDs();
    skew = m_limelight.getSkew();
    rotate = m_limelight.getRotate();
  }

  @Override
  public void execute() {
    if (centeringType) {
      skew = m_limelight.getSkew();
    } else {
      rotate = m_limelight.getRotate();

      if (Math.abs(rotate) < rotateDZ * 10.0) {
        driveSpeed = 0.3;
      } else {
        driveSpeed = 0.5;
      }

      if (rotate > 0 + rotateDZ) {
        m_drive.tankDrive(driveSpeed, -driveSpeed);
      } else if (rotate < 0 - rotateDZ) {
        m_drive.tankDrive(-driveSpeed, driveSpeed);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_limelight.pipelineLEDs();
    m_drive.tankDrive(0, 0);
  }

}