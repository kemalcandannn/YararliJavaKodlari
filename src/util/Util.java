package util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.DonusTipiEnum;
import util.PasswordGenerator.PasswordGeneratorBuilder;
public class Util{
	
	public static final int LIMIT = 10;

	public static <E> List<E> toList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}

	public static boolean empty(Object obj) {
		if(obj instanceof String) {
			return ((String) obj) == null || ((String) obj).trim().isEmpty();
		}else if(obj instanceof Long) {
			return ((Long) obj) == null || ((Long) obj) == 0L;
		}

		return obj == null;
	}
	
	public static boolean notEmpty(Object obj) {
		if(obj instanceof String) {
			return ((String) obj) != null && ((String) obj).trim().isEmpty() == false;
		}else if(obj instanceof Long) {
			return ((Long) obj) != null && ((Long) obj) != 0L;
		}

		return obj != null;
	}

	public static boolean isValidIPAddress(String ip) {
		
		String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])"; 
        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255; 
        Pattern p = Pattern.compile(regex); 
  
        if (ip == null) { 
            return false; 
        } 
  
        Matcher m = p.matcher(ip); 
  
        return m.matches();
	}

	public static Object rastgeleSayiUret(float min, float max, DonusTipiEnum donusTipi) {
		float range = max - min + 1;
		
		if(donusTipi.equals(DonusTipiEnum.SHORT) || donusTipi.equals(DonusTipiEnum.INTEGER) || donusTipi.equals(DonusTipiEnum.LONG)) {
			return (int) ((Math.random() * range) + min);
		} else if(donusTipi.equals(DonusTipiEnum.FLOAT) || donusTipi.equals(DonusTipiEnum.DOUBLE)) {
			return (float) ((Math.random() * range) + min);
		} else {
			return String.valueOf((Math.random() * range) + min);
		}
	}

	public static String rastgeleIsimUret(int uzunluk) {
		PasswordGenerator pg = new PasswordGenerator(new PasswordGeneratorBuilder().
				useDigits(true).
				useUpper(true).
				useLower(false).
				usePunctuation(false));

		return pg.generate(uzunluk);
	}

	public static boolean yenidenAdlandir(String from, String to) throws IOException {
		File eskiDosya = new File(from);
		File yeniDosya = new File(to);

		if (yeniDosya.exists())
		   throw new IOException("'"+ to + "'" + " konumunda mevcutta dosya bulunuyor.");

		return eskiDosya.renameTo(yeniDosya);
	}

	/**
	 * tckn dogru ise true doner
	 * */
	public static boolean tcknDogruMu(String tckn) {
		//11 hane olmali
		if(tckn.length() != 11)
			return false;
		
		//son hane cift olmali
		if(Integer.parseInt(tckn.charAt(10)+"") % 2 == 1)
			return false;
		
		int tekHaneToplam = Integer.parseInt(tckn.charAt(0)+"") + Integer.parseInt(tckn.charAt(2)+"") + Integer.parseInt(tckn.charAt(4)+"") + Integer.parseInt(tckn.charAt(6)+"") + Integer.parseInt(tckn.charAt(8)+"");
		int ciftHaneToplam = Integer.parseInt(tckn.charAt(1)+"") + Integer.parseInt(tckn.charAt(3)+"") + Integer.parseInt(tckn.charAt(5)+"") + Integer.parseInt(tckn.charAt(7)+"");
		int carpim = tekHaneToplam * 7;
		int fark = carpim - ciftHaneToplam;
		int hane10 = fark % 10;
		int toplamGenel = tekHaneToplam + ciftHaneToplam + hane10;
		int hane11 = toplamGenel % 10;
		
		//son 2 hane algoritmaya uymali
		if(Integer.parseInt(tckn.charAt(9)+"") != hane10 || Integer.parseInt(tckn.charAt(10)+"") != hane11)
			return false;
		
		return true;
	}

}