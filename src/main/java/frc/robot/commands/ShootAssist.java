// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Common;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootAssist extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Sensor_Limelight m_limelight;
  private final Shooter_Kicker m_kicker;
  private final Intake_Centerer m_center;
  private final Intake_Roller m_roller;
  private final Common m_common;

  private double m_mSecs;
  private double endTime;
  private boolean timerOn = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */
  public ShootAssist(Common common, Sensor_Limelight limelight, Shooter_Kicker kicker, Intake_Centerer center, Intake_Roller roller) {
    m_limelight = limelight;
    m_kicker = kicker;
    m_center = center;
    m_roller = roller;
    m_common = common;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_kicker, m_center);
  }

  public ShootAssist(Common common, Sensor_Limelight limelight, Shooter_Kicker kicker, Intake_Centerer center, Intake_Roller roller, double time) {
    m_limelight = limelight;
    m_kicker = kicker;
    m_center = center;
    m_roller = roller;
    m_common = common;
    m_mSecs = time;
    timerOn = true;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_kicker, m_center);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (timerOn) {
      double startTime = System.currentTimeMillis();
      endTime = startTime + this.m_mSecs;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("UP TO SPEED AUTO", m_common.getUpToSpeed());
      if (m_common.getUpToSpeed()) {
        m_kicker.runKicker(ShooterConstants.defKickerInRPM);
        m_center.intake(IntakeConstants.defIntakePower);
        m_roller.intake(0.5);
      } else {
        m_kicker.runKicker(0);
        m_center.intake(0);
        m_roller.intake(0);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_kicker.runKicker(0);
    m_center.intake(0);
    m_roller.intake(0);
  }

  @Override
  public boolean isFinished() {
    if (timerOn){
      return System.currentTimeMillis() >= endTime;
    } else {
      return false;
    }
  }
}
