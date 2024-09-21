// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;

import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.NetworkTablesSubsystem;
import frc.robot.subsystems.LEDSubsystem;

//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // Replace with CommandPS4Controller or CommandJoystick if needed

  //this is for all the other commands INCLUDING arcade drive
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  //these two joysticks are for TANK DRIVE
  private final CommandJoystick left_joystick = new CommandJoystick(1);
  private final CommandJoystick right_joystick = new CommandJoystick(0);

  //SUBSYSTEMS: below consists of the creations and initializations of the subsystems used
      //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(m_driverController);
      private final MotorSubsystem drive_system = new MotorSubsystem();
      private final LEDSubsystem led_system = new LEDSubsystem();
      private final NetworkTablesSubsystem nt_system = new NetworkTablesSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));*/
    
    
    //trying to do the above trigger thing with drive_system here:
      new Trigger(drive_system::motorSystemCondition)
        .onTrue(new ExampleCommand(drive_system));

      new Trigger(led_system::LEDCondition)
        .onTrue(new ExampleCommand())
    

    
    // m_driverController.y().whileTrue(m_exampleSubsystem.LEDCommand());
    // m_driverController.b().whileTrue(m_exampleSubsystem.LEDCommand());
    // m_driverController.x().whileTrue(m_exampleSubsystem.LEDCommand());
    // m_driverController.a().whileTrue(m_exampleSubsystem.LEDCommand());

    // m_driverController.start().whileTrue(m_exampleSubsystem.stickCommand(() -> m_driverController.getRawAxis(0)));
    // m_driverController.back().whileTrue(m_exampleSubsystem.LEDStickCommand(() -> m_driverController.getRawAxis(0)));
    // m_driverController.back().whileTrue(m_exampleSubsystem.LEDStickCommand(() -> m_driverController.getRawAxis(1)));
    // m_driverController.leftBumper().whileTrue(m_exampleSubsystem.ServoCommand(1));
    // m_driverController.rightBumper().whileTrue(m_exampleSubsystem.ServoCommand(2));
    
    m_driverController.a().whileTrue(drive_system.MotorPIDCommand());

    //arcade drive configuration
    // m_driverController.run().whileTrue(m_exampleSubsystem.arcadeDriveCommand(() -> m_driverController.getRawAxis(0), () -> m_driverController.getRawAxis(4)));
    // Commands.run(arcadeDriveCommand(() -> m_driverController.getRawAxis(0), () -> m_driverController.getRawAxis(4)));
    //Commands.run(m_driverController, m_exampleSubsystem, m_exampleSubsystem.arcadeDriveCommand(() -> m_driverController.getRawAxis(0), () -> m_driverController.getRawAxis(4)));
    


    //DEFAULT RUN COMMAND - this one is for arcade drive using command xbox controller x and y sticks
    /* 
    m_exampleSubsystem.setDefaultCommand(
      m_exampleSubsystem.arcadeDriveCommand (
        () -> m_driverController.getRawAxis(0), () -> m_driverController.getRawAxis(4)
        )
      ); */
    
    //DEFAULT RUN COMMAND - this one is for tank drive using command joystick x and y axis for left speed and right speed respectively
    /* drive_system.setDefaultCommand(
      drive_system.tankDriveCommand (
        () -> left_joystick.getRawAxis(1), () -> right_joystick.getRawAxis(1)
        )
      );
    */
/*
    drive_system.setDefaultCommand (
      drive_system.arcadeDriveJoystickCommand (
        () -> left_joystick.getRawAxis(1), () -> right_joystick.getRawAxis(0)
      )
    );
*/
    /* 
    m_driverController.x().whileTrue(drive_system.magneticSwitchCommand());
    m_driverController.a().whileTrue(drive_system.mechSwitchCommand());
    */
  }

    

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
  //An example command will be run in autonomous
      return Autos.exampleAuto(drive_system);
  }
  
}
