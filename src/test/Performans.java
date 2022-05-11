package test;

import tahta.Tahta;

public class Performans extends Tahta {
	
	public Performans() {
		yeniArama();
		long mili = System.nanoTime();		
		for (int i = 100000000;i > 0;i--) {			
			
		}
		double kacSaniye = (( System.nanoTime() - mili)/ 1000000000.0);
		//System.out.println();
        System.out.println(kacSaniye);
	}
	public Performans(int deneme) {
		
	}
	public static void main(String [] args) {
		new Performans();
	}
	
	public void eskiArama() {
		/*
		String[] tahta = Tahta.tahtaVeriAl();
		char kare = 'a';
		for (byte i = 0, j = 8;i < tahta.length;i++) {
			if (i != 0) {kare++;}if (i == 8 || i == 16 ||i== 24 || i== 32 || i== 40 ||i== 48 || i== 56) {j--;kare = 'a';}			
			if (i % 8 == 0) {
				//System.out.println();
			}
			//String hesap = tahta[(tahtaDikeyCeviri((byte)(j))-1)*8+Character.getNumericValue(kare)-10];			
		}
		*/
	}
	public void yeniArama() {
		double[] tahta = Tahta.tahtaVeriAl();
		for (byte i = 0;i < tahta.length;i++) {						
			//double hesap = tahta[(i/8)*8+i%8];		
			if (tahta[i] == 900 && arayuzHamle[0].charAt(0) == 'S') {
				System.out.println("Evet bulduk");
			}
		}
	}
}
