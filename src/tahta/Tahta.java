package tahta;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import grafik.Grafik;

public class Tahta implements MouseListener,MouseMotionListener  {    
	   ArrayList<Integer> sahTehtid = new ArrayList<Integer>(
			  Arrays.asList(11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11,
					   		11,11,11,11,11,11,11,11
					   ));
    public LinkedList<Integer> cizilecekHamleler = new LinkedList<Integer>(),hamleler =new LinkedList<Integer>();
    private static JButton buton = new JButton();
    private static Grafik grafik = new Grafik();
    private int sahKonum = 15235,sahCekenTas = 100;
    private boolean arayuzDinleyiciKarar = false;
    private boolean arayuzHamleSira = true,arayuzCizimYenileme = false,beyazKisaRok = true,beyazUzunRok = true,siyahKisaRok = true,siyahUzunRok = true,piyonTerfi,beyazSah = false,siyahSah = false;    
    private Byte arayuzSayac = 0;
    public String[] arayuzHamle = new String[3];
    public int[] arayuzHamle2 = new int[2];   
    public static double[] tahta2 =
 	   {-5.0,-3.0,-4.0,-9.0,-900.0,-4.0,-3.0,-5.0,
 		-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
         5.0,3.0,4.0,9.0,900,4.0,3.0,5.0};
    public static double[] tahta3 =
  	   {0.0,0.0,0.0,0,0.0,-9.0,0.0,0.0,
  		0.0,0.0,9.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,-900.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,900.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0,0.0,0,0.0};   
    public static JFrame f = new JFrame();
    private static JLabel label[] = new JLabel[72];
    public Tahta() {
        /*
         * Yapýlacaklar;
         * Kýsa Rok , Uzun Rok / Tamamlandý
         * Geçerken Alma , Piyon Terfi / Tamamlandý
         * Açmaz , Þah Çekiþ Kontrol / Tamamlanmadý
         */    	
        if (buton.getActionListeners() != null) {
            buton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   long mili = System.nanoTime();                                        
                    //System.out.println(kacSaniye);                                        
                    cizilecekHamleler.clear();                      
                    sahTehtid();
                    yeniArama(true,true);
                    /*for (int i : cizilecekHamleler) {
                    	System.out.println(i);
                    }*/
                   	//System.out.println("Toplam Hamle = "+cizilecekHamleler.size());                   	
                    double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
                    System.out.println(kacSaniye);
                    cizilecekHamleler.clear();                    
                    /*for (Byte i = 0;i < sahTehtidi.size();i++) {
        			    if (i % 8 == 0) {
		    		    System.out.println();
			            }
			             System.out.print(sahTehtidi.get(i));
		            }*/


                }
            });
        }
        if (arayuzCizimYenileme) {
            grafik.arayuzAyarla();
            f.repaint();
            arayuzCizimYenileme = !arayuzCizimYenileme;
        }
        else if (arayuzCizimYenileme == false) {
            if (f != null) {
                grafik.arayuzAyarla();
                grafik.getGraphics();
                f.remove(grafik);
                f.add(grafik);
                f.repaint();
            }
        }
    }
    public static void main(String [] args) {            
        arayuzTahtaCizimleri();
        f.setSize(1000, 1000);
        f.setTitle("Satranç");
        f.add(buton);
        f.add(grafik);
        f.setResizable(false);        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        buton.setVisible(true);
        buton.setBounds(200,125,75,75);
        buton.addActionListener(null);
    }     
    public static double[] tahta2VeriAl() {
        return tahta2;
    }
    public void tahta2VeriAyarla(double[] ayarlanacakVeri) {
        tahta2 = ayarlanacakVeri;
    }
    public boolean arayuzDinleyiciKarar() {
        return arayuzDinleyiciKarar;
    }
    public void arayuzDinleyiciKararAyar() {
        arayuzDinleyiciKarar = !arayuzDinleyiciKarar;
    }
    public String[] arayuzHamlelerDeger() {
        return arayuzHamle;
    }   
    public LinkedList<Integer> cizilecekHamlelerVeriAl() {
        return cizilecekHamleler;
    }
    public void cizilecekHamleleriVeriAyarla(LinkedList<Integer> hamleler) {
        cizilecekHamleler = hamleler;
    }
    public boolean beyazKisaRokAtilabilirMi() {
        return beyazKisaRok;
    }
    public void beyazKisaRokAyarla(boolean kisaRok) {
        beyazKisaRok = kisaRok;
    }
    public boolean beyazUzunRokAtilabilirMi() {
        return beyazUzunRok;
    }
    public void beyazUzunRokAyarla(boolean uzunRok) {
        beyazUzunRok = false;
    }
    public boolean siyahKisaRokAtilabilirMi() {
        return siyahKisaRok;
    }
    public void siyahKisaRokAyarla(boolean kisaRok) {
        siyahKisaRok = kisaRok;
    }
    public boolean siyahUzunRokAtilabilirMi() {
        return siyahUzunRok;
    }
    public void siyahUzunRokAyarla(boolean uzunRok) {
        siyahUzunRok = uzunRok;
    }
    public void sahKonumAyarla(int sahkonum) {
    	sahKonum = sahkonum;
    }
    public int sahKonumAl() {    	
    	return sahKonum;
    }
    public boolean isPiyonTerfi() {
		return piyonTerfi;
	}
	public void setPiyonTerfi(boolean piyonTerfi) {
		this.piyonTerfi = piyonTerfi;
	}
	public boolean beyazSahAl() {
		return beyazSah;
	}
	public void beyazSahAyarla() {
		beyazSah = !beyazSah;
	}
	public boolean siyahSahAl() {
		return siyahSah;
	}
	public void siyahSahAyarla() {
		siyahSah = !siyahSah;
	}
    // Draw pieces , Taslari Cizdirme   
    public static void arayuzTahtaCizimleri() {     	
    	double[] tahta = tahta2VeriAl();
    	for (byte i = 0;i < tahta.length;i++) {
    		if (tahta[i] > 0 || tahta[i] < 0) {    			
    			 label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tahta[i]+".png"), JLabel.HEIGHT);    			 
                 f.add(label[i]);
                 label[i].setBounds(207+((i%8)*75), 200+((i/8)*75), 75,75);                 
    		}
    	}
    	label[64] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-9.0+".png"), JLabel.HEIGHT);    	
    	label[65] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-5.0+".png"), JLabel.HEIGHT);
    	label[66] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-4.0+".png"), JLabel.HEIGHT);
    	label[67] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-3.0+".png"), JLabel.HEIGHT);
    	label[68] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+9.0+".png"), JLabel.HEIGHT);    	
    	label[69] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+5.0+".png"), JLabel.HEIGHT);
    	label[70] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+4.0+".png"), JLabel.HEIGHT);
    	label[71] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+3.0+".png"), JLabel.HEIGHT);
    	f.add(label[64]);
    	f.add(label[65]);
    	f.add(label[66]);
    	f.add(label[67]);
    	f.add(label[68]);
    	f.add(label[69]);
    	f.add(label[70]);
    	f.add(label[71]);
    	label[64].setBounds(357,125,75,75);    	    	
    	label[65].setBounds(432,125,75,75);
    	label[66].setBounds(507,125,75,75);    	    	
    	label[67].setBounds(582,125,75,75);
    	label[68].setBounds(357,125,75,75);    	    	
    	label[69].setBounds(432,125,75,75);
    	label[70].setBounds(507,125,75,75);    	    	
    	label[71].setBounds(582,125,75,75);
    	label[64].setVisible(false);
    	label[65].setVisible(false);
    	label[66].setVisible(false);
    	label[67].setVisible(false);
    	label[68].setVisible(false);
    	label[69].setVisible(false);
    	label[70].setVisible(false);
    	label[71].setVisible(false);
    }    
    public void yeniHamle() {
    	double[] tahta = tahta2VeriAl();
    	for (int i : cizilecekHamleler) {
    		//System.out.println("Ý = "+i+" Arayüz hamle = "+arayuzHamle2[1]);
    		if (i > 0) {
    			if ((i/10000)%10 == (arayuzHamle2[1]%10) && tahta[((i/100)%10)*8+(i/10)%10] == 1 && (i/100)%10 == 1) {
    				label[68].setVisible(true);
    		    	label[69].setVisible(true);
    		    	label[70].setVisible(true);
    		    	label[71].setVisible(true);    		    	    		    	
    				setPiyonTerfi(true);    			
    			}
    			else if ((i/10000)%10 == (arayuzHamle2[1]%10) && (i/1000)%10 == (arayuzHamle2[1]/10)%10 && i/100000 == 900 && (arayuzHamle2[1]/10)%10-((i/10)%10) == 2) {
    				tahta[60] = 0.0;
    				tahta[63] = 0.0;
    				tahta[62] = 900.0;
    				tahta[61] = 5.0;
    				f.remove(label[60]);
    				f.remove(label[63]);
    				label[62] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+900.0+".png"), JLabel.HEIGHT);
    				label[61] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+5.0+".png"), JLabel.HEIGHT);
    				f.add(label[62]);
    				f.add(label[61]);
    				label[62].setBounds(207+(6*75),725,75,75);
    				label[61].setBounds(207+(5*75),725,75,75);
    			}
    			else if ((i/10000)%10 == (arayuzHamle2[1]%10) && (i/1000)%10 == (arayuzHamle2[1]/10)%10 && i/100000 == 900 && (arayuzHamle2[1]/10)%10-((i/10)%10) == -2) {
    				tahta[60] = 0.0;
    				tahta[56] = 0.0;
    				tahta[58] = 900.0;
    				tahta[59] = 5.0;
    				f.remove(label[60]);
    				f.remove(label[56]);
    				label[58] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+900.0+".png"), JLabel.HEIGHT);
    				label[59] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+5.0+".png"), JLabel.HEIGHT);
    				f.add(label[58]);
    				f.add(label[59]);
    				label[58].setBounds(207+(2*75),725,75,75);
    				label[59].setBounds(207+(3*75),725,75,75);
    				
    			}
    			else if (isPiyonTerfi() == false &&(i/10000)%10 == (arayuzHamle2[1]%10) && (i/1000)%10 == (arayuzHamle2[1]/10)%10 && arayuzHamle2[0] / 100 > 0 || (i/10000)%10 == ((arayuzHamle2[1]*-1)%10) && (i/1000)%10 == ((arayuzHamle2[1]*-1)/10)%10 && isPiyonTerfi() == false) {    				
    				if (arayuzHamle2[0] / 100 == 1 && i/100%10 == 3 && ((hamleler.get(hamleler.size()-1)*-1)/10000%10) == 3 && ((hamleler.get(hamleler.size()-1)*-1)/100%10) == 1) {    					
    					f.remove(label[((hamleler.get(hamleler.size()-1)*-1)/10000%10)*8+(hamleler.get(hamleler.size()-1)*-1)/1000%10]);
    					tahta[((hamleler.get(hamleler.size()-1)*-1)/10000%10)*8+(hamleler.get(hamleler.size()-1)*-1)/1000%10] = 0.0;    					    				
    				}    				
    				hamleler.add(i);
    				tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
    				tahta[((i/100)%10)*8+(i/10)%10] = 0.0; 
    				if (label[((i/100)%10)*8+(i/10)%10] != null) {f.remove(label[((i/100)%10)*8+(i/10)%10]);}
    				if (label[((i/10000)%10)*8+(i/1000)%10] != null) {f.remove(label[((i/10000)%10)*8+(i/1000)%10]);}    			
    				label[((i/10000)%10)*8+(i/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tahta[((i/10000)%10)*8+(i/1000)%10]+".png"), JLabel.HEIGHT);
    				f.add(label[((i/10000)%10)*8+(i/1000)%10]);
    				label[((i/10000)%10)*8+(i/1000)%10].setBounds(207+((i/1000)%10)*75,200+((i/10000)%10)*75,75,75);    				    		
    			}
    		}
    		else {        			
    			if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10 && tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] == -1 && ((i*-1)/100)%10 == 6) {
    				label[64].setVisible(true);
    		    	label[65].setVisible(true);
    		    	label[66].setVisible(true);
    		    	label[67].setVisible(true);
    				setPiyonTerfi(true);
    			}
    			else if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10 && i/100000 == -900 && (arayuzHamle2[1]/10)%10-(((i*-1)/10)%10) == 2) {
    				tahta[4] = 0.0;
    				tahta[7] = 0.0;
    				tahta[6] = -900.0;
    				tahta[5] = -5.0;
    				f.remove(label[4]);
    				f.remove(label[7]);
    				label[6] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[5] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-5.0+".png"), JLabel.HEIGHT);
    				f.add(label[6]);
    				f.add(label[5]);
    				label[6].setBounds(207+(6*75),200,75,75);
    				label[5].setBounds(207+(5*75),200,75,75);
    			}
    			else if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10 && i/100000 == -900 && (arayuzHamle2[1]/10)%10-(((i*-1)/10)%10) == -2) {
    				tahta[4] = 0.0;
    				tahta[0] = 0.0;
    				tahta[2] = -900;
    				tahta[3] = -5.0;
    				f.remove(label[4]);
    				f.remove(label[0]);
    				label[2] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[3] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+-5.0+".png"), JLabel.HEIGHT);
    				f.add(label[2]);
    				f.add(label[3]);
    				label[2].setBounds(207+(2*75),200,75,75);
    				label[3].setBounds(207+(3*75),200,75,75);
    			}
    			else if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10) {    				
    				hamleler.add(i);    				
    				tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = i/100000;    				
    				tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = 0.0;     				    				
    				if (label[(((i*-1)/100)%10)*8+((i*-1)/10)%10] != null) {System.out.println("");f.remove(label[(((i*-1)/100)%10)*8+((i*-1)/10)%10]);}
    				if (label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] != null) {f.remove(label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]);}
    				label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]+".png"), JLabel.HEIGHT);
    				f.add(label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]);
    				label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10].setBounds(207+(((i*-1)/1000)%10)*75,200+(((i*-1)/10000)%10)*75,75,75);       				
    			} 
    			else if (arayuzHamle2[0] / 100 == -1 && (i*-1)/100%10 == 4 && arayuzHamle2[0] != arayuzHamle2[1]) {    				
    				if ((i*-1)/10%10 != (i*-1)/10000%10) {    					
    					tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = 0.0;
    					tahta[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10] = i/100000;    					    					
    					tahta[((hamleler.get(hamleler.size()-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10] = 0.0;
    					f.remove(label[((hamleler.get(hamleler.size()-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]);
    					f.remove(label[(((i*-1)/100)%10)*8+((i*-1)/10)%10]);
    					label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tahta[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]+".png"), JLabel.HEIGHT);
    					f.add(label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]);    					
    					label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10].setBounds(207+((arayuzHamle2[1]/10)%10)*75,200+(arayuzHamle2[1]%10)*75,75,75);    					    					
    				}    			
    			}
    		}    		
    	}    	
    	f.remove(grafik);
    	f.add(grafik);
    	f.repaint();
    	tahta2VeriAyarla(tahta);
    }
    public void piyonTerfi(int hedefKare, double tasDeger,int piyonTerfiKare) {    	
    	double[] tahta = tahta2VeriAl();    	    	
    	if (tasDeger > 0) {
    		tahta[piyonTerfiKare] = 0.0;
    		tahta[hedefKare] = tasDeger;    		
    		f.remove(label[piyonTerfiKare]);
    		if (label[hedefKare] != null) {f.remove(label[hedefKare]);}
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tasDeger+".png"), JLabel.HEIGHT);
    		f.add(label[hedefKare]);
    		if (arayuzHamle2[1] < 0) {label[hedefKare].setBounds(207+(((arayuzHamle2[1]*-1)/10)%10)*75,200+((arayuzHamle2[1]*-1)%10)*75,75,75);} else {label[hedefKare].setBounds(207+((arayuzHamle2[1]/10)%10)*75,200+(arayuzHamle2[1]%10)*75,75,75);}    		
    		label[68].setVisible(false);
	    	label[69].setVisible(false);
	    	label[70].setVisible(false);
	    	label[71].setVisible(false);
    	}
    	else {    		
    		tahta[piyonTerfiKare] = 0.0;
    		tahta[hedefKare] = tasDeger;	
    		f.remove(label[piyonTerfiKare]);
    		if (label[hedefKare] != null) {f.remove(label[hedefKare]);}
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taþlarý\\"+tasDeger+".png"), JLabel.HEIGHT);
    		f.add(label[hedefKare]);
    		label[hedefKare].setBounds(207+((arayuzHamle2[1]/10)%10)*75,200+(arayuzHamle2[1]%10)*75,75,75);    		
    		label[64].setVisible(false);
	    	label[65].setVisible(false);
	    	label[66].setVisible(false);
	    	label[67].setVisible(false);
    	}    	    	
    	setPiyonTerfi(false);
    	cizilecekHamleler.clear();
    	f.remove(grafik);
    	f.add(grafik);
    	f.repaint();
    	tahta2VeriAyarla(tahta);
    }
    public void arayuzCizilecekLegalHamleler(boolean hamleAl,boolean sahKontrol,boolean motor) {
    	/*
        if (hamleAl && arayuzHamle[0] == null) {arayuzHamle[0] = "   ";}
        boolean solUstKose = true , sagUstKose = true , solAltKose = true , sagAltKose = true,solYatay = true , sagYatay = true , ustDikey = true , altDikey = true;
        
        String[] tahta = Tahta.tahtaVeriAl();
        char kare = 'a';
        for (byte i = 0, j = 8;i < tahta.length;i++) {        	                                    
            if (i != 0) {kare++;}if (i == 8 || i == 16 ||i== 24 || i== 32 || i== 40 ||i== 48 || i== 56) {j--;kare = 'a';}
            if (motor) {
            	arayuzHamle[0] = "ca8";
            }
            
            // King movements , Þah hareketleri
            if (sahKontrol && tahta[i] == "S" && arayuzHamle[0].charAt(0) == 'S' || sahKontrol && tahta[i] == "s" && arayuzHamle[0].charAt(0) == 's' || sahKontrol && hamleAl && tahta[i] == "s" || sahKontrol && hamleAl && tahta[i] == "S") {                
                if ((i+8 < tahta.length) && j-1 >= 1 && j-1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)))).charAt(0) != 't' && !Character.isUpperCase(tahta[i+8].charAt(0)) || (i+8 < tahta.length) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i+8].charAt(0))) {j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if ((i+9 < tahta.length) && kare+1 >= 'a' && kare+1 <= 'h' && j-1 >= 1 && j-1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i+9].charAt(0)) || (i+9 < tahta.length) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i+9].charAt(0))) {kare++;j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare--;j++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if ((i+7 < tahta.length) && kare-1 >= 'a' && kare-1 <= 'h' && j-1 >= 1 && j-1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i+7].charAt(0)) || (i+7 < tahta.length) && (i+7 < tahta.length) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)+1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i+7].charAt(0))) {kare--;j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare++;j++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if ((i+1 < tahta.length) && kare+1 >= 'a' && kare+1 <= 'h' && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8))*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i+1].charAt(0)) || (i+1 < tahta.length) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8))*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(1) != 'T'  && !Character.isLowerCase(tahta[i+1].charAt(0))) {kare++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}               
                if ((i-8 >= 0) && j+1 >= 1 && j+1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)))).charAt(0) != 't' && !Character.isUpperCase(tahta[i-8].charAt(0)) || (i-8 >= 0) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i-8].charAt(0))) {j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if ((i-1 >= 0) && kare-1 >= 'a' && kare-1 <= 'h' && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8))*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i-1].charAt(0)) || (i-1 >= 0) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8))*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i-1].charAt(0))) {kare--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if ((i-7 >= 0) && i-7 >= 0 && kare+1 >= 'a' && kare+1 <= 'h' && j+1 >= 1 && j+1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i-7].charAt(0)) || (i-9 >= 0) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+1))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i-7].charAt(0))) {kare++;j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare--;j--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}                
                if ((i-9 >= 0) && kare-1 >= 'a' && kare-1 <= 'h' && j+1 >= 1 && j+1 <= 8 && tahta[i] == "S" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(0) != 't' && !Character.isUpperCase(tahta[i-9].charAt(0)) || (i-9 >= 0) && tahta[i] == "s" && sahTehtidi.get((byte)((((i/8)-1)*8)+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)-1))).charAt(1) != 'T' && !Character.isLowerCase(tahta[i-9].charAt(0))) {kare--;j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare++;j--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if (beyazKisaRokAtilabilirMi() && sahTehtidi.get(62).charAt(0) != 't' && sahTehtidi.get(61).charAt(0) != 't' && tahta[61] == " " && tahta[62] == " " && tahta[63] == "K" && tahta[i] == "S" || siyahKisaRokAtilabilirMi() && tahta[6] == " " && tahta[5] == " " && tahta[7] == "k" && tahta[i] == "s") {kare+=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+"O-O");}else {tasLegalHamle.add(Character.toString(kare)+String.valueOf(j));}kare-=2;arayuzHamle[1] = "O-O";}
                if (beyazUzunRokAtilabilirMi() && tahta[59] == " " && tahta[58] == " " && tahta[57] == " " && tahta[56] == "K" && tahta[i] == "S" || siyahUzunRokAtilabilirMi() && tahta[3] == " " && tahta[2] == " " && tahta[1] == " " && tahta[0] == "k" && tahta[i] == "s") {kare-=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+"O-O-O");}else {tasLegalHamle.add(Character.toString(kare)+String.valueOf(j));}kare+=2;arayuzHamle[1] = "O-O-O";}
                if (tahta[60] != "S") {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}} if (tahta[4] != "s") {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
            }        
            
            // Knight movements , At hareketleri
            if (acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "A" && arayuzHamle[0].charAt(0) == 'A' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "a" && arayuzHamle[0].charAt(0) == 'a' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "A" || acmazKontrol() == "acmazYok" && tahta[i] == "a" && hamleAl || acmazKontrol() == "acmazYok" && tahta[i] == "a" && motor || acmazKontrol() == "acmazYok" && tahta[i] == "A" && motor) {
                if (kare+1 >= 'a' && kare+1 <= 'h' && j-2 >= 1 && j-2 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10] == " ") {kare++;j-=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare--;j+=2;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare+1)-10).charAt(0)+"T");}}}
                if (kare+1 >= 'a' && kare+1 <= 'h' && j+2 >= 1 && j+2 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10] == " ") {kare++;j+=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare--;j-=2;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare+1)-10).charAt(0)+"T");}}}
                if (kare+2 >= 'a' && kare+2 <= 'h' && j+1 >= 1 && j+1 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10] == " ") {kare+=2;j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare-=2;j--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare+2)-10).charAt(0)+"T");}}}
                if (kare+2 >= 'a' && kare+2 <= 'h' && j-1 >= 1 && j-1 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10] == " ") {kare+=2;j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare-=2;j++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare+2)-10).charAt(0)+"T");}}}
                if (kare-2 >= 'a' && kare-2 <= 'h' && j+1 >= 1 && j+1 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10] == " ") {kare-=2;j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare+=2;j--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j+1))-1)*8+Character.getNumericValue(kare-2)-10).charAt(0)+"T");}}}
                if (kare-2 >= 'a' && kare-2 <= 'h' && j-1 >= 1 && j-1 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10] == " ") {kare-=2;j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare+=2;j++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j-1))-1)*8+Character.getNumericValue(kare-2)-10).charAt(0)+"T");}}}
                if (kare-1 >= 'a' && kare-1 <= 'h' && j+2 >= 1 && j+2 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10] == " ") {kare--;j+=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare++;j-=2;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j+2))-1)*8+Character.getNumericValue(kare-1)-10).charAt(0)+"T");}}}
                if (kare-1 >= 'a' && kare-1 <= 'h' && j-2 >= 1 && j-2 <= 8) {if (tahta[i] == "a"  && Character.isUpperCase(tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10] == " " || tahta[i] == "A" && Character.isLowerCase(tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10].charAt(0)) || tahta[(tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10] == " ") {kare--;j-=2;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare++;j+=2;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}else {if (tahta[i] == "a") {sahTehtidi.set((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10, "t"+sahTehtidi.get((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10).charAt(1));}else{sahTehtidi.set((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10,sahTehtidi.get((tahtaDikeyCeviri((byte) (j-2))-1)*8+Character.getNumericValue(kare-1)-10).charAt(0)+"T");}}}
            }
            // Bishop movements , Fil hareketleri
            if (acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "F" && arayuzHamle[0].charAt(0) == 'F' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "f" && arayuzHamle[0].charAt(0) == 'f' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "F" || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "f" || acmazKontrol() == "acmazYok" && tahta[i] == "a" && motor || acmazKontrol() == "acmazYok" && tahta[i] == "A" && motor) {
                for (byte p = 1,c = 0;p < 8;p++) {
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 &&Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "f" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "F" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {c = 100;}if (tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && c != 100 || tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)))/0.125)*0.025)*10)) > 0) {if (tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 &&Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "f" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "F" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {c = 100;}if (tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && c != 100 || tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && c != 100) {c = p;while(((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p)/8 < 8) {if (tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 &&Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "f" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "F" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {c = 100;}if (tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && c != 100 || tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && c != 100) {c = p;while(((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p)/8 < 8) {if (tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 &&Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "f" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "F" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {c = 100;}if (tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && c != 100 || tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)))/0.125)*0.025)*10)) > 0) {if (tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}p++;}p = c;}}
                    //
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagUstKose) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagUstKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "s"  && tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "F") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}if (tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "k" || tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "K") {sagUstKose = true;}if (sagUstKose) {kare+=p;j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagUstKose = false;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solUstKose) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solUstKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "F") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "k" || tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "K") {solUstKose = true;}if (solUstKose) {kare-=p;j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solUstKose = false;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solAltKose) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solAltKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "F") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "k" || tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "K") {solAltKose = true;}if (solAltKose) {kare-=p;j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solAltKose = false;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagAltKose) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagAltKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "s"  && tahta[i] == "f") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "F") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}if (tahta[i] == "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "k" || tahta[i] == "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "K") {sagAltKose = true;}if (sagAltKose) {kare+=p;j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagAltKose = false;}}
                }
                if (hamleAl) {solUstKose = true;sagUstKose = true;solAltKose = true;sagAltKose = true;}
            }
            // Rook movements , Kale hareketleri
            if (acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && arayuzHamle[0].charAt(0) == 'K' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j && tahta[i] == "K" || acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && arayuzHamle[0].charAt(0) == 'k' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j && tahta[i] == "k" || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "K" || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "k" || acmazKontrol() == "acmazYok" && motor && tahta[i] == "k" || acmazKontrol() == "acmazYok" && motor && tahta[i] == "K") {
                for (byte p = 1,c = 0;p < 8;p++) {
                    if (Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "k" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "K" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {c = 100;}if (tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && c != 100 || tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)))/0.125)*0.025)*10)) > 0) {p++;if (tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}p = c;}}
                    if (Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "k" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "K" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {c = 100;}if (tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && c != 100 || tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)))/0.125)*0.025)*10)) > 0) {p++;;if (tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}p = c;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8) {if (tahta[i] == "k" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "S" || tahta[i] == "K" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "s") {c = 100;}if (tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] == "s" && c != 100 || tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] == "S" && c != 100) {c = p;while((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10 > 0) {if (tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8) {if (tahta[i] == "k" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "S" || tahta[i] == "K" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "s") {c = 100;}if (tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] == "s" && c != 100 || tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] == "S" && c != 100) {c = p;while((((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10)/8) < 8) {if (tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-9-1,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-9-1,sahTehtidi.get((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10).charAt(0)+"T");}p++;}p = c;}}
                    //
                    if (Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagYatay) {if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagYatay = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s" && tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "K") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}  if (tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "a" || tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "P") {sagYatay = true;}if (sagYatay) {kare+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagYatay = false;}}}
                    if (Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solYatay) {if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solYatay = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "K") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "a" || tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p]  != "P") {solYatay = true;}if (solYatay) {kare-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solYatay = false;}}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && altDikey) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != " ") {altDikey = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1] != "s"  && tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1] != "S" && tahta[i] == "K") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1).charAt(0)+"T");}}if (tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "s" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "f" || tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "S" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "P") {altDikey = true;}if (altDikey) {j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != " ") {altDikey = false;}}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && ustDikey) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != " ") {ustDikey = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1] != "s"  && tahta[i] == "k") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1] != "S" && tahta[i] == "K") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1).charAt(0)+"T");}}if (tahta[i] == "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "s" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "f" || tahta[i] == "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "S" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "P") {ustDikey = true;}if (ustDikey) {j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != " ") {ustDikey = false;}}}
                    if (tahta[63] != "K" && beyazKisaRokAtilabilirMi()) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}} else if (tahta[7] != "k" && siyahKisaRokAtilabilirMi()) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}}
                    if (tahta[56] != "K" && beyazUzunRokAtilabilirMi()) {if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}} else if (tahta[0] != "k" && siyahUzunRokAtilabilirMi()) {if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
                }
                if (hamleAl) {ustDikey = true;altDikey = true;solYatay = true;sagYatay = true;}
            }
            // Queen movements , Vezir hareketleri            
            if (acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "V" && arayuzHamle[0].charAt(0) == 'V' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "v" && arayuzHamle[0].charAt(0) == 'v' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "V" || acmazKontrol() == "acmazYok" && hamleAl && tahta[i] == "v" || acmazKontrol() == "acmazYok" && motor && tahta[i] == "V" || acmazKontrol() == "acmazYok" && motor && tahta[i] == "v") {
            	System.out.println();
                for (byte p = 1,c = 0,b = 0;p < 8;p++) {
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 &&Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {c = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && c != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p)/8)))/0.125)*0.025)*10)) > 0) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 &&Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {c = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && c != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && c != 100) {c = p;while(((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p)/8 < 8) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 &&Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {c = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && c != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && c != 100) {c = p;while(((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p)/8 < 8) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}p++;}p = c;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 &&Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {c = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && c != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && c != 100) {c = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p)/8)))/0.125)*0.025)*10)) > 0) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}p++;}p = c;}}
                    if (Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s") {b = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] == "s" && b != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] == "S" && b != 100) {b = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p)/8)))/0.125)*0.025)*10)) > 0) {p++;if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}p = b;}}
                    if (Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s") {b = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] == "s" && b != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] == "S" && b != 100) {b = p;while((byte)(((((((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)))))-(((((double)((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)-(((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p)/8)))/0.125)*0.025)*10)) > 0) {p++;;if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}p = b;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "s") {b = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] == "s" && b != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] == "S" && b != 100) {b = p;while((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10 > 0) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1,sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10).charAt(0)+"T");}p++;}p = b;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8) {if (tahta[i] == "v" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "S" || tahta[i] == "V" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "s") {b = 100;}if (tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] == "s" && b != 100 || tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] == "S" && b != 100) {b = p;while((((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10)/8) < 8) {if (tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-9-1,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10).charAt(1));}else {sahTehtidi.set((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-9-1,sahTehtidi.get((((tahtaDikeyCeviri(j))+p)*8)+Character.getNumericValue(kare)-10).charAt(0)+"T");}p++;}p = b;}}
                    //
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagUstKose) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagUstKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "s" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != "S") {sagUstKose = true;}if (sagUstKose) {kare+=p;j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagUstKose = false;}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solUstKose) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solUstKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "s" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != "S") {solUstKose = true;}if (solUstKose) {kare-=p;j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solUstKose = false;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solAltKose) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solAltKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "s" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != "S") {solAltKose = true;}if (solAltKose) {kare-=p;j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solAltKose = false;}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagAltKose) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagAltKose = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "s" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "P" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != "S") {sagAltKose = true;}if (sagAltKose) {kare+=p;j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagAltKose = false;}}
                    if (Character.getNumericValue(kare)-9+p >= 1 && Character.getNumericValue(kare)-9+p <= 8 && sagYatay) {if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagYatay = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s" && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,"t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(1));}else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p,sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p).charAt(0)+"T");}}  if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "s" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "a" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "S" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != "P") {sagYatay = true;}if (sagYatay) {kare+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1+p] != " ") {sagYatay = false;}}}
                    if (Character.getNumericValue(kare)-9-p >= 1 && Character.getNumericValue(kare)-9-p <= 8 && solYatay) {if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solYatay = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p, sahTehtidi.get((((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "p" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "k" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "f" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "v" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "s" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "a" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "A" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "K" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "S" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "V" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != "F" && tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p]  != "P") {solYatay = true;}if (solYatay) {kare-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}kare+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1)*8)+Character.getNumericValue(kare)-9-1-p] != " ") {solYatay = false;}}}
                    if (tahtaDikeyCeviri(j)+p >= 1 && tahtaDikeyCeviri(j)+p <= 8 && altDikey) {if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != " ") {altDikey = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1, sahTehtidi.get((((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-9-1).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "p" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "a" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "k" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "v" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "s" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "f" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "A" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "F" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "V" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "S" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "K" && tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != "P") {altDikey = true;}if (altDikey) {j-=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j+=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1+p)*8)+Character.getNumericValue(kare)-10] != " ") {altDikey = false;}}}
                    if (tahtaDikeyCeviri(j)-p >= 1 && tahtaDikeyCeviri(j)-p <= 8 && ustDikey) {if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != " ") {ustDikey = false;if (Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1] != "s"  && tahta[i] == "v") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1, "t"+sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1).charAt(1));} else if (Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1].charAt(0)) && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1] != "S" && tahta[i] == "V") {sahTehtidi.set((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1, sahTehtidi.get((((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-9-1).charAt(0)+"T");}}if (tahta[i] == "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "p" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "a" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "k" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "v" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "s" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "f" || tahta[i] == "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "A" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "F" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "V" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "S" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "K" && tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != "P") {ustDikey = true;}if (ustDikey) {j+=p;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j-=p;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}if (tahta[(((tahtaDikeyCeviri(j))-1-p)*8)+Character.getNumericValue(kare)-10] != " ") {ustDikey = false;}}}
                }
                if (hamleAl) {solUstKose = true;sagUstKose = true;solAltKose = true;sagAltKose = true;}
                if (hamleAl) {ustDikey = true;altDikey = true;solYatay = true;sagYatay = true;}
            }          
            // Pawn movements , Piyon hareketleri
            if (acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "P" && arayuzHamle[0].charAt(0) == 'P' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || acmazKontrol() == "acmazYok" && arayuzHamle[0].charAt(1) == kare && tahta[i] == "p" && arayuzHamle[0].charAt(0) == 'p' && Character.getNumericValue(arayuzHamle[0].charAt(2)) == j || hamleAl && tahta[i] == "P" || hamleAl && tahta[i] == "p" || motor && tahta[i] == "p" && acmazKontrol() == "acmazYok" || motor && tahta[i] == "P" && acmazKontrol() == "acmazYok") {
                if (j+1 >= 1 && j+1 <= 8 && tahta[(((tahtaDikeyCeviri(j))-1-1)*8)+Character.getNumericValue(kare)-9-1] == " " && tahta[i] == "P" || j-1 >= 1 && j-1 <= 8 && tahta[(((tahtaDikeyCeviri(j))-1+1)*8)+Character.getNumericValue(kare)-9-1] == " " && tahta[i] == "p") {if (tahta[i] == "P") {j++;} else {j--;};if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}if (tahta[i] == "P") {j--;} else {j++;}if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if (j+2 >= 2 && j+2 <= 8 && i/8+1 == 7 && tahta[(((tahtaDikeyCeviri(j))-1-1)*8)+Character.getNumericValue(kare)-9-1] == " " &&  tahta[(((tahtaDikeyCeviri(j))-1-2)*8)+Character.getNumericValue(kare)-9-1] == " " && tahta[i] == "P" || j-2 >= 2 && j-2 <= 8 && i/8+1 == 2 && tahta[(((tahtaDikeyCeviri(j))-1+1)*8)+Character.getNumericValue(kare)-9-1] == " " && tahta[(((tahtaDikeyCeviri(j))-1+2)*8)+Character.getNumericValue(kare)-9-1] == " " && tahta[i] == "p") {if (tahta[i] == "P") {j+=2;} else {j-=2;}if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}if (tahta[i] == "P") {j-=2;} else {j+=2;}if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}
                if (j+1 >= 1 && j+1 <= 8 && tahta[i] == "P" || j-1 >= 1 && j-1 <= 8 && tahta[i] == "p") {if (tahta[i] == "P" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-2)*8)+Character.getNumericValue(kare)-11].charAt(0))) {j++;kare--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j--;kare++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[i] == "P" && Character.isLowerCase(tahta[(((tahtaDikeyCeviri(j))-2)*8)+Character.getNumericValue(kare)-9].charAt(0))) {j++;kare++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j--;kare--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[i] == "p" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j)))*8)+Character.getNumericValue(kare)-11].charAt(0))) {j--;kare--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j++;kare++;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}if (tahta[i] == "p" && Character.isUpperCase(tahta[(((tahtaDikeyCeviri(j)))*8)+Character.getNumericValue(kare)-9].charAt(0))) {j--;kare++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j++;kare--;if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}}
                if (((hamleler.size() > 0)) && j == 5 && tahta[i] == "P"  && (hamleler.get(hamleler.size()-1).charAt(2) == '5' && hamleler.get(hamleler.size()-1).charAt(0) == 'p') || ((hamleler.size() > 0)) && j == 4 && tahta[i] == "p" && Math.abs((Character.getNumericValue(kare)-9)-(Character.getNumericValue(hamleler.get(hamleler.size()-1).charAt(1))-9)) == 1 && (hamleler.get(hamleler.size()-1).charAt(2) == '4' && hamleler.get(hamleler.size()-1).charAt(0) == 'P')) {if (j == 5) {kare = hamleler.get(hamleler.size()-1).charAt(1);j++;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j--;kare = (char)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+97);if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}} else {kare = hamleler.get(hamleler.size()-1).charAt(1);j--;if (hamleAl) {legalHamleHesap.add(tahta[i]+Character.toString(kare)+String.valueOf(j));} else {tasLegalHamle.add(tahta[i]+Character.toString(kare)+String.valueOf(j));}j++;kare = (char)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)+98);if (!hamleAl) {tasLegalHamle.set(tasLegalHamle.size()-1, tasLegalHamle.get(tasLegalHamle.size()-1)+Character.toString(kare)+String.valueOf(j));}}}                
            }
                                 
        }*/
    }
    public void yeniArama(boolean motor, boolean sahKontrolsuz) {
		double[] tahta = Tahta.tahta2VeriAl();
		for (byte i = 0;i < tahta.length;i++) {									
			//King movements, Þah hareketleri
			if (tahta[i] == 900 && motor || tahta[i] == -900 && motor || tahta[i] == 900 && arayuzHamle2[0] / 100 == 900 || tahta[i] == -900 && arayuzHamle2[0] / 100 == -900) {	
				if (sahKontrolsuz) {
					sahArama(tahta,i);
				}
				else {
					if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
					if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				}				
				if (tahta[60] != 900) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}}
				if (tahta[4] != -900) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
				if (tahta[i] == -900 && siyahKisaRokAtilabilirMi() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 || tahta[i] == 900 && beyazKisaRokAtilabilirMi() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == -900 && siyahUzunRokAtilabilirMi() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[3] == 0.0 || tahta[i] == 900 && beyazUzunRokAtilabilirMi() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}
			}
			//Knight movements, At hareketleri
			if ( tahta[i] == 3 && motor || tahta[i] == -3 && motor || tahta[i] == 3 && arayuzHamle2[0] / 100 == 3 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -3 && arayuzHamle2[0] / 100 == -3 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {				
				if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)-2)*8+i%8-1] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8-2)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)-2)*8+i%8-1] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8-2)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}				
				if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)-1)*8+i%8-2] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)-1)*8+i%8-2] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8-1)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)-1)*8+i%8+2] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)-1)*8+i%8+2] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8-1)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)-2)*8+i%8+1] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8-2)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)-2)*8+i%8+1] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8-2)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)+2)*8+i%8+1] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8+2)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)+2)*8+i%8+1] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8+2)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)+2)*8+i%8-1] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8+2)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)+2)*8+i%8-1] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8+2)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)+1)*8+i%8-2] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8+1)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)+1)*8+i%8-2] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] > -1) {if (tahta[i] > 0) {if (tahta[((i/8)+1)*8+i%8+2] == -900.0){siyahSahAyarla();}cizilecekHamleler.add(3*100000+(i/8+1)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else {if (tahta[((i/8)+1)*8+i%8+2] == 900.0){beyazSahAyarla();}cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}				
			}
			//Bishop movements, Fil hareketleri
			if (tahta[i] == 4 && motor || tahta[i] == -4 && motor || tahta[i] == 4 && arayuzHamle2[0] / 100 == 4 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -4 &&  arayuzHamle2[0] / 100 == -4 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {				
				uzuntasHesap(true,i,tahta);
			}
			//Rook movements, Kale hareketleri
			if (tahta[i] == 5 && motor || tahta[i] == -5 && motor || tahta[i] == 5 && arayuzHamle2[0] / 100 == 5 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -5 && arayuzHamle2[0] / 100 == -5 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				//if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					uzuntasHesap(false,i,tahta);
				//}				
				if (tahta[63] != 5.0 && beyazKisaRokAtilabilirMi()) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}} else if (tahta[7] != -5.0 && siyahKisaRokAtilabilirMi()) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}}
                if (tahta[56] != 5.0 && beyazUzunRokAtilabilirMi()) {if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}} else if (tahta[0] != -5.0 && siyahUzunRokAtilabilirMi()) {if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
			}
			//Queen movements, Vezir hareketleri
			if (tahta[i] == 9 && motor || tahta[i] == -9 && motor || tahta[i] == 9 && arayuzHamle2[0] / 100 == 9 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -9 && arayuzHamle2[0] / 100 == -9 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {			
				uzuntasHesap(false,i,tahta);
				uzuntasHesap(true,i,tahta);
			}
			//Pawn movements, Piyon hareketleri
			if (tahta[i] == 1 && motor || tahta[i] == -1 && motor || tahta[i] == 1 && arayuzHamle2[0] / 100 == 1 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -1 && arayuzHamle2[0] / 100 == -1 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {			
				if (i/8-1 > 0 && tahta[i] == 1 || i/8+1 < 7 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
				if (i/8 == 6 && tahta[i] == 1 || i/8 == 1 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-2)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+2)*8+i%8] == 0.0)  {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+2)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
				if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 && tahta[(i/8-1)*8+i%8-1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 && tahta[(i/8-1)*8+i%8+1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] > 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == -1 && i/8 == 3 && ((hamleler.get(hamleler.size()-1)*-1)/10000%10) == 3 && ((hamleler.get(hamleler.size()-1)*-1)/100%10) == 1 || tahta[i] < 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == 1 && i/8 == 4 && (hamleler.get(hamleler.size()-1)/10000%10) == 4 && (hamleler.get(hamleler.size()-1)/100%10) == 6) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+((hamleler.get(hamleler.size()-1)/1000%10)*-1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(1*100000+(i/8+1)*10000+((hamleler.get(hamleler.size()-1)/1000%10))*1000+(i/8)*100+(i%8)*10);}}
				if (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
				if (i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}
				if (i/8 == 1 && i%8-1 > -1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8-1] < 0 || i/8 == 6 && i%8-1 > -1 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (i/8 == 1 && i%8+1 < 8 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8+1] < 0 || i/8 == 6 && i%8+1 < 8 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
			}
		}		
	}
    public void sahArama(double[] tahta, int i) {
    	if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8)/10 == 1 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
		if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8)/10 == 1 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8+1)/10 == 1 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8+1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8-1)/10 == 1 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8-1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8-1)/10 == 1 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8-1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8+1)/10 == 1 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8+1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8+1)/10 == 1 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8+1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
		if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8-1)/10 == 1 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8-1)%10 == 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
    }
    public void sahTehtid() {
    	sahTehtid = new ArrayList<Integer>(
  			  Arrays.asList(11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11,
  					   		11,11,11,11,11,11,11,11
  					   ));
    	cizilecekHamleler.clear();
    	yeniArama(true,false);
    	for (int i : cizilecekHamleler) {
    		if (i > 0) {    			
    			sahTehtid.set(((i/10000)%10)*8+(i/1000)%10, (sahTehtid.get(((i/10000)%10)*8+(i/1000)%10)/10)*10+2);
    		}
    		else {    			
    			sahTehtid.set((((i*-1)/10000)%10)*8+((i*-1)/1000)%10, 20+sahTehtid.get((((i*-1)/10000)%10)*8+((i*-1)/1000)%10)%10);    			
    		}
    	
    	}
    	/*for (Byte i = 0;i < sahTehtid.size();i++) {
    		if (i % 8 == 0) {
    			System.out.println();
    		}
    		System.out.print(sahTehtid.get(i)+" ");
    	}*/
    	cizilecekHamleler.clear();
    }
    public void uzuntasHesap(boolean yon, byte i, double[] tahta) {
    	if (yon) {    		
    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			
    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 && tahta[i] < 0) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8+solAltKose)*10000+(i%8-solAltKose)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8+solAltKose)*10000-(i%8-solAltKose)*1000-(i/8)*100-(i%8)*10));} if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0) {solAltKose = 8;}} else {solAltKose = 8;}
    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1  && tahta[i] < 0) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8-solUstKose)*10000+(i%8-solUstKose)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8-solUstKose)*10000-(i%8-solUstKose)*1000-(i/8)*100-(i%8)*10));} if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0) {solUstKose = 8;}} else {solUstKose = 8;}
    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 && tahta[i] < 0) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8-sagUstKose)*10000+(i%8+sagUstKose)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8-sagUstKose)*10000-(i%8+sagUstKose)*1000-(i/8)*100-(i%8)*10));} if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0) {sagUstKose = 8;}} else {sagUstKose = 8;}
    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 && tahta[i] < 0) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8+sagAltKose)*10000+(i%8+sagAltKose)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8+sagAltKose)*10000-(i%8+sagAltKose)*1000-(i/8)*100-(i%8)*10));} if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0) {sagAltKose = 8;}} else {sagAltKose = 8;}
    			
    		}    		
    	}
    	else {
    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
    			if ((ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] < 1 && tahta[i] > 0 || (ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] > -1 && tahta[i] < -1) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8-ustDikey)*10000+(i%8)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8-ustDikey)*10000-(i%8)*1000-(i/8)*100-(i%8)*10));}if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
    			if ((altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] < 1 && tahta[i] > 0 || (altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] > -1 && tahta[i] < -1) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8+altDikey)*10000+(i%8)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8+altDikey)*10000-(i%8)*1000-(i/8)*100-(i%8)*10));}if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
    			if ((sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] < 1 && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] > -1 && tahta[i] < -1) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8)*10000+(i%8+sagYatay)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8)*10000-(i%8+sagYatay)*1000-(i/8)*100-(i%8)*10));}if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
    			if ((solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] < 1 && tahta[i] > 0 || (solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] > -1 && tahta[i] < -1) {if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(i/8)*10000+(i%8-solYatay)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(i/8)*10000-(i%8-solYatay)*1000-(i/8)*100-(i%8)*10));}if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
    		}
    	}    	    	    	
    }   
    public void tahtayiGoster() {
    	double[] tahta = tahta2VeriAl(); 
    	for (int i = 0;i < tahta.length;i++) {
    		System.out.print(tahta[i]+", ");
    		if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i ==  47 ||i == 55 ||i == 63) {
    			System.out.println();
    		}
    	}
    }
    public String acmazKontrol() {
    	/*
    	for (byte p = (byte)1;p < 8;p++) {
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Character.getNumericValue(sahKonumAl().charAt(0))-10 == Character.getNumericValue(arayuzHamle[0].charAt(1))-10 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10 > (((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10 || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Character.getNumericValue(sahKonumAl().charAt(2))-10 == Character.getNumericValue(arayuzHamle[0].charAt(1))-10 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10 > (((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) {    			
       		 if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
       			 System.out.println((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10));    	           	
    				if ((Integer.valueOf(String.valueOf(sahKonumAl().charAt(1)))-Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))-p+1 > 1) && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "k" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "v" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {    					
    					return "acmazYok";
    				}
    				else if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) > 0 && (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) <= 7 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "v" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "k" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {    					    					
    					return "acmazYok";
    				}    				
    				if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) > 0 && (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) <= 7) {
    					if (tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "v" || tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "k") {    						
    						return "acmazAltDikey";
    					}    					
    				}    	              	                  				 	    	           
    			 }
       		     else {
    	            	if ((Integer.valueOf(String.valueOf(sahKonumAl().charAt(3)))-Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))-p+1 > 1) && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "K" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "V" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {        					        				
        					return "acmazYok";
        				}
    	            	else if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) > 0 && (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) <= 7 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "V" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "K" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {    					        					
        					return "acmazYok";
        				}
    	            	if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) > 0 && (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))) <= 7) {
        					if (tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "V" || tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "K") {        						
        						return "acmazAltDikey";
        					}    					
        				}    	            
    	         }    		    		    		            		         
    		}
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Character.getNumericValue(sahKonumAl().charAt(0))-10 == Character.getNumericValue(arayuzHamle[0].charAt(1))-10 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10 < (((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10 || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Character.getNumericValue(sahKonumAl().charAt(2))-10 == Character.getNumericValue(arayuzHamle[0].charAt(1))-10 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10 < (((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) {    		
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {    				    				    				        			        			
        			if (Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))-Integer.valueOf(String.valueOf(sahKonumAl().charAt(1)))-p > 0 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "k" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "v" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {        				
        				return "acmazYok";
        			}
        			else if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))) >= 0 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "k" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "v" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {        				        			
        				return "acmazYok";
        			}
        			if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))) >= 0) {
        				if (tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "v" || tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "k") {
        					return "acmazUstDikey";
        				}
        			}        			
    			}
    			else {
    				if (Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))-Integer.valueOf(String.valueOf(sahKonumAl().charAt(1)))-p > 0 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "K" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "V" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {        				
        				return "acmazYok";
        			}
        			else if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))) >= 0 && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "K" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != "V" && tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] != " ") {        				        			
        				return "acmazYok";
        			}
        			if ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))) >= 0) {
        				if (tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "V" || tahta[(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)] == "K") {
        					return "acmazUstDikey";
        				}
        			}
    			}
    		}    		
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && (Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) == Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))) && arayuzHamle[0].charAt(1) > sahKonumAl().charAt(0) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && (Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) == Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))) && arayuzHamle[0].charAt(1) > sahKonumAl().charAt(2)) {    			    			
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {    				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >  Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "k" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "v") {    						
    						return "acmazSagYatay";
    					}    					
    				}
    			}
    			else {    				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "K" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					
    					return "acmazYok";    					
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >  Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "K" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "K" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "V") {    						
    						return "acmazSagYatay";
    					}    					
    				} 				
    			}
    		}    		
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && (Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) == Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))) && arayuzHamle[0].charAt(1) < sahKonumAl().charAt(0) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && (Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) == Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2)))) && arayuzHamle[0].charAt(1) < sahKonumAl().charAt(2)) {    			
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}     				
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					    				
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "k" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "v") {    						
    						return "acmazSolYatay";
    					}    					
    				}
    			}
    			else {     				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "K" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}     				
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "K" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "V") {    					
    						return "acmazSolYatay";
    					}    					
    				}				
    			}    			
    		}
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) > Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 9) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10) % 9) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) > Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 9 ) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) % 9)) {
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {    				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}    				
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {    					
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "v" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "f") {    						
    						return "acmazSolUstKose";
    					}    					
    				}    				
    			}
    			else {
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    					
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					
    					return "acmazYok";
    				}
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {    					
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "V" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "F") {    						
    						return "acmazSolAltKose";
    					}    					
    				}
    			}    			    			    
    		}
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) < Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 9) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10) % 9) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) < Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 9 ) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) % 9)) {
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {    				    			
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {
    					return "acmazYok";
    				}    				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "f" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "v") {    						
    						return "acmazSagAltKose";
    					}
    				}    						    						    	
    			}
    			else {
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {    					
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {    				
    					return "acmazYok";
    				}    				
    				if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8) {    					
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "F" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "V") {    						    						
    						return "acmazSagAltKose";
    					}
    				}
    			}
    		}
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) > Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 7) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10) % 7) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) > Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 7 ) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) % 7)) {
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p)) > -1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {
    					return "acmazYok";
    				}
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p)) > -1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "f" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "v") {
    						return "acmazSagUstKose";
    					}
    				}    				
    			}
    			else {
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p)) > -1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
    					return "acmazYok";
    				}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {
    					return "acmazYok";
    				}
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p)) > -1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "F" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "V") {
    						return "acmazSagUstKose";
    					}
    				}
    			}
    		}
    		if (Character.isUpperCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) < Integer.valueOf(String.valueOf(sahKonumAl().charAt(1))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 7) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(1))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(0))-10) % 7) || Character.isLowerCase(arayuzHamle[0].charAt(0)) && Integer.valueOf(String.valueOf(arayuzHamle[0].charAt(2))) < Integer.valueOf(String.valueOf(sahKonumAl().charAt(3))) && ((tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) % 7 ) == (((((tahtaDikeyCeviri((byte) Character.getNumericValue(sahKonumAl().charAt(3))))-1)*8)+Character.getNumericValue(sahKonumAl().charAt(2))-10) % 7)) {
    			if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > -1 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {
        				return "acmazYok";
        			}
        			else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(0))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
        				return "acmazYok";
        			}
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > -1) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "f" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "v") { 
    						return "acmazSolAltKose";
    					}
    				}
    			}
    			else {
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > -1 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != " ") {
        				return "acmazYok";
        			}
    				else if (Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < Character.getNumericValue(sahKonumAl().charAt(2))-10 && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "F" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != " ") {
        				return "acmazYok";
        			}
    				if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) < 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p > -1) {
    					if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "F" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] == "V") { 
    						return "acmazSolAltKose";
    					}
    				}
    			}    			    			    	
    		}       		
		}       	
		*/
        return "acmazYok";
    }
    public int acmazTest(byte i,int j,double[] tahta) {
    	//((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)
    	//((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)
    	//System.out.println((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))%9);    	
    	if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 > i/8) {
    		//System.out.println("Þahýn üst tarafý");
    		// üst taraf i/8
    		// alt taraf (sahKonumAl()/100%10 - i/8)-1, (sahKonumAl()/10%10 - i/8)-1       		
    		if (j > 0) {return acmazHesap(j,1,i,i/8,(sahKonumAl()/1000%10 - i/8)-1,tahta);} else {return acmazHesap(j,1,i,i/8,(sahKonumAl()/10%10 - i/8)-1,tahta);}    		    	
    	}
    	else if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 < i/8) {
    		//System.out.println("Þahýn alt tarafý");
    		// alt taraf 7-i/8
    		// ust taraf (i/8 - sahKonumAl()/100%10)-1, (i/8 - sahKonumAl()/10%10)-1     		
    		if (j > 0) {return acmazHesap(j,1,i,(i/8 - sahKonumAl()/100%10)-1,7-i/8,tahta);} else {return acmazHesap(j,1,i,(i/8 - sahKonumAl()/10%10)-1-1,7-i/8,tahta);}
    		//return acmazHesap(j,2,i);
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 > i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 > i%8) {
    		//System.out.println("Þahýn sol tarafý");
    		// üst taraf i%8
    		// alt taraf (sahKonumAl()/100%10 - i%8)-1, sahKonumAl()%10 - i%8     		    		
    		if (j > 0) {return acmazHesap(j,3,i,i%8,(sahKonumAl()/100%10 - i%8)-1,tahta);} else {return acmazHesap(j,3,i,i%8,(sahKonumAl()%10 - i%8)-1,tahta);}
    		//return acmazHesap(j,3,i);
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 < i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 < i%8) {
    		//System.out.println("Þahýn sað tarafý");
    		// alt taraf (i%8-sahKonumAl()/100%10)-1), (i%8-sahKonumAl()%10)-1)
    		// ust taraf 7-i%8    		
    		if (j > 0) {acmazHesap(j,4,i,7-i%8,(i%8-sahKonumAl()/100%10)-1,tahta);} else {acmazHesap(j,4,i,(i%8-sahKonumAl()/100%10)-1,7-i%8,tahta);}
    		//return acmazHesap(j,4,i);
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 > i/8) {
    		//System.out.println("Þahýn sol üst köþe tarafý");
    		System.out.println(i);
    		if (j > 0) {} else {}
    		//return acmazHesap(j,5,i);
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 < i/8) {
    		//System.out.println("Þahýn sað alt köþe tarafý");
    		if (j > 0) {} else {}
    		//return acmazHesap(j,6,i);
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 > i/8) {
    		//System.out.println("Þahýn sað üst köþe tarafý");
    		if (j > 0) {} else {}
    		//return acmazHesap(j,7,i);
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 < i/8) {
    		//System.out.println("Þahýn sol alt köþe tarafý");
    		if (j > 0) {} else {}
    		//return acmazHesap(j,8,i);
    	}    	
    	return 0;
    }
    public int acmazHesap(int tasDeger,int hangiYon,int i,int tehtidTaraf,int sahTaraf,double[] tahta) {
    	System.out.println("Bu yönde üst tarafa doðru hesaplanýlmasý gereken kare sayýsý = "+tehtidTaraf);
		System.out.println("Bu yönde üst tarafa doðru hesaplanýlmasý gereken kare sayýsý = "+sahTaraf);
    	switch(hangiYon) {    	
    		case 1:    			
    			for (byte p = 1;p <= sahTaraf;p++) {
    				System.out.println((i/8+p)*8+i%8);
    				if (tasDeger > 0 && tahta[(i/8+p)*8+i%8] != -5.0 && tahta[(i/8+p)*8+i%8] != -9.0 && tahta[(i/8+p)*8+i%8] != 0.0 || tasDeger < 0 && tahta[(i/8+p)*8+i%8] < 0 && tahta[(i/8+p)*8+i%8] != 5.0 && tahta[(i/8+p)*8+i%8] != 9.0 && tahta[(i/8+p)*8+i%8] != 0.0) {
    					System.out.println("Hedef taþýn arkasý dolu açmaz yok !");
    					return 0;
    				}
    			}
    			for (byte p = 1;p <= tehtidTaraf;p++) {
    				if (tasDeger > 0 && tahta[(i/8-p)*8+i%8] != -5.0 && tahta[(i/8-p)*8+i%8] != -9.0 && tahta[(i/8-p)*8+i%8] != 0.0  || tasDeger < 0 && tahta[(i/8-p)*8+i%8] != 5.0 && tahta[(i/8-p)*8+i%8] != 9.0 && tahta[(i/8-p)*8+i%8] != 0.0) {
    					System.out.println("Hedef taþýn önü dolu açmaz yok !");
    					return 0;
    				}
    				else if (tasDeger > 0 && tahta[(i/8-p)*8+i%8] > -900.0 && tahta[(i/8-p)*8+i%8] < -4.9 || tasDeger < 0 && tahta[(i/8-p)*8+i%8] > 4.9 && tahta[(i/8-p)*8+i%8] < 900.0) {    			
    					System.out.println((i/8-p)*8+i%8);
    					return 1;
    				}    				
    			}
    			break;
    		case 2:
    			break;
    		case 3:    			
    			break;
    		case 4:
    			break;
    		case 5:
    			break;
    		case 6:
    			break;
    		case 7:
    			break;
    		case 8:
    			break;
    	}
    	return 0;
    }    
    public byte tahtaDikeyCeviri(Byte istenilenYukseklik) {
        if (istenilenYukseklik == 8) {return 1;}
        if (istenilenYukseklik == 7) {return 2;}
        if (istenilenYukseklik == 6) {return 3;}
        if (istenilenYukseklik == 5) {return 4;}
        if (istenilenYukseklik == 4) {return 5;}
        if (istenilenYukseklik == 3) {return 6;}
        if (istenilenYukseklik == 2) {return 7;}
        if (istenilenYukseklik == 1) {return 8;}
        return 0;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public void mouseClicked(MouseEvent e) {    	
        if (((e.getX()+25)/75)>= 3 && ((e.getX()+25)/75)<= 10 && ((e.getY()+25)/75)>= 3 && ((e.getY()+25)/75)<= 10 && isPiyonTerfi() == false) {            
            String mevcutHamle = String.valueOf((char)(((e.getX()+25)/75)-3+97)+String.valueOf((byte) tahtaDikeyCeviri((byte)((e.getY()+25)/75-2))));                          
            int arayuzBilgi = (int) (tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] > 0 || tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] == 0 ? tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*10+(e.getY()+25)/75-3 : tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*-10+((e.getY()+25)/75-3)*-1);                                            
            if (mevcutHamle.length() == 2) {
                mevcutHamle = "X"+mevcutHamle;
            }
            if (arayuzHamleSira) {             
                arayuzHamle[0] = mevcutHamle;
                arayuzHamle2[0] = arayuzBilgi;
                arayuzCizimYenileme = true;
                new Tahta();
                arayuzCizimYenileme = false;
                arayuzSayac++;

            } else {
                arayuzHamle[1] = mevcutHamle;
                arayuzHamle2[1] = arayuzBilgi;
                arayuzSayac++;
                new Tahta();
            }
            if (arayuzSayac == 2) {
                System.out.println("Birinci Hamle = "+arayuzHamle[0]+" "+"Ýkinci Hamle = "+arayuzHamle[1]);
                System.out.println("Birinci Hamle = "+arayuzHamle2[0]+" "+"Ýkinci Hamle = "+arayuzHamle2[1]);                               
                yeniHamle();
                //tahtayiGoster();
                if (isPiyonTerfi() == false) {
                	cizilecekHamleler.clear();
                }                
            }
            else {            
                System.out.println("Hamleler Tamamlanmadý !");
            }
            if (arayuzSayac >=2) {
                arayuzSayac = 0;
            }
            arayuzHamleSira = !arayuzHamleSira;
        }
        else if (isPiyonTerfi() && ((e.getX()+25)/75)>= 3 && ((e.getX()+25)/75) <= 10 && ((e.getY()+25)/75) >= 2 && ((e.getY()+25)/75) < 3) {        	
        	int hedefKare,tasDeger;        	        
        	if (cizilecekHamleler.get(cizilecekHamleler.size()-1) > 0) {        		
        		if (arayuzHamle2[1] < 0) {hedefKare = (arayuzHamle2[1]%10*-1)*8+((arayuzHamle2[1]*-1)/10)%10;} else {hedefKare = arayuzHamle2[1]%10*8+(arayuzHamle2[1]/10)%10;}        		
        		tasDeger = ((cizilecekHamleler.get(cizilecekHamleler.size()-1)/100)%10)*8+((cizilecekHamleler.get(cizilecekHamleler.size()-1))/10)%10;
        	}
        	else {
        		if (arayuzHamle2[1] > 0) {hedefKare = arayuzHamle2[1]%10*8+(arayuzHamle2[1]/10)%10;} else {hedefKare = arayuzHamle2[1]%10*8+(arayuzHamle2[1]/10)%10;}        		
        		tasDeger = ((cizilecekHamleler.get(cizilecekHamleler.size()-1)*-1/100)%10)*8+((cizilecekHamleler.get(cizilecekHamleler.size()-1)*-1)/10)%10;
        	}        	
        	if (((e.getX()+25)/75) == 5) {        		
        		piyonTerfi(hedefKare,(double)tahta2[tasDeger]*9,tasDeger);        		
        	}
        	else if (((e.getX()+25)/75) == 6) {        		
        		piyonTerfi(hedefKare,(double)tahta2[tasDeger]*5,tasDeger);
        	}
        	else if (((e.getX()+25)/75) == 7) {        		
        		piyonTerfi(hedefKare,(double)tahta2[tasDeger]*4,tasDeger);
        	}
        	else if (((e.getX()+25)/75) == 8) {        		
        		piyonTerfi(hedefKare,(double)tahta2[tasDeger]*3,tasDeger);
        	}
        }
        else {
        	System.out.println("Piyon terfi aþamasýnda olabiliriz = "+isPiyonTerfi()+" Y = "+((e.getY()+25)/75));        	        	
        	System.out.println("Piyon terfi aþamasýnda olabiliriz = "+isPiyonTerfi()+" X = "+((e.getX()+25)/75));
        }
    }    
	@Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }	
}