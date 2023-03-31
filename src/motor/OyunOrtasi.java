package motor;

public class OyunOrtasi {
	private int[] beyazAt =
			   {-50,-30,-30,-30,-30,-30,-30,-50,
				-40,-20,  0,  0,  0,  0,-20,-40,
				-30,  0, 15, 15, 15, 15,  0,-30,
				-30,  5, 15, 15, 15, 15,  5,-30,
				-30,  0, 15, 15, 15, 15,  0,-30,
				-30,  5, 10, 15, 15, 10,  5,-30,
				-40,-20,  0,  5,  5,  0,-20,-40,
				-50,-30,-30,-30,-30,-30,-30,-50,};
	private int[] beyazFil = 
			   {-20,-10,-10,-10,-10,-10,-10,-20,
				-10,  0,  0,  0,  0,  0,  0,-10,
				-10,  0,  5, 10, 10,  5,  0,-10,
				-10,  5,  5, 10, 10,  5,  5,-10,
				-10,  0, 10, 10, 10, 10,  0,-10,
				-10, 10, 10, 10, 10, 10, 10,-10,
				-10,  5,  0,  0,  0,  0,  5,-10,
				-20,-10,-10,-10,-10,-10,-10,-20,};
	private int[] beyazKale = 
				{0,  0,  0,  0,  0,  0,  0,  0,
				 5, 10, 10, 10, 10, 10, 10,  5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				 0,  0,  0,  5,  5,  0,  0,  0};
	private int[] beyazSah = 
			    {-30,-40,-40,-50,-50,-40,-40,-30,
				 -30,-40,-40,-50,-50,-40,-40,-30,
				 -30,-40,-40,-50,-50,-40,-40,-30,
				 -30,-40,-40,-50,-50,-40,-40,-30,
				 -20,-30,-30,-40,-40,-30,-30,-20,
				 -10,-20,-20,-20,-20,-20,-20,-10,
				  20, 20,  0,  0,  0,  0, 20, 20,
				  20, 30, 10,  0,  0, 10, 30, 20};
	private int[] beyazPiyon = 
				{0,  0,  0,  0,  0,  0,  0,  0,
				50, 50, 50, 50, 50, 50, 50, 50,
				10, 10, 20, 30, 30, 20, 10, 10,
				5,  5, 10, 25, 25, 10,  5,  5,
				0,  0,  0, 20, 20,  0,  0,  0,
				5, -5,-10,  0,  0,-10, -5,  5,
				5, 10, 10,-20,-20, 10, 10,  5,
				0,  0,  0,  0,  0,  0,  0,  0};
	private int[] beyazVezir = 
			   {-20,-10,-10, -5, -5,-10,-10,-20,
				-10,  0,  0,  0,  0,  0,  0,-10,
				-10,  0,  5,  5,  5,  5,  0,-10,
				 -5,  0,  5,  5,  5,  5,  0, -5,
				  0,  0,  5,  5,  5,  5,  0, -5,
				-10,  5,  5,  5,  5,  5,  0,-10,
				-10,  0,  5,  0,  0,  0,  0,-10,
				-20,-10,-10, -5, -5,-10,-10,-20};
	private int[] siyahAt =
				{-50,-30,-30,-30,-30,-30,-30,-50,
				 -40,-20,  0,  5,  5,  0,-20,-40,
				 -30,  0, 10, 15, 15, 10,  0,-30,
				 -30,  5, 15, 15, 15, 15,  5,-30,
				 -30,  0, 15, 15, 15, 15,  0,-30,
				 -30,  5, 15, 15, 15, 15,  5,-30,
				 -40,-20,  0,  0,  0,  0,-20,-40,
				 -50,-30,-30,-30,-30,-30,-30,-50,};
	private int[] siyahFil = 
				{-20,-10,-10,-10,-10,-10,-10,-20,
				-10,  5,  0,  0,  0,  0,  5,-10,
				-10, 10, 10, 10, 10, 10, 10,-10,
				-10,  0, 10, 10, 10, 10,  0,-10,
				-10,  5,  5, 10, 10,  5,  5,-10,
				-10,  0,  5, 10, 10,  5,  0,-10,
				-10,  0,  0,  0,  0,  0,  0,-10,
				-20,-10,-10,-10,-10,-10,-10,-20,};
	private int[] siyahKale = 
				{0,  0,  0,  5,  5,  0,  0,  0,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				-5,  0,  0,  0,  0,  0,  0, -5,
				5, 10, 10, 10, 10, 10, 10,  5,
				0,  0,  0,  0,  0,  0,  0,  0,};
	private int[] siyahSah =
				{20, 30, 10,  0,  0, 10, 30, 20,
				 20, 20,  0,  0,  0,  0, 20, 20,
				-10,-20,-20,-20,-20,-20,-20,-10,
				-20,-30,-30,-40,-40,-30,-30,-20,
				-30,-40,-40,-50,-50,-40,-40,-30,
				-30,-40,-40,-50,-50,-40,-40,-30,
				-30,-40,-40,-50,-50,-40,-40,-30,
				-30,-40,-40,-50,-50,-40,-40,-30,};
	private int[] siyahPiyon = 
				{0,  0,  0,  0,  0,  0,  0,  0,
				 5, 10, 10,-20,-20, 10, 10,  5,
				 5, -5,-10,  0,  0,-10, -5,  5,
				 0,  0,  0, 20, 20,  0,  0,  0,
				 5,  5, 10, 25, 25, 10,  5,  5,
				 10, 10, 20, 30, 30, 20, 10, 10,
				 50, 50, 50, 50, 50, 50, 50, 50,
				  0,  0,  0,  0,  0,  0,  0,  0};
	private int[] siyahVezir = 
				{-20,-10,-10, -5, -5,-10,-10,-20,
				 -10,  0,  0,  0,  0,  0,  0,-10,
				 -10,  0,  5,  5,  5,  5,  0,-10,
				 -5,  0,  5,  5,  5,  5,  0, -5,
				  0,  0,  5,  5,  5,  5,  0, -5,
				 -10,  5,  5,  5,  5,  5,  0,-10,
				 -10,  0,  5,  0,  0,  0,  0,-10,
				 -20,-10,-10, -5, -5,-10,-10,-20};
	
