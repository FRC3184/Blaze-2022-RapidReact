// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Shooter_Flywheels;
import frc.robot.subsystems.Shooter_Kicker;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class SpinUpThenKick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Kicker m_kicker;
  private final Shooter_Flywheels m_flywheel;

  private int fireSpeed = 1200;

  private final double m_mSecs;
  private double endTime;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SpinUpThenKick(Shooter_Kicker kickSS, Shooter_Flywheels flywheelSS, int shotSpeed) {
    m_kicker = kickSS;
    m_flywheel = flywheelSS;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_kicker);
    m_mSecs = 2500;
    fireSpeed = shotSpeed;
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
    m_flywheel.setShotSpeed(fireSpeed);
    m_flywheel.runShooter();
    Timer.delay(1);
    m_kicker.runKicker(1000);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flywheel.setShotSpeed(0);
    m_flywheel.runShooter();
    m_kicker.runKicker(0);
  }

  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}
