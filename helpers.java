
public class helpers {
	
	static int intHelper(int badInput){
		if (badInput == 10){
			return 91;
		} else if (badInput == 32){
			return 92;
		}else if (badInput >= 97){
			return badInput - 32;
		}else{
			return badInput;
		
		}
	}
	static char doubsToChars(double i){
		if (i == 26){
			return '\\';
		}else if (i == 27){
			return '[';
		}else {
			return ((char) (i + 65));
		}
	}
	static void prompt(){
		System.out.print(">  ");
	}
	
	static double charToDoub(char c){
		return (double) c;
	}
}
