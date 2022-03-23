// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  private final Drivetrain m_drive;
  private final double m_inches;
  private final double m_speed;
  private double clicks;

  /**
   * Creates a new DriveTime.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveDistance(double inches, double speed, Drivetrain drive) {
    m_inches = inches;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    clicks = m_drive.inchToClicks(m_inches);
    m_drive.resetEncoders();
    m_drive.resetEncoders();
    m_drive.resetEncoders();
  }

  @Override
  public void execute() {
    m_drive.aDrive(m_speed, m_speed);
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