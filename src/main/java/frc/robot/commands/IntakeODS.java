// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Sensors.Sensor_ODS;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeODS extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Intake_Roller m_intakeRoller;
  private final Intake_Centerer m_intakeCenter;
  private final Shooter_Kicker m_kicker;
  private final Sensor_ODS m_odsHigh;

  private XboxController driveController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeODS(Intake_Roller subsystem, Intake_Centerer centerSubsystem, Shooter_Kicker kicker, Sensor_ODS odsHigh) {
    m_intakeRoller = subsystem;
    m_intakeCenter = centerSubsystem;
    m_kicker = kicker;
    m_odsHigh = odsHigh;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intakeRoller, m_intakeCenter, m_kicker);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_odsHigh.getODSVal() < 15.0){
      m_intakeRoller.intake(0.5);
      m_intakeCenter.intake(0.1);
      m_kicker.runKicker(ShooterConstants.defKickerInRPM);
    } else if (m_odsHigh.getODSVal() > 15.0) {
      m_intakeRoller.intake(0.5);
      m_intakeCenter.intake(0.0);
      m_kicker.runKicker(0);
    } else {
      m_intakeRoller.intake(0.0);
      m_intakeCenter.intake(0.0);
      m_kicker.runKicker(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeRoller.intake(0.0);
    m_intakeCenter.intake(0.0);
    m_kicker.runKicker(0);
  }
}
