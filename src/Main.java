import edu.greenriver.sdev333.ArrayList;
import edu.greenriver.sdev333.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        List<String> friends = new ArrayList<String>();
        System.out.println("Initial size is: "+friends.size());

        friends.add("Ehren");
        System.out.println("Size after added one: "+friends.size());

    }
}