import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class FamilyTree {
	public static void main(String[] args) throws IOException {
		
		//Variable decleration and initialization
		Scanner keyboard1 = new Scanner((System.in));
		Scanner keyboard2 = new Scanner((System.in));
		Map<String, Person> people = new HashMap<String, Person>(); 
		Map<Person, LinkedHashSet<Person>> links = new HashMap<Person, LinkedHashSet<Person>>();
		String name, parent, children; char sex; 
		
		
		while (!keyboard1.hasNext("done")) {
			name = keyboard1.next();
			sex = keyboard1.next().charAt(0);
			
			Person person = new Person(name, sex);
			people.put(name, person);
			
		}
		
		while (!keyboard2.hasNext("done")) {
			parent = keyboard2.next();
			children = keyboard2.next();
			
			if (people.get(parent).getSex() == 'W') {
				if (people.get(children).getMom() == null) {
					people.get(children).setMom(people.get(parent));
					people.get(parent).setKid(people.get(children));
				} else {
					people.get(children).setDad(people.get(parent));
				}
				
			} else if (people.get(parent).getSex() == 'M') {
				if (people.get(children).getDad() == null) {
					people.get(children).setDad(people.get(parent));
					people.get(parent).setKid(people.get(children));
				} else {
					people.get(children).setMom(people.get(parent));
				}
			}
			
		}
		
		for (Person p : people.values()) {
			if (p.getKid() == null) {
				LinkedHashSet<Person> set = new LinkedHashSet<Person>();
				set.add(p);
				links.put(p, set);
			}
		}
		
		for (Person root : links.keySet()) {
			for (Person p : people.values()) {
				if (links.get(root).contains(p.getKid()) && p.getSex() == 'W') {
					links.get(root).add(p);
				}
			}
		}
		
		
		for (Entry<Person, LinkedHashSet<Person>> entry : links.entrySet()) {
            Person k = entry.getKey();
            LinkedHashSet<Person> link = entry.getValue();
            ArrayList<String> familyTree = new ArrayList<String>();
            
            for (Person p : link) {
            	familyTree.add(p.getName());
            }
            
            Collections.reverse(familyTree);
            
            System.out.print(k.getName() + ": ");
            
            for (String s : familyTree) {
            	if (s.equals(familyTree.get(familyTree.size() - 1))) {
            		System.out.print(s);
            	} else {
            		System.out.print(s + " --> ");
            	}
            }
            
            System.out.println();
        }
		
		keyboard1.close();
		keyboard2.close();
		
	}
	
	public static class Person {
		private String name;
		private char sex;
		private Person mom;
		private Person dad;
		private Person kid;
		
		public Person(String name, char sex){
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

		public Person getMom() {
			return mom;
		}

		public void setMom(Person mom) {
			this.mom = mom;
		}

		public Person getDad() {
			return dad;
		}

		public void setDad(Person dad) {
			this.dad = dad;
		}

		public Person getKid() {
			return kid;
		}

		public void setKid(Person kid) {
			this.kid = kid;
		}
	} 
}
