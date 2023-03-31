package uci;

import java.util.Scanner;

import motor.Motor;

public class UCI {
	private static Motor motor = new Motor();
	
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		String inputString;
		while (input.hasNext()) {			
			inputString = input.nextLine();
			if ("uci".equals(inputString)) {
				System.out.println("id name Spiritual");
				System.out.println("id author ProgramciDusunur");				
			} else if ("ucinewgame".equals(inputString)) {
				motor.FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
			} else if ("isready".equals(inputString)) {
				System.out.println("readyok");
			}
		}
		input.close();
	}
}
	
	

