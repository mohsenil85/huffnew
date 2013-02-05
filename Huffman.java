public class Huffman {
	private static int ALPHABET_SIZE = 29;
	static PriorityQ theQ = new PriorityQ(ALPHABET_SIZE);
	static int[] freqTable = new int[ALPHABET_SIZE];
	static String inputString;
	static Tree huffTree;
	String codedMsg;

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
			while (theQ.size() > 1) {
				Tree left = theQ.remove();
				Tree right = theQ.remove();
				theQ.insert(merge(left, right));
			}
			huffTree = theQ.remove();
			huffTree.displayTree();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Nothing to display");
		}

	}

	public static Tree merge(Tree item1, Tree item2) {
		Node newNode = new Node();
		newNode.freq = item1.root.freq + item2.root.freq;
		newNode.ch = '+';
		newNode.leftChild = item1.root;
		newNode.rightChild = item2.root;
		Tree aTree = new Tree();
		aTree.insert(newNode);
		return aTree;
	}

}
