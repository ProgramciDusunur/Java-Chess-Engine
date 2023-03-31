package motor;

public class OrtakHesap {
	
	
	public int sahCekisSagAltKoseHesaplanmasiGerekenKareSayisi(int i, int hedefKare) {
		 if (hedefKare % 9 == i%9 && i < hedefKare) {
			 return 7 - Math.max(i/8, i%8);
		 }
		 return 0;
	 }
	 public int sahCekisSolUstKoseHesaplanmasiGerekenKareSayisi(int i, int hedefKare) {
		 if (hedefKare % 9 == i%9 && i > hedefKare) {
			 return Math.min(i/8, i%8);
		 }		 
		 return 0;
	 }
	 public int sahCekisSolAltKoseHesaplanmasiGerekenKareSayisi(int i, int hedefKare) {
		 if (hedefKare % 7 == i%7 && i < hedefKare) {
			 return 7 - Math.max(i/8, 7 - i%8);
		 }
		 return 0;
	 }
	 public int sahCekisSagUstKoseHesaplanmasiGerekenKareSayisi(int i, int hedefKare) {
		 if (hedefKare % 7 == i%7 && i > hedefKare) {
			 return Math.min(i/8, 7 - i%8);
		 }
		 return 0;
	 }
	 
	 public int sagYatayKareSayisi(int i, int hedefKare) {
		 if (i%8 == hedefKare%8 && i < hedefKare) {
			 return hedefKare/8-i/8;
		 }
		return 0;
	 }
	 public int solYatayKareSayisi(int i, int hedefKare) {
		 if (i%8 == hedefKare%8 && i > hedefKare) {
			 return i/8-hedefKare/8;
		 }
		return 0;
	 }
	 public int ustDikeyKareSayisi(int i, int hedefKare) {
		 if (i/8 == hedefKare/8 && i < hedefKare) {
			 return hedefKare%8-i%8;
		 }
		return 0; 
	 }
	 public int altDikeyKareSayisi(int i, int hedefKare) {
		 if (i/8 == hedefKare/8 && i > hedefKare) {
			 return i%8-hedefKare%8;
		 }
		 return 0;			
	 }

}
