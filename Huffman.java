public class Huffman {
	private static int ALPHABET_SIZE = 29;
	static PriorityQ theQ = new PriorityQ(ALPHABET_SIZE);
	static int[] freqTable = new int[ALPHABET_SIZE];
	static String inputString;
	static Tree huffTree;
	static String codedMsg;
	static String[] codeTable = new String[ALPHABET_SIZE];
	static String secretMessage = "";
	static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\\[*";
	static String decodedMsg = "";

	public static void makeFreqTable(String is) {
		inputString = is;

		for (int i = 0; i < is.length(); i++) {
			try {
				if ((int) inputString.charAt(i) - 65 != -23){// this checks for the end of file char '*'
					freqTable[(int) inputString.charAt(i) - 65]++;
				}else{
					freqTable[28]++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Illegal character detected." +
						"  Try removing punctuation");
				break;
			}
		}
		System.out.println("Frequency Table:  ");
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0){
				System.out.print(freqTable[i] + " ");
			}
		}
		System.out.println();
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0)
				System.out.print(ALPHABET.charAt(i) + " ");
		}
		System.out.println();
	}

	public static void enqueue() {
		
			for (int i = 0; i < freqTable.length; i++) {
				

				if (freqTable[i] != 0) {
					Node aNode = new Node();
					aNode.ch = ALPHABET.charAt(i);//its easy to just cast the char, but this way we can easily refer to eol and sp chars
					aNode.freq = freqTable[i];
					Tree aTree = new Tree();
					aTree.insert(aNode);
					theQ.insert(aTree);
				} // end if nodefreq not zero
			} // end for
	}// end enqueue()

	public static void createTree() {
		try {
			while (theQ.size() > 1) {
				Tree left = theQ.remove();
				Tree right = theQ.remove();
				theQ.insert(merge(left, right));
			} 
			huffTree = theQ.remove();
			huffTree.displayTree();
		} catch (ArrayIndexOutOfBoundsException e) {// prevents from trying to display an empty tree
			System.out.println("Nothing to display");
		}
	}

	private static Tree merge(Tree item1, Tree item2) {
		Node newNode = new Node();
		newNode.freq = item1.root.freq + item2.root.freq;
		newNode.ch = '+';
		newNode.leftChild = item1.root;
		newNode.rightChild = item2.root;
		Tree aTree = new Tree();
		aTree.insert(newNode);
		return aTree;
	}
	
	public static void encode(){
		codedMsg = "";
		Node current = huffTree.root;
		makeCodeTable(current, codedMsg);
		
		for (int i = 0; i < inputString.length(); i++){
			try{
			secretMessage += codeTable[inputString.charAt(i) - 65];//appends the representation  for a given char onto the secret message
			}catch (ArrayIndexOutOfBoundsException e){
				secretMessage += codeTable[28];
			}
		}
		System.out.println("Encoded Message:  ");
		System.out.println(secretMessage);
	}
	
	private static void makeCodeTable(Node localRoot, String binString){
		if (!(localRoot.ch == '+')){
			if (localRoot.ch == '*'){//checks for non-letter chars and substitutes them for our internal representation of them
				codeTable[28] = binString;
			} else if (localRoot.ch == '['){
				codeTable[27] = binString;
			} else if (localRoot.ch == '\\'){
				codeTable[26] = binString;
			} else {
			codeTable[(((int)localRoot.ch) - 65)] = binString;
			
			}
			
		} else {
			makeCodeTable(localRoot.leftChild, binString + "1"); 
			makeCodeTable(localRoot.rightChild, binString + "0"); 
			
		}

	}//end makecodetab
	
	public static void decode() {

		decodeRecursive(huffTree.root, 0);
		System.out.println("Decoded Message");
		System.out.println(decodedMsg);

	}// end decod

	private static void decodeRecursive(Node current, int index) {
		try{
			while (current.ch == '+') {//while on a non-leaf node, seek the leaves and when it finds them, appends them to the decoded message
				if (secretMessage.charAt(index) == '1') {
					current = current.leftChild;
					index++;
				} else {
					current = current.rightChild;
					index++;
				}

			}// end while
			decodedMsg += current.ch;
			decodeRecursive(huffTree.root, index);
		} catch (StringIndexOutOfBoundsException e){
			return;
		}
	}

}
