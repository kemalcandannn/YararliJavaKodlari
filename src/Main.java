import util.FileSorting;

public class Main {

	public static void main(String[] args) {
		
		try {
			FileSorting.sort("/home/kemal/Desktop/Projects/BAYS/src/main/resources/lang", "/labels_tr.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
