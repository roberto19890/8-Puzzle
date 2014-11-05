public class OwnHeuristic implements AStarHeuristic {

	public int getCost(Board state, Board goalState) {
		int cost = 0;
		boolean contains = false;
		int[][] tiles = state.tiles;
		int[][] goalTiles = goalState.tiles;
		
		//check if goal position is in the same row
		for(int i=0; i<5; i++)
		{
			for(int j = 0; j<3; j++)
			{	
				if(tiles[i][j]!=0 && tiles[i][j]!=-1)
				{
					rowCheck:
						for(int k = 0; k<3; k++)
							if(tiles[i][j] == goalTiles[i][k])
							{
								contains = true;
								break rowCheck;
							}
				if(!contains)
					cost++;
				}
				contains = false;
			}
		}
		
		//check if goal position is in the same column
		for(int j = 0; j<3; j++)
		{
			for(int i=0; i<5; i++)
			{
				if(tiles[i][j]!=0 && tiles[i][j]!=-1)
				{
					colCheck:
						for(int k=0; k<5; k++)
							if(tiles[i][j] == goalTiles[k][j])
							{
								contains = true;
								break colCheck;
							}
				if(!contains)
					cost++;
				}
				contains = false;
			}
		}
		return cost;
	}

}
