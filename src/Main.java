import edu.greenriver.sdev333.*;

import java.util.ListIterator;

/**
 * main class that runs everything
 *
 * @author Jack Ruiz
 */
public class Main {


    private static void printIntList(List<Integer> l) {
        for(int i: l) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Our main method where we have our testing for our List
     * classes.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Create list of integers, adding 10 elements, 1 .. 10");
        //List<Integer> intList = new ArrayList<>();
        List<Integer> intList = new DoublyLinkedList<>();

        System.out.println("testing isEmpty on empty list, returns: " + intList.isEmpty());

        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        printIntList(intList);



        System.out.println("testing isEmpty on a list with 10 items, returns: " + intList.isEmpty());
        System.out.println();
        System.out.println("Testing get(0):, should return 1: " + intList.get(0));
        System.out.println();
        System.out.println("testing contains(), looking for int value 5");
        System.out.println("result of contains(Integer.valueOf(5)): " + intList.contains(Integer.valueOf(5)));
        System.out.println("testing contains(), looking for int value of 90");
        System.out.println("result of contains(Integer.valueOf(90)): " + intList.contains(Integer.valueOf(90)));
        System.out.println();

        System.out.println("Printing list while testing .iterator() ...");
        printIntList(intList);
        System.out.println();

        System.out.println("Adding 20 to list");
        intList.add(20);
        System.out.println("Size: " + intList.size());
        printIntList(intList);
        System.out.println();

        System.out.println("Setting index 8 (#9) to 90");
        intList.set(8, 90);
        System.out.println("Size: " + intList.size());
        printIntList(intList);
        System.out.println();

        System.out.println("Adding 40 at index 4");
        intList.add(4,40);
        System.out.println("Size: " + intList.size());
        printIntList(intList);
        System.out.println();

        System.out.println("Testing indexOf(item), index of 90 should be 9.");
        System.out.println(intList.indexOf(90));
        System.out.println();

        System.out.println("Testing indexOf(item), index of 3 should be 2.");
        System.out.println(intList.indexOf(3));
        System.out.println();

        System.out.println("Adding 5 to end of list for next test");
        intList.add(5);
        printIntList(intList);
        System.out.println();

        System.out.println("Finding last index of 5, should be 12");
        System.out.println("Calculated lastIndex of 5: " + intList.lastIndexOf(5));
        System.out.println("Finding last index of 40, should be 4");
        System.out.println("Calculated lastIndex of 40: " + intList.lastIndexOf(40));
        System.out.println();

        printIntList(intList);

        System.out.println("Testing remove(index), removing index 4 (#40)");
        intList.remove(4);
        printIntList(intList);
        System.out.println();

        List<Integer> newList = new DoublyLinkedList<>();
        newList.add(1);
        newList.add(8);
        System.out.println("Testing containsAll() with a sublist of 1 and 8, should return true");
        System.out.println("containsAll() result: " + intList.containsAll(newList));
        newList.add(999);
        System.out.println("Testing containsAll() with a sublist of 1, 8, and 999 should return false");
        System.out.println("containsAll() result: " + intList.containsAll(newList));
        printIntList(intList);
        System.out.println();

        System.out.println("Testing remove(item), removing #90");
        intList.remove(Integer.valueOf(90));


        printIntList(intList);
        System.out.println();

        System.out.println();
        System.out.println("Testing clear(), printed list");
        intList.clear();
        printIntList(intList);
        System.out.println();

        intList = new DoublyLinkedList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        System.out.println("Testing 2nd constructor w/ initial size parameter, should have 1..10 entered.");
        System.out.println("Note, no 2nd constructor for DoublyLinkedList, using original ... ");
        printIntList(intList);
        System.out.println();

        List<Integer> tempList = new DoublyLinkedList<>();
        tempList.add(50);
        tempList.add(51);
        tempList.add(52);
        tempList.add(53);
        tempList.add(54);
        tempList.add(55);
        System.out.println("Testing addAll(), adding a new collection that contains 50 ... 55 to list");
        intList.addAll(tempList);
        printIntList(intList);
        System.out.println();

        System.out.println("Testing equals() method, original list vs tempList (50..55), should be false");
        System.out.println("Result of intList.equals(tempList): " + intList.equals(tempList));
        System.out.println("Testing equals method, tempList (50..55) vs bList (50..55), should be true");
        List<Integer> bList = new DoublyLinkedList<>();
        bList.add(50);
        bList.add(51);
        bList.add(52);
        bList.add(53);
        bList.add(54);
        bList.add(55);
        System.out.println("Result of tempList.equals(bList): " + tempList.equals(bList));

        System.out.println();
        System.out.println("Original list again:");
        printIntList(intList);


        System.out.println("Testing ListIterator forward");
        ListIterator<Integer> myItr = intList.listIterator();
        while(myItr.hasNext()) {
            System.out.print(myItr.next() + " ");
        }
        System.out.println();

        System.out.println("Testing in reverse");
        while(myItr.hasPrevious()) {
            System.out.print(myItr.previous() + " ");
        }
        System.out.println();

        System.out.println();

        List<Integer> cList = new DoublyLinkedList<>();
        cList.add(1);
        cList.add(5);
        cList.add(9);
        cList.add(52);
        cList.add(55);
        cList.add(72);

        System.out.println("Will be removing cList from our original list, cList is:");
        printIntList(cList);
        System.out.println("Testing removeAll(collection), removing cList from intList.");
        intList.removeAll(cList);
        System.out.println("Resulting list: ");
        printIntList(intList);
        System.out.println();

        System.out.println("We are going to test retainAll(), but lets add items back to our original list.");
        System.out.println("Original list again:");
        intList.addAll(cList);

        cList.add(99);
        printIntList(intList);
        System.out.println("cList again is (added 99 to this list):");
        printIntList(cList);
        System.out.println("We are going to retain only the items in cList that are in our original list");
        intList.retainAll(cList);
        System.out.println("Resulting original list:");
        printIntList(intList);

        System.out.println("Adding 1..10 to original list again");
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        printIntList(intList);

        System.out.println("Testing ListIterator forward");
        myItr = intList.listIterator();
        while(myItr.hasNext()) {
            System.out.print(myItr.next() + " ");
        }
        System.out.println();

        System.out.println("Testing in reverse");
        while(myItr.hasPrevious()) {
            System.out.print(myItr.previous() + " ");
        }
        System.out.println();

    }
}
