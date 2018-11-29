public class BoardGame {

		
		private char[][] gameBoard;
		private int board_size;
		private int max_levels;
		
		//This constructor will create the gameboard depending on the size
		public BoardGame (int board_size, int empty_positions, int max_levels)
		{
			
			this.board_size = board_size;
			this.max_levels = max_levels;
			
			gameBoard = new char [board_size][board_size];
					
			for (int i = 0; i < board_size; i++)
			{
				for(int j = 0; j < board_size; j++)
				{
					//give each tile a value of 'g'
					gameBoard[i][j] = 'g';
				}
			}
		}
		
		
		public HashDictionary makeDictionary()
		{
			HashDictionary dict = new HashDictionary(9973);
			return dict;
		}
		
		
		public int isRepeatedConfig(HashDictionary dict)
		{
			int score = 0;
			//calling the helper function which will give value to boardConfig
			String boardConfig = getBoardConfiguration();
			/*
			  using get score from HashDictionary to see if the string representing the gameboard 
			  is in the dict Dictionary, if it is then it will pass and return score, else using the
			  getScore function, it will return -1
			*/
			score = dict.getScore(boardConfig);
			return score;
		}
		
	
		public void putConfig(HashDictionary dict, int score)
		{
			//call the getBoardConfiguration helper function which will give value to boardConfig
			String boardConfig = getBoardConfiguration();
			//Put data into the Configuration object
			Configuration newConfig = new Configuration(boardConfig, score);
			try
			{
				dict.put(newConfig);
			}
			
			catch (DictionaryException e)
			{
				System.out.println("Error: putConfig dict exception");
			}
		}
		
		//stores the symbol which was entered (ex. it can be either 'g', 'o', or 'b')
		public void savePlay(int row, int col, char symbol)
		{
			gameBoard[row][col] = symbol;
		}
		
		
		
		//search a position to see if there is a 'g' (empty) value
		public boolean positionIsEmpty(int row, int col)
		{
			//if empty return true
			if (gameBoard[row][col] == 'g')
			{
				return true;
			}
			
			//if there is another value then return false
			else
			{
				return false;
			}
		}
		
		
		//search a position to see if it is the computers ('o') tile
		public boolean tileOfComputer(int row, int col)
		{
			//return true if it is the computers, else return false
			if(gameBoard[row][col] == 'o')
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		//search a position to see if it is the humans ('b') tile
		public boolean tileOfHuman(int row, int col)
		{
			//return true if it is the humans, else return false
			if(gameBoard[row][col] == 'b')
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		/*
		 * Function used to check if there is a winner or not. This is done by iterating through
		 * the gameboard and subsequently searching each row ,column, and diag if there is a winner.
		 * This is done by calling various helper functions which are explained later on
		 */
		public boolean wins(char symbol)
		{
			
			for(int i = 0; i < board_size; i++)
			{
				for(int j = 0; j < board_size; j++)
				{
					//check if there is a winner on each row
					if(rowCheck(i, j, symbol))
					{
						return true;
					}
					
					//check if there is a winner on each column
					if(colCheck(i, j, symbol))
					{
						return true;
					}
					
					//check if there is a winner for a left to right diagonal
					if(diagLeftToRight(i, j, symbol))
					{
						return true;
					}
					
					//check if there is a winner for a right to left diagonal
					if(diagRightToLeft(i, j, symbol))
					{
						return true;
					}
					
				}
			}
			
			//if there is no winner, return false and the game continues
			return false;
		}
		
		
		public boolean isDraw(char symbol, int empty_positions)
		{
			int[] empty = new int[2];
			int emptyRow = 0;
			int emptyCol = 0;
			
			
			
			//initialize variables used to find the empty spot, and how many there are
			int counter = 0;

			
			//count number of empty positions
			for(int i = 0; i < board_size; i++)
			{
				for(int j = 0; j < board_size; j++)
				{
					if (positionIsEmpty(i, j))
					{
						counter++;
						
					}
				}
			}
			
			//if there is more than one empty spot then it is impossible to be a draw, return false
			if(counter > 1)
			{
				return false;
			}
			
			//else check all surrounding tiles to see if there is a matching symbol
			else
			{
				//check the tile above, if there is no tile above then it will not check
				//as it will be out of bounds, it will then go to the next if statement
				if((emptyRow -1) > 0)
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow - 1][emptyCol] == symbol)
					{
						return false;
					}
					
				}
				
				//check the upper right, if there is no tile upper right then it will not 
				//check as it will be out of bounds, it will then go to the next if statement
				if(((emptyRow - 1) > 0) && ((emptyCol + 1) < board_size))
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow - 1][emptyCol + 1] == symbol)
					{
						return false;
					}
					
				}
				
				//check the  right, if there is no tile right then it will not check
				//as it will be out of bounds, it will then go to the next if statement
				if((emptyCol + 1) < board_size)
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow][emptyCol + 1] == symbol)
					{
						return false;
					}
					
				}
				
				//check the bottom right, if there is no tile bottom right then it will not 
				//check as it will be out of bounds, it will then go to the next if statement 
				if(((emptyRow + 1) < board_size) && ((emptyCol + 1) < board_size))
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow + 1][emptyCol + 1] == symbol)
					{
						return false;
					}
					
				}
				
				//check the tile below, if there is no tile below then it will not check
				//as it will be out of bounds, it will then go to the next if statement
				if((emptyRow + 1) < board_size)
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow + 1][emptyCol] == symbol)
					{
						return false;
					}
					
				}
				
				//check the bottom left, if there is no tile bottom left then it will not 
				//check as it wil be out of bounds, it will then go to the next if statement
				if(((emptyRow + 1) < board_size) && ((emptyCol - 1) > 0))
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow + 1][emptyCol - 1] == symbol)
					{
						return false;
					}
					
				}
				
				//check the left, if there is no tile left then it will not check
				//as it will be out of bounds, it will then go to the next if statement
				if((emptyCol - 1) > 0)
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow][emptyCol - 1] == symbol)
					{
						return false;
					}
					
				}
				
				//check the upper left, if there is no tile top left then it will not 
				//check as it will be out of bounds, it will then go to the next if statement
				if(((emptyRow - 1) > 0) && ((emptyCol - 1) > 0))
				{
					//if it is equal to the parameter symbol, then there is no draw
					if(gameBoard[emptyRow - 1][emptyCol - 1] == symbol)
					{
						return false;
					}
					
				}
				
				
				
			}
			
			//if the above tests fail, then the game is a draw and return true
			return true;
			
		}
		
		public int evalBoard(char symbol, int empty_positions)
		{
			//check if the user corresponding to symbol has won
			if (wins(symbol))
			{
				//if the human has won, return 0
				if (symbol == 'b')
				{
					return 0;
				}
				//if the computer has won, return 3
				else
				{
					return 3;
				}
			}
			
			//If there is a draw return 2
			else if (isDraw(symbol, empty_positions))
			{
				return 2;
			}
			
			//if no one has won and there is no draw, then return 1 and continue playing
			else
			{
				return 1;
			}
		}
		
		
		//ALL PRIVATE FUNCTIONS START HERE
		
		//Helper function to the wins() function, used to check if there is a winner for each row
		private boolean rowCheck(int row, int col, char symbol)
		{
			int counter = 0;
			
			while(row < board_size)
			{
				//if the index is = symbol, then iterate the counter
				if(gameBoard[col][row] == symbol)
				{
					counter++;
				}
				//if the counter is equal to board_size then that means there is a match and there is a winner
				if(counter == board_size)
				{
					return true;
				}
				
				row++;
			}
			//else return false, no winner
			return false;
		}
		//Helper function to the wins() function, used to check if there is a winner for each col
		private boolean colCheck(int row, int col, char symbol)
		{
			int counter = 0;
			while(col < board_size)
			{
				//if the index is = symbol, then iterate the counter
				if(gameBoard[col][row] == symbol)
				{
					counter++;
				}
				//if the counter is equal to board_size then that means there is a match and there is a winner
				if(counter == board_size)
				{
					return true;
				}
				
				col++;
			}
			//else return false, no winner
			return false;
		}
		//Helper function to the wins() function, used to check if there is a winner L to R diagonal
		private boolean diagLeftToRight(int row, int col, char symbol)
		{
			int counter = 0;
			while((row < board_size) && (col < board_size))
			{
				//if the index is = symbol, then iterate the counter
				if (gameBoard[col][row] == symbol)
				{
					counter++;
				}
				//if the counter is equal to board_size then that means there is a match and there is a winner
				if (counter == board_size)
				{
					return true;
				}
				
				col++;
				row++;
			}
			//else return false as there is no winner
			return false;
		}
		
		//Helper function to the wins() function, used to check if there is a winner R to L diagonal
		private boolean diagRightToLeft(int row, int col, char symbol)
		{
			int counter = 0;
			//because we are going from right to left, the rows will be dec but columns are not
			while((row >= 0) && (row < board_size) && (col < board_size))
			{
				//if the index is = symbol, then iterate the counter
				if (gameBoard[col][row] == symbol)
				{
					counter++;
				}
				//if the counter is equal to board_size then that means there is a match and there is a winner
				if (counter == board_size)
				{
					return true;
				}
				
				//because we are going from right to left and the col/rows are read backwards,
				//then increase col and decrease row
				col++;
				row--;
			}
			//else return false as there is no winner
			return false;
			
		}
		
		//this is the helper function which gets the gameboard config
		private String getBoardConfiguration()
		{
			String boardConfig = "";
			for (int i = 0; i < board_size; i++)
			{
				for (int j = 0; j < board_size; j++)
				{
					boardConfig += gameBoard[i][j];	
				}
			}
			return boardConfig;
		}
		
		private int[] getEmptySpace() {
			int[] empty = new int[2];
			
			for (int i = 0; i < board_size; i++) {
				for (int j = 0; j < board_size; j++) {
					if (positionIsEmpty(i, j)) {
						empty[0] = i;
						empty[1] = j;
						return empty;
					}
				}
			}		
			return empty;
		}
		
}
