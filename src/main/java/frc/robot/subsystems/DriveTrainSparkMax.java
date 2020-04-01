
package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static frc.robot.Constants.DriveTrainConstants.*;
import static frc.robot.Constants.GeneralConstants.*;


/**
 * This class is a carbon copy of the DriveTrainFalcon class. They're both
 * drivetrains, but this one is written in the off-chance that we decide to use
 * SparkMax's and NEO's for the drivetrain. A lot of the commands are basically
 * the same.
 */

public class DriveTrainSparkMax {

   private final CANSparkMax leftMaster;
   private final CANSparkMax rightMaster;
   private final CANSparkMax leftSlave;
   private final CANSparkMax rightSlave;

   private final DifferentialDrive driveBase;

   private CANEncoder leftSide;
   private CANEncoder rightSide;

   public DriveTrainSparkMax() {

      leftMaster = new CANSparkMax(LEFT_MASTER_ID, MotorType.kBrushless);
      rightMaster = new CANSparkMax(RIGHT_MASTER_ID, MotorType.kBrushless);
      leftSlave = new CANSparkMax(LEFT_SLAVE_ID, MotorType.kBrushless);
      rightSlave = new CANSparkMax(RIGHT_SLAVE_ID, MotorType.kBrushless);

      configureMotor(leftMaster, true, true);
      configureMotor(rightMaster, true, false);
      configureMotor(leftSlave, false, true);
      configureMotor(rightSlave, false, false);

      leftSlave.follow(leftMaster);
      rightSlave.follow(rightMaster);

      leftSide.setPosition(0);
      rightSide.setPosition(0);
      rightSide.setInverted(true);

      leftSide.setPositionConversionFactor(POSITION_TO_ROTATION_NEO);
      rightSide.setPositionConversionFactor(POSITION_TO_ROTATION_NEO);

      leftSide.setVelocityConversionFactor(POSITION_TO_ROTATION_NEO);
      rightSide.setVelocityConversionFactor(POSITION_TO_ROTATION_NEO);

      driveBase = new DifferentialDrive(leftMaster, rightMaster);
      driveBase.setDeadband(DEADBAND);

   }

   private void configureMotor(CANSparkMax motor, boolean master, boolean left) {
      motor.restoreFactoryDefaults();
      motor.enableVoltageCompensation(VOLTAGE_COMPENSATION);
      motor.setIdleMode(IdleMode.kBrake);

      // Look at this horrifyingly ugly single-line if statement. I hate loving it.
      // Figure it out kid.
      if (master) { if (left) {leftSide = motor.getEncoder();} else {rightSide = motor.getEncoder();}      }

      motor.setOpenLoopRampRate(RAMP_RATE); // how long it takes to go from 0 to the set speed
   }

   public void tankDrive(double leftSpeed, double rightSpeed) {
      // Controlling the left side and the right side seperately
      // Here we can switch the orientation by flipping left and right
      driveBase.tankDrive(leftSpeed, rightSpeed);
   }

   public void tankDriveVolts(double leftSpeed, double rightSpeed) {
      // Tank drive, but in the case we want to use volts, it's here
      leftMaster.setVoltage(12.3 * leftSpeed);
      rightMaster.setVoltage(12.3 * rightSpeed);
   }

   public void arcadeDrive(double speed, double rotation) {
      // How fast you want to go forward, and how much you want to turn
      driveBase.arcadeDrive(speed, rotation);
   }

   public void curvatureDrive(double speed, double rotation) {
      // Curvature drive is subset of arcade drive seems interesting ... I'll try
      // testing it
      driveBase.curvatureDrive(speed, rotation, true);

      /**
       * These should help to control curvature drive, but testing needs to be done
       * driveBase.setQuickStopAlpha(something);
       * driveBase.setQuickStopThreshold(something);
       */
   }

   public void triggerDrive(double forward, double reverse, double rotation) {
      // Basically how driving works in Forza, uses triggers
      driveBase.arcadeDrive(forward-reverse, rotation);
   }

   public double getLeftPos() {
      return leftSide.getPosition();
   }

   public double getRightPos() {
      return rightSide.getPosition();
   }

   public double getLeftVel() {
      return leftSide.getVelocity();
   }

   public double getRightVel() {
      return rightSide.getVelocity();
   }

   public void setAllToCoast() {
      leftMaster.setIdleMode(IdleMode.kCoast);
      rightMaster.setIdleMode(IdleMode.kCoast);
      leftSlave.setIdleMode(IdleMode.kCoast);
      rightSlave.setIdleMode(IdleMode.kCoast);
   }

}