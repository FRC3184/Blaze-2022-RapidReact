// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.independant;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hang.Hang_Winch;

/** An example command that uses an example subsystem. */
public class WinchIn extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Hang_Winch m_hangWinch;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WinchIn(Hang_Winch subsystem) {
    m_hangWinch = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_hangWinch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_hangWinch.runWinchArms(1);  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_hangWinch.runWinchArms(0);
  }
}
