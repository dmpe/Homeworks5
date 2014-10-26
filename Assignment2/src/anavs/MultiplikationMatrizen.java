package anavs;

public class MultiplikationMatrizen {

	public static void main(String[] args) {
		int A[][] = {{1, -2, 3, 4}, {-2, 3, 0, 1}, {4, -1, 2, 1}, {-2, 1, 3, -1}};
	    int B[][] = {{2, -4, -1, 1}, {-1, 1, -2, 2}, {5, 0, 3, -2}, {1, -2, 1, 0}};
	    int C[][] = new int[4][4];
		ThreadPool pool= new ThreadPool(5,A,B,C);
		 C=pool.multiplizieren();
	    
	    //Ausgabe von A
		System.out.println("A: ");
		for(int j =0;j<4;j++){
			for(int i=0;i<4;i++){
				String platzhalter= String.valueOf(A[i][j]);
				int leer=3-platzhalter.length();
				for (int k=0;k<leer;k++)
					System.out.print(" ");
				System.out.print(A[i][j]);
			
			}
			System.out.println();
		}
		System.out.println();
		//Ausgabe von B
		System.out.println("B: ");
		for(int j =0;j<4;j++){
			for(int i=0;i<4;i++){
				String platzhalter= String.valueOf(B[i][j]);
				int leer=3-platzhalter.length();
				for (int k=0;k<leer;k++)
					System.out.print(" ");
				System.out.print(B[i][j]);
			
			}
			System.out.println();
		}
		
		System.out.println();
		//Ausgabe von C
		System.out.println("C: ");
		for(int j =0;j<4;j++){
			for(int i=0;i<4;i++){
				String platzhalter= String.valueOf(C[i][j]);
				int leer=3-platzhalter.length();
				for (int k=0;k<leer;k++)
					System.out.print(" ");
				System.out.print(C[i][j]);
			
			}
			System.out.println();
		}
		
	}

}
