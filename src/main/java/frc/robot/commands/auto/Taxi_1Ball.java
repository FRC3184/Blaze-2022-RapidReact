// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.DriveDistance;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeDeploy;
import frc.robot.commands.TurnGyro;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake_Actuate;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class Taxi_1Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Intake_Actuate m_intakeActuate;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public Taxi_1Ball(Drivetrain driveSS, Intake_Actuate intakeActSS) {

    m_drivetrain = driveSS;
    m_intakeActuate = intakeActSS;

    addCommands(
      new DriveDistance(10, 0.5, m_drivetrain)
    );
  }
}
