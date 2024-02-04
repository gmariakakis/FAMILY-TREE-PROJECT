
package FamilyTree;
 
 
 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
 
 
public class FamilyTree {
   
    static int gender1,gender2;
    static    Map <String, Person> personList = new HashMap<String,Person>();
       
  
    public static void main(String[] args) {
        try{
                  File myfile = new File("FamilyTree/input.csv");
 
            Scanner inputFile = new Scanner(myfile);
      
            personList.clear();
            while(inputFile.hasNext())
            {
 
                String line = inputFile.nextLine();

               String[] arrOfStr = line.split(",");
for (int i = 0; i < arrOfStr.length; i++) {
    arrOfStr[i] = arrOfStr[i].trim();
}
        
        
            if(arrOfStr.length ==2)
            {
                personList.put(arrOfStr[0], new Person(arrOfStr[0],arrOfStr[1]));
            }
            else if(arrOfStr.length ==3)
            {
                if(arrOfStr[1].equals("father"))
                    personList.get(arrOfStr[2]).father=arrOfStr[0];
                else  if(arrOfStr[1].equals("mother"))
                    personList.get(arrOfStr[2]).mother=arrOfStr[0];
                else 
                    personList.get(arrOfStr[2]).spouse=arrOfStr[0];     
            }
        }
        }
        catch(Exception e)
        {
            System.out.println("File no exist");
        }
        System.out.println("Check the contents of the structure");
               personList.forEach((n,p)->System.out.println(n+","+p.gender));
                    
        Scanner keyb = new Scanner(System.in);
        String name1, name2;
     
        do
        {
            
            System.out.println("Enter name 1");
            name1 = keyb.next();
            if(name1.equals("STOP"))
                 break;
            System.out.println("Enter name 2");
            name2 = keyb.next();  
            if(name2.equals("STOP"))
                 break;
            
            check_gender(name1,name2);
             if(check_spouse(name1,name2,true))
                    continue;
            if(check_father(name1,name2,true))
                    continue;
            if( check_mother(name1,name2,true))
                continue;
             if (check_child(name1, name2, true)) 
                continue;
            if(check_sibling(name1,name2,true))
                continue;
                if(check_uncle_or_aunt(name1,name2,true))
                continue;

           if(check_cousins(name1,name2,true))
                    continue;

                if (check_grandfather(name1, name2, true)) 
                continue;
             if (check_grandmother(name1, name2, true))
                continue;
            if (check_grandson(name1, name2, true))
                continue;    
             if (check_granddaughter(name1, name2, true))
                continue;   
                if (check_niece_or_nephew(name1, name2, true)) 
                continue;
            System.out.println("not related");
         }while(true);
       }
    
   static boolean check_spouse(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
   
        return false;
    }

    if (person1.spouse != null && person1.spouse.equals(name2)) {
        String relationship = person1.gender.equals("man") ? "husband" : "wife";
        if (show) {
            System.out.println(name1 + " is the " + relationship + " of " + name2);
        }
        return true;
    }

    if (person2.spouse != null && person2.spouse.equals(name1)) {
        String relationship = person2.gender.equals("man") ? "husband" : "wife";
        if (show) {
            System.out.println(name2 + " is the " + relationship + " of " + name1);
        }
        return true;
    }

    return false;
}
    
    static boolean check_father(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
       
        return false;
    }


    if (person2.father != null && person2.father.equals(name1)) {
        if (show) {
            System.out.println(name1 + " is the father of " + name2);
        }
        return true;
    }

    return false;
}
    static void check_gender(String name1, String name2)
    {
        if (personList.get(name1).gender.equals("man"))
            gender1 = 1;
        else
            gender1 = 2;
        if (personList.get(name2).gender.equals("man"))
            gender2 = 1;
        else
            gender2 = 2;
    }
