
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmFalcon;

public class ArmPosUp extends CommandBase {

   private ArmFalcon arm;

   public ArmPosUp(ArmFalcon arm) {
      this.arm = arm;
      addRequirements(arm);
   }

   @Override
   public void initialize() {
      if ((arm.getState() == 0) || (arm.getState() == 1)) {
         arm.setState(arm.getState() + 1);
      }
   }

   @Override
   public void execute() {
      switch(arm.getState()) {
         case 1:
            arm.setPosition(90);
            break;
         case 2:
            arm.setPosition(180);
            break;
      }
   }

   @Override
   public void end(boolean interrupted) {
      arm.stopMoving();
   }

   @Override
   public boolean isFinished() {
      switch(arm.getState()) {
         case 1:
            if (Math.abs(arm.getPosition() - 2*arm.getState()) < 5) {
               return true;
            }
         case 2:
            if (Math.abs(arm.getPosition() - 2*arm.getState()) < 5) {
               return true;
            }
         default:
            return false;
      } 
   }

}