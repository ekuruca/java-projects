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
	
	public static void main(String[] args) {
		
		//Variable decleration and initialization
		Scanner keyboard = new Scanner(System.in);
		Map<String, Person> people = new HashMap<String, Person>();
		String name, parent, children, partner1, partner2; char sex; boolean stop = true;
		
		//Adds to the first HashMap
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
		
		stop = true;
		
		//Determines whether partners should partner or not
		while (stop) {
			partner1 = keyboard.next();
			
			if (partner1.equals("done")) {
				stop = false;
			} else {
				
				partner2 = keyboard.next();
				
				if (people.get(partner1).kid != null && people.get(partner2) != null) {
					if (people.get(partner1).kid.equals(people.get(partner2))) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not closely related");
					}
					
				} else if (people.get(partner1) != null && people.get(partner2).kid != null) {
					if (people.get(partner1).equals(people.get(partner2).kid)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
					} else {
						System.out.println(partner1 + " and " + partner2 + " are not closely related");
					}
					
				} else if  (people.get(partner1).mom != null && people.get(partner2).mom != null) {
					if (people.get(partner1).mom.equals(people.get(partner2).mom)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
						
					} else {
						if (people.get(partner1).mom.mom.equals(people.get(partner2).mom.mom)) {
							System.out.println(partner1 + " and " + partner2 + " are too closely related");
						} else {
							System.out.println(partner1 + " and " + partner2 + " are not closely related");
						}
				
					}
					
				} else if (people.get(partner1).dad != null && people.get(partner2).dad != null) {
					if (people.get(partner1).dad.equals(people.get(partner2).dad)) {
						System.out.println(partner1 + " and " + partner2 + " are too closely related");
						
					} else {
						if (people.get(partner1).dad.dad.equals(people.get(partner2).dad.dad)) {
							System.out.println(partner1 + " and " + partner2 + " are too closely related");
						} else {
							System.out.println(partner1 + " and " + partner2 + " are not closely related");
						}
						
					}
					
				}  
			}
		}
		
		keyboard.close();
	}
}
