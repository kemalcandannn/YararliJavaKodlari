package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileSorting {

	public static void sort(String path, String fileName) throws Exception {
		/*
		 * 1. Verilen path deki dosya satir satir okunur.
		 * 2. Herbir cümle bos degilse bir listeye eklenir.
		 * 3. Liste siralanir.
		 * 4. Siralanmis liste verilen path'e geri yazdirilir.
		 */

		if (Util.empty(path) || Util.empty(fileName)) {
			throw new Exception("Dosya Uzantisi ya da Dosya Adi Yazilmamis. Lütfen Kontrol Ediniz.");
		}

		path = path.endsWith("/") ? path : path + "/";
		fileName = fileName.startsWith("/") ? fileName.substring(1) : fileName;
		String uzanti = "";
		String geciciUzanti = ".txt";
		
		if(fileName.contains(".")) {
			uzanti = fileName.substring(fileName.lastIndexOf("."));
			fileName = fileName.substring(0, fileName.indexOf("."));
		}
		
		try {
			File file = new File(path + fileName + uzanti);
			Scanner myReader = new Scanner(new FileInputStream(file));
			List<String> textList = new ArrayList<String>();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				if (Util.notEmpty(data)) {
					textList.add(data);
				}
			}
			myReader.close();

			Collections.sort(textList);

			FileWriter myWriter = new FileWriter(path + fileName + geciciUzanti);

			for (int i = 0; i < textList.size(); i++) {
				myWriter.write(textList.get(i));

				if (i != textList.size() - 1) {
					myWriter.write("\n");
				}
			}
			myWriter.close();

			if(file.exists())
				file.delete();

			boolean yenidenAdlandirildi = Util.yenidenAdlandir(path + fileName + geciciUzanti, path + fileName + uzanti);
			if (yenidenAdlandirildi) {
				System.out.println("Dosya basariyla olusturuldu.");
			}else {
				throw new Exception("Dosya yeniden adlandirilirken bir hata olustu.");
			}
		} catch (FileNotFoundException e) {
			throw new Exception("Dosya bulunamadi. [" + e.getMessage() + "]");
		} catch (IOException e) {
			throw new Exception("Dosya hatasi [" + e.getMessage() + "]");
		}

	}
}
