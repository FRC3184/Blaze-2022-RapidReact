// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.independant;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import frc.robot.subsystems.Shooter.Shooter_Hood;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ZeroNavX extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Sensor_NavX m_navx;
  private final double m_mSecs = 1000;
  private double endTime;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ZeroNavX(Sensor_NavX subsystem) {
    m_navx = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_navx);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      double startTime = System.currentTimeMillis();
      endTime = startTime + this.m_mSecs;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_navx.resetHeading();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return m_navx.getYaw() == 0 || System.currentTimeMillis() >= endTime;
  }
}
