package com.kata.constants;

/** OutputTypeListenersEnum class. */
public enum OutputTypeListenersEnum {
	/** MAIL. */
	MAIL("mail"),
	/** SMAIL. */
	SMAIL("smail"),
	/** FACEBOOK. */
	FACEBOOK("facebook"),
	/** TWITTER. */
	TWITTER("twitter");

	/** value. */
	private String value;

	/** constructor. */
	OutputTypeListenersEnum(String value) {
		this.value = value;
	}

	/** get value. */
	public String value() {
		return value;
	}

}
