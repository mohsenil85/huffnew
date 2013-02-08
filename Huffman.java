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

	/**
	 * void makeFreqTable(String is)
	 * 
	 * takes an input string (which we have already pre processed to be all
	 * caps, subsituted spaces and line breaks for char representations thereof,
	 * and appended an eof char to) and increments that letter's position in the
	 * frequency table. then it prints out each character and it's frequency,
	 * omitting chars which have a frequency of zero. nb. that if a character
	 * appears a double-digit number of times, the formatting of the table will
	 * be slightly askew. It would be possible to fix this with formatted
	 * printing, but that would be somewhat outside the scope of this project
	 * 
	 * @param is
	 *            - the inputted string (pre processed)
	 */
	public static void makeFreqTable(String is) {
		inputString = is;

		for (int i = 0; i < is.length(); i++) {
			try {
				if ((int) inputString.charAt(i) - 65 != -23) {// this checks for
																// the end of
																// file char '*'
					freqTable[(int) inputString.charAt(i) - 65]++;
				} else {
					freqTable[28]++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Illegal character detected."
						+ "  Try removing punctuation");
				break;
			}
		}
		System.out.println("Frequency Table:  ");// the printing out of the
													// table
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0) {
				System.out.print(freqTable[i] + " ");
			}
		}
		System.out.println();
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0)
				System.out.print(ALPHABET.charAt(i) + " "); // instead of
															// casting the index
															// to a character,
															// it just does a
															// lookup on
															// ALPHABET. this is
		}// // to keep the code a little cleaner
		System.out.println();
	}

	/**
	 * void enqueue() takes no parameters, and only manipulates static variables
	 * within this here scope. for each non zero frequency in the freqtable, it
	 * creates a new node and inserts it into a tree containing no other nodes.
	 * theQ is scoped outside to the entire class since we refer to it several
	 * times later. aTree and aNode are only scoped inside this method
	 * 
	 */
	public static void enqueue() {

		for (int i = 0; i < freqTable.length; i++) {

			if (freqTable[i] != 0) {
				Node aNode = new Node();
				aNode.ch = ALPHABET.charAt(i);// its easy to just cast the char,
												// but this way we can easily
												// refer to eol and sp chars
				aNode.freq = freqTable[i];// without a ton of if statements
				Tree aTree = new Tree();
				aTree.insert(aNode);
				theQ.insert(aTree);
			} // end if nodefreq not zero
		} // end for
	}// end enqueue()

	/**
	 * void createTree() while there is more than one tree in the priority q, it
	 * takes 2 off and merges them using the private method just below here.
	 * because this method uses the remove method included in the tree class,
	 * this operation can only be performed once per run of the program. ie,
	 * processing the tree destroys the priority queue. Therefore it can only be
	 * run once per input.
	 */
	public static void createTree() {
		try {
			while (theQ.size() > 1) {
				Tree left = theQ.remove();
				Tree right = theQ.remove();
				theQ.insert(merge(left, right));
			}
			huffTree = theQ.remove();
			huffTree.displayTree();
		} catch (ArrayIndexOutOfBoundsException e) {// prevents from trying to
													// display an empty tree
			System.out.println("Nothing to display");
		}
	}

	/**
	 * this method takes 2 trees off of the priority queue and returns a new
	 * tree whose frequency is is the sum of the 2 inputted tree's frequency.
	 * the contents of the new tree is a '+' so it is easy to detect any
	 * non-leaf node in the final tree
	 * 
	 * @param item1
	 *            lowest priority item in the q
	 * @param item2
	 *            the second lowest priority item in the q
	 * @return a new tree
	 */
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

	/**
	 * void encode() this method is a helper method for the private recursive
	 * method makeCodeTable() just below. this method starts at the root, and
	 * then for each charachter in the input string, it traverses the tree and
	 * generates the binary representation thereof. thre reason we have
	 * variables for both "codedMsg" and "secretMessage" is because the
	 * recursive method needs a parameter to store the binary representation of
	 * the string, whereas "secretMessage" is the final resultant string that
	 * the "codedMsg" string gets appended to. at the end of this method, the
	 * entire coded message is printed out
	 */
	public static void encode() {
		codedMsg = "";
		Node current = huffTree.root;
		makeCodeTable(current, codedMsg);

		for (int i = 0; i < inputString.length(); i++) {
			try {
				secretMessage += codeTable[inputString.charAt(i) - 65];// appends the representation for a given char onto the secret message
			} catch (ArrayIndexOutOfBoundsException e) {
				secretMessage += codeTable[28];// this assumes any invalid input
												// is just the eof char '*'
			}
		}
		System.out.println("Encoded Message:  ");
		System.out.println(secretMessage);
	}

	/**
	 * this is the recursive method called by the public method above. localRoot
	 * is a recursive parameter so that we might traverse the tree using the
	 * recursive structure of a tree. binString is where each individual binary
	 * representation gets stored while recursing.
	 * 
	 * @param localRoot
	 *            the local root (so as to make it easier to recurse)
	 * @param binString
	 *            the individual generated binary string which gets concatenated
	 *            onto secretMessage, which is the final binary string which
	 *            gets generated
	 */
	private static void makeCodeTable(Node localRoot, String binString) {
		if (!(localRoot.ch == '+')) {
			if (localRoot.ch == '*') {// checks for non-letter chars and
										// substitutes them for our internal
										// representation of them
				codeTable[28] = binString;
			} else if (localRoot.ch == '[') {
				codeTable[27] = binString;
			} else if (localRoot.ch == '\\') {
				codeTable[26] = binString;
			} else {
				codeTable[(((int) localRoot.ch) - 65)] = binString;// the reason we do not use the static ALPHABET string from above is because here we are
				// //indexing into the actual codeTable array, not making
				// reference to a certain char.
			}

		} else {
			makeCodeTable(localRoot.leftChild, binString + "1"); // this is
																	// where we
																	// recurse
			makeCodeTable(localRoot.rightChild, binString + "0");

		}

	}// end makecodetab

	/**
	 * this is just a public helper method for the private recursive method
	 * below. it starts the recursive method on the root of the master huffman
	 * tree and then prints out the decoded message after the method below
	 * reaches the bottom of the recursion
	 */
	public static void decode() {

		decodeRecursive(huffTree.root, 0);
		System.out.println("Decoded Message");
		System.out.println(decodedMsg);

	}// end decod

	/**
	 * void decodeRecursive(Node current, int index) this method takes 2
	 * parameters, the local root, or current location in the master tree, and
	 * an index variable that gets incremented each recursion so that we can
	 * travel along the binary string secretMessage. the way this method checks
	 * for the bottom of the recursion is just by using a try/catch loop. i'm
	 * not sure if this is cleaner or easier to read than using an if statement
	 * or for loop.
	 * 
	 * @param current
	 *            the location we are at in the tree
	 * @param index
	 *            the location we are at in the binary secret message string
	 */
	private static void decodeRecursive(Node current, int index) {
		try {
			while (current.ch == '+') {// while on a non-leaf node, seek the
										// leaves and when it finds them,
										// appends them to the decoded message
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
		} catch (StringIndexOutOfBoundsException e) {
			return;
		}
	}

}
