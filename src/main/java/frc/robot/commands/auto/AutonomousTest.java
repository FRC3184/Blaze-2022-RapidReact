// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.navigation.DriveGyroDistance;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class AutonomousTest extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Sensor_NavX m_navX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param drive The subsystem used by this command.
   */
  public AutonomousTest(Drivetrain drive, Sensor_NavX navX, Sensor_Limelight lime) {
    m_drivetrain = drive;
    m_navX = navX;
    addCommands(
        // drive straight for 5 seconds
        // new CenterTarget(TurnDir.right, 45.0, 0.3, m_drivetrain, m_navX, m_limelight)
        new DriveGyroDistance(100, -0.5, m_drivetrain, m_navX)
        // new DriveDistance(5, 0.3, m_drivetrain)
        // new CenterIntake(m_center)
        // new TurnGyroPID(45, drive, navX)
        // new TurnGyro(Constants.TurnDir.right, 45, 0.7, m_drivetrain, m_navX)
    );
  }
}
