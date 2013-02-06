public class Huffman {
	private static int ALPHABET_SIZE = 29;
	static PriorityQ theQ = new PriorityQ(ALPHABET_SIZE);
	static int[] freqTable = new int[ALPHABET_SIZE];
	static String inputString;
	static Tree huffTree;
	static String codedMsg;
	static String[] codeTable = new String[ALPHABET_SIZE];

	public static void makeFreqTable(String is) {
		inputString = is;

		for (int i = 0; i < is.length() - 1; i++) {
			try {
				freqTable[(int) inputString.charAt(i) - 65]++;
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
		System.out.print('1');
		System.out.println();
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0)
				System.out.print(((char) (i + 65)) + " ");
		}
		System.out.print('*');
		System.out.println();
	}

	public static void enqueue() {
		try {
			for (int i = 0; i < inputString.length() + 1; i++) {

				if (freqTable[i] != 0) {
					Node aNode = new Node();
					aNode.ch = (char) (i + 65);
					aNode.freq = freqTable[i];
					Tree aTree = new Tree();
					aTree.insert(aNode);
					// aTree.displayTree();
					theQ.insert(aTree);
					// aNode.displayNode();
				} // end if nodefreq not zero
			

			} // end for
			Node eofNode = new Node();
			eofNode.ch = '*';
			eofNode.freq = 1;
			Tree eofTree = new Tree();
			eofTree.insert(eofNode);
			theQ.insert(eofTree);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("this only happens sometimes");
		}// end try catch()
	}// end enqueue()

	public static void createTree() {
		try {
			do {
				Tree left = theQ.remove();
				Tree right = theQ.remove();
				theQ.insert(merge(left, right));
			} while (theQ.size() > 1);
			huffTree = theQ.remove();
			huffTree.displayTree();
		} catch (ArrayIndexOutOfBoundsException e) {
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
		
		
		
		
		//start at root
		//travers until find a leafe node
		//each fork, append either one or zero to 
	}
	
	private static void makeCodeTable(Node localRoot, String binString){
		if (!(localRoot.ch == '+')){
			//codeTable[(((int)localRoot.ch)e - 65)] = binString;
			System.out.print(localRoot.ch + " = ");
			System.out.println(binString);
			
		} else {
			makeCodeTable(localRoot.leftChild, binString + "1"); 
			makeCodeTable(localRoot.rightChild, binString + "0"); 
			
		}
		
	}
	

}
