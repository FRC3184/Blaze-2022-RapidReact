// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.navigation;

import frc.robot.Constants.TurnDir;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnGyro extends CommandBase {
  private final Drivetrain m_drive;
  private final Sensor_NavX m_navX;
  private final TurnDir m_dir;
  private final double m_angle;
  private double m_speed;
  private double targetHeading;
  private double lowLimit;
  private double upLimit;
  private double error;
  private double kP = 0.05;
  private double delta;
  private static final double deadzone = 3.0;

  /**
   * Creates a new DriveTime.
   *
   * @param left Are you turning left?
   * @param angle The desired turn angle
   * @param speed The speed of the turn
   */
  public TurnGyro(TurnDir dir, double angle, double speed, Drivetrain drive, Sensor_NavX navX) {
    m_dir = dir;
    m_angle = angle;
    m_speed = speed;
    m_drive = drive;
    m_navX = navX;
    addRequirements(m_drive, navX);
  }

  @Override
  public void initialize() {
    m_navX.resetHeading();
    if(m_dir == TurnDir.left) {
      targetHeading = -m_angle;
    } else {
      targetHeading = m_angle;
    }
    lowLimit = targetHeading - deadzone;
    upLimit = targetHeading + deadzone;
  }

  @Override
  public void execute() {
    // negative = left, positive = right
    error = targetHeading - m_navX.getYaw();
    delta = Math.pow((error * 2), kP) - 1;
    if (delta > 1.0) {
      delta = 1.0;
    } else if (delta < -1.0) {
      delta = -1.0;
    }
    m_drive.tankDrive(delta, -delta);


    // if (m_dir == TurnDir.left) {
    //   m_drive.tankDrive(-m_speed - delta, m_speed);
    // } else {
    //   m_drive.tankDrive(m_speed - delta, -m_speed);
    // }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (m_navX.getYaw() > lowLimit && m_navX.getYaw() < upLimit);
  }
}