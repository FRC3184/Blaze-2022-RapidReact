// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class SpinUpThenKickWithCenter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Kicker m_kicker;
  private final Shooter_Flywheels m_flywheel;
  private final Intake_Centerer m_centerer;
  private final Intake_Roller m_roller;

  private int fireSpeed = 2500;
  private int stopSpeed = 0;

  private final double m_mSecs;
  private double endTime;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SpinUpThenKickWithCenter(Shooter_Kicker kickSS, Shooter_Flywheels flywheelSS, Intake_Centerer centerSS, Intake_Roller rollerSS, int shotSpeed) {
    m_kicker = kickSS;
    m_flywheel = flywheelSS;
    m_centerer = centerSS;
    m_roller = rollerSS;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_kicker);
    m_mSecs = 1500;
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
    Timer.delay(0.5);
    m_centerer.intake(0.1);
    m_kicker.runKicker(1000);
    m_roller.intake(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flywheel.setShotSpeed(stopSpeed);
    m_flywheel.runShooter();
    m_centerer.intake(stopSpeed);
    m_kicker.runKicker(stopSpeed);
    m_roller.intake(stopSpeed);
  }

  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}
