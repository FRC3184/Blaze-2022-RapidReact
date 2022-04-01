// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Common;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootSpinUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Flywheels m_flywheels;
  private final Sensor_Limelight m_limelight;
  private final Common m_common;
  private double shootRPM;

  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */
  public ShootSpinUp(Common common, Shooter_Flywheels flywheel, Sensor_Limelight limelight, double RPM) {
    m_flywheels = flywheel;
    m_limelight = limelight;
    m_common = common;
    shootRPM = RPM;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //   m_flywheels.setShotSpeed(calcShotSpeedWithLimelight());
      m_flywheels.setShotSpeed(shootRPM);
      m_flywheels.runShooter();
      m_common.setUpToSpeed(m_flywheels.flywheelUpToSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flywheels.stopShooter();
    m_common.setUpToSpeed(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public double calcShotSpeedWithLimelight() {
    double targetDist = m_limelight.getTargetDist();
    double shotSpeed = ShooterConstants.defShotRPM  + targetDist;
    return shotSpeed;
  }
}
