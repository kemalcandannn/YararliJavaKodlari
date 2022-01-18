package util;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import enums.DonusTipiEnum;

public class DateTimeUtil {

	public static Date getDateClass(String tarih) {
		if (Util.empty(tarih))
			return null;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dateClass = null;

		if (Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e1) {
				format = new SimpleDateFormat("MM/dd/yyyy");
				try {
					dateClass = (Date) format.parse(tarih);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		} else if (Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("dd-MM-yyyy");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e1) {
				format = new SimpleDateFormat("MM-dd-yyyy");
				try {
					dateClass = (Date) format.parse(tarih);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		} else if (Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("dd.MM.yyyy");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e1) {
				format = new SimpleDateFormat("MM.dd.yyyy");
				try {
					dateClass = (Date) format.parse(tarih);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		if (Pattern.compile("^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e1) {
				format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				try {
					dateClass = (Date) format.parse(tarih);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		} else if (Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("yyyy-MM-dd");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (Pattern.compile("^\\d{8}$").matcher(tarih).find()) {
			format = new SimpleDateFormat("ddMMyyyy");

			try {
				dateClass = (Date) format.parse(tarih);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dateClass;
	}

	public static Time getTimeClass(String saat) {
		Time timeClass = null;
		try {
			if (Pattern.compile("^\\d{2}$").matcher(saat).find()) {
				timeClass = Time.valueOf(saat + ":00" + ":00");
			} else if (Pattern.compile("^\\d{2}:\\d{2}$").matcher(saat).find()) {
				timeClass = Time.valueOf(saat + ":00");
			} else if (Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$").matcher(saat).find()) {
				timeClass = Time.valueOf(saat);
			}

		} catch (Exception e) {
		}
		return timeClass;
	}

	public static String getDateString(Date tarih, String pattern) {
		if (tarih == null) {
			return null;
		}
		if (Util.empty(pattern)) {
			pattern = "dd/MM/yyyy";
		}

		String dateString = null;
		try {
			dateString = (new SimpleDateFormat(pattern)).format(tarih);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateString;
	}

	public static String getTimeString(Time saat, String pattern) {
		if (saat == null) {
			return null;
		}
		if (Util.empty(pattern)) {
			pattern = "HH:mm";
		}

		String dateString = null;

		try {
			dateString = (new SimpleDateFormat(pattern)).format(saat).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateString;
	}

	public static String getYear(Date tarih) {
		if (tarih != null) {
			return (new SimpleDateFormat("yyyy")).format(tarih);
		} else {
			return (new SimpleDateFormat("yyyy")).format(new Date());
		}
	}

	public static String getMonth(Date tarih, String locale) {
		if (Util.empty(locale)) {
			locale = "tr";
		}

		if (tarih != null) {
			return (new SimpleDateFormat("MMMM", new Locale(locale))).format(tarih);
		} else {
			return (new SimpleDateFormat("MMMM", new Locale(locale))).format(new Date());
		}
	}

	public static long aradaKacGunVar(Calendar startDate, Calendar endDate) {
	    try {
	    	long end = endDate.getTimeInMillis();
		    long start = startDate.getTimeInMillis();
		    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	    }
	    catch(Exception e) {
	    	return -1;
	    }
	}

	public static Time rastgeleSaatUret() {
		Time rastgeleSaat = null;
		
		while(true) {
			try {
				int saatTmp = (Integer) Util.rastgeleSayiUret(0, 23, DonusTipiEnum.INTEGER);
				int dakikaTmp = (Integer) Util.rastgeleSayiUret(0, 59, DonusTipiEnum.INTEGER);
				int saniyeTmp = (Integer) Util.rastgeleSayiUret(0, 59, DonusTipiEnum.INTEGER);

				String saat = saatTmp < 10 ? "0" + saatTmp : saatTmp + "";
				String dakika = dakikaTmp < 10 ? "0" + dakikaTmp : saatTmp + "";
				String saniye = saniyeTmp < 10 ? "0" + saniyeTmp : saatTmp + "";

				rastgeleSaat = Time.valueOf(saat + ":" + dakika + ":" + saniye);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(rastgeleSaat != null) {
				break;
			}
		}
		
		return rastgeleSaat;
	}

	public static Date rastgeleTarihUret() {
		Calendar calendar = Calendar.getInstance();
		return rastgeleTarihUret(1900, calendar.get(Calendar.YEAR));
	}

	public static Date rastgeleGunumuzdenOnceTarihUret() {
		Calendar calendar = Calendar.getInstance();
		return rastgeleTarihUret(0, calendar.get(Calendar.YEAR));
	}

	public static Date rastgeleGunumuzdenSonraTarihUret() {
		Calendar calendar = Calendar.getInstance();
		return rastgeleTarihUret(calendar.get(Calendar.YEAR), 2100);
	}

	public static Date rastgeleTarihUret(int baslangicYili, int bitisYili) {
		Date rastgeleTarih = null;

		while(true) {
			try {
				Calendar calendar = Calendar.getInstance();
				
				int yil = (Integer) Util.rastgeleSayiUret(baslangicYili, bitisYili, DonusTipiEnum.INTEGER);
				int ay = (Integer) Util.rastgeleSayiUret(0, 12, DonusTipiEnum.INTEGER);
				int gun = (Integer) Util.rastgeleSayiUret(0, 31, DonusTipiEnum.INTEGER);
				int saat = (Integer) Util.rastgeleSayiUret(0, 23, DonusTipiEnum.INTEGER);
				int dakika = (Integer) Util.rastgeleSayiUret(0, 59, DonusTipiEnum.INTEGER);
				int saniye = (Integer) Util.rastgeleSayiUret(0, 59, DonusTipiEnum.INTEGER);

				calendar.set(Calendar.YEAR, yil);
				calendar.set(Calendar.MONTH, ay);
				calendar.set(Calendar.DAY_OF_MONTH, gun);
				calendar.set(Calendar.HOUR_OF_DAY, saat);
				calendar.set(Calendar.MINUTE, dakika);
				calendar.set(Calendar.SECOND, saniye);
				rastgeleTarih = calendar.getTime();
			} catch (Exception e) {}
			
			if(rastgeleTarih != null) {
				break;
			}
		}
		
		return rastgeleTarih;
	}
	
}