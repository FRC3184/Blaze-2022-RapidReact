// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.Constants.TurnDir;
import frc.robot.commands.*;
import frc.robot.commands.independant.Intake;
import frc.robot.commands.independant.IntakeDeploy;
import frc.robot.commands.navigation.DriveGyroDistance;
import frc.robot.commands.navigation.TurnGyro;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Intake.Intake_Actuate;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class Taxi_5Ball_Gyro extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Intake_Actuate m_intakeActuate;
  private final Intake_Roller m_intakeRoller;
  private final Intake_Centerer m_intakeCenterer;
  private final Shooter_Flywheels m_flywheels;
  private final Shooter_Kicker m_kicker;
  private final Sensor_NavX m_navX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public Taxi_5Ball_Gyro(Drivetrain driveSS, 
                    Intake_Actuate intakeActSS, Intake_Roller rollerSS, Intake_Centerer centererSS, 
                    Shooter_Flywheels flywheelsSS, Shooter_Kicker kickerSS, Sensor_NavX navX) {

    m_drivetrain = driveSS;
    m_intakeActuate = intakeActSS;
    m_intakeRoller = rollerSS;
    m_intakeCenterer = centererSS;
    m_flywheels = flywheelsSS;
    m_kicker = kickerSS;
    m_navX = navX;

    addCommands(
        // deploy intake
        new IntakeDeploy(m_intakeActuate, 200),
        // spin up shooter, intake, drive towards ball
        new ParallelCommandGroup(new DriveGyroDistance(43, 0.8, m_drivetrain, m_navX), new Intake(3000, m_intakeRoller)),
        new TurnGyro(TurnDir.right, 6, 0.5, m_drivetrain, m_navX),
        // shoot 2 balls
        new SpinUpThenKick(m_kicker,m_flywheels, 2100),
        new SpinUpThenKickWithCenter(m_kicker, m_flywheels, m_intakeCenterer, m_intakeRoller, 2100),
        // turn
        new TurnGyro(TurnDir.right, 100, 0.5, m_drivetrain, m_navX),
        // spin up shooter, intake, drive towards ball
        new ParallelCommandGroup(new DriveGyroDistance(165, 0.5, m_drivetrain, m_navX), new Intake(5000, m_intakeRoller)),
        // line up on goal
        new TurnGyro(TurnDir.left, 42, 0.5, m_drivetrain, m_navX),
        // shoot 1 ball
        new SpinUpThenKickWithCenter(m_kicker, m_flywheels, m_intakeCenterer, m_intakeRoller, 2100),
        // drive towards human station, intake
        new ParallelCommandGroup(new DriveGyroDistance(120, 0.5, m_drivetrain, m_navX), new Intake(3000, m_intakeRoller)),
        new SpinUpThenKickWithCenter(m_kicker, m_flywheels, m_intakeCenterer, m_intakeRoller, 2100)
        // drive back toward shooting spot
    );
  }
}
