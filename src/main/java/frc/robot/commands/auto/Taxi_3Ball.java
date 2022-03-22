// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveDistanceWithIntake;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeDeployTime;
import frc.robot.commands.IntakeTime;
import frc.robot.commands.SpinUpThenKick;
import frc.robot.commands.SpinUpThenKickWithCenter;
import frc.robot.commands.TurnGyro;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake_Actuate;
import frc.robot.subsystems.Intake_Centerer;
import frc.robot.subsystems.Intake_Roller;
import frc.robot.subsystems.Shooter_Flywheels;
import frc.robot.subsystems.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class Taxi_3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Intake_Actuate m_intakeActuate;
  private final Intake_Roller m_intakeRoller;
  private final Intake_Centerer m_intakeCenterer;
  private final Shooter_Flywheels m_flywheels;
  private final Shooter_Kicker m_kicker;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public Taxi_3Ball(Drivetrain driveSS, 
                    Intake_Actuate intakeActSS, Intake_Roller rollerSS, Intake_Centerer centererSS, 
                    Shooter_Flywheels flywheelsSS, Shooter_Kicker kickerSS) {

    m_drivetrain = driveSS;
    m_intakeActuate = intakeActSS;
    m_intakeRoller = rollerSS;
    m_intakeCenterer = centererSS;
    m_flywheels = flywheelsSS;
    m_kicker = kickerSS;

    addCommands(
        new TurnGyro(true, 30, 0.3, m_drivetrain)
        // // shoot first ball
        // new SpinUpThenKick(m_kicker,m_flywheels, 2100),
        // // deploy intake
        // new IntakeDeployTime(200, m_intakeActuate),
        // // turn on intake, start driving forward
        // new DriveDistanceWithIntake(2.5, 0.5, m_drivetrain, m_intakeRoller),
        // new IntakeTime(2000, m_intakeRoller),
        // // drive back to goal
        // new DriveDistance(2.5, -0.5, m_drivetrain),
        // // start up shooter wheel
        // // run center and kicker wheel
        // new SpinUpThenKickWithCenter(m_kicker, m_flywheels, m_intakeCenterer, m_intakeRoller, 2100)
        
    );
  }
}
