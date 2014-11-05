import java.math.*;
public class ManhattanHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{	
		int cost = 0;
		int[][] tiles = state.tiles;
		int[][] goalTiles = goalState.tiles;
		for(int row=0; row<5; row++){
			for(int col = 0; col<3; col++){
				if(tiles[row][col]!=0 && tiles[row][col]!=-1){
					int tile = tiles[row][col];
					finder:
						for(int goalRow=0; goalRow<5; goalRow++){
							for(int goalCol = 0; goalCol<3; goalCol++){
								if(goalTiles[goalRow][goalCol]==tile){
									cost += Math.abs(goalRow-row) + Math.abs(goalCol-col);
									break finder;
								}
							}
						}
				}
			}
		}

		//Implement Manhattan heuristic
		return cost;
	}
}

