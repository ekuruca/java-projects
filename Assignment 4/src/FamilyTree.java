import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class FamilyTree {
	public static void main(String[] args) throws IOException {
		
		//Variable decleration and initialization
		Scanner keyboard1 = new Scanner(new File("prob1-1.txt"));
		Scanner keyboard2 = new Scanner(new File ("prob1-2.txt"));
		Map<String, Node> people = new HashMap<String, Node>(); 
		Set<Node> roots = new HashSet<Node>();
		Queue<BinaryTree> trees = new LinkedList<BinaryTree>();
		String person, parent, children; char sex; 
		
		
		while (!keyboard1.hasNext("done")) {
			person = keyboard1.next();
			sex = keyboard1.next().charAt(0);
			
			Node node = new Node(person, sex);
			people.put(person, node);
			
		}
		
		//Debugging only
		for (Entry<String, Node> entry : people.entrySet()) {
            String k = entry.getKey();
            Node n = entry.getValue();
            System.out.println("Key: " + k + " |-------| " + n.toString());
        }
		
		while (!keyboard2.hasNext("done")) {
			parent = keyboard2.next();
			children = keyboard2.next();
			
			if (people.get(parent).getSex() == 'W') {
				people.get(children).setMom(people.get(parent));
				people.get(parent).setKid(people.get(children));
				
			} else if (people.get(parent).getSex() == 'M') {
				people.get(children).setDad(people.get(parent));
				people.get(parent).setKid(people.get(children));
			}
			
			
		}
		
		//Debugging only
		for (Entry<String, Node> entry : people.entrySet()) {
            String k = entry.getKey();
            Node n = entry.getValue();
            System.out.println(k + "  -->>  " + n.getKid());
        }
		
		for (Node n : people.values()) {
			if (n.getKid() == null) {
				roots.add(n);
			}
		}
		
		//Debugging only
		for (Node n : roots) {
			System.out.println(n.getName());
		}
		
		for (Node n : roots) {
			BinaryTree t = new BinaryTree(n);
			trees.add(t);
		}
				
		//Debugging only 
		for (BinaryTree t : trees) {
			System.out.println(t.getRoot().getName());
		}
		
		
		
		
		
		
		keyboard1.close();
		keyboard2.close();
		
	}
		
	
	
	
	public static class Node {
		private String name;
		private char sex;
		private Node mom;
		private Node dad;
		private Node kid;
		
		public Node(String name, char sex){
			this.name = name;
			this.sex = sex;
			mom = null;
			dad = null;
			kid = null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public char getSex() {
			return sex;
		}

		public void setSex(char sex) {
			this.sex = sex;
		}

		public Node getMom() {
			return mom;
		}

		public void setMom(Node mom) {
			this.mom = mom;
		}

		public Node getDad() {
			return dad;
		}

		public void setDad(Node dad) {
			this.dad = dad;
		}

		public Node getKid() {
			return kid;
		}

		public void setKid(Node kid) {
			this.kid = kid;
		}
	} 
	
	public static class BinaryTree { 
	    private Node root; 
	  
	    BinaryTree(Node node) { 
	        root = node; 
	    } 
	    
	    public Node getRoot() {
			return root;
		}

		public void setRoot(Node root) {
			this.root = root;
		}
	    
	   

	}
	
}
