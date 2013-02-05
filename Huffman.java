
public class Huffman {
	private static int ALPHABET_SIZE = 29;
	static PriorityQ theQ = new PriorityQ(ALPHABET_SIZE);
	static int[] freqTable = new int[ALPHABET_SIZE];
	static String inputString;
	Tree huffTree;
	String codedMsg;
	
	public static void makeFreqTable(String is){
		inputString = is;
		
		for (int i = 0; i < is.length()-1; i++){
			try{
				freqTable[(int)inputString.charAt(i)-65]++;
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Illegal character detected.  Try removing punctuation");
				break;
			}
		}
		System.out.println("Frequency Table:  ");
		for(int i = 0; i < freqTable.length; i++){
			if (freqTable[i] != 0)
				System.out.print(freqTable[i] + " ");
		}
			System.out.println();
		for(int i = 0; i < freqTable.length; i++){
			if (freqTable[i] != 0)
				System.out.print(((char) (i + 65)) + " ");
		}
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
					aTree.displayTree();
					theQ.insert(aTree);
					aNode.displayNode();
				} // end if nodefreq not zero
				Node eofNode = new Node();
				eofNode.ch = '*';
				eofNode.freq = 1;
				Tree aTree = new Tree();
				aTree.insert(eofNode);

			} // end for
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("this only happens sometimes");
		}
	}

}
