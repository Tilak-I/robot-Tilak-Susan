// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class Intake extends SubsystemBase {

  final Compressor pcmCompressor;
  final DoubleSolenoid solenoid1;
  final DoubleSolenoid solenoid2;
  final WPI_TalonSRX intakeMotor;

  /** Creates a new Intake. */
  public Intake()
   {  
    solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0,1);
    solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2,3);
    intakeMotor = new WPI_TalonSRX(16);
    pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    
   }
   public void startRollerIntake()  
   { 
    intakeMotor.set(ControlMode.PercentOutput, 1);
   }
   public void endRollerIntake()
   {
    intakeMotor.set(ControlMode.PercentOutput, 0);
   }
   public void SolenoidExtend()
   {
    solenoid1.set(kForward);
    solenoid2.set(kForward);
    startRollerIntake();
   }

   public void SolenoidRetract()
   {
    solenoid1.set(kReverse);
    solenoid2.set(kReverse);
    endRollerIntake();
   }

  @Override
  public void periodic() {
    Boolean intakeVal;
    if (solenoid1.get() == kReverse) 
    {
     intakeVal = false;
    }
    else if (solenoid1.get() == kForward) 
    {
     intakeVal = true;
    }
    else 
    {
     intakeVal = false;
    }
    SmartDashboard.putBoolean("Intake Position: ", intakeVal);
    // This method will be called once per scheduler 
  }
}
