// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.navigation;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** A command that will turn the robot to the specified angle using a motion profile. */
public class TurnGyroPIDTimeout extends TurnGyroPID {

  private final double m_mSecs;
  private double endTime;
  private boolean timerOn = false;

  /**
   * Turns to robot to the specified angle using a motion profile.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive The drive subsystem to use
   */


  public TurnGyroPIDTimeout(double targetAngleDegrees, Drivetrain drive, Sensor_NavX navx, double timeout) {
    super(targetAngleDegrees, drive, navx);
    m_mSecs = timeout;
    timerOn = true;
  }

   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
     if (timerOn) {
       double startTime = System.currentTimeMillis();
       endTime = startTime + this.m_mSecs;
     }
   }
 

  @Override
  public boolean isFinished() {
    if (timerOn){
      return getController().atSetpoint() || System.currentTimeMillis() >= endTime;
    } else {
      return false;
    }
  }
}