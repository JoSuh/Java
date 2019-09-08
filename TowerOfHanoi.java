/****************************************************************************
 *
 * Created by: Heejo Suh
 * Created on: Apr 2018
 * Created for: learning
 * 
 * This program plays the tower of hanoi using recurion
 ****************************************************************************/



public class TowerOfHanoi{
	//plays the tower of hanoi using recursion


    //--------------------------
    static void towerOfHanoi(int diskNum, char startPoint, char midPoint, char endPoint) {
    	
        if (diskNum == 1){
        	//only one disk left-> move it to destination
            System.out.println("Disk 1:  " +  startPoint + " --> " + endPoint);
            return; //end recursion
        }

        towerOfHanoi(diskNum-1, startPoint, endPoint, midPoint);
        System.out.println("Disk " + diskNum + ":  " +  startPoint + " --> " + endPoint);
        towerOfHanoi(diskNum-1, midPoint, startPoint, endPoint);//start from mid point to endpoint
    }
    

    //--------------------------
	public static void main(String[] args) {
		int n = 3; // Number of disks
		towerOfHanoi(n, 'A', 'B', 'C');  // A, B and C are names of rods
	}
}
