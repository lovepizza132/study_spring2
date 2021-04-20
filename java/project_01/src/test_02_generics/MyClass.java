package test_02_generics;

public class MyClass <KeyType, ValueType> {
	private KeyType key;
	private ValueType value;

	public MyClass() {
	}
	
	public MyClass(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyType getKey() {
		return key;
	}
	
    public ValueType getValue() {
        return value;
    }
    
    public void setKey(KeyType key) {
    	this.key = key;
    }
    
    public void setValue(ValueType value) {
    	this.value = value;
    }

    public String toString() { 
        return "(" + key + ", " + value + ")";  
    }
}
