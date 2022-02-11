// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutonomousTest;
import frc.robot.commands.ShooterTuning;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();

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
    m_drivetrain.setDefaultCommand(new ShooterTuning(m_drivetrain));

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
  private void configureButtonBindings() {}

  public Drivetrain getRobotDrive() {
    return m_drivetrain;
  }

  // public Drivetrain setDrive(int mode) {    // 0 = teleop, 1 = test
  //   // Configure default commands
  //   if (mode == 1) {
  //     m_drivetrain.setDefaultCommand(new ShooterTuning(m_drivetrain));
  //   } else {
  //     // Configure default commands
  //   // Set the default drive command to tank drive
  //     m_drivetrain.setDefaultCommand(new TankDrive(m_drivetrain));
  //   }
  //   return m_drivetrain;
  // }

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