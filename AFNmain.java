package tema4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AFNmain {

	public static void main(String[] args) {
		int optiune = 0;
		boolean continuare = true;
		while (continuare) {
			try {
				AFN object = new AFN();
					object.citire();
					if (object.validAFN() == true) {
					object.start();
				}
					else 
						System.out.println(" there was a problem with the AFN read");
			} catch (Exception e) {
				System.out.println("\nError: " + e.getMessage());
			}
			System.out.print("\nIf you want to stop the program, press 1, else  press 2.\nchoice: ");
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				optiune = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nError!");
			}
			if (optiune == 1)
				continuare = false;
		}
	}
}