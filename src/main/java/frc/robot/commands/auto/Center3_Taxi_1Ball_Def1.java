// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.SpinUpThenKick;
import frc.robot.commands.navigation.DriveDistance;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class Center3_Taxi_1Ball_Def1 extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Shooter_Flywheels m_flywheels;
  private final Shooter_Kicker m_kicker;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public Center3_Taxi_1Ball_Def1(Drivetrain driveSS, Shooter_Flywheels flywheelsSS, Shooter_Kicker kickerSS) {

    m_drivetrain = driveSS;
    m_flywheels = flywheelsSS;
    m_kicker = kickerSS;

    addCommands(
      // shoot first ball
      new SpinUpThenKick(m_kicker,m_flywheels, 2100),
      new DriveDistance(45, 0.5, m_drivetrain)
    );
  }
}
