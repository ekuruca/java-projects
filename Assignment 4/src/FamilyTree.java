import java.util.*;

public class FamilyTree {
	
	public static class Person {
		String name;
		char sex;
		Person mom;
		Person dad;
		Person kid;
		
		public Person(String name, char sex){
			this.name = name;
			this.sex = sex;
			mom = null;
			dad = null;
			kid = null;
		}
	} 
	
	//Doubly Linked List class
	public static class Dll {
		Node head;
		
		public class Node {
			Person person;
			Node next;
			Node previous;
			
			Node(Person person) {
				this.person = person;
			}
			
		}
		
		//Pushes Person to the front
		public void push(Person person) { 
	        Node node = new Node(person); 
	  
	        node.next = head; 
	        node.previous = null; 
	  
	        if (head != null) {
	            head.previous = node; 
	        }
	  
	        head = node; 
	    }
		 
		//Adds Person at the end (tail)
	    public void append(Person person) { 
	        Node node = new Node(person); 
	        Node last = head;
	        node.next = null; 
	  
	        if (head == null) { 
	            node.previous = null; 
	            head = node; 
	            return; 
	        } 
	  
	        while (last.next != null) {
	            last = last.next; 
	        }
	  
	        last.next = node; 
	        node.previous = last; 
	    } 
	  
	    //Prints the doubly linked list
	    public void print(Node node, Person person) { 
	        System.out.print(person.name + ": ");
	        while (node != null) { 
	        	if (node.equals(head)) {
	        		System.out.print( node.person.name);
	        		node = node.next;
	        	} else {
	        		System.out.print(" -> " + node.person.name);
	        		node = node.next; 
	        	}
	        } 
	        
	        System.out.println(); 
	    }
	}
	
		
	public static void main(String[] args) {
		
		//Variable decleration and initialization
		Scanner keyboard = new Scanner(System.in);
		Map<String, Person> people = new HashMap<String, Person>();
		Map<Person, Dll> links = new HashMap<Person, Dll>();
		Queue<Person> insertion = new LinkedList<Person>();
		HashSet<Person> roots = new HashSet<Person>();
		HashSet<Person> parents = new HashSet<Person>();
		String name, parent, children; char sex; boolean stop = true;
		
		//Adds to the first HashMap
		while (stop) {
			name = keyboard.next();
			
			if (name.equals("done")) {
				stop = false;
			} else {
				sex = keyboard.next().charAt(0);
				
				Person person = new Person(name, sex);
				people.put(name, person);
				insertion.add(person); //Keeps track of insertion order
			}
		}
		
		
		stop = true;
		
		//Assings parent informations
		while (stop) {
			parent = keyboard.next();
			
			if (parent.equals("done")) {
				stop = false;
				
			} else {
				children = keyboard.next();
				
				if (people.get(parent).sex == 'W') {
					if (people.get(children).mom == null) {
						people.get(children).mom = people.get(parent);
						people.get(parent).kid = people.get(children);
					} else {
						people.get(children).dad = people.get(parent);
						people.get(parent).kid = people.get(parent);
					}
					
				} else if (people.get(parent).sex == 'M') {
					if (people.get(children).dad == null) {
						people.get(children).dad = people.get(parent);
						people.get(parent).kid = people.get(children);
					} else {
						people.get(children).mom = people.get(parent);
						people.get(parent).kid = people.get(parent);
					}
				}
			}
		}
		
		//If Person doesn't have a kid, adds them to the root HashSet
		//Else adds them to the parent HashSet
		for (String s : people.keySet()) {
			if (people.get(s).kid == null) {
				roots.add(people.get(s));
			} else {
				parents.add(people.get(s));
			}
		}
		
		
		//Adds to the second HashMap where the key points to a doubly linked list
		for (Person p : roots) {
			links.put(p, new Dll());
		}
		
		
		//Adds parents to the doubly linked list
		for (Person l : insertion) { 
			if (links.containsKey(l)) {
				for (Person p : roots) {
					if (p.equals(l)) {
						links.get(l).append(p);
						links.get(l).push(p.mom);
					}
				}
				
				for (Person p : parents) {
					if (links.get(l).head.person.mom != null) {
						if (links.get(l).head.person.mom.equals(p)) {
							links.get(l).push(p);
						}
					} else {
						break;
					}
				}
				
				if (parents.isEmpty()) {
					System.out.println(l.name + ": " + l.name);
				} else {
					links.get(l).print(links.get(l).head, l);
				}
			}
		}
		
		keyboard.close();
	}	
}
