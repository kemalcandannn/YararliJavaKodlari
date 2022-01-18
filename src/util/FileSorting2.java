package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSorting2 {

	public static void sort(String path, String fileName) throws Exception {
		/*
		 * 1. Verilen path deki dosya satir satir okunur. 2. Herbir cümle bos degilse
		 * bir listeye eklenir. 3. Liste siralanir. 4. Siralanmis liste verilen path'e
		 * geri yazdirilir.
		 */

		if (Util.empty(path) || Util.empty(fileName)) {
			throw new Exception("Dosya Uzantisi ya da Dosya Adi Yazilmamis. Lütfen Kontrol Ediniz.");
		}

		path = path.endsWith("/") ? path : path + "/";
		fileName = fileName.startsWith("/") ? fileName.substring(1) : fileName;
		String uzanti = "";

		if (fileName.contains(".")) {
			uzanti = fileName.substring(fileName.lastIndexOf("."));
			fileName = fileName.substring(0, fileName.indexOf("."));
		}

		try (FileInputStream fis = new FileInputStream(new File(path + fileName + uzanti));
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isr)) {

			String str;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readUnicodeFiles(String fileName) {

        Path path = Paths.get(fileName);
        try {

            // Java 8
            List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
            list.forEach(System.out::println);

//            // Java 8
//            Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
//            lines.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
