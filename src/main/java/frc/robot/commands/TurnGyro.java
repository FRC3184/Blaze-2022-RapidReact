// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import org.opencv.highgui.HighGui;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnGyro extends CommandBase {
  private final Drivetrain m_drive;
  private final boolean m_left;
  private final double m_angle;
  private final double m_speed;
  private double targetHeading;
  private double lowLimit;
  private double upLimit;
  private static final double deadzone = 3.0;

  /**
   * Creates a new DriveTime.
   *
   * @param left Are you turning left?
   * @param angle The desired turn angle
   * @param speed The speed of the turn
   */
  public TurnGyro(boolean left, double angle, double speed, Drivetrain drive) {
    m_left = left;
    m_angle = angle;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetHeading();
    if(m_left) {
      targetHeading = -m_angle;
    } else {
      targetHeading = m_angle;
    }
    lowLimit = targetHeading - deadzone;
    upLimit = targetHeading + deadzone;
  }

  @Override
  public void execute() {
    if (m_left) {
      m_drive.drive(-m_speed, m_speed);
    } else {
      m_drive.drive(m_speed, -m_speed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (m_drive.getYaw() > lowLimit && m_drive.getYaw() < upLimit);
  }
}