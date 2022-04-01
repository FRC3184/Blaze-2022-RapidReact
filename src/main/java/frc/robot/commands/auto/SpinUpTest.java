// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import frc.robot.commands.SpinUpThenKickWithCenter;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Intake.Intake_Actuate;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class SpinUpTest extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Intake_Roller m_intakeRoller;
  private final Intake_Centerer m_intakeCenterer;
  private final Shooter_Flywheels m_flywheels;
  private final Shooter_Kicker m_kicker;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveSS The subsystem used by this command.
   */
  public SpinUpTest(Drivetrain driveSS, 
                    Intake_Actuate intakeActSS, Intake_Roller rollerSS, Intake_Centerer centererSS, 
                    Shooter_Flywheels flywheelsSS, Shooter_Kicker kickerSS) {

    m_intakeRoller = rollerSS;
    m_intakeCenterer = centererSS;
    m_flywheels = flywheelsSS;
    m_kicker = kickerSS;

    addCommands(
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
        new SpinUpThenKickWithCenter(m_kicker, m_flywheels, m_intakeCenterer, m_intakeRoller, 2100)
        
    );
  }
}
