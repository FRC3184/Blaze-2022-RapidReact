// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.commands.auto.AutonomousTest;
import frc.robot.commands.drive.TankDrive;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  private final Shooter_Flywheels m_flywheel = new Shooter_Flywheels();
  private final Shooter_Kicker m_kicker = new Shooter_Kicker();
  private final Hang_Actuate m_hangActuate = new Hang_Actuate();
  private final Hang_Winch m_hangWinch = new Hang_Winch();
  private final Intake_Actuate m_intakeAcutate = new Intake_Actuate();
  private final Intake_Roller m_intakeRoller = new Intake_Roller();
  private final Intake_Centerer m_intakeCenterer = new Intake_Centerer();

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
    m_hangActuate.setDefaultCommand(new ActuateIn(m_hangActuate));
    m_hangWinch.setDefaultCommand(new WinchIn(m_hangWinch));

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
    new POVButton(m_driverController, 270).whenHeld(new Intake(m_intakeRoller));
    // DPad Right    = outtake
    new POVButton(m_driverController, 90).whenHeld(new Outtake(m_intakeRoller));

    // Right Trigger  = winch in - LOOK IN HANG IN COMMAND 
    // Left Trigger   = actuate in - LOOK IN HANG IN COMMAND

    // Right Bumper   = winch out
    new JoystickButton(m_driverController, Button.kRightBumper.value).whenHeld(new WinchOut(m_hangWinch));
    // Left Bumper  = actuate out
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenHeld(new ActuateOut(m_hangActuate));
    // DPad Up       = retract intake
    new POVButton(m_driverController, 0).whenHeld(new IntakeRetract(m_intakeAcutate));
    // DPad Down     = deploy intake
    new POVButton(m_driverController, 180).whenHeld(new IntakeDeploy(m_intakeAcutate));
    // A Button      = shoot
    new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Shoot(m_flywheel));
    // X Button      = kick
    new JoystickButton(m_driverController, Button.kX.value).whenHeld(new Kick(m_kicker));
    // left joy click= center in
    new JoystickButton(m_driverController, Button.kLeftStick.value).whenHeld(new CenterIntake(m_intakeCenterer));
    // right joy click = center out 
    new JoystickButton(m_driverController, Button.kRightStick.value).whenHeld(new CenterOuttake(m_intakeCenterer));
  }

  private void configureCompButtonBindings() {
    // RUNNER JOYSTICK
    // drivetrain is already handled above

    // GUNNER JOYSTICK
    // DPad Left     = intake
    new POVButton(m_driverController, 270).whenHeld(new Intake(m_intakeRoller));
    // DPad Right    = outtake
    new POVButton(m_driverController, 90).whenHeld(new Outtake(m_intakeRoller));

    // Right Trigger  = winch in - LOOK IN HANG IN COMMAND 
    // Left Trigger   = actuate in - LOOK IN HANG IN COMMAND

    // Right Bumper   = winch out
    new JoystickButton(m_driverController, Button.kRightBumper.value).whenHeld(new WinchOut(m_hangWinch));
    // Left Bumper  = actuate out
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenHeld(new ActuateOut(m_hangActuate));
    // DPad Up       = retract intake
    new POVButton(m_driverController, 0).whenHeld(new IntakeRetract(m_intakeAcutate));
    // DPad Down     = deploy intake
    new POVButton(m_driverController, 180).whenHeld(new IntakeDeploy(m_intakeAcutate));
    // A Button      = shoot
    new JoystickButton(m_driverController, Button.kA.value).whenHeld(new Shoot(m_flywheel));
    // X Button      = kick
    new JoystickButton(m_driverController, Button.kX.value).whenHeld(new Kick(m_kicker));
    // left joy click= center in
    new JoystickButton(m_driverController, Button.kLeftStick.value).whenHeld(new CenterIntake(m_intakeCenterer));
    // right joy click = center out 
    new JoystickButton(m_driverController, Button.kRightStick.value).whenHeld(new CenterOuttake(m_intakeCenterer));
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