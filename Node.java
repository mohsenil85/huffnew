class Node
{
	public int freq; // data item (key)
	public char ch; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(freq);
		System.out.print(", ");
		System.out.print(ch);
		System.out.print("} ");
	}
} // end class Node