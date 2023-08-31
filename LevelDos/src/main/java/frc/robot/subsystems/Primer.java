// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Primer extends SubsystemBase {
  private final WPI_TalonSRX HopperMotor;
  private final WPI_TalonSRX intakeCargoMotor;
  /** Creates a new Primer. */
  public Primer() 
  {
    HopperMotor = new WPI_TalonSRX(0);
    intakeCargoMotor = new WPI_TalonSRX(1);

    HopperMotor.configFactoryDefault();
    intakeCargoMotor.configFactoryDefault();

    HopperMotor.setNeutralMode(NeutralMode.Coast);
    intakeCargoMotor.setNeutralMode(NeutralMode.Coast);

    intakeCargoMotor.setInverted(true);

  } 
  public void end() 
  {
    HopperMotor.set(0);
    intakeCargoMotor.set(0);
  }

  public void startHopper() 
  {
    HopperMotor.set(0.5);
  }
  
  public void reverseHopper() 
  {
    HopperMotor.set(-0.5);
  }
  public void ShooterIntake() 
  {
    intakeCargoMotor.set(1);
  }
  public void endHopper()
  {
    HopperMotor.set(0);
  }
  public void endCargo()
  {
    intakeCargoMotor.set(0);
  }
  public void shoot()
  {
    intakeCargoMotor.set(-1);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
