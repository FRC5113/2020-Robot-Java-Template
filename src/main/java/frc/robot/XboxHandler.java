//Desperately "borrowed" from team 319 during week 6 of build season
//credit to "Bob", original name was BobController, I renamed it
package frc.robot;


import edu.wpi.first.wpilibj.Joystick;

public class XboxHandler extends Joystick {

	public XboxHandler(int port) {
		super(port);
	}

	public XboxButton xButton = new XboxButton(this, EnumButtons.X);
	public XboxButton yButton = new XboxButton(this, EnumButtons.Y);
	public XboxButton aButton = new XboxButton(this, EnumButtons.A);
	public XboxButton bButton = new XboxButton(this, EnumButtons.B);
	public XboxButton rightBumper = new XboxButton(this, EnumButtons.RIGHT_BUMPER);
	public XboxButton leftBumper = new XboxButton(this, EnumButtons.LEFT_BUMPER);
	public XboxButton startButton = new XboxButton(this, EnumButtons.START);
	public XboxButton selectButton = new XboxButton(this, EnumButtons.SELECT);

	static enum EnumButtons {

		A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6), SELECT(7), START(8), LEFT_STICK(9), RIGHT_STICK(10);

		final int value;

		EnumButtons(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public void setRumble(double leftValue, double rightValue) {
		setRumble(RumbleType.kLeftRumble, leftValue);
		setRumble(RumbleType.kRightRumble, rightValue);
	}

}