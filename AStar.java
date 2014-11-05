import java.util.*;

public class AStar
{
	
	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
		//create data structures for Explored and Frontier
		ArrayList<Board> Explored = new ArrayList<Board>();
		PriorityQueue<Board> Frontier = new PriorityQueue<Board>();

		//add initial state to the frontier
		initialState.setf(this.heuristic.getCost(initialState, goalState));
		Frontier.add(this.initialState);


		while (!Frontier.isEmpty())
		{	
			Board n = Frontier.poll();
			Explored.add(n);
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/

			if (n.equals(goalState))
			{	

				//create stack for nodes along the goal path
				Stack<Board> parents = new Stack<Board>();
				Board tempBoard = n;
				while(!tempBoard.equals(initialState))
				{
					parents.push(tempBoard);
					tempBoard = tempBoard.getParent();
				}
				parents.push(initialState);
				
				//get number of states along path
				int pathLength = parents.size()-1;
				
				//print stack of states
				while(!parents.empty())
				{
					(parents.pop()).print();
					System.out.println();
				}
				System.out.println(pathLength);
				System.out.println(Explored.size());
				return;
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
			}

			ArrayList<Board> successors = n.getSuccessors();
			if(successors.remove(n.getParent())){}

			for (int i = 0 ;i<successors.size(); i++)
			{	
				
				int f, g, h;
				Board n1 = successors.get(i);

				if(!Frontier.contains(n1) && !Explored.contains(n1))
				{
					//get heuristic and path cost for states and set evaluation function
					h = this.heuristic.getCost(n1, goalState);
					g = n1.getg();
					f = g+h;
					n1.setf(f);
					
					//set parent to node
					n1.setParent(n);
					
					//add it to the Frontier
					Frontier.add(n1);
				}
				else
				{	
					/*iterate over Frontier to get the same state
					 * in Frontier
					*/
					Board tempBoard;
					Iterator<Board> itr = Frontier.iterator();
					frontierChecker:
						while(itr.hasNext())
						{	
							tempBoard = itr.next();
							if(tempBoard.equals(n1))
								if(tempBoard.getg()>n1.getg())
								{	
									/*remove inferior copy of board and
									 * add a better copy
									 */
									Frontier.remove(tempBoard);
									Frontier.add(n1);
									break frontierChecker;
								}
						}
					/*check if explored contains n and add it to Frontier
					 * if it is has been found with a better path
					*/
					if(Explored.contains(n1))
						if(Explored.get(Explored.indexOf(n1)).getg()<n.getg())
						{
							Explored.remove(n1);
							Frontier.add(n1);
						}
				}
			}
		}
		System.out.println("No Solution");
	}
}