// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_NavX;

public class DriveGyroDistance extends CommandBase {
  private final Drivetrain m_drive;
  private final Sensor_NavX m_navX;

  private final double m_inches;
  private double m_speed;
  private double clicks;
  private double targetHeading = 0;
  private double error, delta;
  private double kP = 0.05;

  /**
   * Creates a new DriveTime.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveGyroDistance(double inches, double speed, Drivetrain drive, Sensor_NavX navX) {
    m_inches = inches;
    m_speed = speed;
    m_drive = drive;
    m_navX = navX;
    addRequirements(m_drive, m_navX);
  }

  @Override
  public void initialize() {
    clicks = m_drive.inchToClicks(m_inches);
    m_drive.resetEncoders();
    m_drive.resetEncoders();
    m_drive.resetEncoders();
    m_navX.resetHeading();
  }

  @Override
  public void execute() {
    error = -targetHeading;
    delta = error * kP;
    m_drive.tankDrive(m_speed + delta, m_speed - delta);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getAverageEncoderDistance()) >= clicks;
  }
}