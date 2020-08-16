package map;

public class SimpleHashMap<K, V> implements Map<K, V> {

	private Entry<K, V>[] vector;
	private static final float LOAD_FACTOR = 0.75f;
	public int size;
	public boolean rehashing;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default load factor (0.75).
	 */
	public SimpleHashMap() {
		vector = (Entry<K, V>[]) new Entry[16];
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		vector = (Entry<K, V>[]) new Entry[capacity];
	}

	 
	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < vector.length; i++) {
			while (vector[i] != null) {
				sb.append(vector[i]);
				sb.append(" ");
				vector[i] = vector[i].next;
			}
			sb.append("\n");
		}

		return sb.toString();

	}

	// Typ den viktigaste metoden. Använde abs efter modulu
	private int index(K key) {
		int index = key.hashCode() % vector.length;
		index = Math.abs(index);
		return index;
	}

	
	// den näst-viktigaste metoden, bara jämför med alla entry som har index index, returna null om den inte hittas. Kanske ska throwa men jaja
	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = vector[index];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}

	private void rehash() {
		rehashing = true; // Tror man ska göra så här.
		int newCapacity = vector.length * 2;
		Entry<K, V>[] oldVector = vector;
		vector = (Entry<K, V>[]) new Entry[newCapacity];
		for (int i = 0; i < oldVector.length; i++) {
			Entry<K, V> first = oldVector[i];
			while (first != null) {
				put(first.key, first.value);
				first = first.next;
			}
		}
		rehashing = false;
	}
	// Hjälpmetod för att veta när man ska rehasha
	private boolean isTooBig() {
		return ((float) size / vector.length) > LOAD_FACTOR;
	}

	@Override
	public V get(Object arg0) {
		// instanceof ??
		K key = (K) arg0;
		Entry<K, V> entry = find(index(key), key);
		if (entry != null) {
			return entry.getValue();
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		Entry<K, V> entry = find(index, key);
		// Ifall key redan finns så e det bara att ändra value på entryn.
		if (entry != null) {
			return entry.setValue(value);
		} else {
		// Annars måste vi skapa ett nytt Entry ( det är här parametrana på Entryn kommer in)	
			Entry<K, V> newEntry = new Entry<K, V>(key, value);
		// Så sätter vi in nya entryn i map, anting först lr i slutet av kejdan.
			if (vector[index] != null) {
				newEntry.next = vector[index];
				vector[index] = newEntry;
			} else {
				vector[index] = newEntry;
			}
			
		// Ifall den inte e för stor så bara öka size
			if (!rehashing) {
				size++;
			}
		// Ifall den e för stor rehasha	
			if (isTooBig()) {
				rehash();
			}
			return null;

		}

	}

	@Override
	public V remove(Object arg0) {
		//Testar ifall mapen e tom
		if (!isEmpty()) {
			K key = (K) arg0;
			int index = index(key);
			Entry<K, V> removeEntry = find(index, key);
			if (removeEntry != null) {
				// Första entryn i mapen;
				Entry<K, V> entry = vector[index];
				if (entry.getKey().equals(key)) {
					vector[index] = entry.next;
					size--;
					return entry.getValue();
				}
				//Ifall den finns längre ner i kedjan
				while (entry.next != null) {
					if (entry.next.getKey().equals(key)) {
						Entry<K, V> temp = entry.next;

						// Detta vi gjorde i labb 2
						entry.next = entry.next.next;
						size--;
						return temp.getValue();
					}
					entry = entry.next;
				}

			}
		}
		//Returnar null ifall den inte finns lr mapen e tom.
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;
		private Entry<K, V> next;

		// Antar man kan har parametra i konstruktorn, men vet i fan.
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {

			return value;
		}

		/*
		 * Returns old value
		 */
		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}

	}

}