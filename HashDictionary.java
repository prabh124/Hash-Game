public class HashDictionary implements DictionaryADT
{
	public Node<Configuration>[] table;
	private int tableSize;
	
	public HashDictionary(int size)
	{
		tableSize = size;
		table = (Node<Configuration>[]) new Node[size];
	}
	

	public int put(Configuration data) throws DictionaryException
	{
		
				//use get scores as a helper function, if it returns -1 that means that we  can continue with inserting the node
				if(getScore(data.getStringConfiguration()) == -1)
				{
					int indexOfData = hashIndex(data.getStringConfiguration());
					
					Node<Configuration> tempNode = new Node<Configuration>(data);
					Node<Configuration> prevNode = tempNode;
					
					//if collision has occured, then return 1 and insert the element elsewhere
					if (table[indexOfData] != null)
					{
						//iterate until tempnode is equal to null and doesnt equal to the config data
						while((tempNode != null) &&(!(tempNode.getCurrentElement()).getStringConfiguration().equals(data.getStringConfiguration())))
						{
							prevNode = tempNode;
							tempNode = tempNode.getNextElement();
						}
						//inserting element
						tempNode.setNextElement(table[indexOfData]);
						table[indexOfData] = tempNode;
						return 1; 
					}
					//if null and no collision, return 0
					else
					{
						table[indexOfData] = tempNode;
						return 0;
					}
				}
				
				else
				{
					throw new DictionaryException("Node exception");
				}
		
	}
	
	
	public void remove(String config) throws DictionaryException 
	{
			
		if (getScore(config) != -1)
		{
			int indexOfData = hashIndex(config);
			
			Node<Configuration> tempNode = table[indexOfData];
			Node<Configuration> prevNode = tempNode;
			
			//if the current node is equal to the config, then set the prev node equal to current's next node.
			if(!(tempNode.getCurrentElement().getStringConfiguration().equals(config)))
			{
				while(!(tempNode.getCurrentElement().getStringConfiguration().equals(config)))
				{
					prevNode = tempNode;
					tempNode = tempNode.getNextElement();
				}
				
				
				//this line states that the next value of tempNode will become the next value of prev node
				prevNode.setNextElement(tempNode.getNextElement());
				
			}

			else
			{
				//if the current node is not equal to the current config, search through the list until they match
				prevNode.setNextElement(tempNode.getNextElement());
			}
		}
		
		//if the value returned from getScore is -1, then the given config does not exist
		else
		{
			throw new DictionaryException("Remove Failed");
			
		}

		
	}
	
	public int getScore(String config)
	{

		int indexOfData = hashIndex(config);
		
		
		Node<Configuration> tempNode = table[indexOfData];
		while((tempNode != null) && (!(tempNode.getCurrentElement().getStringConfiguration().equals(config))))
			
			{				
				tempNode = tempNode.getNextElement();
			}
		//if tempNode is not empty, return the value
		if(tempNode != null)
		{
			return tempNode.getCurrentElement().getScore();
		}
		//if the tempNode is null, return -1 as it is not found
		else
		{
			return -1;
		}
				
	}
	
	
	private int hashIndex(String config)
	{
		
		int i = config.length() - 1;
		int index = (int) config.charAt(i) % tableSize;
		
		while(i > 0)
		{
			index = (((int) config.charAt(i)) % tableSize) * (index + (int) Math.pow(97, i));
			index %= tableSize;
			i--;
		}

		return index;
	}
	

}
