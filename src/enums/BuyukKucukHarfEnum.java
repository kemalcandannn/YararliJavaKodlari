package enums;

public enum BuyukKucukHarfEnum {
	KUCUK (1, "K���k"), 
	OLDUGU_GIBI (2, "Oldu�u Gibi"),
	BUYUK (3, "B�y�k");

	private final int value;
	private final String label;

	BuyukKucukHarfEnum(int value, String label) {
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

		for (BuyukKucukHarfEnum buyukKucukHarfEnum : BuyukKucukHarfEnum.values()) {
			if (buyukKucukHarfEnum.getValue() == val) {
				label = buyukKucukHarfEnum.getLabel();
				break;
			}
		}

		return label;
	}

}