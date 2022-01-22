// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTime extends CommandBase {
  private final Drivetrain m_drive;
  private final double m_mSecs;
  private final double m_speed;
  private double endTime;

  /**
   * Creates a new DriveTime.
   *
   * @param mSecs The number of milliseconds the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveTime(double mSecs, double speed, Drivetrain drive) {
    m_mSecs = mSecs;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    double startTime = System.currentTimeMillis();
    endTime = startTime + this.m_mSecs;
  }

  @Override
  public void execute() {
    m_drive.drive(-m_speed, -m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}