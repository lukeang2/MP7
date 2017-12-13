import java.util.Scanner;
import java.text.DecimalFormat;

public class Calculator {
	private static double velocity;
	private static double initialH;
	private static double finalH;
	private static boolean direction;
	
	public static void setDirection() {
		String userInput = "";
		System.out.println("Enter up if the ball is heading down\n"
				+ "Enter down if the ball is heading up");
		Scanner dirP = new Scanner(System.in);
		try {
			userInput = dirP.next();
		} catch (Exception E) {
			System.out.println("Invalid Input");
			setDirection();
		}
		if (userInput.matches("up") || userInput.matches("down")) {
			if (userInput.matches("up")) {
				direction = true;
			} else if (userInput.matches("down")) {
				direction = false;
			}
		} else {
			setDirection();
		}
	}
	
	public static void setVelocity() {
		System.out.println("Enter initial velocity (m/s)");
		Scanner inputSpeed = new Scanner(System.in);
		try {
			velocity = inputSpeed.nextDouble();
		} catch (Exception E) {
			System.out.println("Invalid Input");
			setVelocity();
		}
		if (velocity < 0) {
			System.out.println("Invalid Initial Velocity");
			setVelocity();
		}
	}
	
	public static void setInitialH() {
		System.out.println("Enter initial height (m)");
		Scanner inputInitialH = new Scanner(System.in);
		try {
			initialH = inputInitialH.nextDouble();
		} catch (Exception E) {
			System.out.println("Invalid Input");
			setInitialH();
		}
		if (initialH < 0) {
			System.out.println("Invalid Initial Height");
			setInitialH();
		}
	}
	
	public static void setFinalH() {
		System.out.println("Enter final height (m)");
		Scanner inputFinalH = new Scanner(System.in);
		try {
			finalH = inputFinalH.nextDouble();
		} catch (Exception E) {
			System.out.println("Invalid Input");
			setFinalH();
		}
		if (finalH < 0) {
			System.out.println("Invalid Final Height");
			setFinalH();
		}
	}

	public static void main(String[] args) {
		int doSomething = 0;
		System.out.println("Enter 1 to find maximum height projectile reaches\n"
				+ "Enter 2 to find time taken to reach maximum height\n"
				+ "Enter 3 to find total time spent in the air");
		Scanner userInput = new Scanner(System.in);
		try {
			doSomething = userInput.nextInt();
		} catch (Exception E) {
			System.out.println("Invalid Input");
			Calculator.main(args);
		}
		if (doSomething < 1 || doSomething > 3) {
			Calculator.main(args);
		} else {
			checkInput(doSomething);
			Calculator.main(args);
		}
	}
	
	public static void checkInput(int x) {
		DecimalFormat df = new DecimalFormat(".####");
		if (x == 1) {
			setVelocity();
			setDirection();
			setInitialH();
			System.out.println("The projectile reaches a maximum height of: "
					+ df.format(maxHeight(velocity, direction, initialH)) + " m");
		} else if (x == 2) {
			setVelocity();
			setDirection();
			System.out.println("It takes " + df.format(maxHeightTime(velocity, direction)) +
					" seconds to reach maximum height");
		} else if (x == 3) {
			setVelocity();
			setDirection();
			setInitialH();
			setFinalH();
			System.out.println("The total time the projectile spends in the air is " 
			+ df.format(timeInAir(velocity, direction, initialH, finalH)) + " seconds");
		} 
	}
	

	public static double maxHeight(double speed, boolean dir, double start) {
		if (dir == true) {
			double maxh = (speed * speed) / (2 * 9.81) + start;
			return maxh;
		}
		if (dir == false) {
			return start;
		}
		return 0.0;
	}
	
	public static double maxHeightTime(double speed, boolean dir) {
		if (dir == true) {
			double maxTime = speed / 9.81;
			return maxTime;
		}
		if (dir == false) {
			return 0.0;
		}
		return 0.0;
	}
	public static double timeInAir(double speed, boolean dir, double start, double end) {
		if (dir == true) {
			if(start > end) {
				return (2 * maxHeightTime(speed, dir)) + (Math.sqrt(speed + 2 * 9.81 *(start - end)) - speed) / 9.81;
			}
			if (start < end) {
				return (speed / 9.81) + Math.sqrt(2 * 9.81 * (maxHeight(speed, dir, start))) / 9.81;
			} 
			if (start == end) {
				return 2 * maxHeightTime(speed, dir);
			}
		}
		if (dir == false) {
			double maxspeed = Math.sqrt(Math.pow(speed, 2) + 2*9.8*(start - end));
			return (maxspeed - speed)/9.8;
		}
		return 0.0;
	}
}
