// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake.Intake_Roller;

public class IntakeTime extends CommandBase {
  private final Intake_Roller m_intakeRoller;
  private final double m_mSecs;
  private double endTime;

  /**
   * Creates a new DriveTime.
   *
   * @param mSecs The number of milliseconds the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public IntakeTime(double mSecs, Intake_Roller intake) {
    m_mSecs = mSecs;
    m_intakeRoller = intake;
    addRequirements(m_intakeRoller);
  }

  @Override
  public void initialize() {
    double startTime = System.currentTimeMillis();
    endTime = startTime + this.m_mSecs;
  }

  @Override
  public void execute() {
    m_intakeRoller.intake(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    m_intakeRoller.intake(0);
  }

  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}