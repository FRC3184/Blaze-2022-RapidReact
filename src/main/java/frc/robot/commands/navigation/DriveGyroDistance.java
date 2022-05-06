// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_NavX;

public class DriveGyroDistance extends CommandBase {
  private final Drivetrain m_drive;
  private final Sensor_NavX m_navX;

  private final double m_inches;
  private double m_speed;
  private double m_lspeed;
  private double m_rspeed;
  private double clicks;
  private double targetHeading = 0;
  private double currHeading = 0;
  private double error, delta;
  private double kP = 0.01;

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
    m_lspeed = 0;
    m_rspeed = 0;
    addRequirements(m_drive, m_navX);
  }

  @Override
  public void initialize() {
    dashboardOut();
    clicks = m_drive.inchToClicks(m_inches);
    m_drive.resetEncoders();
    m_drive.resetEncoders();
    m_drive.resetEncoders();
    currHeading = m_navX.getYaw();
    m_navX.resetHeading();
    m_lspeed = 0;
    m_rspeed = 0;
  }

  @Override
  public void execute() {
    dashboardOut();
    if (m_speed > 0 && (m_lspeed < m_speed || m_rspeed < m_speed))
    {
      m_lspeed += 0.02;
      m_rspeed += 0.02;
    } else if (m_speed < 0 && (m_lspeed > m_speed || m_rspeed > m_speed))
    {
      m_lspeed -= 0.02;
      m_rspeed -= 0.02;
    }
    error = -(m_navX.getYaw());
    // delta = error * kP;
    delta = 0;
    m_drive.tankDrive(m_lspeed + delta, m_rspeed - delta);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getAverageEncoderDistance()) >= clicks;
  }

  public void dashboardOut() {
    // SmartDashboard.putNumber("LEFTDriveSpeed", m_lspeed);
    // SmartDashboard.putNumber("RIGHTDriveSpeed", m_rspeed);
    // SmartDashboard.putNumber("error", error);
    // SmartDashboard.putNumber("delta", delta);




  }
}