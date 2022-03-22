// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.commands.auto.SpinUpTest;
import frc.robot.commands.auto.Taxi;
import frc.robot.commands.auto.Taxi_2Ball;
import frc.robot.commands.auto.Taxi_3Ball;
import frc.robot.commands.drive.ArcadeDrive;
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
  private final Sensor_ODS odsHigh = new Sensor_ODS();
  private final LimeLight limelight = new LimeLight();

  // AUTONOMOUS ROUTINES
  // A simple autonomous routine that shoots the loaded frisbees
  private final Command m_simpleAuto = null;
  private final Command m_complexAuto = null;
  private final Command spinuptest = new SpinUpTest(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker);
  private final Command m_taxiOnly = new Taxi(m_drivetrain);
  private final Command m_2Ball = new Taxi_2Ball(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker);
  private final Command m_3Ball = new Taxi_3Ball(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker);
  //private final Command m_autoTest = new Taxi_2Ball(m_drivetrain);

  // autonomous chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_gunnerController = new XboxController(OIConstants.kGunnerControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureCompButtonBindings();

    // Configure default commands
    // Set the default drive command to tank drive
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain));
    m_flywheel.setDefaultCommand(new Shoot(m_flywheel));
    m_intakeRoller.setDefaultCommand(new Intake(m_intakeRoller, m_intakeCenterer));

    // Configure autonomous options
    //m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);
    //m_chooser.addOption("Complex Auto", m_complexAuto);
    m_chooser.setDefaultOption("DONT RUN - Taxi Only", m_taxiOnly);
    m_chooser.addOption("2 Ball Auto", m_2Ball);
    m_chooser.addOption("DONT RUN - spin up test", spinuptest);
    m_chooser.addOption("DONT RUN - 3 Ball Auto", m_3Ball);
    SmartDashboard.putData("Select Autonomous", m_chooser);

    // CameraServer.getInstance().startAutmomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureCompButtonBindings() {
    // RUNNER JOYSTICK
    // drivetrain is already handled above
    new JoystickButton(m_driverController, Button.kRightBumper.value).whenHeld(new IntakeRetract(m_intakeAcutate));
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenHeld(new IntakeDeploy(m_intakeAcutate));

    // GUNNER JOYSTICK
    //new JoystickButton(m_gunnerController, Button.kRightBumper.value).whenHeld(new ShootReverse(m_flywheel));
    new JoystickButton(m_gunnerController, Button.kY.value).whenHeld(new WinchOut(m_hangWinch));
    new JoystickButton(m_gunnerController, Button.kA.value).whenHeld(new WinchIn(m_hangWinch));
    new JoystickButton(m_gunnerController, Button.kX.value).whenHeld(new IntakeRetract(m_intakeAcutate));
    new JoystickButton(m_gunnerController, Button.kB.value).whenHeld(new IntakeDeploy(m_intakeAcutate));
    new POVButton(m_gunnerController, 0).whenHeld(new ActuateIn(m_hangActuate));
    new POVButton(m_gunnerController, 180).whenHeld(new ActuateOut(m_hangActuate));
    new JoystickButton(m_gunnerController, Button.kLeftStick.value).whenHeld(new KickIn(m_kicker));
    new POVButton(m_gunnerController, 270).whenHeld(new KickOut(m_kicker));
    new JoystickButton(m_gunnerController, Button.kRightStick.value).whenHeld(new CenterIntake(m_intakeCenterer));
    new POVButton(m_gunnerController, 90).whenHeld(new CenterOuttake(m_intakeCenterer));
    // new JoystickButton(m_gunnerController, Button.kLeftBumper.value).whenHeld(new FenderShot(m_flywheel));
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