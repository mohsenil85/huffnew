// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ
{
	// array in sorted order, from max at 0 to min at size-1
	private int maxSize;
	private Tree[] queArray;
	private int nItems;
	//-------------------------------------------------------------
	public PriorityQ(int s) // constructor
	{
		maxSize = s;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	public int size(){
		return this.nItems;
	}
	//-------------------------------------------------------------
	public void insert(Tree item) // insert item
	{
		int j;
		if(nItems==0) // if no items,
			queArray[nItems++] = item; // insert at 0
		else // if items,
		{
			for(j=nItems-1; j>=0; j--) // start at end,
			{
				if( item.root.freq > queArray[j].root.freq ) // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = item; // insert it
			nItems++;
		} // end else (nItems > 0)
	} // end insert()
	//-------------------------------------------------------------
	public Tree remove() // remove minimum item
	{ return queArray[--nItems]; }
} // end class PriorityQ
