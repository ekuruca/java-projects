import java.io.File;
import java.io.IOException;
import java.util.*;
public class FamilyTree {
	public static void main(String[] args) throws IOException {
		
		Scanner keyboard1 = new Scanner(new File("prob1-1.txt"));
		Scanner keyboard2 = new Scanner(new File ("prob1-2.txt"));
		Map<String, Character> people = new HashMap<String, Character>();
		List<BinaryTree> binaryTrees = new ArrayList<BinaryTree>();
		String name, parent; char sex;
		int count = 0;
		
		while (!keyboard1.hasNext("done")) {
			name = keyboard1.next();
			sex = keyboard1.next().charAt(0);
			people.put(name, sex);
			
		}
		
		for (Map.Entry<String, Character> entry : people.entrySet()) {
            String k = entry.getKey();
            char v = entry.getValue();
            System.out.println("Key: " + k + ", Value: " + v);
        }
		
		while (!keyboard2.hasNext("done")) {
			parent = keyboard2.next();
			name = keyboard2.next();
			
			for (BinaryTree t : binaryTrees) {
				if (name.equals(t.getRoot().getName())) {
					t.insert(parent, people.get(parent));
					count++;
				} 
			}
			
			if (count == 0) {
				BinaryTree tree = new BinaryTree(name, people.get(name));
				tree.insert(parent, people.get(parent));
				binaryTrees.add(tree);
			}
			
		}
		
		System.out.println();
		for(BinaryTree t : binaryTrees) {
            System.out.println(t.getRoot().getName());
        }
		
		StringBuilder string = new StringBuilder();
		
		for (BinaryTree t : binaryTrees) {
			System.out.println(t.printOrder(t.getRoot(), string));
		}
		
		
		
		
		
		keyboard1.close();
		keyboard2.close();
	}
	
	public static class Node{
		private String name;
		private char sex;
		Node left;
		Node right;	
		
		public Node(String name, char sex){
			this.name = name;
			this.sex = sex;
			left = null;
			right = null;
		}
		
		public String getName() {
			return name;
		}
		
		public char getSex() {
			return sex;
		}
	} 
	  
	public static class BinaryTree { 
	    private Node root; 
	  
	    BinaryTree(String name, char sex) { 
	        root = new Node(name, sex); 
	    } 
	  
	    BinaryTree() { 
	        root = null; 
	    } 
	    
	    
	    public Node getRoot() {
	    	return root;
	    }
	    
	    public void insert(String name, char sex ) {
	    	Node newNode = new Node(name, sex);
	    	
	    	if (root == null) {
	    		root = newNode;
	    		return;
	    	}
	    	
	    	Node current = root;
	    	Node parent = null;
	    	
	    	while (true) {
	    		parent = current;
	    		
	    		if (sex == 'W') {
	    			current = current.left;
	    			
	    			if (current == null) {
	    				parent.left = newNode;
	    				return;
	    				
	    			}
	    			
	    		} else if (sex == 'M') {
	    			current = current.right;
	    			
	    			if (current == null) {
	    				parent.right = newNode;
	    				return;
	    				
	    			}
	    		}
	    	}
		}
	    
	    public int depth(Node root) {
	    	if (root == null) {
	    		return 0;
	    	} else {
	    		int left= depth(root.left);
	    		int right = depth(root.right);
	    
		    	if (left > right) {
		    		return (left + 1);
		    	} else {
		    		return (right+ 1);
		    	}
	    	}
	    }
	    
	    public StringBuilder printOrder(Node node, StringBuilder string) {
	    	if (node == null) {
	    		return null;
	    	}
	    	
	    	if (node == root) {
	    		string.append(node.getName() + ": ");
	    	}
	    	
	    	printOrder(node.left, string);
	    	string.append(node.getName() + "-> ");
	    	
	    	return string;
	    }
	    
	    
	    
	}
}
