/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
Hi to whoever's reading this, probably someone on FRC 5113, the Combustible Lemons. 
My name's Vinay, and I was the lead of Software on the team for two years, and the
co-president for one. I really enjoyed robotics when I was part of the team, and 
I'm going to make sure to properly comment this template and explain everything 
that's in it. This is only going to have drivetrain code, at least how it was 
written when I was part of the team. Good luck lemon. Hope you add to the team.
*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveCommand;

public class Robot extends TimedRobot {
   private RobotContainer robotContainer;

   @Override
   // As soon as the code is deployed, this happens
   public void robotInit() {
      // You always want to create a new RobotContainer,
      // It's where the majority of the robot is "handled"
      robotContainer = new RobotContainer();
   }

   @Override
   // No matter what state the robot is in, this runs
   public void robotPeriodic() {
      // This looks at whatever command is scheduled, and runs it
      CommandScheduler.getInstance().run();
   }

   @Override
   public void disabledInit() {
      CommandScheduler.getInstance().cancelAll();
      robotContainer.driveTrain.setAllToCoast();
      // As soon as the robot is disabled, stop all commands and make the drive motors
      // coast
   }

   @Override
   public void disabledPeriodic() {
   }

   @Override
   public void autonomousInit() {
   }

   @Override
   public void autonomousPeriodic() {
   }

   @Override
   public void teleopInit() {
      CommandScheduler.getInstance().cancelAll(); // Cancel whatever you were doing in auton
      // As soon as teleop starts, make sure the drive always works
      // The () -> notation is a lambda ... just look it up, I'm too lazy to explain
      robotContainer.driveTrain.setDefaultCommand(new DriveCommand(() -> robotContainer.getLeftStickValue(),
            () -> robotContainer.getRightStickValue(), robotContainer.driveTrain));
   }

   @Override
   public void teleopPeriodic() {
   }

   @Override
   public void testInit() {
   }

   @Override
   public void testPeriodic() {
   }
}
