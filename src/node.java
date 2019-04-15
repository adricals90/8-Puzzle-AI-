import java.awt.List;
import java.util.ArrayList;

public class node implements Comparable<node> {
	private int [][] state= new int[3][3];
    private 	int manhattan;
	private int misplacedNum;
	private node parent;
	private int level;
	
	private String action;
	int x, y;
	
	
	
	node(node parent, int[][] parentn, int level){
		this.state= parentn;
		this.parent= parent;
		this.level= level;
	}
	
    node(node parent, int[][] parentn, int level, String action){ // for children to add actions
		this.state= parentn;
		this.parent= parent;
		this.level= level;
		this.action= action;
	}
	
	public void setParent(node parent) {
		   this.parent=parent;
	}
	public int getMisplaceNum() {return misplacedNum;}
	
	public void setLevel(int l) {level=l;}

	public int setMisplaced() {
		
		if(puzzle.checkHeuristic == 0) {	
			
			misplacedNum=compare(puzzle.goalState)+level;
		}
		else {
		
			misplacedNum= manhattanHeuristic(puzzle.goalState)+level;   
		}
		
		return misplacedNum;
	}
	
	public void setFunc(int v) {
		this.misplacedNum=v;
	}
	
	public node getParent() {
		return parent; 
	}
	public int getLevel() {
		return level;
	}
	
	public void addNode(node n) {
		this.parent= n;	
	}
		
	public node getNode() { return parent;}
	
	public void setState(int [][] b) {this.state= b;}
	
	public int [][] getState(){ return state; }	
		
	public int getX() {return x;	}	
	
	public int getY() {return y;}
	
	
	
	public String toString() {

			  String aString = "";
			  for(int row = 0; row < state.length; row++) {
			     for(int col = 0; col < state[row].length; col++) {
			        aString +=" " + state[row][col];
			     }
			  }
			    
			  return /* aString+*/"\n level "+level+"  "+ "\n heuristic f(n)  "+misplacedNum +"\n Move: "+action ;

	}
	
	
	public int compare( int [][] b) { // this returns the number of misplaced tiles
		int m=0;
		for(int i=0; i<b.length; i++) {
			for(int j=0; j<b.length; j++) {
			    if (state[i][j]==0) {
						continue; 
				}
				if (state [i][j]==b[i][j]) {
					
				    m=m+0;
				    
 				}else {
					m++;
				}
		
			}
		}
		return m;
	}
	
	
	public void emptyTyle(int [][] b) { // returns the empty tiles coordinates
		
		for (int i = 0 ; i < 3; i++)
		    for(int j = 0 ; j < 3 ; j++)
		    {
		         if ( b[i][j] == 0)
		         {
		              x=i;
		              y=j;
		              break;
		         }
	       }
	}

	
	public int manhattanHeuristic( int[][] goal) {   //returns the Manhattan heuristic 
		
		int manhattan=0;
		int temp=0;
		
		
				for(int i=0; i<state.length; i++) {
					for(int j=0; j<state.length; j++) {
						
						if (state[i][j]== goal [i][j]|| state[i][j]==0) {
							manhattan= manhattan+0;
						}else {
							temp=state[i][j];
							manhattan+= manhattanD(temp,i,j,goal);
						}
					}
					
				}
		
		return manhattan;
	}
	
	public int manhattanD(int temp,int sX, int  sY, int [][] goal ) {
		int distance=0;
		for(int i=0; i<goal.length; i++) {
			for(int j=0; j<state.length; j++) {
				
				if (goal[i][j]==temp) {
					distance= Math.abs(sX-i)+ Math.abs(sY-j);
				}
			}
			
		}
		
		return distance;
	}
 	

	@Override
	public int compareTo(node o) {		// misplacedNum means compare  the object by heuristic Manhattan and misplaced 
		
		if(this.misplacedNum<o.misplacedNum) {
			return -1;
			
		}else if(this.misplacedNum>o.misplacedNum) {
			return 1;
		}
		else 
			return 0;
		
	}
	

}