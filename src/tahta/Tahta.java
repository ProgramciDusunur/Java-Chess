package tahta;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import grafik.Grafik;

public class Tahta implements MouseListener,MouseMotionListener  {
    //public String[] arayuzCizilecekHamleler = new String[27];
    List<String> sahTehtidi =new LinkedList<String>(
            Arrays.asList("  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  ",
                    "  ","  ","  ","  ","  ","  ","  ","  "));
    LinkedList<String> tasLegalHamle =new LinkedList<String>();
    public LinkedList<String> legalHamleHesap =new LinkedList<String>(),hamleler =new LinkedList<String>();
    public List<Double> myList = new LinkedList<Double>();    
    private static JButton buton = new JButton();
    private static Grafik grafik = new Grafik();
    private String sahKonum = "a8e8";
    private boolean arayuzDinleyiciKarar = false;
    private boolean arayuzHamleSira = true,arayuzCizimYenileme = false,beyazKisaRok = true,beyazUzunRok = true,siyahKisaRok = true,siyahUzunRok = true;
    //private String arayuzSonTiklananKare;
    private Byte arayuzSayac = 0;
    public String[] arayuzHamle = new String[3];
    public static String[] tahta =
            	   {"k","a","f","v","s","f","a","k",
                    "p","p","p","p","p","p","p","p",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    "P","P","P","P","P","P","P","P",
                    "K","A","F","V","S","F","A","K"};
    public static double[] tahta2 =
 	   {-5.0,-3.0,-4.0,-9,-900,-4.0,-3,-5.0,
 		-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
         1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
         5.0,3.0,4.0,9,900,4.0,3,5.0};
    private static String[] tahtaa =
                   {"S"," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," ","K"," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," ","v",
                    " "," "," "," "," "," "," "," "};
    public static JFrame f = new JFrame();
    private static JLabel label[] = new JLabel[64];
    public Tahta() {
        /*
         * Yap�lacaklar;
         * K�sa Rok , Uzun Rok / Tamamland�
         * Ge�erken Alma , Piyon Terfi / Tamamlanmad�
         * A�maz , �ah �eki� Kontrol / Tamamlanmad�
         */
        if (buton.getActionListeners() != null) {
            buton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   long mili = System.nanoTime();                                        
                    //System.out.println(kacSaniye);
                   tasLegalHamle.clear();
                   arayuzCizilecekLegalHamleler(false,false,true);
                   	System.out.println(tasLegalHamle.size());
                    for (String i : tasLegalHamle) {
                    	System.out.println(i);
                    }
                    double kacSaniye = (( System.nanoTime() - mili)/ 1000000000.0);
                    System.out.println(kacSaniye);
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
        kareTasBulmaCizdirme('j',(byte) 1);
        f.setSize(1000, 1000);
        f.setTitle("Satran�");
        f.add(buton);
        f.add(grafik);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        buton.setVisible(true);
        buton.setBounds(200,150,125,50);
        buton.addActionListener(null);
    }
    public static String[] tahtaVeriAl() {
        return tahta;
    }
    public void tahtaVeriAyarla(String[] ayarlanacakVeri) {
        tahta = ayarlanacakVeri;
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
    public LinkedList<String> arayuzCizilecekHamlelerVeriAl() {
        return tasLegalHamle;
    }
    public void arayuzCizimHamleleriVeriAyarla(LinkedList<String> hamleler) {
        tasLegalHamle = hamleler;
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
    public void sahKonumAyarla(String sahkonum) {
    	sahKonum = sahkonum;
    }
    public String sahKonumAl() {
    	return sahKonum;
    }
    // Draw pieces , Taslari Cizdirme
    public static String kareTasBulmaCizdirme(char istenilenKare,byte yatay) {
        int baslangicX = 207,baslangicY = 200;
        String[] tahta = tahtaVeriAl();
        char kare = 'a';
        for (int i = 0,j = 8;i < tahta.length;i++) {
            if (i != 0) {
                baslangicX+=75;
                kare++;
            }
            if (i == 8 || i == 16 ||i== 24 || i==32 || i==40 || i== 48 || i==56) {
                kare = 'a';
                j--;
                baslangicX=207;
                baslangicY+=75;
            }
            if (istenilenKare == 'j') {
                if (tahta[i].equals("S")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz sah.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("V")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz vezir.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("F")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz fil.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("K")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz kale.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("P")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz piyon.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("A")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz at.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                if (tahta[i].equals("s")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah sah.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("v")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah vezir.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("f")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah fil.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("k")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah kale.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("p")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah piyon.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
                else if (tahta[i].equals("a")) {
                    label[i] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah at.png"), JLabel.HEIGHT);
                    f.add(label[i]);
                    label[i].setBounds(baslangicX, baslangicY, 75,75);
                }
            }
            if (kare == istenilenKare && j == yatay) {
                return tahta[i];
            }
        }
        return "Istenilen deger bulunamadi.";
    }
    public void hamle() {
        String[] tahta = tahtaVeriAl();        
        for (String i : tasLegalHamle) {
            if (i.length() > 0) {
                if (arayuzHamle[0].equals("Se1") && i.charAt(0) == 'g' && i.charAt(1) == '1' && arayuzHamle[1].equals("Xg1") && beyazKisaRokAtilabilirMi() || arayuzHamle[0].equals("Se1")&& i.charAt(0) == 'c' && i.charAt(1) == '1' && arayuzHamle[1].equals("Xc1") && beyazUzunRokAtilabilirMi() || arayuzHamle[0].equals("se8")&& i.charAt(0) == 'g' && i.charAt(1) == '8' && arayuzHamle[1].equals("Xg8") && siyahKisaRokAtilabilirMi() || arayuzHamle[0].equals("se8") && i.charAt(0) == 'c' && i.charAt(1) == '8' && arayuzHamle[1].equals("Xc8") && siyahUzunRokAtilabilirMi()) {
                    if (arayuzHamle[1].equals("Xg1") || arayuzHamle[1].equals("Xg8")) {arayuzHamle[1] = "O-O";} else if (arayuzHamle[1].equals("Xc1") || arayuzHamle[1].equals("Xc8")) {arayuzHamle[1] = "O-O-O";}
                }
                if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O" && beyazKisaRokAtilabilirMi() || arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O-O" && beyazUzunRokAtilabilirMi() || arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O-O" && siyahUzunRokAtilabilirMi() || arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O" && siyahKisaRokAtilabilirMi()) {
                    if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O") {tahta[60] = " ";tahta[63] = " ";tahta[62] = "S";tahta[61] = "K";f.remove(label[60]);f.remove(label[63]);} else if (arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O") {tahta[7] = " ";tahta[4] = " ";tahta[5] = "k";tahta[6] = "s";if (label[4] != null) {f.remove(label[4]);}if (label[5] != null) {f.remove(label[5]);}if (label[7] != null) {f.remove(label[7]);}}
                    if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O") {label[62] = new JLabel(new ImageIcon("C:\\Users\\Eren\\Desktop\\Satran�\\Beyaz Ta�lar\\beyaz sah.png"), JLabel.HEIGHT);label[61] = new JLabel(new ImageIcon("C:\\Users\\Eren\\Desktop\\Satran�\\Beyaz Ta�lar\\beyaz kale.png"), JLabel.HEIGHT);label[61].setBounds(582, 725, 75, 75);label[62].setBounds(657, 725, 75, 75);f.add(label[61]);f.add(label[62]);} else if (arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O") {label[6] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah sah.png"), JLabel.HEIGHT);label[5] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah kale.png"), JLabel.HEIGHT);label[6].setBounds(657, 200, 75, 75);label[5].setBounds(582, 200, 75, 75);f.add(label[6]);f.add(label[5]);}
                    if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O-O") {tahta[60] = " ";tahta[56] = " ";tahta[58] = "S";tahta[59] = "K";f.remove(label[60]);f.remove(label[56]);} else if (arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O-O") {tahta[4] = " ";tahta[0] = " ";tahta[2] = "s";tahta[3] = "k";f.remove(label[4]);f.remove(label[0]);}
                    if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1] == "O-O-O") {label[58] = new JLabel(new ImageIcon("C:\\Users\\Eren\\Desktop\\Satran�\\Beyaz Ta�lar\\beyaz sah.png"), JLabel.HEIGHT);label[59] = new JLabel(new ImageIcon("C:\\Users\\Eren\\Desktop\\Satran�\\Beyaz Ta�lar\\beyaz kale.png"), JLabel.HEIGHT);label[58].setBounds(357, 725, 75, 75);label[59].setBounds(432, 725, 75, 75);f.add(label[58]);f.add(label[59]);} else if (arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1] == "O-O-O") {label[2] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah sah.png"), JLabel.HEIGHT);label[3] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah kale.png"), JLabel.HEIGHT);label[2].setBounds(357, 200, 75, 75);label[3].setBounds(432, 200, 75, 75);f.add(label[2]);f.add(label[3]);}
                }
                if (arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                	hamleler.add(i);
                    tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[0].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] = " ";
                    if (label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[0].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != null) {
                        f.remove(label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[0].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10]);
                    }
                    if (label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] != null) {
                        f.remove(label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10]);
                    }
                    if (arayuzHamle[0].charAt(0) == 'A' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "A";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz at.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'a' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "a";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah at.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'V' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "V";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz vezir.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'v' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "v";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah vezir.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'F' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "F";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz fil.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'f' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "f";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah fil.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'S' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {                    	
                    	sahKonumAyarla(""+i.charAt(1)+i.charAt(2)+sahKonumAl().charAt(0)+sahKonumAl().charAt(1));                    	
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "S";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz sah.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 's' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                    	sahKonumAyarla(""+sahKonumAl().charAt(0)+sahKonumAl().charAt(1)+i.charAt(1)+i.charAt(2));                    	
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "s";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah sah.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'K' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {                    	
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "K";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz kale.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'k' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "k";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah kale.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'P' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {                    	
                    	if (tahta[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2)))))-1)*8)+Character.getNumericValue(i.charAt(1))-10] == " " && i.charAt(1) != i.charAt(3)) {                    		
                    		f.remove(label[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2))))))*8)+Character.getNumericValue(i.charAt(1))-10]);
                    		tahta[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2))))))*8)+Character.getNumericValue(i.charAt(1))-10] = " ";
                    	}
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "P";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Beyaz Ta�lar\\beyaz piyon.png"), JLabel.HEIGHT);
                    }
                    else if (arayuzHamle[0].charAt(0) == 'p' && arayuzHamle[1].charAt(1) == i.charAt(1) && arayuzHamle[1].charAt(2) == i.charAt(2)) {
                    	if (tahta[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2)))))-1)*8)+Character.getNumericValue(i.charAt(1))-10] == " " && i.charAt(1) != i.charAt(3)) {                    		
                    		f.remove(label[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2)))))-2)*8)+Character.getNumericValue(i.charAt(1))-10]);
                    		tahta[(((tahtaDikeyCeviri(Byte.valueOf(String.valueOf(i.charAt(2)))))-2)*8)+Character.getNumericValue(i.charAt(1))-10] = " ";
                    	}
                        tahta[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = "p";
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10] = new JLabel(new ImageIcon("C:\\Users\\erena\\OneDrive\\Masa�st�\\Satran� Ta�lar�\\Siyah Ta�lar\\siyah piyon.png"), JLabel.HEIGHT);
                    }
                    if (arayuzHamle[0] != "bsd") {
                        label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10].setBounds((Character.getNumericValue(arayuzHamle[1].charAt(1))-10)*75+207, tahtaDikeyCeviri((byte) ((byte)(arayuzHamle[1].charAt(2))-48))*75+125, 75, 75);
                        f.add(label[tahtaDikeyCeviri((byte) ((byte) (((byte)arayuzHamle[1].charAt(2))-48)))*8-8+Character.getNumericValue(arayuzHamle[1].charAt(1))-10]);
                    }
                }
            }
        }
        grafik.getGraphics();
        f.remove(grafik);
        f.add(grafik);
        f.repaint();
        tahtaVeriAyarla(tahta);
    }   
    public void arayuzCizilecekLegalHamleler(boolean hamleAl,boolean sahKontrol,boolean motor) {
        if (hamleAl && arayuzHamle[0] == null) {arayuzHamle[0] = "   ";}
        boolean solUstKose = true , sagUstKose = true , solAltKose = true , sagAltKose = true,solYatay = true , sagYatay = true , ustDikey = true , altDikey = true;
        String[] tahta = Tahta.tahtaVeriAl();
        char kare = 'a';
        for (byte i = 0, j = 8;i < tahta.length;i++) {        	                                    
            if (i != 0) {kare++;}if (i == 8 || i == 16 ||i== 24 || i== 32 || i== 40 ||i== 48 || i== 56) {j--;kare = 'a';}
            if (motor) {
            	arayuzHamle[0] = "ca8";
            }
            // King movements , �ah hareketleri
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
        }
    }
    public void yeniArama() {
		double[] tahta = Tahta.tahta2VeriAl();
		for (byte i = 0;i < tahta.length;i++) {						
			//double hesap = tahta[(i/8)*8+i%8];		
			if (tahta[i] == 900 && arayuzHamle[0].charAt(0) == 'S' || tahta[i] == -900 && arayuzHamle[0].charAt(0) == 's') {
				System.out.println("Evet bulduk");
			}
		}
	}
    public void legalHamleler() {
        legalHamleHesap.removeAll(legalHamleHesap);
        sahTehtidi = Arrays.asList
                ("  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ",
                        "  ","  ","  ","  ","  ","  ","  ","  ");
        arayuzCizilecekLegalHamleler(true,true,false);
        int toplamOynanabilirHamle = 0;
        String ayarlanacak = "  ";
        for (String i : legalHamleHesap) {
            //System.out.println(i);
            if (Character.isLowerCase(i.charAt(0)) && i.charAt(0) != 'p') {
                if (((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1)) >= 0 && ((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1)) <= 63) {
                    ayarlanacak = "t"+sahTehtidi.get((((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1)))).charAt(1);
                    sahTehtidi.set((((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1))), ayarlanacak);
                }
            }
            if (Character.isUpperCase(i.charAt(0)) && i.charAt(0) != 'P') {
                ayarlanacak = sahTehtidi.get((((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1)))).charAt(0)+"T";
                sahTehtidi.set((((tahtaDikeyCeviri((byte) ((byte) (((byte)i.charAt(2))-48)))*8-8+Character.getNumericValue(i.charAt(1))-9-1))), ayarlanacak);
            }
        }
        toplamOynanabilirHamle = legalHamleHesap.size();
        buton.setText(""+toplamOynanabilirHamle);
		/*for (Byte i = 0;i < sahTehtidi.size();i++) {
			if (i % 8 == 0) {
				System.out.println();
			}
			System.out.print(sahTehtidi.get(i));

		}*/
    }
    public String acmazKontrolOld() {
    	/*
        // tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10
        for (Byte i = 0;i < tahta.length;i++) {
            if (tahta[i] == "S" && Character.isUpperCase(arayuzHamle[0].charAt(0)) || tahta[i] == "s" && Character.isLowerCase(arayuzHamle[0].charAt(0))) {
                for (Byte p =1;p < 8 ;p++) {
                    if ((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(1))-10) == (byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10))) {
                        if (i > tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) {
                            if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0))) {
                                if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != " ") {
                                    return "acmazYok";
                                }
                                for (Byte j = 1;j <= ((i/8)+1)-tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))));j++) {
                                    if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "S") {
                                        return "acmazYok";
                                    }
                                }

                                if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "v" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "k") {
                                    return "acmazUstDikey";
                                }
                            }
                            else if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0))) {
                                if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "K" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != " ") {
                                    return "acmazYok";
                                }
                                for (Byte j = 1;j <= ((i/8)+1)-tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))));j++) {
                                    if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "s") {
                                        return "acmazYok";
                                    }
                                }
                                if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "V" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "K") {
                                    return "acmazUstDikey";
                                }
                            }
                        }
                        if (i < tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) {
                            if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) <=8) {
                                if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0))) {
                                    // tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "k"
                                    if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "k" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != " ") {
                                        return "acmazYok";
                                    }
                                    for (Byte j = 1;j >= ((i/8)+1)-tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))));j++) {
                                        if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "S") {
                                            return "acmazYok";
                                        }
                                    }

                                    if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "v" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "k") {
                                        return "acmazUstDikey";
                                    }
                                }
                                else if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0))) {
                                    if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "K" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != " ") {
                                        return "acmazYok";
                                    }
                                    for (Byte j = 1;j >= ((i/8)+1)-tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))));j++) {
                                        if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] != "s") {
                                            return "acmazYok";
                                        }
                                    }

                                    if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "V" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10] == "K") {
                                        return "acmazUstDikey";
                                    }
                                }
                            }
                        }
                    }
                    if ((((i/8))*8+(byte)(((((((((double)i/8)-(i/8)))))-(((((double)i/8)-(i/8)))/0.125)*0.025)*10)))%9 == (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10)%9 && arayuzHamle[0].charAt(0) != 's' && arayuzHamle[0].charAt(0) != 'S') {
                        if (i < tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) {
                            if (Character.isLowerCase(arayuzHamle[0].charAt(0))) {
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) <=8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p >=1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 8) {
                                    if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p].charAt(0)) || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "V" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "F") {
                                        return "acmazYok";
                                    }

                                    //System.out.println(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p);
                                }
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {
                                    if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "s" && Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p].charAt(0))) {
                                        return "acmazYok";
                                    }

                                }
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) <=8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p >=1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 8) {
                                    if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "F" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "V") {
                                        System.out.println("A�maz");
                                        return "acmazSagAltKose";
                                    }
                                }

                            }
                            if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))-p)) <=8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p >=1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 8) {
                                    //if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p].charAt(0)) || Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "f" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] != "v") {
                                        //return "acmazYok";
                                    //}
                                }
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+p)) <=8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >=1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p <= 8) {
                                    for (Byte j = 1;(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-j)-i > 0;j++) {
                                        if (Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-j].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-j] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+j))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-j] != "f") {
                                            System.out.println("A�maz Yok");
                                            return "acmazYok";
                                        }

                                    }
                                }
                                if ((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p) >= 1 && (byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p) <= 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p >= 1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p < 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 63) {
                                    if (tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "v" || tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p] == "f") {
                                        return "acmazSagAltKose";
                                    }
                                }

                            }
                        }
                        if (i > tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10) {
                            if (Character.isUpperCase(arayuzHamle[0].charAt(0))) {
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p >= 0) {
                                    if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p].charAt(0)) || Character.isLowerCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "v" && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p] != "f") {
                                        //System.out.println(tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1+p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10-p+"Debug test <----");

                                    }
                                }
                                if (tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) >= 1 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p)) <= 8 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p >= 1 && Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 8 && tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p <= 63) {
                                    if (Character.isUpperCase(tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p].charAt(0)) && tahta[tahtaDikeyCeviri((byte) ((byte)Character.getNumericValue(arayuzHamle[0].charAt(2))+1-p))*8+Character.getNumericValue(arayuzHamle[0].charAt(1))-10+p].charAt(0) != 'S') {

                                    }
                                }



                            }
                            if (Character.isLowerCase(arayuzHamle[0].charAt(0))) {

                            }



                        }
                    }

                }
            }
        }
        */
        return "acmazYok";
    }
    public String acmazKontrol() {
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
        return "acmazYok";
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
        if (((e.getX()+25)/75)>= 3 && ((e.getX()+25)/75)<= 10 && ((e.getY()+25)/75)>= 3 && ((e.getY()+25)/75)<= 10) {            
            String mevcutHamle = String.valueOf((char)(((e.getX()+25)/75)-3+97)+String.valueOf((byte) tahtaDikeyCeviri((byte)((e.getY()+25)/75-2))));              
            //System.out.println(tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]*100+((e.getX()+25)/75-3)*10+(e.getY()+25)/75-3);
            //System.out.println((tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]/tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]));            
            System.out.println(tahta2[((e.getY()+25)/75-3)*8+((e.getX()+25)/75)-3]);           
            //System.out.println((e.getY()+25)/75-3);
            if (!kareTasBulmaCizdirme(mevcutHamle.charAt(0),(byte) Character.getNumericValue(mevcutHamle.charAt(1)) ).equals(" ") ) {
            	//System.out.println((byte)(((e.getY()+25)/75-2)-1)*8+Character.getNumericValue(mevcutHamle.charAt(0))-10);            	
                mevcutHamle = kareTasBulmaCizdirme(mevcutHamle.charAt(0),(byte) Character.getNumericValue(mevcutHamle.charAt(1)) )+mevcutHamle;
            }
            if (mevcutHamle.length() == 2) {
                mevcutHamle = "X"+mevcutHamle;
            }
            if (arayuzHamleSira) {
                if (arayuzCizilecekHamlelerVeriAl() != null) {
                    LinkedList<String> gerekli = arayuzCizilecekHamlelerVeriAl();
                    for (int i = 0;i < gerekli.size();i++) {
                        gerekli.set(i, "");
                    }
                    arayuzCizimHamleleriVeriAyarla(gerekli);
                }
                arayuzHamle[0] = mevcutHamle;
                arayuzCizimYenileme = true;
                new Tahta();
                arayuzCizimYenileme = false;
                arayuzSayac++;

            } else {
                arayuzHamle[1] = mevcutHamle;
                arayuzSayac++;
                new Tahta();
            }
            if (arayuzSayac == 2) {
                System.out.println("Birinci Hamle = "+arayuzHamle[0]+" "+"�kinci Hamle = "+arayuzHamle[1]);
                hamle();
            }
            else {            
                System.out.println("Hamleler Tamamlanmad� !");
            }
            if (arayuzSayac >=2) {
                arayuzSayac = 0;
            }
            arayuzHamleSira = !arayuzHamleSira;
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