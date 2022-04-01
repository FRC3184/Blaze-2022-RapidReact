// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Flywheels m_flywheels;
  private final Sensor_Limelight m_limelight;
  private XboxController gunnerController = new XboxController(OIConstants.kGunnerControllerPort);
  
  private double fireSpeed = ShooterConstants.defShotRPM;
  private double frontSpeed, backSpeed;
  private int stopSpeed = 0;


  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */
  public Shoot(Shooter_Flywheels flywheel, Sensor_Limelight limelight) {
    m_flywheels = flywheel;
    m_limelight = limelight;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
    addRequirements(m_limelight);
    m_flywheels.setShotSpeed(fireSpeed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (gunnerController.getRightTriggerAxis() > 0.1){
      frontSpeed = 200;
      backSpeed = 200;
    // fender
    } else if (gunnerController.getLeftTriggerAxis() > 0.1) {
      frontSpeed = 4000;
      backSpeed = 2500; 
    } else if (gunnerController.getLeftBumper()) {
      frontSpeed = 5000;
      backSpeed = 5000;
    } else if (gunnerController.getRightBumper()) {
      frontSpeed = -2400;
      backSpeed = -1200;
    } else {
      frontSpeed = 0;
      backSpeed = 0;
    }
    m_flywheels.setShotSpeed(frontSpeed, backSpeed);
    m_flywheels.runShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flywheels.setShotSpeed(stopSpeed);
    m_flywheels.runShooter();
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
