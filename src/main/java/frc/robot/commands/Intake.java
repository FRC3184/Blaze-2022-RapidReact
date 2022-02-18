// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.IntakeArm;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Intake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final IntakeArm m_intakeArm;

  private XboxController driveController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Intake(IntakeArm subsystem) {
    m_intakeArm = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intakeArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (driveController.getLeftTriggerAxis() > 0.1) {
    //   m_intakeArm.runIntakeRoller(driveController.getLeftTriggerAxis());
    // } else if (driveController.getRightTriggerAxis() > 0.1) {
    //   m_intakeArm.runIntakeRoller(-driveController.getRightTriggerAxis());
    // } else {
    //   m_intakeArm.runIntakeRoller(0.0);
    // }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_intakeArm.runIntakeRoller(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean runsWhenDisabled() {
    return false;
  }
}
