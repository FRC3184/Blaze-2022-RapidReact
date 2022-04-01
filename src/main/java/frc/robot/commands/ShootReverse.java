// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Common;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootReverse extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Shooter_Kicker m_kicker;

  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */
  public ShootReverse(Shooter_Kicker kicker) {
    m_kicker = kicker;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_kicker);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_kicker.runKicker(-ShooterConstants.defKickerInRPM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_kicker.runKicker(0);
  }
}
