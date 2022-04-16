// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.independant;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake.Intake_Actuate;

/** An example command that uses an example subsystem. */
public class IntakeDeploy extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Intake_Actuate m_intakeActuate;
  private final double m_mSecs;
  private double endTime;
  private boolean timerOn = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeDeploy(Intake_Actuate intake) {
    m_intakeActuate = intake;
    m_mSecs = 0;
    timerOn = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intakeActuate);
  }

  public IntakeDeploy(Intake_Actuate intake, double mSecs) {
    m_mSecs = mSecs;
    m_intakeActuate = intake;
    timerOn = true;
     // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intakeActuate);
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
    m_intakeActuate.deployIntake(0.15);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeActuate.deployIntake(0.0);
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
