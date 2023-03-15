//Created by Dominic Scalies
//Sources: Lafore pg. 620, 622, 631-634, 639-640, 641-642, 662-664, 703-707
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Vertex Class
//Represents an individual in the social network
class Vertex
{
	public String person;		//the person from
	public boolean visited;		//has the vertex been visited
	
	public Vertex(String n)		//constructor
	{
		person = n;
	}
}

//Stack class
//Used to implement depth first search
class Stack
{
	private final int SIZE = 100;	//size cap to match maximum vertices
	private int[] st;				//the array where the stack will store information
	private int top;				//the current top of the stack
	
	public Stack()					//constructor
	{
		st = new int[SIZE]; 		//new array
		top = -1;
	}
	
	public void push(int i)			//put item on stack
	{
		st[++top] = i;
	}
	
	public int pop()				//take item off stack
	{
		return st[top--];
	}
	
	public int peek()				//peek at the top of the stack
	{
		return st[top];
	}
	
	public boolean isEmpty()		//true if nothing on stack
	{
		return (top == -1);
	}
}

//Queue class
//Used to implement breadth first search
class Queue
{
	private final int SIZE = 100;		//max size for the queue
	private int[] queArray;				//the Queue
	private int front;
	private int rear;
	
	public Queue()						//constructor
	{
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
	
	public void insert(int j)			//put item at rear of queue
	{
		if(rear == SIZE-1)
		{
			rear = -1;
		}
		queArray[++rear] = j;
	}
	
	public int remove()
	{
		int temp = queArray[front++];
		if(front == SIZE)
		{
			front = 0;
		}
		return temp;
	}
	
	public boolean isEmpty()
	{
		return (rear+1 == front || (front+SIZE-1 == rear));
	}
}

//for implementing dijkstra
class DistPar							//distance and parent
{										//items stored in sPath array
	public int distance;				//distance from start to this vertex
	public int parentVert;				//current parent of this vertex
	
	public DistPar(int pv, int d)		//constructor
	{
		distance = d;
		parentVert = pv;
	}
}

//Graph class
//Represents a weighted, directed graph of following relationships in a social network
class Graph
{
	private final int MAX_VERTS = 100;	//size cap
	private final int INFINITY = 1000000;
	private Vertex vertexList[];	//array of vertices
	private int adjMat[][];			//adjacency matrix
	private int nVerts;				//current number of vertices
	private int nTree;				//number of vertices in the tree
	private int nEdges;				//current number of edges
	private Stack theStack;			//used in dfs implementation
	private Queue theQueue;			//used in bfs implementation
	private int  tranClosure[][];	//transitive closure
	private DistPar sPath[];		//array for the shortest path for dijkstra
	private int currentVert;		//current vertex for dijkstra
	private int startToCurrent;		//distance to currentVert for dijkstra
	
	public Graph()					//constructor
	{
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		tranClosure = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		nTree = 0;
		for(int i = 0; i < MAX_VERTS; i++)
		{
			for(int j = 0; j < MAX_VERTS; j++)
			{
				adjMat[i][j] = INFINITY;
				tranClosure[i][j] = 0;
			}
		}
		theStack = new Stack();
		theQueue = new Queue();
		sPath = new DistPar[MAX_VERTS];
	}
	
	public void addVertex(String per)		//adds a vertex to the graph
	{
		vertexList[nVerts] = new Vertex(per);
		nVerts++;
	}
	
	//calculates the weight of an edge given a start and end node
	public void weight(int start, int end)	
	{
		if(adjMat[start][end] < INFINITY)
		{
			int followStart = 0;
			int followEnd = 0;
			for(int i = 0; i < nVerts; i++)
			{
				if(adjMat[i][start] < INFINITY)
				{
					followStart++;
				}
				if(adjMat[i][end] < INFINITY)
				{
					followEnd++;
				}
			}
			adjMat[start][end] = (nEdges - followStart)*(nEdges - followEnd);
		}
	}
	
