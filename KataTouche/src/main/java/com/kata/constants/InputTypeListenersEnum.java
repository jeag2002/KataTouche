package com.kata.constants;

/** InputTypeListenersEnum class. */
public enum InputTypeListenersEnum {
	/** FILE.*/
	FILE("file"), 
	/** DB.*/
	DB("db"),
	/** REST.*/
	REST("rest");
	
	/** value. */
	private String value;

	/** inputTypeListenersEnum. */
	InputTypeListenersEnum(String value) {
        this.value = value;
    }

	/** getValue. */
    public String value() {
        return value;
    }
    
    
	
}
