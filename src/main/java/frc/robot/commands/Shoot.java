// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Shooter_Flywheels;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Flywheels m_flywheels;
  private XboxController driveController = new XboxController(OIConstants.kDriverControllerPort);
  
  private int fireSpeed = 5000;
  private int stopSpeed = 0;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shoot(Shooter_Flywheels subsystem) {
    m_flywheels = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
    m_flywheels.setShotSpeed(fireSpeed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveController.getAButton()){
      m_flywheels.setShotSpeed(fireSpeed); 
    } else {
      m_flywheels.setShotSpeed(stopSpeed);
    }
    m_flywheels.runShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flywheels.setShotSpeed(stopSpeed);
    m_flywheels.runShooter();
  }
}
