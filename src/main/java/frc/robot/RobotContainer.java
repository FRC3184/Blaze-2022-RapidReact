// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //private final Controllers m_controllers = new Controllers();
  // The robot's subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final HangArms m_hangArms = new HangArms();
  private final IntakeArm m_intakeArm = new IntakeArm();

  // AUTONOMOUS ROUTINES
  // A simple autonomous routine that shoots the loaded frisbees
  private final Command m_simpleAuto = null;
  private final Command m_complexAuto = null;
  private final Command m_autoTest = new AutonomousTest(m_drivetrain);

  // autonomous chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to tank drive
    m_drivetrain.setDefaultCommand(new TankDrive(m_drivetrain));

    // Configure autonomous options
    m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);
    m_chooser.addOption("Complex Auto", m_complexAuto);
    m_chooser.addOption("Auto Test", m_autoTest);
    SmartDashboard.putData("Select Autonomous", m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // RUNNER JOYSTICK
    // drivetrain is already handled above

    // GUNNER JOYSTICK
    // DPad Left     = intake
    new POVButton(m_driverController, 270).whenPressed(new Intake(m_intakeArm));
    // DPad Right    = outtake
    new POVButton(m_driverController, 90).whenPressed(new Outtake(m_intakeArm));
    // Left Trigger  = winch in 
    // Left Bumper   = winch out
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenPressed(new WinchOut(m_hangArms));
    // Right Bumper  = actuate out
    // new JoystickButton(m_driverController, Button.kLeftBumper).whenPressed(command);
    // Right Trigger = actuate in
    // DPad Up       = retract intake
    new POVButton(m_driverController, 0).whenPressed(new RetractIntake(m_intakeArm));
    // DPad Down     = deploy intake
    new POVButton(m_driverController, 180).whenPressed(new DeployIntake(m_intakeArm));
    // A Button      = shoot
    new JoystickButton(m_driverController, Button.kA.value).whenPressed(new Shoot(m_shooter));
    // reverse shooter

  }

  public Drivetrain getRobotDrive() {
    return m_drivetrain;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}