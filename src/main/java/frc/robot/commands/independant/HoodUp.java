// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.independant;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter.Shooter_Hood;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class HoodUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Hood m_hood;

  private double m_mSecs;
  private double endTime;
  private boolean timerOn = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public HoodUp(Shooter_Hood subsystem) {
    m_hood = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_hood);
  }

  public HoodUp(Shooter_Hood subsystem, double time) {
    m_hood = subsystem;
    m_mSecs = time;
    timerOn = true;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (timerOn) {
      double startTime = System.currentTimeMillis();
      endTime = startTime + this.m_mSecs;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_hood.runHood(ShooterConstants.defHoodRPM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hood.stopHood();
  }

  @Override
  public boolean isFinished() {
    if (timerOn){
      return System.currentTimeMillis() >= endTime;
    } else {
      return false;
    }
  }
}
