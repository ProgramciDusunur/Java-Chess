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
			  Arrays.asList(0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0
					   ));
    public LinkedList<Integer> cizilecekHamleler = new LinkedList<Integer>(),hamleler =new LinkedList<Integer>();
    private static JButton buton = new JButton();
    private static Grafik grafik = new Grafik();
    private int sahKonum = 13341,sahCekenTas = 100;
    private boolean arayuzDinleyiciKarar = false;
    private boolean arayuzHamleSira = true,arayuzCizimYenileme = false,beyazKisaRok = true,beyazUzunRok = true,siyahKisaRok = true,siyahUzunRok = true,piyonTerfi,beyazSah = false,siyahSah = false;    
    private Byte arayuzSayac = 0;
    public String[] arayuzHamle = new String[3];
    public int[] arayuzHamle2 = new int[2];   
    public static double[] tahta =
 	   {-5.0,-3.0,-4.0,-9.0,-900.0,-4.0,-3.0,-5.0,
 		-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
         5.0,3.0,4.0,9.0,900,4.0,3.0,5.0};
    public static double[] tahta2 =
  	   {0.0,0.0,0.0,0,0.0,0.0,0.0,5.0,
  		0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,900.0,0.0,0.0,0.0,0.0,
          0.0,-900.0,0.0,9.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,-9.0,0.0,0.0,0.0,0.0,0.0,
          0.0,0.0,0.0,0.0,0,0.0,0,0.0};   
    public static JFrame f = new JFrame();
    private static JLabel label[] = new JLabel[72];
    public Tahta() {
        /*
         * Yap�lacaklar;
         * K�sa Rok , Uzun Rok / Tamamland�
         * Ge�erken Alma , Piyon Terfi / Tamamland�
         * A�maz , �ah �eki� Kontrol / Tamamlanmad�
         */     	
        if (buton.getActionListeners() != null) {
            buton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   long mili = System.nanoTime();                                        
                    //System.out.println(kacSaniye);                                        
                    cizilecekHamleler.clear();
                    for (int i = 0;i < 25000;i++) {
                    	yeniArama(true);
                        sahTehtid();
                        sahTehtid((byte) -1);
                    }                                                                              
                    /*for (Byte i = 0;i < sahTehtid.size();i++) {
            		if (i % 8 == 0) {
            			System.out.println();
            		}
        			System.out.print(sahTehtid.get(i)+" ");
            		} */  	   
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
        f.setTitle("Satran�");
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
    	double[] tahta = tahtaVeriAl();
    	for (byte i = 0;i < tahta.length;i++) {
    		if (tahta[i] > 0 || tahta[i] < 0) {    			
    			 label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tahta[i]+".png"), JLabel.HEIGHT);    			 
                 f.add(label[i]);
                 label[i].setBounds(207+((i%8)*75), 200+((i/8)*75), 75,75);                 
    		}
    	}    	    	
    	double[] veri = {-9.0,-5.0,-4.0,-3.0,9.0,5.0,4.0,3.0};    	    
    	for (int i = 64,j = 1;i <= 71;i++,j++) {
    		label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+veri[i-64]+".png"), JLabel.HEIGHT);
    		label[i].setVisible(false);
    		f.add(label[i]);
    		label[i].setBounds(j*75+282,125,75,75);
    		if (j == 4) {j = 0;}
    	}
    }    
    public void yeniHamle() {
    	double[] tahta = tahtaVeriAl();
    	for (int i : cizilecekHamleler) {
    		//System.out.println("� = "+i+" Aray�z hamle = "+arayuzHamle2[1]);
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
    				label[62] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+900.0+".png"), JLabel.HEIGHT);
    				label[61] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+5.0+".png"), JLabel.HEIGHT);
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
    				label[58] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+900.0+".png"), JLabel.HEIGHT);
    				label[59] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+5.0+".png"), JLabel.HEIGHT);
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
    				label[((i/10000)%10)*8+(i/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tahta[((i/10000)%10)*8+(i/1000)%10]+".png"), JLabel.HEIGHT);
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
    				label[6] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[5] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+-5.0+".png"), JLabel.HEIGHT);
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
    				label[2] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+-900.0+".png"), JLabel.HEIGHT);
    				label[3] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+-5.0+".png"), JLabel.HEIGHT);
    				f.add(label[2]);
    				f.add(label[3]);
    				label[2].setBounds(207+(2*75),200,75,75);
    				label[3].setBounds(207+(3*75),200,75,75);
    			}
    			else if (((i*-1)/10000)%10 == (arayuzHamle2[1]%10) && ((i*-1)/1000)%10 == (arayuzHamle2[1]/10)%10) {    				
    				hamleler.add(i);    				
    				tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = i/100000;    				
    				tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = 0.0;     				    				
    				if (label[(((i*-1)/100)%10)*8+((i*-1)/10)%10] != null) {f.remove(label[(((i*-1)/100)%10)*8+((i*-1)/10)%10]);}
    				if (label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] != null) {f.remove(label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]);}
    				label[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10]+".png"), JLabel.HEIGHT);
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
    					label[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tahta[(((i*-1)/10000)%10)*8+(hamleler.get(hamleler.size()-1)/1000)%10]+".png"), JLabel.HEIGHT);
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
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tasDeger+".png"), JLabel.HEIGHT);
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
    		label[hedefKare] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\"+tasDeger+".png"), JLabel.HEIGHT);
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
			//King movements, �ah hareketleri
			if (tahta[i] == 900 && motor || tahta[i] == -900 && motor || tahta[i] == 900 && arayuzHamle2[0] / 100 == 900 || tahta[i] == -900 && arayuzHamle2[0] / 100 == -900) {					
				if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8)%10 < 1 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
				if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8)%10 < 1 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8+1)%10 < 1 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8+1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8-1)%10 < 1 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8-1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8-1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8+1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8+1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
				if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8-1)/10 < 1) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8-1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[60] != 900) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}}
				if (tahta[4] != -900) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
				if (tahta[i] == -900 && siyahKisaRokAtilabilirMi() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 || tahta[i] == 900 && beyazKisaRokAtilabilirMi() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}
				if (tahta[i] == -900 && siyahUzunRokAtilabilirMi() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[3] == 0.0 || tahta[i] == 900 && beyazUzunRokAtilabilirMi() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0) {if (tahta[i] > 0) {cizilecekHamleler.add(900*100000+(i/8)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else{cizilecekHamleler.add(-900*100000-(i/8)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}													
			}
			//Knight movements, At hareketleri
			if (tahta[i] == 3 && motor || tahta[i] == -3 && motor || tahta[i] == 3 && arayuzHamle2[0] / 100 == 3 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -3 && arayuzHamle2[0] / 100 == -3 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8-2)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8-2)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}				
					if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8-1)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8-1)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8-1)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8-2)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8-2)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8+2)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8+2)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8+2)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8+2)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8+1)*10000+(i%8-2)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8-2)*1000-(i/8)*100-(i%8)*10);}}
					if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] > -1) {if (tahta[i] > 0) {cizilecekHamleler.add(3*100000+(i/8+1)*10000+(i%8+2)*1000+(i/8)*100+(i%8)*10);}else {cizilecekHamleler.add(-3*100000-(i/8+1)*10000-(i%8+2)*1000-(i/8)*100-(i%8)*10);}}
				}							
			}
			//Bishop movements, Fil hareketleri
			if (tahta[i] == 4 && motor || tahta[i] == -4 && motor || tahta[i] == 4 && arayuzHamle2[0] / 100 == 4 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -4 &&  arayuzHamle2[0] / 100 == -4 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					uzuntasHesap(true,i,tahta);
				}				
			}
			//Rook movements, Kale hareketleri
			if (tahta[i] == 5 && motor || tahta[i] == -5 && motor || tahta[i] == 5 && arayuzHamle2[0] / 100 == 5 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -5 && arayuzHamle2[0] / 100 == -5 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					uzuntasHesap(false,i,tahta);
				}				
				if (beyazKisaRokAtilabilirMi() && tahta[63] != 5.0) {beyazKisaRokAyarla(false);} else if (siyahKisaRokAtilabilirMi() && tahta[7] != -5.0) {siyahKisaRokAyarla(false);}
                if (beyazUzunRokAtilabilirMi() && tahta[56] != 5.0) {beyazUzunRokAyarla(false);} else if (siyahUzunRokAtilabilirMi() && tahta[0] != -5.0) {siyahUzunRokAyarla(false);}
			}
			//Queen movements, Vezir hareketleri
			if (tahta[i] == 9 && motor || tahta[i] == -9 && motor || tahta[i] == 9 && arayuzHamle2[0] / 100 == 9 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -9 && arayuzHamle2[0] / 100 == -9 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {					
					uzuntasHesap(false,i,tahta);
					uzuntasHesap(true,i,tahta);
				}				
			}
			//Pawn movements, Piyon hareketleri
			if (tahta[i] == 1 && motor || tahta[i] == -1 && motor || tahta[i] == 1 && arayuzHamle2[0] / 100 == 1 && (arayuzHamle2[0]/10)%10 == i%8 && arayuzHamle2[0]%10 == i/8 || tahta[i] == -1 && arayuzHamle2[0] / 100 == -1 && ((arayuzHamle2[0]/10)%10)*-1 == i%8 && (arayuzHamle2[0]%10)*-1 == i/8) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
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
    	double[] tahta = tahtaVeriAl();
    	for (byte i = 0;i < tahta.length;i++) {    		
    		if (j != -1) {i = j;}    		
    		if (tahta[i] == 900 || tahta[i] == -900) {    			
        		if (i/8-1 > -1 && tahta[i] == 900 || i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8);}
        		if (i/8+1 < 8 && tahta[i] == 900 || i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8);}
        		if (i%8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8+1);}
        		if (i%8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8-1);}
        		if (i%8-1 > -1 && i/8+1 < 8 && tahta[i] == 900 || i%8-1 > -1 && i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-1);}
        		if (i%8+1 < 8 && i/8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && i/8+1 < 8 &&  tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+1);}
        		if (i%8+1 < 8 && i/8-1 > -1 && tahta[i] == 900 || i%8+1 < 8 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+1);}
        		if (i%8-1 > -1 && i/8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-1);}
        	}
    		if (tahta[i] == 3 || tahta[i] == -3) {
    			if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8-1);}	
				if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-2);}
				if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+2);}
				if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8+1);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8+1);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8-1);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-2);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+2);}
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
    		if (j != -1 && tahta[i] == 1 || j != -1 && tahta[i] == -1 || tahta[i] == 1 || tahta[i] == -1) {
    			if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8-1, sahTehtid.get((i/8-1)*8+i%8-1)+10);}else{sahTehtid.set((i/8+1)*8+i%8-1, sahTehtid.get((i/8+1)*8+i%8-1)+1);}}
				if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8+1, sahTehtid.get((i/8-1)*8+i%8+1)+10);}else{sahTehtid.set((i/8+1)*8+i%8+1, sahTehtid.get((i/8+1)*8+i%8+1)+1);}}
    		}
    		if (j != -1) {break;}
    	}    	
    }
    public void sahTehtidAyarla(double i, int kare) {
    	if (i > 0) {
    		sahTehtid.set(kare, sahTehtid.get(kare)+10);    		    		
    	}
    	else {
    		sahTehtid.set(kare, sahTehtid.get(kare)+1);    		    		
    	}
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
    public void sahTehtidUzunTasHesap(boolean yon, byte i, double[] tahta) {
    	if (yon) {    		
    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 
    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == -900) && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+solAltKose)*8+i%8-solAltKose);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != -900.0 && tahta[i] > 0) {solAltKose = 8;}} else {solAltKose = 8;}
    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == -900)  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 900)  && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-solUstKose)*8+i%8-solUstKose);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != -900.0 && tahta[i] > 0) {solUstKose = 8;}} else {solUstKose = 8;}
    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == -900) && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-sagUstKose)*8+i%8+sagUstKose);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != -900.0 && tahta[i] > 0) {sagUstKose = 8;}} else {sagUstKose = 8;}
    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == -900) && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 900 ) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+sagAltKose)*8+i%8+sagAltKose);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != -900.0 && tahta[i] > 0) {sagAltKose = 8;}} else {sagAltKose = 8;}
    		}    		
    	}
    	else {
    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
    			if ((ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] > -1 || tahta[(((i/8)-ustDikey)*8)+i%8] == -900) && tahta[i] > 0 || (ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] < 1  || tahta[(((i/8)-ustDikey)*8)+i%8] == 900)&& tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)-ustDikey)*8)+i%8);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
    			if ((altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] > -1 || tahta[(((i/8)+altDikey)*8)+i%8] == -900) && tahta[i] > 0 || (altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] < 1 || tahta[(((i/8)+altDikey)*8)+i%8] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)+altDikey)*8)+i%8);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
    			if ((sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] > -1 || tahta[(((i/8))*8)+i%8+sagYatay] == -900) && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] < 1 || tahta[(((i/8))*8)+i%8+sagYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8+sagYatay);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != -900.0 && tahta[i] > 0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
    			if ((solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] > -1 || tahta[(((i/8))*8)+i%8-solYatay] == -900) && tahta[i] > 0 || (solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] < 1 || tahta[(((i/8))*8)+i%8-solYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8-solYatay);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != -900.0 && tahta[i] > 0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
    		}
    	}    	    	    	
    }
    public void tahtayiGoster() {
    	double[] tahta = tahtaVeriAl(); 
    	for (int i = 0;i < tahta.length;i++) {
    		System.out.print(tahta[i]+", ");
    		if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i ==  47 ||i == 55 ||i == 63) {
    			System.out.println();
    		}
    	}
    }    
    public int acmazTest(byte i,int j,double[] tahta) {    	    
    	if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 > i/8) {       	
    		if (j > 0) {return acmazHesap(j,1,i,(sahKonumAl()/1000%10 - i/8)-1,tahta);} else {return acmazHesap(j,1,i,(sahKonumAl()/10%10 - i/8)-1,tahta);}    		    	
    	}
    	else if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 < i/8) {    		
    		if (j > 0) {return acmazHesap(j,2,i,i/8-(sahKonumAl()/1000)%10-1,tahta);} else {return acmazHesap(j,2,i,i/8-(sahKonumAl()/10)%10-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 > i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 > i%8) {    		    		     		    
    		if (j > 0) {return acmazHesap(j,3,i,(sahKonumAl()/100%10 - i%8)-1,tahta);} else {return acmazHesap(j,3,i,(sahKonumAl()%10 - i%8)-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 < i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 < i%8) {    		    	
    		if (j > 0) {return acmazHesap(j,4,i,(i%8-sahKonumAl()/100%10)-1,tahta);} else {return acmazHesap(j,4,i,(i%8-sahKonumAl()%10)-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 0) {return acmazHesap(j,5,i,(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)-i)/9-1,tahta);} else {return acmazHesap(j,5,i,(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)-i)/9-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 < i/8) {    		    	
    		if (j > 0) {return acmazHesap(j,6,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/9-1,tahta);} else {return acmazHesap(j,6,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/9-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 0) {return acmazHesap(j,7,i,((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))-i)/7-1,tahta);} else {return acmazHesap(j,7,i,((((sahKonumAl()/10)%10)*8+(sahKonumAl()%10))-i)/7-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 < i/8) {    		    	
    		if (j > 0) {return acmazHesap(j,8,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/7-1,tahta);} else {return acmazHesap(j,8,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/7-1,tahta);}    	
    	}    	
    	return 0;
    }
    public int acmazHesap(int tasDeger,int hangiYon,int i,int sahTaraf,double[] tahta) {
    	// Uzun hareketli ta�lar = Fil, Vezir, Kale
    	// k�sa hareketli ta�lar = At, Piyon    	    	    	
		//System.out.println("Bu y�nde �st tarafa do�ru hesaplan�lmas� gereken kare say�s� = "+sahTaraf);    	
    	switch(hangiYon) {    	
    		case 1:    	    		    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				}
    			}
    			for (int y = i/8,x = i%8;y > 0;) {    				
    				y--;		    		    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}    			
    			break;
    		case 2:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {
    					return 0;
    				}
    			}
    			for (int y = i/8,x = i%8;y < 7;) {    				
    				y++;		    		    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}
    			break;
    		case 3:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8)*8+i%8+p] != 0) {    					
    					return 0;
    				}
    			}
    			for (int y = i/8,x = i%8;x > 0;) {       				
    				x--;		    		    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}
    			break;
    		case 4:
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8)*8+i%8-p] != 0) {    					
    					return 0;
    				}
    			}
    			for (int y = i/8,x = i%8;x < 7;) {       				
    				x++;		    		    	    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {    					
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}
    			break;    			
    		case 5:
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8+p] != 0) {
    					return 0;
    				}
    			}
    			for (int y = i/8,x = i%8;x > 0 && y > 0;) {
    				y--;x--;		    		
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}    			
    			break;    			    		
    		case 6:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8-p] != 0) {
    					return 0;
    				}
    			}
    			for (int y = i/8+1,x = i%8+1;x < 7 && y < 8;y++,x++) {    						    	
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
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
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
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
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					return 1;
    				}
    				if (tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				
    			}    			
    			break;
    	}
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
            int arayuzBilgi = (int) (tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] > 0 || tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3] == 0 ? tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*10+(e.getY()+25)/75-3 : tahta[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*-10+((e.getY()+25)/75-3)*-1);                                                        
            if (arayuzHamleSira) {                             
                arayuzHamle2[0] = arayuzBilgi;
                arayuzCizimYenileme = true;
                new Tahta();
                arayuzCizimYenileme = false;
                arayuzSayac++;

            } else {
                
                arayuzHamle2[1] = arayuzBilgi;
                arayuzSayac++;
                new Tahta();
            }
            if (arayuzSayac == 2) {
                //System.out.println("Birinci Hamle = "+arayuzHamle[0]+" "+"�kinci Hamle = "+arayuzHamle[1]);
                System.out.println("Birinci Hamle = "+arayuzHamle2[0]+" "+"�kinci Hamle = "+arayuzHamle2[1]);                
                yeniHamle();
                sahTehtid.clear();
               if (hamleler.size() > 0) {
            	   sahTehtid();
            	   sahTehtid((byte) -1); 
               }            	   	            	
                //tahtayiGoster();
                if (isPiyonTerfi() == false) {
                	cizilecekHamleler.clear();
                }                
            }
            else {            
                System.out.println("Hamleler Tamamlanmad� !");
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
        	System.out.println("Piyon terfi a�amas�nda olabiliriz = "+isPiyonTerfi()+" Y = "+((e.getY()+25)/75));        	        	
        	System.out.println("Piyon terfi a�amas�nda olabiliriz = "+isPiyonTerfi()+" X = "+((e.getX()+25)/75));
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