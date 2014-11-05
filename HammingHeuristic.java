
public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int cost = 0;
		int[][] tiles = state.tiles;
		int[][] goalTiles = goalState.tiles;
		for(int i=0; i<5; i++)
			for(int j = 0; j<3; j++)
				if(tiles[i][j]!=0 && tiles[i][j]!=-1)
					if(tiles[i][j] != goalTiles[i][j])
						cost++;
		return cost;
	}
}
