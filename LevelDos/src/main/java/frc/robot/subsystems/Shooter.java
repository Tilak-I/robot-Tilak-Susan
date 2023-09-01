// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  public WPI_TalonSRX leftShooter;
  public WPI_TalonSRX rightShooter;
  public WPI_TalonSRX hoodMotor;

  /** Creates a new Shooter. */
  public Shooter() 
  {
    leftShooter = new WPI_TalonSRX(22);
    rightShooter = new WPI_TalonSRX(15);
    hoodMotor = new WPI_TalonSRX(35);  
    
    leftShooter.follow(rightShooter);
    rightShooter.setInverted(true);
    hoodMotor.setInverted(true);

  }

  public void shoot()
  {
    rightShooter.set(ControlMode.PercentOutput, 1);
  }
  public void hoodStop()
  {
    hoodMotor.set(0);
  }

  public void extendHood()
  {
    hoodMotor.set(1);
  }
  public void retractHood()
  {
    hoodMotor.set(-0.5);
  }
  public void stopShooters()
  {
    rightShooter.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
