import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HuffmanApp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			System.out
					.println("Enter first letter of enter, show, code, decode, or quit.");
			System.out.print(">  ");
			String choice = in.next();
			switch (choice) {
			case ("e"):
			case ("E"):
				System.out.println("Please enter the string to encode.  "
						+ "Terminate the string with a '$'");

				String input = processInput();
				System.out.println("Message : \n" + input);
				Huffman.makeFreqTable(input);
				Huffman.enqueue();
				break;
			case ("s"):
			case ("S"):
				System.out.println("SHOW");
				Huffman.createTree();
				break;
			case ("c"):
			case ("C"):
				System.out.println("CODE");
				Huffman.encode();
				break;

			case ("d"):
			case ("D"):
				System.out.println("DECODE");
				Huffman.decode();
				break;

			case ("q"):
			case ("Q"):
				System.out.println("QUIT");
				done = true;
				break;

			default:
				System.out.println("Unrecognized choice.  Please try again.");

			}

		}
		in.close();
	}

	private static String processInput() {// fitler out unwanted input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean done = false;
		int input = 0;
		String inputString = "";
		while (!done) {
			try {
				input = br.read();
			} catch (IOException e) {
				System.out.println(e);
			} 
			if (input == 36) {
				done = true;
				break;
			}
			if (input == 10)
				input = (char) '\\';// filter out actual linefeeds and change
									// them to slashes
			if (input == 32)
				input = (char) '[';// ditto for spaces
			inputString += (char) input;

		}
		inputString += (char) '*'; // eof char
		inputString = inputString.toUpperCase();// uppercase anything that can
												// be uppercased
		return inputString;

	}

}
