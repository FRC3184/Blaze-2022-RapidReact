// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Common;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootSpinUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Shooter_Flywheels m_flywheels;
  private final Sensor_Limelight m_limelight;
  private final Common m_common;
  private double m_mSecs;
  private double endTime;
  private boolean timerOn = false;
  private double [] shootRPM = {0,0};
  private boolean speedInput = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param flywheel The subsystem used by this command.
   */

  public ShootSpinUp(Common common, Shooter_Flywheels flywheel, Sensor_Limelight limelight) {
    m_flywheels = flywheel;
    m_limelight = limelight;
    m_common = common;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
  }

  public ShootSpinUp(Common common, Shooter_Flywheels flywheel, Sensor_Limelight limelight, double time) {
    m_flywheels = flywheel;
    m_limelight = limelight;
    m_common = common;
    m_mSecs = time;
    timerOn = true;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
  }

  public ShootSpinUp(Common common, Shooter_Flywheels flywheel, Sensor_Limelight limelight, double frontRPM, double backRPM) {
    m_flywheels = flywheel;
    m_limelight = limelight;
    m_common = common;
    shootRPM[0] = frontRPM;
    shootRPM[1] = backRPM;
    speedInput = true;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_flywheels);
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
      if (speedInput) {
          
      } else {
        shootRPM = calcShotSpeedWithLimelight();
      }
      m_flywheels.setShotSpeed(shootRPM[0], shootRPM[1]);
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
    if (timerOn){
        return System.currentTimeMillis() >= endTime;
      } else {
        return false;
      }
  }

  double speed[] = {0, 0};  //small big
  double targetAngle = 0;

  public double[] calcShotSpeedWithLimelight() {
    targetAngle = m_limelight.getTargetAngle();
    
    speed[0] = (0.0535*(Math.pow(targetAngle,3)))+(1.1854*(Math.pow(targetAngle, 2)))-(1.8222*targetAngle)+(1841.7);  //y = 0.0535x3 + 1.1854x2 - 1.8222x + 1841.7
    // speed[1] = (-0.0508*(Math.pow(targetAngle,3)))+(0.2784*(Math.pow(targetAngle, 2)))-(23.001*targetAngle)+(2956.3); // y = -0.0823x3 + 1.402x2 - 29.47x + 2905.7
    speed[1] = (-0.0862*(Math.pow(targetAngle,3)))+(0.5153*(Math.pow(targetAngle, 2)))-(5.7743*targetAngle)+(2852.6); // y = -0.0823x3 + 1.402x2 - 29.47x + 2905.7

    // if (targetAngle > 10) {
    //   speed[0] = 4000;
    //   speed[1] = 2500;
    // } else {
    //   speed[0] = 4000;
    //   speed[1] = 2100;
    // }

    return speed;
  }
}
