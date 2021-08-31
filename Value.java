package com.kewal;

import java.io.Serializable;
import java.time.Instant;

public class Value implements Serializable {
	
	private static final long serialVersionUID = -2179691118817504328L;
	
	public int value;
	
	public Instant time;
	
	public Value(int value, Instant time) {
		this.value = value;
		this.time = time;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}
}
