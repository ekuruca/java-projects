import java.io.File;
import java.io.IOException;
import java.util.*;

public class FamilyTree {
	public static void main(String[] args) throws IOException {
		
		//Variable decleration and initialization
		Scanner keyboard1 = new Scanner(new File("prob1-1.txt"));
		Scanner keyboard2 = new Scanner(new File ("prob1-2.txt"));
		Map<String, Character> people = new HashMap<String, Character>();
		List<BinaryTree> binaryTrees = new ArrayList<BinaryTree>();
		List<String> storedParents = new ArrayList<String>();
		List<String> storedChildrens = new ArrayList<String>();
		String name, parent, children; char sex; int countP = 0, countC = 0;
		
		
		while (!keyboard1.hasNext("done")) {
			name = keyboard1.next();
			sex = keyboard1.next().charAt(0);
			people.put(name, sex);
			
		}
		
		//Debugging only
		for (Map.Entry<String, Character> entry : people.entrySet()) {
            String k = entry.getKey();
            char v = entry.getValue();
            System.out.println("Key: " + k + ", Value: " + v);
        }
		
		while (!keyboard2.hasNext("done")) {
			countP = 0;
			countC = 0;
			parent = keyboard2.next();
			children = keyboard2.next();
			
			storedParents.add(parent);
			storedChildrens.add(children);
			
			for (String str1 : storedParents) {
				if (children.equals(str1)) {
					countP++;
				}
			}
			
			for (String str1 : storedChildrens) {
				if (children.equals(str1)) {
					countC++;
				}
			}
			
			System.out.println("countP: " + countP + " countC: " + countC);
			
			if (countP == 0 && countC < 2) {
				BinaryTree tree = new BinaryTree(children, people.get(children));
				tree.insert(tree.getRoot(), parent, people.get(parent));
				binaryTrees.add(tree);
				
			} else if (countP == 1) {
				for (int i = 0; i < binaryTrees.size(); i++) {
					if (binaryTrees.get(i).find(children) == true) {
						binaryTrees.get(i).insert(binaryTrees.get(i).getRoot(), parent, people.get(parent));
					}
				}
			} else {
				for (BinaryTree t : binaryTrees) {
					if (t.rootMatch(children)) {
						t.insert(t.getRoot(), parent, people.get(parent));
						
					}
				}
			} 
			
			
			
			
		}
		
				
		
		//Debugging only
		System.out.println(binaryTrees.size());
		for(BinaryTree t : binaryTrees) {
            System.out.println(t.getRoot().getName());
        }
		System.out.println();
		
		
		for (BinaryTree t : binaryTrees) {
			t.printInorder(t.getRoot());
			System.out.println();
		}
		
		
		
		
		
		
		
		keyboard1.close();
		keyboard2.close();
		
	}
		
	
	
	
	public static class Node {
		private String name;
		private char sex;
		protected Node left;
		protected Node right;
		
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
	    	if (root != null) {
	    		return root;
	    	}
	    	return null;
	    }

	    public void insert(Node node, String name, char sex) { 
	    	Queue<Node> q = new LinkedList<Node>(); 
	        q.add(node); 
	       
	        // Do level order traversal until we find 
	        // an empty place.  
	        while (!q.isEmpty()) { 
	            node = q.peek(); 
	            q.remove(); 
	       
	            if (node.left == null) { 
	                node.left = new Node(name, sex); 
	                break; 
	            } else
	                q.add(node.left); 
	       
	            if (node.right == null) { 
	                node.right = new Node(name, sex); 
	                break; 
	            } else
	                q.add(node.right); 
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
	    
	    public boolean find(String name) {
	    	return findRec(root, name);
	    }
	    
	    public boolean rootMatch(String name) {
	    	if (root.getName().equals(name)) {
	    		return true;
	    	}
	    	
	    	return false;
	    }
	   
	    private boolean findRec(Node node, String name) {  
            if (node.getName().equals(name)) {
            	return true;
            }
            
            if (node.left != null) {
            	return findRec(node.left, name);
            }
            
            if (node.right != null) {
            	return findRec(node.right, name);
            }
            
            return false;
	        
	    }

	    public void printInorder(Node node) { 
	        if (node == null) 
	            return; 
	  
	        /* first recur on left child */
	        printInorder(node.left); 
	  
	        /* then print the data of node */
	        System.out.print(node.getName() + " "); 
	  
	        /* now recur on right child */
	        printInorder(node.right); 
	    }
	      
	}
	
}
