//Milind Pathiyal

//USER INPUTS ROW AND COL --> PROGRAM REMOVES SELECTED OBJECT
import java.util.*;
import java.io.*;
public class EraseObject 
{
	private final int MAXROW = 20;
	private final int MAXCOL = 20;
	char[][] myTable = new char[MAXROW+1][MAXCOL+1];
	int myRow;
	int myCol;
	//Pre:NONE
	//Post:NONE
	public static void main(String[] args)
	{
		EraseObject e = new EraseObject();
		e.generate("digital (1).txt");
	}
    //Pre:FILENAME MUST EXIST
	//Post:PRINTS OUT RESULTS
    public void generate(String fileName)
    {
    	loadTable(fileName);
    	System.out.println("Image before an erasure\n");
    	printTable(myTable);
    	requestStart();
    	System.out.println("\nImage after first erasure\n");
    	removeObject(myTable, myRow, myCol);
    	requestStart();
    	System.out.println("\nImage after second erasure\n");
    	removeObject(myTable, myRow, myCol);
    	
    }
    //Pre:FILENAME & VARIABLES MUST EXIST
    //Post:STORES VALUES INTO myTable --> CREATES A DEFAULT SETTING TABLE
    public void loadTable(String fileName)
    {
    	for(int row = 1; row < myTable.length; row++)
    	{
    		for(int col = 1; col < myTable[0].length; col++)
    		{
    			myTable[row][col] = '-';
    		}
    	}
    	//DEFAULT SETTING FOR TABLE
    	try
    	{
    		Scanner in = new Scanner(new File(fileName));
    		int numCoordinates = in.nextInt();
    		for(int i = 0; i < numCoordinates; i++)
    		{
    			myTable[in.nextInt()][in.nextInt()] = '@'; 
    		}
    	}catch(FileNotFoundException e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    //Pre:NONE
    //Post:REGISTERS WHICH OBJECT USER WANTS TO REMOVE
    public void requestStart()
    {
   		Scanner key = new Scanner(System.in);
   		System.out.println("Enter starting row: ");
   		myRow = key.nextInt();
   		System.out.println("Enter starting col: ");
   		myCol = key.nextInt();
    }
    //Pre:VARIABLES MUST EXIST
    //Post:ERASES OBJECT
    public void removeObject(char[][] table, int row, int col)
    {
   		erase(table, row, col);
   		printTable(table);
    }
    //Pre:VARIABLES MUST EXIST
    //Post:CHECKS WHICH CELLS ARE TOUCHING AND REMOVES OBJE
    public void erase(char[][] table, int row, int col)
    {
   		if(checkBoundaries(row, col))
   		{
   			if(table[row][col] == '@')
   			{
   				table[row][col] = '-';
   				erase(table, row-1, col);
   				erase(table, row, col+1);
   				erase(table, row+1, col);
   				erase(table, row, col-1);		
   			}
   		}
    }
    //Pre:VARIABLES MUST EXIST
    //Post:PRINTS OUT TABLE WITH Y & X AXIS
    public void printTable(char[][] table)
    {
   		System.out.print("   ");
 		for(int row = 1; row < table[0].length; row++)
 			System.out.print(row%10);
 		
 		System.out.println();
 			
		for(int row = 1; row < table.length; row++){
			System.out.printf("%2d ", row);
			for(int col = 1; col < table[0].length; col++)
				System.out.print(table[row][col]);
			
			System.out.println();
		}
    }
    //Pre:VARIABLES MUST EXIST
    //Post:MAKES SURE ROW AND COL IS NOT OUT OF BOUNDS
    private boolean checkBoundaries(int row, int col)
    {
   		if(row >= 1 && row <= MAXROW && col >= 1 && col <= MAXCOL)
   			return true;
   		else 
   			return false;
    } 
}

/*
Image before an erasure

   12345678901234567890
 1 --------------------
 2 --------------------
 3 -@@@@@@@@@@@@@------
 4 -------------@------
 5 -------------@------
 6 -------------@------
 7 -------------@------
 8 -------------@------
 9 -------------@------
10 ----@@@@@-----------
11 ----@@@@@-----------
12 ----@@@@@-----------
13 ----@@--@-----------
14 ----@--@@-----------
15 ----@@@@@-----------
16 --------------------
17 -------------@@@@---
18 -------------@------
19 ----------@@@@------
20 ----------@---------
Enter starting row: 
11
Enter starting col: 
8

Image after first erasure

   12345678901234567890
 1 --------------------
 2 --------------------
 3 -@@@@@@@@@@@@@------
 4 -------------@------
 5 -------------@------
 6 -------------@------
 7 -------------@------
 8 -------------@------
 9 -------------@------
10 --------------------
11 --------------------
12 --------------------
13 --------------------
14 --------------------
15 --------------------
16 --------------------
17 -------------@@@@---
18 -------------@------
19 ----------@@@@------
20 ----------@---------
Enter starting row: 
3
Enter starting col: 
7

Image after second erasure

   12345678901234567890
 1 --------------------
 2 --------------------
 3 --------------------
 4 --------------------
 5 --------------------
 6 --------------------
 7 --------------------
 8 --------------------
 9 --------------------
10 --------------------
11 --------------------
12 --------------------
13 --------------------
14 --------------------
15 --------------------
16 --------------------
17 -------------@@@@---
18 -------------@------
19 ----------@@@@------
20 ----------@---------

 */