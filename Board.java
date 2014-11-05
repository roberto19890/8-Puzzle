import java.util.ArrayList;
public class Board implements Comparable<Board>
{
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	
	//evaluation function
	private int f;

	public Board(int[][] cells)                 //Populate states
	{	
		tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
				tiles[i][j] = cells[i][j];
	}
	
	//mutator
	public void setf(int fn){
		this.f = fn;
	}
	
	//accessor
	public int getf(){
		return this.f;
	}
	
	/*get path cost of board by 
	 * following its parent to the initial state
	*/
	public int getg(){
		int g = 0;
		Board temp = this.parent;
		while(temp!=null)
		{
			g++;
			temp = temp.getParent();
		}
		return g;
	}

	public boolean equals(Object x)         //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}

	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{	
		ArrayList<Board> successors = new ArrayList<Board>();
		int row = (getEmpty())[0];
		int col = (getEmpty())[1];
		int[][] cells;
		
		//not on right edge
		if(col<2){
			cells = copyOf(tiles);
			if(cells[row][col+1]!=-1)
			{
			cells[row][col] = cells[row][col+1];
			cells[row][col+1] = 0;
			successors.add(new Board(cells));
			}
		}
		
		//not on bottom edge
		if(row<4){
			cells = copyOf(tiles);
			if(cells[row+1][col]!=-1){
			cells[row][col] = cells[row+1][col];
			cells[row+1][col] = 0;
			successors.add(new Board(cells));
			}
		}
		
		//not on left edge
		if(col>0){
			cells = copyOf(tiles);
			if(cells[row][col-1]!=-1){
			cells[row][col] = cells[row][col-1];
			cells[row][col-1] = 0;
			successors.add(new Board(cells));
			}
		}
		
		//not on top edge
		if(row>0){
			cells = copyOf(tiles);
			if(cells[row-1][col]!=-1){
			cells[row][col] = cells[row-1][col];
			cells[row-1][col] = 0;
			successors.add(new Board(cells));
			}
		}
		for(Board board: successors){
			board.setParent(this);
		}
		return successors;
	}

	public void print()
	{
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}

	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}

	public Board getParent()
	{
		return parent;
	}
	
	//get location of empty tile
	private int[] getEmpty()
	{	
		int[] location = new int[2];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
				if(this.tiles[i][j] == 0)
				{
					location[0] = i;
					location[1] = j;
				}
		return location;
	}

	private int[][] copyOf(int[][] matrix)
	{
		int[][] tdArray = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
				tdArray[i][j] = matrix[i][j];
		return tdArray;
	}

	@Override
	//Comparator for priority queue
	public int compareTo(Board b) {
		if(this.f<b.getf())
			return -1;
		if(this.f>b.getf())
			return 1;
		return 0;
	}
}
