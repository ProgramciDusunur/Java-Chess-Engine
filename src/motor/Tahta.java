package motor;

public class Tahta {		
	
	public void hamleYap(int i, boolean geriCevir, boolean siraKimde, double[] tahta) {		
		if (geriCevir) {			
			if (siraKimde) {
				if (i/100000 == 7) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = 900;
				} else {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = i/100000;
				}
				
			} else {
				if (i/100000 == 7) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = -900;
				} else {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = (i/100000)*-1;
				}
				
			}
						
		} else {
			if (siraKimde) {				
				tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
				tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
			} else {				
				tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
				tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000)*-1;
			}						
		}
	}

}
