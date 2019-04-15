
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class puzzle {

	 static int total=0;
	 static int [][] initialState = {{1,3,4}, {8,6,2},{7,0,5}};
	 static int [][] initialState1 = {{2,8,1}, {0,4,3},{7,6,5}};
	 static int [][] initialState2 = {{2,8,1},{4,6,3}, {0,7,5}};
	 static int [][] initialState3 = {{5,6,7},{4,0,8},{3,2,1}};
     static int [][] goalState  =     {{1,2,3},{8,0,4},{7,6,5}};
     static int checkHeuristic=0;
     
	public static void main(String[] args) {
		
		ArrayList <int [][]> array= new ArrayList<>();
		
		array.add(initialState);   //easy
		array.add(initialState1);  //medium
		array.add(initialState2);  //hard
		array.add(initialState3);  // Worst
		
		String [] dificulty= {" Easy ", " Medium ", " Hard ", " Worst "};
		
		 System.out.println("=================Mispaced Heuristic==================");
		for(int i =0; i<array.size(); i++) {
			System.out.println("///////////////////////////////");
			
			long tStart = System.currentTimeMillis();

			System.out.println("A* Search "+ dificulty[i]);
			searchA(array.get(i),goalState); 
			
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("total expanded "+total);
			System.out.println("Time taken " +elapsedSeconds);
			total=0;
			
			System.out.println("/--------------------------------------------");
			

	      	}
		checkHeuristic =1; // changes the heuristic to manhattan
		
		
		 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		 System.out.println(" =========Manhattan Heuristic==================== ");

			for(int m =0; m<array.size(); m++) {
				System.out.println("///////////////////////////////");
			
				long tStart = System.currentTimeMillis();

				System.out.println("A* Search "+ dificulty[m]);
				searchA(array.get(m),goalState); 
			
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta / 1000.0;
				System.out.println("total expanded "+total);
				System.out.println("Time taken " +elapsedSeconds);
				total=0;
			
			System.out.println("/--------------------------------------------");
		

	      	}
		
			for(int j=0; j<array.size();j++) {
				long tStart1 = System.currentTimeMillis();
				System.out.println(" IDA* Search "+ dificulty[j]);
				IDA(array.get(j), goalState);
				long tEnd1 = System.currentTimeMillis();
				long tDelta1 = tEnd1 - tStart1;
				double elapsedSeconds1 = tDelta1 / 1000.0;
				System.out.println("total expanded "+total);
				System.out.println("Time taken " +elapsedSeconds1);
			total=0;
			
			System.out.println("/--------------------------------------------");
			}
				
			for(int k=0;k<array.size();k++) {
				long tStart3 = System.currentTimeMillis();
				System.out.println(" Branch and Bound Search "+ dificulty[k]);
				branchAndBound(array.get(k), goalState);
				long tEnd2 = System.currentTimeMillis();
				long tDelta2 = tEnd2 - tStart3;
				double elapsedSeconds3 = tDelta2 / 1000.0;
				System.out.println("total expanded "+total);
				System.out.println("Time taken " +elapsedSeconds3);
				total=0;
				System.out.println("/--------------------------------------------");
	          }
	
	
		
}
	
	public static void printTable(int [][] a) {
		
			for(int i=0; i<3; i++) {
				System.out.println("");

				for(int j=0; j<3; j++) {
					System.out.print(" "+a[i][j] +"");
				}
		  }
			
			System.out.println(" ");
	}
	
	public static int compare(int [][] a ,int [][] b) {
		
	int misplacedNum=0;
	
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if (a [i][j]== b[i][j]) {
				    misplacedNum=misplacedNum+0;
				}else {
					misplacedNum++;
				}
			}
		}
		return misplacedNum;
	}

	public static ArrayList<node> expandNodes(node n) {// returns arraylist of children by move done
		n.emptyTyle(n.getState());
		int x= n.getX();
		int y= n.getY();
		ArrayList <node> children= new ArrayList<node>();
		
		if (x ==0&& y ==0) {	    // setMisplaced() is calculating the heuristic function;
				
			node child= new node(n,moveRight(x,y, copyArray(n.getState())),n.getLevel()+1, "Right");		child.setMisplaced();
			node child1=new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1, "Down"); 		child1.setMisplaced();
	
		     children.add(child);
		     children.add(child1);
		     return children;

		}
		if (x == 1 && y == 0) {		
			
			node child1=new node(n,moveRight(x,y, copyArray(n.getState())),n.getLevel()+1, "Right"); 			child1.setMisplaced();
			node child3= new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1, "Down");  			child3.setMisplaced();
			node child=new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1, "Up");				child.setMisplaced();
		
			  children.add(child1);
			  children.add(child3);
			  children.add(child);
			   return children;
		}
		if (x==2&& y==0) {		
			
		    node child3= new node(n,moveRight(x,y, copyArray(n.getState())), n.getLevel()+1,"Right");			child3.setMisplaced();
			node child=new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1, "Up");				     child.setMisplaced();
			  children.add(child3);
		      children.add(child);
		
		       return children;
		}
		if (x==0&& y==1) {		
			node child=new node(n,moveRight(x,y, copyArray(n.getState())),n.getLevel()+1,"Right");      		child.setMisplaced();
			node child1=new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1,"Down");			child1.setMisplaced();
			node child3= new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");			child3.setMisplaced();
	    
			children.add(child);
		    children.add(child1);
		    children.add(child3);
			return children;

		
		}
		if (x==1&& y==1) {		
			node child=new node(n,moveRight(x,y, copyArray(n.getState())),n.getLevel()+1,"Right");     		 child.setMisplaced();
			node child1=new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1,"Down");	 		     child1.setMisplaced();
			node child2= new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");	  		 child2.setMisplaced();
			node child3= new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1,"Up");		  		 child3.setMisplaced();

			     children.add(child);
				 children.add(child1);
				 children.add(child2);
				 children.add(child3);
				 return children;
			
		}
		if (x==2&& y==1) {		
			node child=new node(n,moveRight(x,y, copyArray(n.getState())),n.getLevel()+1,"Right");		child.setMisplaced();
			node child1=new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");		child1.setMisplaced();
			node child3= new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1,"Up");			child3.setMisplaced();
			  children.add(child);
			  children.add(child1);
			  children.add(child3);
			   return children;

		} 
			
		if (x==0&& y==2) {		
			node childs=new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1,"Down");   			childs.setMisplaced();
			node childs1=new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");   		childs1.setMisplaced();

			  children.add(childs);
			  children.add(childs1);
			
			   return children;
		}
		if (x==1&& y==2) {		
			node childs=new node(n,moveDown(x,y, copyArray(n.getState())),n.getLevel()+1,"Down");		childs.setMisplaced();	
			node childs1=new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");			childs1.setMisplaced();
			node childs3= new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1,"Up");			childs3.setMisplaced();
		
			
			     children.add(childs);
				 children.add(childs1);
				 children.add(childs3);
				 return children;

		}
		
		if (x==2&& y==2) {		
			node childs=new node(n,moveLeft(x,y, copyArray(n.getState())),n.getLevel()+1,"Left");  	childs.setMisplaced();
			node childs1=new node(n,moveUp(x,y, copyArray(n.getState())),n.getLevel()+1,"Up");	   childs1.setMisplaced();
		
		   
	
			  children.add(childs);
			  children.add(childs1);
			
			   return children;
			
		  }
		

		return children;
	    
	  }
		
	
	
	public static int [][] moveRight(int row, int column, int [][] state ) { // moves empty space
		int[][] a= state;
		int tile = a[row][column];
		 a [row][column] = a [row][column + 1];
		 a [row][column + 1] = tile;		
		  total++;
		 return a;
	}
	public static int [][] moveLeft(int row, int column, int [][] state) {
		int [][] a= state;
		int tile = a[row][column];
		 a [row][column] = a [row][column-1];
		 a [row][column-1] = tile;
		 total++;
		 return a;
	}
	public static int [][] moveUp(int row, int column, int [][] state ) {
		int [][] a= state;
		int tile = a[row][column];
		 a[row][column] = a [row-1][column];
		 a [row-1][column] = tile;
		 total++;
		 return a;
	}
	public static int [][] moveDown(int row, int column, int [][] state ) {
		int [][] a= state;
		 int tile = a[row][column];
		a [row][column] = a [row+1][column];
		a [row+1][column] = tile;	
		total++;
		return a;
	}	
	
	
	public static int [][] copyArray(int [][] b) {// deep copy
		int [][] a= new int [b.length][b.length];
		
		for (int i=0; i<b.length; i++) {
			for (int j=0; j<b.length; j++) {
				a[i][j]= b[i][j];
				
			}
		}
		
		return a;
	}
	
	public static int optimalPath(node n) { //Path for bottom to root, 
		int i=0;
		while(n.getParent()!=null) {
			printTable(n.getState());
			System.out.println(n);
			    n= n.getParent();	
			i++;
		}
		return i;
	}
	
	public static void searchA(int [][]  initial, int  [][] goal) { // A star with both heuristics
		
		node root = new node (null,initial,0);
		root.setState(initial);
	    PriorityQueue<node> pQueueA= new PriorityQueue<node>();
		pQueueA.add(root);
		int  visited=0;
		HashMap <String,node> close = new HashMap<String,node>();
		
		while (!pQueueA.isEmpty()) {
			
			  node current= pQueueA.remove();	
			  current.setMisplaced();
			  visited++;
			  
			  if(Arrays.deepEquals(copyArray(current.getState()), goal)){
				  
				  printTable(current.getState());
				  System.out.println("Goal found!  ");
				  System.out.println("OptimalPath "+optimalPath(current));
				  System.out.println("nodes visited  " + visited);
					break;
				   
			  }else {

				   if(!close.containsKey(Arrays.deepToString(current.getState()))) {
				     close.put(Arrays.deepToString(current.getState()), current);
				     
				         
				          ArrayList<node> children= expandNodes(current);
				          for(int i=0; i< children.size(); i++) {
				        	  	pQueueA.add(children.get(i));
				          }
				          
				   }


			  }	  
			 
		}
				
	}

   static int count=0;
	public static void IDA(int [][] initial , int goal [][]) {
		 
		node root= new node (null, initial, 0);
		root.setMisplaced();
		int depthByHeuristic= root.setMisplaced();  // initial heuristic 
				
		boolean goals= false;
		while(goals==false) {
			
			System.out.println(" goal not found");

			depthByHeuristic= DFS(root,depthByHeuristic);
		    count++;
			System.out.println(" new limit equals   "+ depthByHeuristic);
			 
			if (depthByHeuristic==0) {
				goals= true;
				
				System.out.println(" goal found ");
			}
			
			
		}		
	
	}
	
	public static int  DFS (node n, int depth) {  //depth first search
	
		if (n.setMisplaced()>depth) {
			return n.setMisplaced(); // returns new heuristic bound
		}
		
		 if (Arrays.deepEquals(n.getState(), goalState)) {
			  optimalPath(n);
			  return 0;  
		 }
		
			         int min=99999999;
			     
			         ArrayList<node> children = expandNodes(n);
			        
			          for(int i=0; i< children.size(); i++) {
			        	  
							children.get(i).setMisplaced();
					
							
								if (n.setMisplaced()<=depth) {
									
									int depth2=DFS(children.get(i), depth);
									
									if(depth2==0) { // iff goal is found 
								 		   return 0;
								 	}
									
								if (depth2<=min) {
									
									min = depth2;
							     }		
							}
			          }
			         
				return min;
	}
	
	
	public static void branchAndBound (int [][] initial, int [][] goal) {//  branch and bound 
		
		node root = new node(null, initial, 0);
		root.setMisplaced();
	 
	    PriorityQueue<node> pQueue1= new PriorityQueue<node>();
		pQueue1.add(root);
		HashMap<String, node> close = new HashMap<String , node>();
		
		int bestSolution=999999;
		
		while(!pQueue1.isEmpty()){
			
			node currentNode= pQueue1.remove();
			currentNode.setMisplaced();
			
			if (Arrays.deepEquals(currentNode.getState(), goal)) {
				if (currentNode.setMisplaced()< bestSolution)
				 bestSolution= currentNode.setMisplaced();
				
				printTable(currentNode.getState());
				System.out.println(" Best Solution Update");
				System.out.println(currentNode);
				
			} else {
				      ArrayList<node> children=expandNodes(currentNode);
				
			      	for(int i=0; i< children.size(); i++) {
			         
			         	    int n = children.get(i).setMisplaced();
				
						if (n >bestSolution) {  // children pruned if they have bigger f(n)
							continue;    // skip iteration

						}else {
							pQueue1.add(children.get(i));
						}
				  }
				
			}
			
		}
		
	}
	
}
