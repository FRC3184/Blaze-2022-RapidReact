// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.Common;
import frc.robot.commands.HoodSetPos;
import frc.robot.commands.ShootAssist;
import frc.robot.commands.ShootSpinUp;
// import frc.robot.commands.SpinUpThenKick;
// import frc.robot.commands.SpinUpThenKickWithCenter;
import frc.robot.commands.independant.HoodUp;
import frc.robot.commands.independant.Intake;
import frc.robot.commands.independant.IntakeDeploy;
import frc.robot.commands.independant.ZeroHood;
// import frc.robot.commands.navigation.DriveDistance;
// import frc.robot.commands.navigation.DriveDistanceWithIntake;
import frc.robot.commands.navigation.DriveGyroDistance;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Intake.Intake_Actuate;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Hood;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class Left_Taxi_2Ball_Def1R extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Intake_Actuate m_intakeActuate;
  private final Intake_Roller m_intakeRoller;
  private final Intake_Centerer m_intakeCenterer;
  private final Shooter_Flywheels m_flywheels;
  private final Shooter_Kicker m_kicker;
  private final Sensor_Limelight m_limelight;
  private final Shooter_Hood m_hood;
  private final Sensor_NavX m_navx;
  private final Common m_common;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public Left_Taxi_2Ball_Def1R(Drivetrain driveSS, 
                    Intake_Actuate intakeActSS, Intake_Roller rollerSS, Intake_Centerer centererSS, 
                    Shooter_Flywheels flywheelsSS, Shooter_Kicker kickerSS, Sensor_Limelight lime, Shooter_Hood hood, Sensor_NavX navx, Common common) {

    m_drivetrain = driveSS;
    m_intakeActuate = intakeActSS;
    m_intakeRoller = rollerSS;
    m_intakeCenterer = centererSS;
    m_flywheels = flywheelsSS;
    m_kicker = kickerSS;
    m_common = common;
    m_limelight = lime;
    m_navx = navx;
    m_hood = hood;


    addCommands(
        // shoot first ball
        new HoodUp(m_hood, 250),
        new ZeroHood(m_hood),
        new HoodSetPos(m_hood, m_limelight, true),
        new ParallelCommandGroup(new ShootSpinUp(m_common, m_flywheels, m_limelight, 2000), new ShootAssist(m_common, m_limelight, m_kicker, m_intakeCenterer, m_intakeRoller, 3000)),
        // deploy intake
        new IntakeDeploy(m_intakeActuate, 200),
        // turn on intake, start driving forward
        new ParallelCommandGroup(new DriveGyroDistance(45, 0.5, m_drivetrain, m_navx), new Intake(3000, m_intakeRoller)),
        // // drive back to goal
        // new DriveGyroDistance(45, -0.5, m_drivetrain, m_navx),
        // // start up shooter wheel
        // // run center and kicker wheel
        new ZeroHood(m_hood),
        new HoodSetPos(m_hood, m_limelight, true),
        new ParallelCommandGroup(new ShootSpinUp(m_common, m_flywheels, m_limelight, 2000), new ShootAssist(m_common, m_limelight, m_kicker, m_intakeCenterer, m_intakeRoller, 3000)));
  }
}