static boolean check_mother(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {

        return false;
    }

    if (person2.mother != null && person2.mother.equals(name1)) {
        if (show) {
            System.out.println(name1 + " is the mother of " + name2);
        }
        return true;
    }

    return false;
}
   static boolean check_child(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
     
        return false;
    }

    String childRelationship = person1.gender.equals("man") ? "son" : "daughter";

    if (person1.father != null && person1.father.equals(name2)) {
        if (show) {
            System.out.println(name1 + " is the " + childRelationship + " of " + name2);
        }
        return true;
    }

    if (person1.mother != null && person1.mother.equals(name2)) {
        if (show) {
            System.out.println(name1 + " is the " + childRelationship + " of " + name2);
        }
        return true;
    }

    return false;
}

     static boolean check_sibling(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
  
        return false;
    }

    String siblingRelationship = person1.gender.equals("man") ? "brother" : "sister";

    if ((person1.father != null && person2.father != null && person1.father.equals(person2.father)) ||
        (person1.mother != null && person2.mother != null && person1.mother.equals(person2.mother))) {
        if (show) {
            System.out.println(name1 + " is the " + siblingRelationship + " of " + name2);
        }
        return true;
    }

    return false;
}
        static boolean check_grandmother(String name1, String name2, boolean show) {
        Person person2 = personList.get(name2);
    if (person2 == null) {
        System.out.println("Name 2 (" + name2 + ") is not in the list.");
        return false;
    }

    String fatherOfName2 = person2.father;
    String motherOfName2 = person2.mother;


    // Check if name1 is the mother of name2's father
    if (fatherOfName2 != null && personList.containsKey(fatherOfName2) && name1.equals(personList.get(fatherOfName2).mother)) {
        if (show) System.out.println(name1 + " is the grandmother of " + name2);
        return true;
    }

    // Check if name1 is the mother of name2's mother
    if (motherOfName2 != null && personList.containsKey(motherOfName2) && name1.equals(personList.get(motherOfName2).mother)) {
        if (show) System.out.println(name1 + " is the grandmother of " + name2);
        return true;
    }

    return false;
}
static boolean check_grandfather(String name1, String name2, boolean show) {
  
    Person person2 = personList.get(name2);
    if (person2 == null) {
        System.out.println("Name 2 (" + name2 + ") is not in the list.");
        return false;
    }

    String fatherOfName2 = person2.father;
    String motherOfName2 = person2.mother;


    // Check if name1 is the father of name2's father
    if (fatherOfName2 != null && personList.containsKey(fatherOfName2) && name1.equals(personList.get(fatherOfName2).father)) {
        if (show) System.out.println(name1 + " is the grandfather of " + name2);
        return true;
    }

    // Check if name1 is the father of name2's mother
    if (motherOfName2 != null && personList.containsKey(motherOfName2) && name1.equals(personList.get(motherOfName2).father)) {
        if (show) System.out.println(name1 + " is the grandfather of " + name2);
        return true;
    }

    return false;
}
static boolean check_grandson(String name1, String name2, boolean show) {
 
    Person person1 = personList.get(name1);
    if (person1 == null || !person1.gender.equals("man")) {
       
        return false;
    }

    // Get parents of name1
    Person fatherOfName1 = personList.get(person1.father);
    Person motherOfName1 = personList.get(person1.mother);

    // Check paternal grandfather
    if (fatherOfName1 != null && fatherOfName1.father != null && fatherOfName1.father.equals(name2)) {
        if (show) System.out.println(name1 + " is the grandson of " + name2);
        return true;
    }

    // Check paternal grandmother
    if (fatherOfName1 != null && fatherOfName1.mother != null && fatherOfName1.mother.equals(name2)) {
        if (show) System.out.println(name1 + " is the grandson of " + name2);
        return true;
    }

    // Check maternal grandfather
    if (motherOfName1 != null && motherOfName1.father != null && motherOfName1.father.equals(name2)) {
        if (show) System.out.println(name1 + " is the grandson of " + name2);
        return true;
    }

    // Check maternal grandmother
    if (motherOfName1 != null && motherOfName1.mother != null && motherOfName1.mother.equals(name2)) {
        if (show) System.out.println(name1 + " is the grandson of " + name2);
        return true;
    }

    return false;
}

static boolean check_granddaughter(String name1, String name2, boolean show) {
  
    Person person1 = personList.get(name1);
     if (person1 == null || !person1.gender.equals("woman")) {
       
        return false;
    }

    // Check if name1's parents are the children of name2
    Person fatherOfName1 = personList.get(person1.father);
    Person motherOfName1 = personList.get(person1.mother);

    if (fatherOfName1 != null && (fatherOfName1.father != null && fatherOfName1.father.equals(name2)) || 
        (fatherOfName1.mother != null && fatherOfName1.mother.equals(name2))) {
        if (show) System.out.println(name1 + " is the granddaughter of " + name2);
        return true;
    }

    if (motherOfName1 != null && (motherOfName1.father != null && motherOfName1.father.equals(name2)) || 
        (motherOfName1.mother != null && motherOfName1.mother.equals(name2))) {
        if (show) System.out.println(name1 + " is the granddaughter of " + name2);
        return true;
    }

    return false;
}

static boolean check_cousins(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
       
        return false;
    }

    String fatherOfName1 = person1.father;
    String motherOfName1 = person1.mother;
    String fatherOfName2 = person2.father;
    String motherOfName2 = person2.mother;

    // Checking if the parents of name1 and name2 are siblings
    if ((fatherOfName1 != null && fatherOfName2 != null && check_sibling(fatherOfName1, fatherOfName2, false)) ||
        (fatherOfName1 != null && motherOfName2 != null && check_sibling(fatherOfName1, motherOfName2, false)) ||
        (motherOfName1 != null && fatherOfName2 != null && check_sibling(motherOfName1, fatherOfName2, false)) ||
        (motherOfName1 != null && motherOfName2 != null && check_sibling(motherOfName1, motherOfName2, false))) {
        if (show) {
            System.out.println(name1 + " and " + name2 + " are cousins");
        }
        return true;
    }

    return false;
}
     static boolean check_uncle_or_aunt(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
     
        return false;
    }

    String relation = person1.gender.equals("man") ? "uncle" : "aunt";
    String fatherOfName2 = person2.father;
    String motherOfName2 = person2.mother;

    // Check if name1 is the sibling of name2's parents
    if ((fatherOfName2 != null && check_sibling(name1, fatherOfName2, false)) ||
        (motherOfName2 != null && check_sibling(name1, motherOfName2, false))) {
        if (show) {
            System.out.println(name1 + " is the " + relation + " of " + name2);
        }
        return true;
    }

    return false;
}
 static boolean check_niece_or_nephew(String name1, String name2, boolean show) {
    Person person1 = personList.get(name1);
    Person person2 = personList.get(name2);

    if (person1 == null || person2 == null) {
       
        return false;
    }

    String relation = person1.gender.equals("man") ? "nephew" : "niece";
    String fatherOfName1 = person1.father;
    String motherOfName1 = person1.mother;

    // Check if name2 is the sibling of name1's parents
    if ((fatherOfName1 != null && check_sibling(fatherOfName1, name2, false)) ||
        (motherOfName1 != null && check_sibling(motherOfName1, name2, false))) {
        if (show) {
            System.out.println(name1 + " is the " + relation + " of " + name2);
        }
        return true;
    }

    return false;
}

    
}
 
 
 
 
 
class Person{
    String name;
    String gender;
    String father;
    String mother;
    String spouse;
    Person(String name, String gender)
    {
        this.name = name;
        this.gender = gender;
    }
}