
public class Main {

	public static void main(String[] args) {
		//HashNode<String, Integer> test = new HashNode<String, Integer>("cat", 1);
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		map.put(1, 2);
		map.put(11, 22);
		map.put(1, 100);
		map.put(2, 4);
		map.put(3, 6);
		map.put(4, 8);
		map.put(6, 12);
		map.put(7, 14);
		map.printTable();
		System.out.println(map.keySet().toString());
		map.containsKey(20);
		map.containsValue(78);
		map.remove(4);
		map.remove(20);
		map.remove(12);
		map.remove(20);
		map.remove(5);
		map.put(10, 30);
		map.get(12);
		map.printTable();

	}
	

}
