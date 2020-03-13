import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FamilyRoots {
		
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
	
	
	public static int ancestor(int count, Person p1, Person p2) {
		if (p1.mom != null && p2.mom != null) { 
			if (p1.mom.equals(p2.mom)) {
				count++;
			} else {
				count = ancestor(count, p1, p2.mom);
				count = ancestor(count, p1.mom, p2);
			}
			
		} else if (p1.dad != null && p2.dad != null) { 
			if (p1.dad.equals(p2.dad)) {
				count++;
			} else {
				count = ancestor(count, p1, p2.dad);
				count = ancestor(count, p1.dad, p2);
			}
		}
		
    	return count;
    }
	
	public static void main(String[] args) throws IOException {
		
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
				
				Person p1 = people.get(partner1);
				Person p2 = people.get(partner2);
				
				
				int i = ancestor(0, p1, p2);
				
				if (i == 0) {
					System.out.println(p1.name + " and " + p2.name + " do not share any known ancestors");
					
				} else {
					System.out.println(p1.name + " and " + p2.name + " share a common ancestor from " + i + " generations ago");
				}
			}			
		}
		
		keyboard.close();
	}
}
