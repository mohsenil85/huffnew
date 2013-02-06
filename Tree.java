// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*; // for Stack class
/////////////////////////////////////////////////////////////////////////////////////////
class Tree
{
	public Node root; // first node of tree
	// -------------------------------------------------------------
	public Tree() // constructor
	{ root = null; } // no nodes in tree yet
	//-------------------------------------------------------------
//	public void merge(Tree item1, Tree item2){
//		Node newNode = new Node();
//		newNode.freq = item1.root.freq + item2.root.freq;
//		newNode.ch = '+';
//		newNode.leftChild = item1.root;
//		newNode.rightChild = item2.root;
//		Tree aTree = new Tree();
//		aTree.insert(newNode);
//	}
	// -------------------------------------------------------------
	public void insert(Node n)
	{
		if(root==null) // no node in root
			root = n;
		else // root occupied
		{
			System.out.println("Something is wrong"); //insert is only called to create brand new trees, otherwise use merge()
		} // end else not root
	} // end insert()
	public void traverse(int traverseType)
	{
		switch(traverseType)
		{
		case 1: System.out.print("\nPreorder traversal: ");
		preOrder(root);
		break;
		case 2: System.out.print("\nInorder traversal: ");
		inOrder(root);
		break;
		case 3: System.out.print("\nPostorder traversal: ");
		postOrder(root);
		break;
		}
		System.out.println();
	}
	// -------------------------------------------------------------
	private void preOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			System.out.print(localRoot.freq + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	// -------------------------------------------------------------
	private void inOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.freq + " ");
			inOrder(localRoot.rightChild);
		}
	}
	// -------------------------------------------------------------
	private void postOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.freq + " ");
		}
	}
	// -------------------------------------------------------------
	public void displayTree()
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
				"......................................................");
		while(isRowEmpty==false)
		{
			Stack localStack = new Stack();
			isRowEmpty = true;
			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.freq + " " + temp.ch);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if(temp.leftChild != null ||
							temp.rightChild != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		} // end while isRowEmpty is false
		System.out.println(
				"......................................................");
	} // end displayTree()
	// -------------------------------------------------------------
} // end class Tree