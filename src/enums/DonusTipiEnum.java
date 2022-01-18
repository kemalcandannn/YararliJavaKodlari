package enums;

public enum DonusTipiEnum {
	BOOLEAN (1, "BOOLEAN"), 
	CHAR (2, "CHAR"), 
	BYTE (3, "BYTE"), 
	SHORT (4, "SHORT"), 
	INTEGER (5, "INTEGER"), 
	LONG (6, "LONG"), 
	FLOAT (7, "FLOAT"), 
	DOUBLE (8, "DOUBLE"), 
	STRING (9, "STRING");

	private final int value;
	private final String label;

	DonusTipiEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static String getLabel(int val) {
		String label = "";

		for (DonusTipiEnum buyukKucukHarfEnum : DonusTipiEnum.values()) {
			if (buyukKucukHarfEnum.getValue() == val) {
				label = buyukKucukHarfEnum.getLabel();
				break;
			}
		}

		return label;
	}

}