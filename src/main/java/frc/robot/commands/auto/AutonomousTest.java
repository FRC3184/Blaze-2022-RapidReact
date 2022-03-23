// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.TurnGyro;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Sensor_NavX;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class AutonomousTest extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Sensor_NavX m_navX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonomousTest(Drivetrain subsystem, Sensor_NavX navX) {
    m_drivetrain = subsystem;
    m_navX = navX;
    addCommands(
        // drive straight for 5 seconds
        new TurnGyro(true, 45.0, 0.2, m_drivetrain, m_navX)
    );
  }
}
