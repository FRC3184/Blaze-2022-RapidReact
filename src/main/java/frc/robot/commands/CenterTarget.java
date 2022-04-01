// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.TurnDir;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Sensors.Sensor_NavX;

import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CenterTarget extends CommandBase {
  private final Drivetrain m_drive;
  private final Sensor_NavX m_navX;
  private final Sensor_Limelight m_limelight;
  private final double m_speed;
  private static final double deadzone = 3.0;
  private double skew;
  private static final double leftBound = 89.5;
  private static final double rightBound = 0.5;
  private double error;
  private double targetHeading = 0;
  private double kP = -1.0/10.0;
  private double runSpeed = 0;

  /**
   * Creates a new DriveTime.
   *
   * @param left Are you turning left?
   * @param angle The desired turn angle
   * @param speed The speed of the turn
   */
  public CenterTarget(TurnDir dir, double angle, double speed, Drivetrain drive, Sensor_NavX navX, Sensor_Limelight lime) {
    m_speed = speed;
    m_drive = drive;
    m_navX = navX;
    m_limelight = lime;
    addRequirements(m_drive);
    addRequirements(m_navX);
  }

  @Override
  public void initialize() {
    m_navX.resetHeading();
    m_limelight.turnOnLEDs();
    skew = m_limelight.getSkew();
  }

  @Override
  public void execute() {
    // get skew and convert value
    skew = m_limelight.getSkew(); 
    if (skew > 45) {
      skew = skew - 90.0;
    }
    // calc error, and repsective speed
    error = targetHeading - skew;
    // runSpeed = m_speed * kP * error;
    runSpeed = Math.log10(error) + 0.2;
    if (error > 0) {
      runSpeed = runSpeed * -1.0;
    }
    SmartDashboard.putNumber("RUNSPEED", runSpeed);
    if (runSpeed < 0.2) {
      runSpeed = 0.2;
    } else if(runSpeed > 1.0) {
      runSpeed = 1.0;
    }

    m_drive.tankDrive(-runSpeed, runSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_limelight.pipelineLEDs();
    m_drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    boolean reachedTarget;
    return (m_limelight.hasValidTarget() && (m_limelight.getSkew() <= rightBound || m_limelight.getSkew() >= leftBound));
  }
}