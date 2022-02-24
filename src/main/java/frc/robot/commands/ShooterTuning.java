// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterTuning extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Drivetrain m_drivetrain;

  private XboxController driveController = new XboxController(0);

  private double grabSpeed = 0.5;
  private double shootSpeed = 0.5;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterTuning(Drivetrain subsystem) {
    m_drivetrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("First Grab Wheel Speed", grabSpeed);
    SmartDashboard.putNumber("Second Shooter Wheel Speed", shootSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    grabSpeed = SmartDashboard.getNumber("First Grab Wheel Speed", 0.0);
    shootSpeed = SmartDashboard.getNumber("Second Shooter Wheel Speed", 0.0);

    SmartDashboard.putNumber("First Grab Wheel Speed", grabSpeed);
    SmartDashboard.putNumber("Second Shooter Wheel Speed", shootSpeed);

    if (driveController.getAButton()) {
      m_drivetrain.tankDrive(shootSpeed, grabSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean runsWhenDisabled() {
    return false;
  }
}
