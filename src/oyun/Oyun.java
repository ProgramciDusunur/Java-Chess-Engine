package oyun;



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
import javax.swing.JOptionPane;

import grafik.Grafik;

public class Oyun implements MouseListener,MouseMotionListener  {    
	   public ArrayList<Integer> sahTehtid = new ArrayList<Integer>(
			  Arrays.asList(0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0
					   ));
    public LinkedList<Integer> cizilecekHamleler = new LinkedList<Integer>(),hamleler =new LinkedList<Integer>(),sahMudaheleKare = new LinkedList<Integer>();
    private static JButton buton = new JButton();    
    private static Grafik grafik = new Grafik();
    public int sahKonum = 17404;	
    private boolean arayuzDinleyiciKarar = false;
    private boolean arayuzHamleSira = true,arayuzCizimYenileme = false,beyazKisaRok = true,beyazUzunRok = true,siyahKisaRok = true,siyahUzunRok = true,piyonTerfi;    
    private byte arayuzSayac = 0,siyahSahCekis = 0,beyazSahCekis = 0,sahCekenTas = 0;
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
    public static double[] tahta =
  	     {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
  		  0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};   
    public static JFrame f = new JFrame();
    private static JLabel label[] = new JLabel[72];
    public Oyun() {     	
        if (buton.getActionListeners() != null) {
            buton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   long mili = System.nanoTime();                                        
                    //System.out.println(kacSaniye);                                        
                    cizilecekHamleler.clear();                    
                    yeniArama(true);                   
                    sahTehtid((byte) -1);                                                                                                                                                                                              
                    /*for (Byte i = 0;i < sahTehtid.size();i++) {
            		if (i % 8 == 0) {
            			System.out.println();
            		}
        			System.out.print(sahTehtid.get(i)+" ");
            		}*/   	   
                    for (int i : cizilecekHamleler) {
                    	System.out.println(i);
                    }
                   	System.out.println("Toplam Hamle = "+cizilecekHamleler.size());                    
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
        //buton.addActionListener(null);
    }     
    public static double[] tahtaVeriAl() {
        return tahta;
    }
    public void tahtaVeriAyarla(double[] ayarlanacakVeri) {
        tahta = ayarlanacakVeri;
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
    	this.cizilecekHamleler = hamleler;
    }
    public boolean beyazKisaRokAtilabilirMi() {
        return beyazKisaRok;
    }
    public void beyazKisaRokAyarla(boolean kisaRok) {
    	this.beyazKisaRok = kisaRok;
    }
    public boolean beyazUzunRokAtilabilirMi() {
        return beyazUzunRok;
    }
    public void beyazUzunRokAyarla(boolean uzunRok) {
    	this.beyazUzunRok = false;
    }
    public boolean siyahKisaRokAtilabilirMi() {
        return siyahKisaRok;
    }
    public void siyahKisaRokAyarla(boolean kisaRok) {
    	this.siyahKisaRok = kisaRok;
    }
    public boolean siyahUzunRokAtilabilirMi() {
        return siyahUzunRok;
    }
    public void siyahUzunRokAyarla(boolean uzunRok) {
    	this.siyahUzunRok = uzunRok;
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
	public byte siyahSahCekisAl() {
		return siyahSahCekis;
	}
	public void siyahSahCekisAyarla(byte siyahSah) {		
		this.siyahSahCekis = siyahSah;
	}
	public byte beyazSahCekisAl() {
		return beyazSahCekis;
	}
	public void beyazSahCekisAyarla(byte beyazSah) {
		this.beyazSahCekis = beyazSah;
	}
	public byte sahCekenTasAl() {
		return sahCekenTas;
	}
	public void sahCekenTasAyarla(byte sahCekenTas) {
		this.sahCekenTas = sahCekenTas;
	}
	public LinkedList<Integer> sahMudaheleKareAl() {
		return sahMudaheleKare;
	}
	public void sahMudaheleKareAyarla(LinkedList<Integer> sahMudaheleKare) {
		this.sahMudaheleKare = sahMudaheleKare;
	}
	public void mesaj(String s){
		JOptionPane.showMessageDialog(grafik, s);
	}
    // Draw pieces , Taslari Cizdirme   
    public static void arayuzTahtaCizimleri() {     	
    	double[] tahta = tahtaVeriAl();
    	for (byte i = 0;i < tahta.length;i++) {
    		if (tahta[i] > 0 || tahta[i] < 0) {    			
    			 label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tahta[i]+".png"), JLabel.HEIGHT);    			 
                 f.add(label[i]);
                 label[i].setBounds(207+((i%8)*75), 200+((i/8)*75), 75,75);                 
    		}
    	}    	    	
    	double[] veri = {-9.0,-5.0,-4.0,-3.0,9.0,5.0,4.0,3.0};    	    
    	for (int i = 64,j = 1;i <= 71;i++,j++) {
    		label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+veri[i-64]+".png"), JLabel.HEIGHT);
    		label[i].setVisible(false);
    		f.add(label[i]);
    		label[i].setBounds(j*75+282,125,75,75);
    		if (j == 4) {j = 0;}
    	}
    }    
    public void yeniHamle() {
    	double[] tahta = tahtaVeriAl();
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
    				hamleler.add(i);
    				tahta[60] = 0.0;
    				tahta[63] = 0.0;
    				tahta[62] = 900.0;
    				tahta[61] = 5.0;
    				f.remove(label[60]);
    				f.remove(label[63]);
    				label[62] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+900.0+".png"), JLabel.HEIGHT);
    				label[61] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+5.0+".png"), JLabel.HEIGHT);
    				f.add(label[62]);
    				f.add(label[61]);
    				label[62].setBounds(207+(6*75),725,75,75);
    				label[61].setBounds(207+(5*75),725,75,75);
    			}
    			else if ((i/10000)%10 == (arayuzHamle2[1]%10) && (i/1000)%10 == (arayuzHamle2[1]/10)%10 && i/100000 == 900 && (arayuzHamle2[1]/10)%10-((i/10)%10) == -2) {
    				hamleler.add(i);
    				tahta[60] = 0.0;
    				tahta[56] = 0.0;
    				tahta[58] = 900.0;
    				tahta[59] = 5.0;
    				f.remove(label[60]);
    				f.remove(label[56]);
    				label[58] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+900.0+".png"), JLabel.HEIGHT);
    				label[59] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+5.0+".png"), JLabel.HEIGHT);
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
    				if (beyazSahCekisAl() > 0) {beyazSahCekisAyarla((byte) 0);}
    				if (i/100000 == 900) {sahKonumAyarla(10000+(i/10000%10)*1000+(i/1000%10)*100+(sahKonumAl()/10%10)*10+sahKonumAl()%10);}    				
    				tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
    				tahta[((i/100)%10)*8+(i/10)%10] = 0.0; 
    				if (label[((i/100)%10)*8+(i/10)%10] != null) {f.remove(label[((i/100)%10)*8+(i/10)%10]);}
    				if (label[((i/10000)%10)*8+(i/1000)%10] != null) {f.remove(label[((i/10000)%10)*8+(i/1000)%10]);}    			
    				label[((i/10000)%10)*8+(i/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tahta[((i/10000)%10)*8+(i/1000)%10]+".png"), JLabel.HEIGHT);
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
    				hamleler.add(i);
    				tahta[4] = 0.0;
    				tahta[7] = 0.0;
    				tahta[6] = -900.0;
    				tahta[5] = -5.0;
    				f.remove(label[4]);
    				f.remove(label[7]);
    				label[6] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[5] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+-5.0+".png"), JLabel.HEIGHT);
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
    				label[2] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[3] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+-5.0+".png"), JLabel.HEIGHT);
    				f.add(label[2]);
    				f.add(label[3]);
    				label[2].setBounds(207+(2*75),200,75,75);
    				label[3].setBounds(207+(3*75),200,75,75);
    			}
    			else if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10) {    				
    				hamleler.add(i);
    				if (siyahSahCekisAl() > 0) {siyahSahCekisAyarla((byte) 0);}
    				if (i/100000 == -900) {sahKonumAyarla(10000+(sahKonumAl()/1000%10)*1000+(sahKonumAl()/100%10)*100+(((i*-1)/10000)%10)*10+((i*-1)/1000)%10);}    				
    				tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = i/100000;    				
    				tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = 0.0;     				    				
    				if (label[(((i*-1)/100)%10)*8+((i*-1)/10)%10] != null) {f.remove(label[(((i*-1)/100)%10)*8+((i*-1)/10)%10]);}
    				if (label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] != null) {f.remove(label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]);}
    				label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]+".png"), JLabel.HEIGHT);
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
    					label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tahta[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]+".png"), JLabel.HEIGHT);
    					f.add(label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]);    					
    					label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10].setBounds(207+((arayuzHamle2[1]/10)%10)*75,200+(arayuzHamle2[1]%10)*75,75,75);    					    					
    				}    			
    			}
    		}    		
    	}    	
    	f.remove(grafik);
    	f.add(grafik);
    	f.repaint();
    	tahtaVeriAyarla(tahta);
    }
    public void piyonTerfi(int hedefKare, double tasDeger,int piyonTerfiKare) {    	
    	double[] tahta = tahtaVeriAl();    	    	
    	if (tasDeger > 0) {
    		tahta[piyonTerfiKare] = 0.0;
    		tahta[hedefKare] = tasDeger;    		
    		f.remove(label[piyonTerfiKare]);
    		if (label[hedefKare] != null) {f.remove(label[hedefKare]);}
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tasDeger+".png"), JLabel.HEIGHT);
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
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masaüstü\\Satranç Taşları\\"+tasDeger+".png"), JLabel.HEIGHT);
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
    	tahtaVeriAyarla(tahta);
    }   
    public void yeniArama(boolean motor) {    	
		double[] tahta = tahtaVeriAl();
		for (byte i = 0;i < tahta.length;i++) {									
			//King movements, Þah hareketleri
			if (tahta[i] == 900 && motor || tahta[i] == -900 && motor || tahta[i] == 900 && arayuzHamle2[0] / 100 == 900 || tahta[i] == -900 && arayuzHamle2[0] / 100 == -900) {					
				if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8)%10 < 1 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-1);}
				if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8)%10 < 1 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+1);}
				if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8+1)%10 < 1 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8);}
				if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8-1)%10 < 1 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8);}
				if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+1);}
				if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+1);}
				if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-1);}
				if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-1);}
				if (tahta[60] != 900) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}}
				if (tahta[4] != -900) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
				if (tahta[i] == -900 && siyahKisaRokAtilabilirMi() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 || tahta[i] == 900 && beyazKisaRokAtilabilirMi() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8);}
				if (tahta[i] == -900 && siyahUzunRokAtilabilirMi() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[3] == 0.0 || tahta[i] == 900 && beyazUzunRokAtilabilirMi() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8);}													
			}
			//Knight movements, At hareketleri
			if (tahta[i] == 3 && motor || tahta[i] == -3 && motor || tahta[i] == 3 && arayuzHamle2[0] / 100 == 3 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -3 && arayuzHamle2[0] / 100 == -3 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2);}				
						if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1);}
						if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1);}
						if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1);}
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}				
			}
			//Bishop movements, Fil hareketleri
			if (tahta[i] == 4 && motor || tahta[i] == -4 && motor || tahta[i] == 4 && arayuzHamle2[0] / 100 == 4 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -4 &&  arayuzHamle2[0] / 100 == -4 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}								
			}
			//Rook movements, Kale hareketleri
			if (tahta[i] == 5 && motor || tahta[i] == -5 && motor || tahta[i] == 5 && arayuzHamle2[0] / 100 == 5 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -5 && arayuzHamle2[0] / 100 == -5 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(false,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}							
				if (beyazKisaRokAtilabilirMi() && tahta[63] != 5.0) {beyazKisaRokAyarla(false);} else if (siyahKisaRokAtilabilirMi() && tahta[7] != -5.0) {siyahKisaRokAyarla(false);}
                if (beyazUzunRokAtilabilirMi() && tahta[56] != 5.0) {beyazUzunRokAyarla(false);} else if (siyahUzunRokAtilabilirMi() && tahta[0] != -5.0) {siyahUzunRokAyarla(false);}
			}
			//Queen movements, Vezir hareketleri
			if (tahta[i] == 9 && motor || tahta[i] == -9 && motor || tahta[i] == 9 && arayuzHamle2[0] / 100 == 9 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -9 && arayuzHamle2[0] / 100 == -9 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {					
						uzuntasHesap(false,i,tahta);
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}						
			}
			//Pawn movements, Piyon hareketleri
			if (tahta[i] == 1 && motor || tahta[i] == -1 && motor || tahta[i] == 1 && arayuzHamle2[0] / 100 == 1 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -1 && arayuzHamle2[0] / 100 == -1 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (i/8-1 > 0 && tahta[i] == 1 || i/8+1 < 7 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
						if (i/8 == 6 && tahta[i] == 1 || i/8 == 1 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-2)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+2)*8+i%8] == 0.0)  {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+2)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 && tahta[(i/8-1)*8+i%8-1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 && tahta[(i/8-1)*8+i%8+1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-1*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
						if (tahta[i] > 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == -1 && i/8 == 3 && ((hamleler.get(hamleler.size()-1)*-1)/10000%10) == 3 && ((hamleler.get(hamleler.size()-1)*-1)/100%10) == 1 || tahta[i] < 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == 1 && i/8 == 4 && (hamleler.get(hamleler.size()-1)/10000%10) == 4 && (hamleler.get(hamleler.size()-1)/100%10) == 6) {if (tahta[i] > 0) {cizilecekHamleler.add(1*100000+(i/8-1)*10000+((hamleler.get(hamleler.size()-1)/1000%10)*-1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(1*100000+(i/8+1)*10000+((hamleler.get(hamleler.size()-1)/1000%10))*1000+(i/8)*100+(i%8)*10);}}
						if (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
						if (i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}
						if (i/8 == 1 && i%8-1 > -1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8-1] < 0 || i/8 == 6 && i%8-1 > -1 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
						if (i/8 == 1 && i%8+1 < 8 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8+1] < 0 || i/8 == 6 && i%8+1 < 8 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {cizilecekHamleler.add(9*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(4*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);cizilecekHamleler.add(5*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);} else {cizilecekHamleler.add(-9*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-4*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);cizilecekHamleler.add(-5*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}		
				}					
			}
		}		 
	}
    public void sahCekisArama(double tas, byte i) {
    	double[] tahta = tahtaVeriAl();    
    	LinkedList<Integer> mudaheleKareleri = sahMudaheleKareAl();
    	for (int p = 0;p < mudaheleKareleri.size();p++) {    		
    		if (Math.abs(tas) == 3) {
        		if (i/8-2 > -1 && i%8-1 > -1 && ((i/8)-2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2);}	
    			if (i/8-1 > -1 && i%8-2 > -1 && ((i/8)-1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1);}
    			if (i/8-1 > -1 && i%8+2 < 8 && ((i/8)-1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1);}
    			if (i/8-2 > -1 && i%8+1 < 8 && ((i/8)-2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2);}
    			if (i/8+2 < 8 && i%8+1 < 8 && ((i/8)+2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2);}
    			if (i/8+2 < 8 && i%8-1 > -1 && ((i/8)+2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2);}
    			if (i/8+1 < 8 && i%8-2 > -1 && ((i/8)+1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1);}
    			if (i/8+1 < 8 && i%8+2 < 8 && ((i/8)+1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1);}
        	}
    		else if (tas == 1) {    			
    			if (i/8 == 6 && tahta[((i/8)-2)*8+i%8] == 0 && ((i/8)-2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-2);}
    			if (((i/8)-1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-1);}
    		}
    		else if (tas == -1) {    			    		
    			if (i/8 == 1 && tahta[((i/8)+2)*8+i%8] == 0 && ((i/8)+2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+2);}
    			if (((i/8)+1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+1);}
    		}
    		else if (Math.abs(tas) == 9) {
    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
    		}
    		else if (Math.abs(tas) == 5) {
    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
    		}
    		else if (Math.abs(tas) == 4) {
    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
    		}
    	}    	
    }
    
    public void sahCekenTasNerede(byte i, int j, double[] tahta) {
    	int x = i%8, y = i/8, sahX, sahY;
    	LinkedList<Integer> sahMudaheleKare = new LinkedList<Integer>();
    	if (j > 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 > i/8 || j < 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 > i/8) {
    		if (j > 0) {sahX = sahKonumAl()%10;sahY = sahKonumAl()/10%10-1;} else {sahX = sahKonumAl()/100%10;sahY = sahKonumAl()/1000%10-1;}
    		for (;sahY >= y;sahY--) {
    			sahMudaheleKare.add(sahY*8+sahX);
    		}    		
    	}
    	else if (j > 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 < i/8 || j < 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 < i/8) {    		
    		if (j > 0) {sahX = sahKonumAl()%10;sahY = sahKonumAl()/10%10+1;} else {sahX = sahKonumAl()/100%10;sahY = sahKonumAl()/1000%10+1;}
    		for (;sahY <= y;sahY++) {    			
    			sahMudaheleKare.add(sahY*8+sahX);
    		}
    	}
    	else if (j > 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 > i%8 || j < 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 > i%8) {
    		if (j > 0) {sahX = sahKonumAl()%10-1;sahY = sahKonumAl()/10%10;} else {sahX = sahKonumAl()/100%10-1;sahY = sahKonumAl()/1000%10;}
    		for (;sahX >= x;sahX--) {
    			sahMudaheleKare.add(sahY*8+sahX);
    		}    		
    	}
    	else if (j > 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 < i%8 || j < 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 < i%8) {    		
    		if (j > 0) {sahX = sahKonumAl()%10+1;sahY = sahKonumAl()/10%10;} else {sahX = sahKonumAl()/100%10+1;sahY = sahKonumAl()/1000%10;}
    		for (;sahX <= x;sahX++) {
    			sahMudaheleKare.add(sahY*8+sahX);
    		}
    	}    	
    	else if (j > 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 > i/8 || j < 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 > i/8) {
    		if (j > 0) {sahX = sahKonumAl()%10-1;sahY = sahKonumAl()/10%10-1;} else {sahX = sahKonumAl()/100%10-1;sahY = sahKonumAl()/1000%10-1;}
    		for (;sahX >= x && sahY >= y;sahX--,sahY--) {    			
    			sahMudaheleKare.add(sahY*8+sahX);
    		}    		
    	}    	
    	else if (j > 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 < i/8 || j < 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 < i/8) {
    		if (j > 0) {sahX = sahKonumAl()%10+1;sahY = sahKonumAl()/10%10+1;} else {sahX = sahKonumAl()/100%10+1;sahY = sahKonumAl()/1000%10+1;}
    		for (;sahX <= x && sahY <= y;sahX++,sahY++) {    			
    			sahMudaheleKare.add(sahY*8+sahX);
    		}
    	}    	
    	else if (j > 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 > i/8 || j < 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 > i/8) {
    		if (j > 0) {sahX = sahKonumAl()%10+1;sahY = sahKonumAl()/10%10-1;} else {sahX = sahKonumAl()/100%10+1;sahY = sahKonumAl()/1000%10-1;}    		
    		for (;sahX <= x && sahY >= y;sahX++,sahY--) {    			
    			sahMudaheleKare.add(sahY*8+sahX);
    		}
    	}    	
    	else if (j > 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 < i/8 || j < 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 < i/8) {
    		if (j > 0) {sahX = sahKonumAl()%10-1;sahY = sahKonumAl()/10%10+1;} else {sahX = sahKonumAl()/100%10-1;sahY = sahKonumAl()/1000%10+1;}
    		for (;sahX >= x && sahY <= y;sahX--,sahY++) {    			
    			sahMudaheleKare.add(sahY*8+sahX);
    		}
    	}
    	sahMudaheleKareAyarla(sahMudaheleKare);    	
    }    
	public void sahTehtid() {     	    	
    	 sahTehtid = new ArrayList<Integer>(
				  Arrays.asList(0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0
						   		));    
    }
    public void sahTehtid(byte j) {    	
    	sahTehtid();    	
    	double[] tahta = tahtaVeriAl();
    	for (byte i = 0;i < tahta.length;i++) {    		    		    	
    		if (tahta[i] == 900 || tahta[i] == -900) {     			    		
        		if (i/8-1 > -1 && tahta[i] == 900 || i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8,i);}
        		if (i/8+1 < 8 && tahta[i] == 900 || i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8,i);}
        		if (i%8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8+1,i);}
        		if (i%8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8-1,i);}
        		if (i%8-1 > -1 && i/8+1 < 8 && tahta[i] == 900 || i%8-1 > -1 && i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-1,i);}
        		if (i%8+1 < 8 && i/8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && i/8+1 < 8 &&  tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+1,i);}
        		if (i%8+1 < 8 && i/8-1 > -1 && tahta[i] == 900 || i%8+1 < 8 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+1,i);}
        		if (i%8-1 > -1 && i/8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-1,i);}
        	}
    		if (tahta[i] == 3 || tahta[i] == -3) {
    			if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8-1,i);}	
				if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-2,i);}
				if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+2,i);}
				if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8+1,i);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8+1,i);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8-1,i);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-2,i);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+2,i);}
    		}
    		if (tahta[i] == 4 || tahta[i] == -4) {
    			sahTehtidUzunTasHesap(true,i,tahta);
    		}
    		if (tahta[i] == 5 || tahta[i] == -5) {
    			sahTehtidUzunTasHesap(false,i,tahta);
    		}
    		if (tahta[i] == 9 || tahta[i] == -9) {
    			sahTehtidUzunTasHesap(true,i,tahta);
    			sahTehtidUzunTasHesap(false,i,tahta);
    		}
    		if (tahta[i] == 1 || tahta[i] == -1) {
    			if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8-1, sahTehtid.get((i/8-1)*8+i%8-1)+10);}else{sahTehtid.set((i/8+1)*8+i%8-1, sahTehtid.get((i/8+1)*8+i%8-1)+1);}}
				if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8+1, sahTehtid.get((i/8-1)*8+i%8+1)+10);}else{sahTehtid.set((i/8+1)*8+i%8+1, sahTehtid.get((i/8+1)*8+i%8+1)+1);}}
    		}    		
    	}    	
    }
    public void sahTehtidAyarla(double i, int kare, byte sahCekenTas) {    	
    	if (i > 0) {
    		sahTehtid.set(kare, sahTehtid.get(kare)+10);
    		if (tahta[kare] == -900) {    			    			    		
    			sahCekenTasAyarla(sahCekenTas);
    			siyahSahCekisAyarla((byte) (siyahSahCekisAl()+1));
    			sahCekenTasNerede(sahCekenTasAl(),(byte)tahta[sahCekenTasAl()],tahta);
    		}
    	}
    	else {
    		sahTehtid.set(kare, sahTehtid.get(kare)+1);
    		if (tahta[kare] == 900) {
    			    			
    			sahCekenTasAyarla(sahCekenTas);
    			beyazSahCekisAyarla((byte) (beyazSahCekisAl()+1));
    			sahCekenTasNerede(sahCekenTasAl(),(byte)tahta[sahCekenTasAl()],tahta);
    		}
    	}
    }    
	public void arayuzCizilecekHamleAyarla(double j, int i,int x, int y) {    	
    	if (j > 0) {
    		cizilecekHamleler.add((int) (j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10));
    	}
    	else {
    		cizilecekHamleler.add((int) (j*100000-(y)*10000-(x)*1000-(i/8)*100-(i%8)*10));
    	}
    }       	
    public void uzuntasHesap(boolean yon, byte i, double[] tahta) {
    	if (yon) {    		
    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			
    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solAltKose,i/8+solAltKose);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0) {solAltKose = 8;}} else {solAltKose = 8;}
    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1  && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solUstKose,i/8-solUstKose);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0) {solUstKose = 8;}} else {solUstKose = 8;}
    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagUstKose,i/8-sagUstKose);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0) {sagUstKose = 8;}} else {sagUstKose = 8;}
    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagAltKose,i/8+sagAltKose);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0) {sagAltKose = 8;}} else {sagAltKose = 8;}    			
    		}    		
    	}
    	else {
    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
    			if ((ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] < 1 && tahta[i] > 0 || (ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-ustDikey);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
    			if ((altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] < 1 && tahta[i] > 0 || (altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+altDikey);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
    			if ((sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] < 1 && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagYatay,i/8);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
    			if ((solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] < 1 && tahta[i] > 0 || (solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solYatay,i/8);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
    		}
    	}    	    	    	
    } 
    public void sahTehtidUzunTasHesap(boolean yon, byte i, double[] tahta) {
    	if (yon) {    		
    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 
    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == -900) && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+solAltKose)*8+i%8-solAltKose,i);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != -900.0 && tahta[i] > 0) {solAltKose = 8;}} else {solAltKose = 8;}
    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == -900)  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 900)  && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-solUstKose)*8+i%8-solUstKose,i);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != -900.0 && tahta[i] > 0) {solUstKose = 8;}} else {solUstKose = 8;}
    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == -900) && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-sagUstKose)*8+i%8+sagUstKose,i);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != -900.0 && tahta[i] > 0) {sagUstKose = 8;}} else {sagUstKose = 8;}
    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == -900) && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 900 ) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+sagAltKose)*8+i%8+sagAltKose,i);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != -900.0 && tahta[i] > 0) {sagAltKose = 8;}} else {sagAltKose = 8;}
    		}    		
    	}
    	else {
    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
    			if ((ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] > -1 || tahta[(((i/8)-ustDikey)*8)+i%8] == -900) && tahta[i] > 0 || (ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] < 1  || tahta[(((i/8)-ustDikey)*8)+i%8] == 900)&& tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)-ustDikey)*8)+i%8,i);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
    			if ((altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] > -1 || tahta[(((i/8)+altDikey)*8)+i%8] == -900) && tahta[i] > 0 || (altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] < 1 || tahta[(((i/8)+altDikey)*8)+i%8] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)+altDikey)*8)+i%8,i);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
    			if ((sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] > -1 || tahta[(((i/8))*8)+i%8+sagYatay] == -900) && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] < 1 || tahta[(((i/8))*8)+i%8+sagYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8+sagYatay,i);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != -900.0 && tahta[i] > 0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
    			if ((solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] > -1 || tahta[(((i/8))*8)+i%8-solYatay] == -900) && tahta[i] > 0 || (solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] < 1 || tahta[(((i/8))*8)+i%8-solYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8-solYatay,i);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != -900.0 && tahta[i] > 0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
    		}
    	}    	    	    	
    }
    public void sahCekisUzunTasHesap(boolean yon, byte i, double[] tahta, int hedefKare) {
    	int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1,ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1,m = 0,j = 0,k = 0, l = 0;    	    
    	if (yon) {
    		if (hedefKare % 9 == i%9) {
        		if (i < hedefKare) {m = (hedefKare-i)/9;} else {j = (i-hedefKare)/9;}    	
        	}
    		else if (hedefKare % 7 == i%7) {        		
        		if (i < hedefKare) {k = (hedefKare-i)/7;} else {l = (i-hedefKare)/7;}    		
        	}
    		for (;sagAltKose <= m || solUstKose <= j || sagUstKose <= l || solAltKose <= k;sagAltKose++,solUstKose++,sagUstKose++,solAltKose++) {
    			if (i+sagAltKose*9 < 64 && i+sagAltKose*9 != hedefKare && tahta[i+sagAltKose*9] != 0) {sagAltKose = 8;}else if (i+sagAltKose*9 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i+sagAltKose*9)%8,(i+sagAltKose*9)/8);}    			
    			if (i-solUstKose*9 > -1 && i-solUstKose*9 != hedefKare && tahta[i-solUstKose*9] != 0) {solUstKose = 8;}else if (i-solUstKose*9 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i-solUstKose*9)%8,(i-solUstKose*9)/8);}
    			if (i-sagUstKose*7 > -1 && i-sagUstKose*7 != hedefKare && tahta[i-sagUstKose*7] != 0) {sagUstKose = 8;}else if (i-sagUstKose*7 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i-sagUstKose*7)%8,(i-sagUstKose*7)/8);}
    			if (i+solAltKose*7 > -1 && i+solAltKose*7 != hedefKare && tahta[i+solAltKose*7] != 0) {solAltKose = 8;}else if (i+solAltKose*7 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i+solAltKose*7)%8,(i+solAltKose*7)/8);}    			
    		}
    	} else {
    		if (i%8 == hedefKare%8) {    			
    			if (i < hedefKare) {m = hedefKare/8-i/8;}else {j = i/8-hedefKare/8;}
    		}
    		else if (i/8 == hedefKare/8) {
    			if (i < hedefKare) {k = hedefKare%8-i%8;}else {l = i%8-hedefKare%8;}
    			
    		}
    		for (;ustDikey <= j || altDikey <= m || sagYatay <= k || solYatay <= l;ustDikey++,altDikey++,sagYatay++,solYatay++) {
    			if (i-ustDikey*8 >- 1 && i-ustDikey*8 != hedefKare && tahta[i-ustDikey*8] != 0) {ustDikey = 8;}else if (i-ustDikey*8 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,(i-ustDikey*8)/8);}    				
    			if (i+altDikey*8 < 64 && i+altDikey*8 != hedefKare && tahta[i+altDikey*8] != 0) {altDikey = 8;}else if (i+altDikey*8 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,(i+altDikey*8)/8);}
    			if (i/8*8+i%8+sagYatay < 64 && i/8*8+i%8+sagYatay != hedefKare && tahta[i/8*8+i%8+sagYatay] != 0) {sagYatay = 8;}else if (i/8*8+i%8+sagYatay == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagYatay,i/8);}    				
    			if (i/8*8+i%8-solYatay > -1 && i/8*8+i%8-solYatay != hedefKare && tahta[i/8*8+i%8-solYatay] != 0) {solYatay = 8;}else if (i/8*8+i%8-solYatay == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solYatay,i/8);}
    		}
    	}
    	
    }
    public void tahtayiGoster(double[] tahta) {    	
    	String tas = "";
    	for (int i = 0;i < tahta.length;i++) {    		
    		if (tahta[i] == 900) {
    			tas = "S";
    		} else if (tahta[i] == 9) {
    			tas = "V";
    		} else if (tahta[i] == 5) {
    			tas = "K";
    		} else if (tahta[i] == 4) {
    			tas = "F";
    		} else if (tahta[i] == 1) {
    			tas = "P";
    		} else if (tahta[i] == 3) {
    			tas = "A";
    		} if (tahta[i] == -900) {
    			tas = "s";
    		} else if (tahta[i] == -9) {
    			tas = "v";
    		} else if (tahta[i] == -5) {
    			tas = "k";
    		} else if (tahta[i] == -4) {
    			tas = "f";
    		} else if (tahta[i] == -1) {
    			tas = "p";
    		} else if (tahta[i] == -3) {
    			tas = "a";
    		} else if (tahta[i] == 0) {
    			tas = "-";
    		}
    		System.out.print(tas+" ");
    		if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i ==  47 ||i == 55 ||i == 63) {
    			System.out.println();
    		}
    	}
    	System.out.println();
    }    
    public int acmazTest(byte i, int j, double[] tahta) {     	
    	if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 0) {return acmazKontrol(j,1,i,(sahKonumAl()/1000%10 - i/8)-1,tahta);} else if (j < 0) {return acmazKontrol(j,1,i,(sahKonumAl()/10%10 - i/8)-1,tahta);}    		    	
    	}
    	else if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 < i/8) {    		
    		if (j > 0) {return acmazKontrol(j,2,i,i/8-(sahKonumAl()/1000)%10-1,tahta);} else if (j < 0)  {return acmazKontrol(j,2,i,i/8-(sahKonumAl()/10)%10-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 > i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 > i%8) {    		
    		if (j > 0) {return acmazKontrol(j,3,i,(sahKonumAl()/100%10 - i%8)-1,tahta);} else if (j < 0)  {return acmazKontrol(j,3,i,(sahKonumAl()%10 - i%8)-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 < i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 < i%8) {    		
    		if (j > 0) {return acmazKontrol(j,4,i,(i%8-sahKonumAl()/100%10)-1,tahta);} else if (j < 0)  {return acmazKontrol(j,4,i,(i%8-sahKonumAl()%10)-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 1) {return acmazKontrol(j,5,i,(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)-i)/9-1,tahta);} else if (j < -1)  {return acmazKontrol(j,5,i,(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)-i)/9-1,tahta);}
    		else {    			
    			if (j == 1) {return piyonAcmaz(j,5,i,(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)-i)/9-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 < i/8) {    		
    		if (j > 1) {return acmazKontrol(j,6,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/9-1,tahta);} else if (j < -1)  {return acmazKontrol(j,6,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/9-1,tahta);}
    		else {    			
    			if (j == -1) {return piyonAcmaz(j,6,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/9-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 > i/8 && sahKonumAl()/100%10 < i%8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 > i/8 && sahKonumAl()%10 < i%8) {    	
    		if (j > 1) {return acmazKontrol(j,7,i,((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))-i)/7-1,tahta);} else if (j < -1)  {return acmazKontrol(j,7,i,((((sahKonumAl()/10)%10)*8+(sahKonumAl()%10))-i)/7-1,tahta);}    		
    		else {
    			if (j == 1) {return piyonAcmaz(j,7,i,((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))-i)/7-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 < i/8 && sahKonumAl()/100%10 > i%8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 < i/8 && sahKonumAl()%10 > i%8) {    		
    		if (j > 1) {return acmazKontrol(j,8,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/7-1,tahta);} else if (j < -1)  {return acmazKontrol(j,8,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/7-1,tahta);}
    		else {
    			if (j == -1) {return piyonAcmaz(j,8,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/7-1,tahta);}
    		}
    	}     	
    	return 0;
    }
    public int acmazKontrol(int tasDeger,int hangiYon,int i,int sahTaraf,double[] tahta) {
    	
    	LinkedList<Integer> acmazKare = new LinkedList<Integer>();
    	// Uzun hareketli taþlar = Fil, Vezir, Kale
    	// kýsa hareketli taþlar = At, Piyon    	    	    			    
    	switch(hangiYon) {    	
    		case 1:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				} else {    					
    					acmazKare.add((i/8+p)*8+i%8);
    				}    				
    			}
    			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add((y*8+x));
    				}    				
    			}    			
    			break;
    		case 2:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8);
    				}    				
    			}
    			for (int y = i/8+1,x = i%8;y < 8;y++) {    				    				
    				if ((tahta[i] > 0 && (tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0) || tahta[i] < 0 && (tahta[y*8+x] != 5 && tahta[y*8+x] != 9 && tahta[y*8+x] != 0))  || y == 7) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[y*8+x] == -5 || tahta[y*8+x] == -9) || tahta[i] < 0 && (tahta[y*8+x] == 5 || tahta[y*8+x] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 3:    			    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8+p] != 0) {    					
    					return 0;
    				} else {
    					acmazKare.add((i/8)*8+i%8+p);
    				}    				
    			}
    			for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 4:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				    			
    				if (tahta[(i/8)*8+i%8-p] != 0) {    			    					
    					return 0;
    				} else {
    					acmazKare.add((i/8)*8+i%8-p);    				
    				}    				
    			}
    			for (int y = i/8,x = i%8+1;x < 8;x++) {
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7) {    					
    					return 0;
    				}    		
    				acmazHesap(i,true,acmazKare);
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);    					
    				}
    						    				
    			}
    			break;    			
    		case 5:    		
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8+p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8+p)*8+i%8+p);
    				}
    			}
    			for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0 && y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    					
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);    					
    				}
    			}    			
    			break;    			    		
    		case 6:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8-p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8-p);
    				}    				
    			}
    			for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7 && y == 7) {    					
    					return 0;
    				}    				
    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    					
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 7:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8-p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8+p)*8+i%8-p);
    				}    				
    			}
    			for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7 && y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 8:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8+p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8+p);
    				}
    				
    			}
    			for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0 && y == 7) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;    					
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}    			
    			break;
    	}
    	return 0;
    }
    public int piyonAcmaz(int tasDeger, int hangiYon, int i, int sahTaraf, double[] tahta) {    	
    	switch (hangiYon) {
    	case 5:    		
    		for (int p = 1;p <= sahTaraf;p++) {    			
    			if (tahta[(i/8+p)*8+i%8+p] != 0) {    				
    				return 0;
    			}
    		}
    		for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {
    			if (((y == i/8-1 && x == i%8-1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
    				return 1;
    			} else if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
    				return 1;
    			}    			
    		}
    		break;
    	case 6:    		
    		for (int p = 1;p <= sahTaraf;p++) {    			
    			if (tahta[(i/8-p)*8+i%8-p] != 0) {    				
    				return 0;
    			}
    		}
    		for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {
    			if (((y == i/8+1 && x == i%8+1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
    				return 1;
    			} else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
    				return 1;
    			}
    		}
    		break;    		
    	case 7:
    		for (int p = 1;p <= sahTaraf;p++) {    			
    			if (tahta[(i/8+p)*8+i%8-p] != 0) {    				
    				return 0;
    			}
    		}
    		for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {
    			if (((y == i/8-1 && x == i%8+1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
    				return 1;
    			} else if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
    				return 1;
    			}    		
    		}    		
    		break;
    	case 8:
    		for (int p = 1;p <= sahTaraf;p++) {    			
    			if (tahta[(i/8-p)*8+i%8+p] != 0) {    				
    				return 0;
    			}
    		}
    		for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {
    			if (((y == i/8+1 && x == i%8-1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
    				return 1;
    			} else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
    				return 1;
    			}
    		}
    		break;
    	}
    	
    	return 0;
    }
    public void acmazHesap(int i,boolean yatayCapraz, LinkedList<Integer> kareler) {
    	for (int m = 0;m < kareler.size();m++) {
    		if (((yatayCapraz)) && ((tahta[i] == 5 || tahta[i] == 9) || (tahta[i] == -5 || tahta[i] == -9))) {
    			if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(kareler.get(m)/8)*10000+(kareler.get(m)%8)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(kareler.get(m)/8)*10000-(kareler.get(m)%8)*1000-(i/8)*100-(i%8)*10));}    					
    		}
        	else if (((!yatayCapraz)) && ((tahta[i] == 4 || tahta[i] == 9) || (tahta[i] == -4 || tahta[i] == -9))) {    		
    			if (tahta[i] > 0) {cizilecekHamleler.add((int) (tahta[i]*100000+(kareler.get(m)/8)*10000+(kareler.get(m)%8)*1000+(i/8)*100+(i%8)*10));}else {cizilecekHamleler.add((int) (tahta[i]*100000-(kareler.get(m)/8)*10000-(kareler.get(m)%8)*1000-(i/8)*100-(i%8)*10));}    					
    		}
    	}    
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public void mouseClicked(MouseEvent e) {    	
        if (((e.getX()+25)/75)>= 3 && ((e.getX()+25)/75)<= 10 && ((e.getY()+25)/75)>= 3 && ((e.getY()+25)/75)<= 10 && isPiyonTerfi() == false) {                                                  
            int arayuzBilgi = (int) (tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] > 0 || tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] == 0 ? tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*10+(e.getY()+25)/75-3 : tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*-10+((e.getY()+25)/75-3)*-1);                                                        
            if (arayuzHamleSira) {                             
                arayuzHamle2[0] = arayuzBilgi;
                arayuzCizimYenileme = true;
                new Oyun();
                arayuzCizimYenileme = false;
                arayuzSayac++;

            } else {
                
                arayuzHamle2[1] = arayuzBilgi;
                arayuzSayac++;
                new Oyun();
            }
            if (arayuzSayac == 2) {                
                System.out.println("Birinci Hamle = "+arayuzHamle2[0]+" "+"İkinci Hamle = "+arayuzHamle2[1]);                
                yeniHamle();
                sahTehtid.clear();
               if (hamleler.size() > 0) {
            	   sahTehtid();
            	   sahTehtid((byte) -1); 
               }
               System.out.println("Siyah şah Çekiş Sayısı = "+siyahSahCekisAl());
               System.out.println("Beyaz şah Çekiş Sayısı = "+beyazSahCekisAl());
                //tahtayiGoster();
                if (isPiyonTerfi() == false) {
                	cizilecekHamleler.clear();
                }                
            }
            else {            
                System.out.println("Hamleler Tamamlanmadı !");
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
        		piyonTerfi(hedefKare,(double)tahta[tasDeger]*9,tasDeger);        		
        	}
        	else if (((e.getX()+25)/75) == 6) {        		
        		piyonTerfi(hedefKare,(double)tahta[tasDeger]*5,tasDeger);
        	}
        	else if (((e.getX()+25)/75) == 7) {        		
        		piyonTerfi(hedefKare,(double)tahta[tasDeger]*4,tasDeger);
        	}
        	else if (((e.getX()+25)/75) == 8) {        		
        		piyonTerfi(hedefKare,(double)tahta[tasDeger]*3,tasDeger);
        	}
        }
        else {
        	System.out.println("Piyon terfi aşamasında olabiliriz = "+isPiyonTerfi()+" Y = "+((e.getY()+25)/75));        	        	
        	System.out.println("Piyon terfi aşamasında olabiliriz = "+isPiyonTerfi()+" X = "+((e.getX()+25)/75));
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