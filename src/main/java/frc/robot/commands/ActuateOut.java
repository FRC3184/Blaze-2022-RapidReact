// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hang.Hang_Actuate;

/** An example command that uses an example subsystem. */
public class ActuateOut extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Hang_Actuate m_hangArms;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ActuateOut(Hang_Actuate subsystem) {
    m_hangArms = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_hangArms);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (m_hangArms.getHangDownActuateLimit()){
      m_hangArms.runActuatingArms(0.1);
    // }  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_hangArms.runActuatingArms(0);
  }

  // // Returns true when the command should end.
  // @Override
  // public boolean isFinished() {
  //   return false;
  // }

  // @Override
  // public boolean runsWhenDisabled() {
  //   return false;
  // }
}
