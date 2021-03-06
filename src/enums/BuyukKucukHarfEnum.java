package enums;

public enum BuyukKucukHarfEnum {
	KUCUK (1, "KUCUK"), 
	OLDUGU_GIBI (2, "OLDUGU GIBI"),
	BUYUK (3, "BUYUK");

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