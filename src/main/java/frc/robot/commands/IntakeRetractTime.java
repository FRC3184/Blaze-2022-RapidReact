// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Intake_Actuate;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeRetractTime extends CommandBase {
  private final Intake_Actuate m_intakeActuate;
  private final double m_mSecs;
  private double endTime;

  /**
   * Creates a new DriveTime.
   *
   * @param mSecs The number of milliseconds the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public IntakeRetractTime(double mSecs, Intake_Actuate intake) {
    m_mSecs = mSecs;
    m_intakeActuate = intake;
    addRequirements(m_intakeActuate);
  }

  @Override
  public void initialize() {
    double startTime = System.currentTimeMillis();
    endTime = startTime + this.m_mSecs;
  }

  @Override
  public void execute() {
    m_intakeActuate.deployIntake(0.1);
  }

  @Override
  public void end(boolean interrupted) {
    m_intakeActuate.deployIntake(0);
  }

  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}