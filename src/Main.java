import edu.greenriver.sdev333.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        List<String> friends = new ArrayList<String>();
        List<String> friends2 = new ArrayList<String>();
        System.out.println("initial size is " + friends.size());

        friends.add("Jess");
        friends.add("Tina");
        friends.add("Josh");
        friends.add("Susan");
        friends.add("Tyler");
        friends.add("Usman");
        friends.add("Dee");
        friends.add("Rose");
        friends.add("Blanche");
        friends.add("Dorothy");
        friends.add("Sophia");
        friends.add(2, "Wednesday");
        friends.add("Tina");
        friends2.add("daw");
        friends2.add("hi");
        friends2.add("Tina");
        friends2.add("Rose");
        System.out.println("size is now " + friends.size());
        System.out.println(friends.lastIndexOf("Tina"));

        //for (int i = 0; i < friends.size(); i++) {
        //    System.out.println(friends.get(i));
        //}

        // above: import java.util.Iterator;
        Iterator<String> itr = friends.iterator();
        while (itr.hasNext()) {
            String name = itr.next();
            System.out.println(name);
        }
        friends.addAll(friends2);
        System.out.println("size is now " +friends.size());
        friends.removeAll(friends2);
        System.out.println("size is now " +friends.size());
        System.out.println(friends2.size());
        Iterator<String> itrs = friends2.iterator();
        while (itrs.hasNext()) {
            String name = itrs.next();
            System.out.println(name);
        }
       //for (String name : friends) {
        //    System.out.println(name);
        //}





    }
}