import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FamilyJoins {
		
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
			
		public void push(Person person) { 
	        Node node = new Node(person); 
	  
	        node.next = head; 
	        node.previous = null; 
	  
	        if (head != null) {
	            head.previous = node; 
	        }
	  
	        head = node; 
	    }
		  
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
	  
	    public void printlist(Node node, Person person) { 
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
		String name, parent, children, partner1, partner2; char sex; boolean stop = true;
		
		while (stop) {
			name = keyboard.next();
			
			if (name.equals("done")) {
				stop = false;
			} else {
				sex = keyboard.next().charAt(0);
				
				Person person = new Person(name, sex);
				people.put(name, person);
			}
		}
		
		
		stop = true;
		
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
		
		stop = true;
		
		while (stop) {
			partner1 = keyboard.next();
			
			if (partner1.equals("done")) {
				stop = false;
			} else {
				
				partner2 = keyboard.next();
				
				if (people.get(partner1).mom != null && people.get(partner2).mom != null) {
					if (people.get(partner1).mom.equals(people.get(partner2).mom)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
					
				} else if (people.get(partner1).dad != null && people.get(partner2).dad != null) {
					if (people.get(partner1).dad.equals(people.get(partner2).dad)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
					
				} else if (people.get(partner1).mom.mom != null && people.get(partner2).mom.mom != null) {
					if (people.get(partner1).mom.mom.equals(people.get(partner2).mom.mom)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
					
				} else if (people.get(partner1).mom.dad != null && people.get(partner2).mom.dad != null) {
					if (people.get(partner1).mom.dad.equals(people.get(partner2).mom.dad)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
					
				} else if (people.get(partner1).dad.mom != null && people.get(partner2).dad.mom != null) {
					if (people.get(partner1).dad.mom.equals(people.get(partner2).dad.mom)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
					
				} else if (people.get(partner1).dad.dad != null && people.get(partner2).dad.dad != null) {
					if (people.get(partner1).dad.dad.equals(people.get(partner2).dad.dad)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not too closely related");
					}
				}
			}
		}
		
		
		
		keyboard.close();
		
		
	}

}