	public void addEdge(int start, int end)	//adds an edge to the graph
	{
		adjMat[start][end] = INFINITY - 1;
		nEdges++;
	}
	
	//for part 1
	public String getVertexName(int num)
	{
		return vertexList[num].person;
	}
	
	public int getVertexNum(String name)
	{
		for(int i = 0; i < nVerts; i++)
		{
			if(vertexList[i].person.equals(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	//depth first search
	public void dfs(String start, String end)
	{
		System.out.print("Depth First Search:");
		boolean found = false;
		int thePath[] = new int[nVerts];	//the current path
											//begin at vertex 0
		for(int i = 0; i < nVerts; i++)
		{
			thePath[i] = nVerts;			//initialize to an impossible state
		}
		int startIndex = getVertexNum(start);
		int goalIndex = getVertexNum(end);
		int steps = 0;
		vertexList[startIndex].visited = true;	//mark it visited
		theStack.push(startIndex);
		while(!theStack.isEmpty())		//until stack empty,
		{
			int v = getAdjUnvisitedVertexDFS(theStack.peek());
			if(v == -1)					//if no such vertex
			{
				if(theStack.peek() == goalIndex && steps > 0)
				{
					found = true;
					break;
				}
				else
				{
					theStack.pop(); 		//pop a new one
					steps--;
				}
			}
			else						//if the unvisited adjacent vertex exists
			{
				vertexList[v].visited = true;	//mark it
				theStack.push(v);				//put it on the stack
				steps++;
			}
		}
		
		for(int j = 0; j < nVerts; j++)
		{
			vertexList[j].visited = false;
		}
		
		if(found)
		{
			int currIndex = 0;
			while(!theStack.isEmpty())
			{
				thePath[currIndex] = theStack.peek();
				theStack.pop();
				currIndex++;
			}
		
			for(int i = nVerts-1; i >= 0; i--)
			{
				if(thePath[i] < nVerts)
				{
					System.out.print(" " + getVertexName(thePath[i]));
				}
			}
		}
		else
		{
			System.out.print(" No path between " + start + " and " + end + " could be found.");
		}
		System.out.println();
	}
	
	//returns an adjacent unvisited vertex for dfs
	public int getAdjUnvisitedVertexDFS(int v)
	{
		for(int j = 0; j < nVerts; j++)
		{
			if(adjMat[v][j] < INFINITY && vertexList[j].visited==false)
			{
				return j;
			}
		}
		return -1;
	}
	
	//breadth first search
	public void bfs(String start, String end)
	{
		System.out.print("Breadth First Search:");
		vertexList[getVertexNum(start)].visited = true;		//mark as visited
		theQueue.insert(getVertexNum(start));				//insert at rear
		int v2;
		boolean found = false;
		int thePath[] = new int[nVerts];
		int steps = 0;
		int goalIndex = getVertexNum(end);
		for(int i = 0; i < nVerts; i++)
		{
			thePath[i] = nVerts;
		}
		while(!theQueue.isEmpty())
		{
			boolean deadEnd = true;
			if(!found)
			{
				int v1 = theQueue.remove();		//remove vertex at head
				while((v2=getAdjUnvisitedVertexBFS(v1)) != -1 )
				{
					deadEnd = false;
					vertexList[v2].visited = true;
					theQueue.insert(v2);
					if(v2 == goalIndex)
					{
						found = true;
					}
				}
				if(!deadEnd)
				{
					thePath[steps] = v1;
					if(!found)
					{
						steps++;
					}
				}
			}
			else
			{
				while(!theQueue.isEmpty())
				{
					int potential = theQueue.remove();
					if(potential == goalIndex)
					{
						thePath[++steps] = potential;
						break;
					}
				}
			}
		}
		
		for(int j = 0; j < nVerts; j++)
		{
			vertexList[j].visited = false;
		}
		
		if(found)
		{
			for(int i = 0; i < nVerts; i++)
			{
				if(thePath[i] < nVerts)
				{
					System.out.print(" " + getVertexName(thePath[i]));
				}
			}
		}
		else
		{
			System.out.print(" No path between " + start + " and " + end + " could be found.");
		}
		System.out.println();
	}
	
	//returns an adjacent unvisited vertex for bfs
	public int getAdjUnvisitedVertexBFS(int v)
	{
		for(int j = 0; j < nVerts; j++)
		{
			if(adjMat[v][j] < INFINITY && vertexList[j].visited==false)
			{
				return j;
			}
		}
		return -1;
	}
	
	//implements Warshall's Algorithm
	public void warshall()
	{
		System.out.println("Warshall's Algorithm");
		for(int i = 0; i < nVerts; i++)
		{
			for(int j = 0; j < nVerts; j++)
			{
				tranClosure[i][j] = adjMat[i][j];
			}
		}
		for(int y = 0; y < nVerts; y++)			//looks at each row
		{
			for(int x = 0; x < nVerts; x++)		//looks at each cell in a row
			{
				if(tranClosure[y][x] < INFINITY)		//does a path from y to x exist?
				{
					for(int z = 0; z < nVerts; z++)	//looks at all cells (z, y)
					{
						if(tranClosure[z][y] < INFINITY)	//does a path from z to y exist?
						{
							//there is a path from x to z
							tranClosure[z][x] = Math.min((tranClosure[y][x] + tranClosure[z][y]), 
														tranClosure[z][x]);	
						}
					}
				}
			}
		}
		System.out.println("Transitive Closure Table Generated");
	}
	
	//uses the Transitive Closure to display who the entered person can reach
	public void warshallLookup(String name)
	{
		System.out.print("Transitive Closure: ");
		int row = getVertexNum(name);
		boolean deadEnd = true;
		for(int i = 0; i < nVerts; i++)
		{
			if(tranClosure[row][i] < INFINITY)
			{
				deadEnd = false;
				System.out.print(" " + getVertexName(i));
			}
		}
		if(deadEnd)
		{
			System.out.print(name + " cannot reach other users.");
			System.out.println();
		}
	}
	
	//get min distance entry from sPath
	public int getMin()
	{
		int minDist = INFINITY;
		int indexMin = 0;
		for(int j = 1; j < nVerts; j++)
		{
			if(!vertexList[j].visited && sPath[j].distance < minDist)
			{
				minDist = sPath[j].distance;
				indexMin = j;
			}
		}
		return indexMin;
	}
	
	//adjusts sPath for dijkstra's algorithm
	public void adjust_sPath()
	{
		//adjust values in shortest-path array sPath
		int column = 0;
		while(column < nVerts)				//go across columns
		{
			if(vertexList[column].visited)
			{
				column++;
				continue;
			}
			//calculate distance for one sPath entry
								//get edge from currentVert to column
			int currentToFringe = adjMat[currentVert][column];
								//add distance from start
			int startToFringe = startToCurrent + currentToFringe;
								//get distance of current sPath entry
			int sPathDist = sPath[column].distance;
			
			//compare distance from start with sPath entry
			if(startToFringe < sPathDist)		//if shorter, update sPath
			{
				sPath[column].parentVert = currentVert;
				sPath[column].distance = startToFringe;
			}
			column++;
		}
	}
	
	//implements Dijkstra's algorithm
	public void dijkstra(String name)
	{
		int startTree = getVertexNum(name);			//start at vertex name
		vertexList[startTree].visited = true;
		nTree = 1;					//put it in the tree
		
		//transfer row of distances from adjMat to sPath
		for(int j = 0; j < nVerts; j++)
		{
			int tempDist = adjMat[startTree][j];
			sPath[j] = new DistPar(startTree, tempDist);
		}
		
		//until all vertices are in the tree
		while(nTree < nVerts)
		{
			int indexMin = getMin();			//get minimum from sPath
			int minDist = sPath[indexMin].distance;
			
			if(minDist == INFINITY)
			{
				break;
			}
			else
			{
				currentVert = indexMin;
				startToCurrent = sPath[indexMin].distance;
			}
			vertexList[currentVert].visited = true;
			nTree++;
			adjust_sPath();
		}
		
		//prints shortest paths
		for(int i = 0; i < nVerts; i++)
		{
			if(i != startTree && sPath[i].distance != INFINITY)
			{
				String output = "";
			
				int curr = i;
				if(curr != startTree)
				{
					output = " " + vertexList[curr].person + output;
					while(sPath[curr].parentVert != startTree)
					{
						curr = sPath[curr].parentVert;
						output = " " + vertexList[curr].person + output;
					}
				}
				output = vertexList[i].person + ": " + name + output;
				System.out.println(output);
			}
		}
		
		nTree = 0;
		for(int i = 0; i < nVerts; i++)
		{
			vertexList[i].visited = false;
		}
	}
}

//driver class
public class SocialNetwork 
{

	public static void main(String[] args) throws IOException 
	{
		Graph network = new Graph();
		Scanner fileScan = new Scanner(new File("input1.txt"));
		//Scanner fileScan = new Scanner(new File("graph1.txt"));
		//Scanner fileScan = new Scanner(new File("graph2.txt"));
		//Scanner fileScan = new Scanner(new File("graph3.txt"));
		//Scanner fileScan = new Scanner(new File("graph4.txt"));
		//Scanner fileScan = new Scanner(new File("graph5.txt"));
		
		int numVerts = fileScan.nextInt(); //get number of individuals
		fileScan.nextLine();
		String preNetwork[][] = new String[numVerts][numVerts];	//holds the text file
		//there will be numVerts number of lines, starting with the starting individual, and 
		//followed exclusively by following relationships if any exist, and then a return 
		//character.
		int currPerson = 0;
		while(fileScan.hasNext())	//read in the txt file
		{
			int currFollow = 0;
			String row = fileScan.nextLine();	//gets next row
			Scanner rowScan = new Scanner(row);	//reads in data from the row
			//read in the individual's name and create their vertex in the graph
			preNetwork[currPerson][currFollow] = rowScan.next();
			currFollow++;
			//reads in following relationships
			while(rowScan.hasNext())
			{
				preNetwork[currPerson][currFollow] = rowScan.next();
				currFollow++;
			}
			currPerson++;
			rowScan.close();
		}
		fileScan.close();
		for(int i = 0; i < numVerts; i++) //adds vertices
		{
			network.addVertex(preNetwork[i][0]);
			for(int j = 1; j < numVerts; j++) //adds edges
			{
				if(preNetwork[i][j] != null)
				{
					String key = preNetwork[i][j];
					for(int k = 0; k < numVerts; k++) //finds index of name returned
					{
						if(key.equals(preNetwork[k][0]))
						{
							network.addEdge(i, k);
							k = numVerts;
						}
					}
					
				}
			}
		}
		//populates adjMat with proper weights
		for(int i = 0; i < numVerts; i++)
		{
			for(int j = 0; j < numVerts; j++)
			{
				network.weight(i, j);
			}
		}
		System.out.println("Graph Created");
		System.out.print("Social network members:");
		for(int i = 0; i < numVerts; i++)
		{
			System.out.print(" " + network.getVertexName(i));
		}
		//end of part 1
		System.out.println();
		System.out.println();
		//start of part 2
		Scanner input = new Scanner(System.in);
		System.out.println("Breadth and Depth First Search");
		System.out.print("Enter starting person: ");
		String start = input.nextLine();
		System.out.print("Enter goal person: ");
		String end = input.nextLine();
		network.dfs(start, end);
		network.bfs(start, end);
		System.out.println();
		network.warshall();
		System.out.print("Enter a name: ");
		network.warshallLookup(input.nextLine());
		System.out.println();
		System.out.println();
		System.out.println("Dijkstra's Algorithm");
		System.out.print("Enter a name: ");
		network.dijkstra(input.nextLine());
		input.close();
	}
}