	// king end game
	/*-50,-40,-30,-20,-20,-30,-40,-50,
	-30,-20,-10,  0,  0,-10,-20,-30,
	-30,-10, 20, 30, 30, 20,-10,-30,
	-30,-10, 30, 40, 40, 30,-10,-30,
	-30,-10, 30, 40, 40, 30,-10,-30,
	-30,-10, 20, 30, 30, 20,-10,-30,
	-30,-30,  0,  0,  0,  0,-30,-30,
	-50,-30,-30,-30,-30,-30,-30,-50*/
	public double getBeyazAt(int i) {
		return beyazAt[i];
	}
	public double getBeyazFil(int i) {
		return beyazFil[i];
	}
	public double getBeyazKale(int i) {
		return beyazKale[i];
	}
	public double getBeyazSah(int i) {
		return beyazSah[i];
	}
	public double getBeyazPiyon(int i) {
		return beyazPiyon[i];
	}
	public double getBeyazVezir(int i) {
		return beyazVezir[i];
	}
	public double getSiyahAt(int i) {
		return siyahAt[i];
	}
	public double getSiyahFil(int i) {
		return siyahFil[i];
	}
	public double getSiyahKale(int i) {
		return siyahKale[i];
	}
	public double getSiyahSah(int i) {
		return siyahSah[i];
	}
	public double getSiyahPiyon(int i) {
		return siyahPiyon[i];
	}
	public double getSiyahVezir(int i) {
		return siyahVezir[i];
	}		
}
