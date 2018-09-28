package com.cict.offical.network.utils;

public enum CODE {
    OK(200),ERROR(500);
	
	private int value;

	private CODE(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
