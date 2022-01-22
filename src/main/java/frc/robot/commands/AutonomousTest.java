// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class AutonomousTest extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonomousTest(Drivetrain subsystem) {
    m_drivetrain = subsystem;
    addCommands(
        // drive straight for 5 seconds
        new DriveDistance(1, 0.2, m_drivetrain)
    );
  }
}
