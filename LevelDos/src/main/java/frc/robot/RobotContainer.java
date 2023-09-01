// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Primer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private boolean runHopper;
  private Primer mPrimer;
  private Shooter mShooter;
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivebase mDrive;
  private final Intake mIntake;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      private XboxController driveController;

  
    

    
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    mPrimer = new Primer();
    runHopper = true;
    driveController = new XboxController(0);
    mIntake = new Intake();
    mDrive = new Drivebase();
    mShooter = new Shooter();
    mDrive.setDefaultCommand(new DefaultDrive(
      () -> driveController.getLeftY(),
      () -> driveController.getRightX(),
      mDrive
    ));
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
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_driverController.x().onTrue(new InstantCommand(() -> mPrimer.ShooterIntake()));
    m_driverController.x().onFalse(new InstantCommand(() -> mPrimer.endCargo()));

    m_driverController.a().onTrue(new InstantCommand(() -> 
    {
      if(runHopper)
      {
        runHopper = false;
        mShooter.shoot();
        mPrimer.startHopper();
      }
      else 
      {
        runHopper = true;
        mShooter.stopShooters();
        mPrimer.endHopper();
      }
    }));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_driverController.rightBumper().whileTrue(new InstantCommand(() -> {
      mPrimer.startHopper();
      mIntake.SolenoidExtend();
    }));
    m_driverController.leftBumper().whileTrue(new InstantCommand(() -> {
      mPrimer.endHopper();
      mIntake.SolenoidRetract();
    }));
    m_driverController.leftBumper().whileTrue(new InstantCommand(mIntake::SolenoidRetract,mIntake));

    m_driverController.leftTrigger().whileTrue(new InstantCommand(() -> {if(runHopper) {mPrimer.startHopper();}}));
    m_driverController.leftTrigger().onFalse(new InstantCommand(() -> mPrimer.endHopper()));
    m_driverController.rightTrigger().whileTrue(new InstantCommand(() -> {if(runHopper) {mPrimer.reverseHopper();}}));
    m_driverController.rightTrigger().onFalse(new InstantCommand(() -> mPrimer.endHopper()));
    m_driverController.leftBumper().and(() -> m_driverController.rightTrigger().getAsBoolean());

  }

    


  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);

  }
}
