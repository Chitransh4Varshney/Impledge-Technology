// used to store a pair of original word and suffix
public class Pair<T> {
	
	private T originalWord;
	private T suffix;
	
	public Pair(T first, T second) {
		this.originalWord = originalWord;
		this.suffix = suffix;
	}
	
	// return first element
	public T first() {
		return originalWord;
	}
	
	// return second element
	public T second() {
		return suffix;
	}
}