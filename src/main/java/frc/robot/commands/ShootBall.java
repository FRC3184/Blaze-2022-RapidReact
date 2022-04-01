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
public class ShootBall extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Sensor_Limelight m_limelight;
  private final Shooter_Kicker m_kicker;
  private final Common m_common;

  private boolean onTarget = false; 

  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */
  public ShootBall(Common common, Sensor_Limelight limelight, Shooter_Kicker kicker) {
    m_common = common;
    m_limelight = limelight;
    m_kicker = kicker;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_limelight, m_kicker);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (m_common.getUpToSpeed() /*&& !m_limelight.isSkewed()*/) {
        m_kicker.runKicker(ShooterConstants.defKickerInRPM);
      } else {
        m_kicker.runKicker(0);
      }

      // if (m_limelight.getSkew() < 0.0) {
      //   // turn left? 
      // } else if (m_limelight.getSkew() > 0.0){
      //   // turn right? 
      // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_kicker.runKicker(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
