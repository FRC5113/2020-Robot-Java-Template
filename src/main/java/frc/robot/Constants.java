/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 * 
 * In short, programmers are lazy, and they don't want to change a value
 * everywhere it exists (like a motor ID or something), so they do this, so they
 * can change it in just one place.
 */
public final class Constants {

   public final static class GeneralConstants {
      public static final double VOLTAGE_COMPENSATION = 12.3;
   }

   // Each subsystem's motor ID's should start with different numbers.
   public final static class DriveTrainConstants {
      // DRIVETRAIN ID'S
      // first digit is whether it is a master, second is if it's on the left side; 1
      // is no, 2 is yes
      public static final int LEFT_MASTER_ID = 22;
      public static final int RIGHT_MASTER_ID = 21;
      public static final int LEFT_SLAVE_ID = 12;
      public static final int RIGHT_SLAVE_ID = 11;

      // MISC CONSTANTS
      public static final double RAMP_RATE = 0.5;
      public static final double DEADBAND = 0.05;

      // CONVERSIONS CONSTANTS
      public static final double FALCON_ENCODER_CPR = 2048;
      public static final double NEO_ENCODER_CPR = 42;

      public static final double GEAR_RATIO = 1 / 8.3125; // CHANGE
      public static final double WHEEL_DIAMETER = 6; // inches ofc
      public static final double INCHES_TO_FEET = 1 / 12;

      public static final double POSITION_TO_ROTATION_FALCON = 1 / FALCON_ENCODER_CPR * GEAR_RATIO * WHEEL_DIAMETER
            * Math.PI * INCHES_TO_FEET;

      public static final double POSITION_TO_ROTATION_NEO = 1 / NEO_ENCODER_CPR * GEAR_RATIO * WHEEL_DIAMETER * Math.PI
            * INCHES_TO_FEET;
   }

   public static final class ArmConstants {
      // ARM ID
      public static final int ARM_ID = 31;

      public static final double FALCON_ENCODER_CPR = 2048;
      public static final double NEO_ENCODER_CPR = 42;

      public static final double GEAR_RATIO = 0.6969;

      public static final double POSITION_TO_DEGREE_FALCON = 360 / FALCON_ENCODER_CPR * GEAR_RATIO;
   }

}
