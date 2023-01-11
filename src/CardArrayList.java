import java.util.*;

/**The CardArrayList object contains an array of Cards and povides methods for manipulating that array
 * While the actual array may contain null values at indexes greater than the size of the Collection of Cards it contains,
 * the public methods provided here give access to only the properties of the portion of the array that holds that Collection
 *
 * @author Adam Winter
 * @version
 */
public class CardArrayList implements CardList
{

    private Card[] ca;   //card array
    private int size;

    /** Constructor: a new CardArrayList with size 0 (zero) but an internal array with length 10
     *
     */
    public CardArrayList(){
        this.ca = new Card[10];
        this.size = 0;
    }

    /**Constructor: a new CardArrayList with size 0 (zero) but an internal array with length equal to the argument
     *
     *  @param x The size to intialize the internal array
     *  @throws IllegalArgumentException The size of the array must be at least zero.
     *
     */
    public CardArrayList(int x) throws IllegalArgumentException{
        if(x < 0)throw new IllegalArgumentException("The size of the array must be at least zero.");
        this.ca = new Card[x];
        this.size = 0;
    }


    /** This returns a representation of the list from index 0 to the final index.
     *
     * @return The string visulation of the list.
     */
    public String toString(){
        String strCards = "[0: ";
        for(int i = 0; i < this.size; i++){
            if(i == 0){
                strCards = strCards + ca[i].toString();
            }else{
                strCards = strCards + "," + ca[i].toString();
            }
        }//end for
        strCards = strCards + " :" + this.size + "]";
        return strCards;
    }//end toString()

    /** This method returns the current number of elements in the list.
     *  Note, this is not the length of the interal array, but the number of Cards in the collection
     *
     *  @return the size of the list as an integer
     */
    public int size(){
        return this.size;
    }


    /** This method adds a card to the end of the list in the first available spot.
     *  The size of the internal array is checked first, and expanded if there is not room
     *
     * @param c the Card object to be added.
     */
    public void add(Card c){
        if(!this.isRoom()){
            this.expand();
            this.ca[this.size] = c;
            this.size++;
        }else{
            this.ca[this.size] = c;
            this.size++;
        }
    }


    /** This adds a card to the indicated location, per the given argument, sliding all other elements over one.
     *
     * @param loc the desired index of the card to be added.
     * @param c the Card object to be added.
     * @throws IndexOutOfBoundsException if the loc is outside the current list.
     */
    public void add(int loc, Card c) throws IndexOutOfBoundsException {
        if(loc < 0 || loc > this.size) throw new IndexOutOfBoundsException("Index out of bounds in call to add(int, Card)");
        if(!this.isRoom()){
            this.expand();
            for(int i = this.size; i >= loc + 1; i--){
                this.ca[i] = this.ca[i - 1];
            }
            this.ca[loc] = c;
            this.size++;
        }else{
            for(int i = this.size; i >= loc + 1; i--){
                this.ca[i] = this.ca[i - 1];
            }
            this.ca[loc] = c;
            this.size++;
        }
    }//end add


    /** This method removes the last element from the list, and returns it.
     *
     * @return The card object removed from the list.
     * @throws IndexOutOfBoundsException if the list is empty.
     */
    public Card remove() throws IndexOutOfBoundsException {
        if(this.size == 0)throw new IndexOutOfBoundsException("The list is empty, but you called remove().");
        this.size--;
        return this.ca[this.size];
    }

    /** This removes the identified card from the list and return it.
     *  The remain cards to the right then slide the the left
     *
     * @param loc the index of the card to be removed.
     * @return The card object removed from the list.
     * @throws IndexOutOfBoundsException if the loc is outside the current array size.
     */
    public Card remove(int loc) throws IndexOutOfBoundsException {
        if(loc < 0 || loc >= this.size) throw new IndexOutOfBoundsException("Index out of bounds in call to remove(int).");
        Card requested = this.ca[loc];
        for (int i = loc; i < this.size - 1; i++){
            this.ca[i] = this.ca[i + 1];
        }
        this.size--;
        return requested;
    }

    /** This returns the element from the list that is the index provided as the argument
     *
     * @param i The index of the desired card.
     * @return The card object locatated in index x from the list.
     * @throws IndexOutOfBoundsException if the i value is outside the range of the list
     */
    public Card get(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= this.size) throw new IndexOutOfBoundsException("Index is out of bounds in call to get().");
        return this.ca[i];
    }//end get

    /** Returns the index of specified Card object
     *
     * @param c Card object
     * @return index of Card otherwise -1 if not found
     */
    public int indexOf(Card c){
        for (int i = 0; i < size; i++){
            if(this.ca[i].equals(c)){
                return i;
            }
        }
        return -1;
    }

    /** Sort the items in the list from smallest to largest
     *
     */
    public void sort(){
        if(this.size < 2)return;
        Card[] exact = new Card[this.size];       //learned this lesson the long and hard way
        for(int i = 0; i < this.size; i++){       // Before the list can be sorted, the null positions in the array are removed
            exact[i] = this.ca[i];                 // This is done by copying all the Cards into an array of the exact size needed
        }
        this.ca = exact;                          //The main/internal array that hold the collection is then set to the new array
        mergeSort(this.ca);                       //And then it is sorted
    }

    //standard mergeSort using recursion
    private void mergeSort(Card[] ca){
        if(ca.length > 1){
            Card[] left = Arrays.copyOfRange(ca, 0, ca.length/2);
            Card[] right = Arrays.copyOfRange(ca, ca.length/2, ca.length);
            mergeSort(left);
            mergeSort(right);
            merge(ca, left, right);
        }//end if
    }//end mergeSort

    //standard merge for a mergeSort
    private void merge(Card[] result, Card[] left, Card[] right){
        int i1 = 0;
        int i2 = 0;
        for(int i = 0; i < result.length; i++){            //started by taking this directly from the book
            if(i2 >= right.length) {
                result[i] = left[i1];
                i1++;
            }else if(i1 >= left.length) {                   //split this into pieces while troubleshooting the problem I was having before I realized
                result[i] = right[i2];                       //that the array needed to be trimmed of the null values to the exact size
                i2++;                                        //otherwise you end up trying to sort null values
            }else if(left[i1].compareTo(right[i2]) < 0){    //this is the line where the null valus first become a problem
                result[i] = left[i1];
                i1++;
            }else{
                result[i] = right[i2];
                i2++;
            }
        }//end for
    }//end merge

    /** Not such a weak shuffle algorithm; Shuffles the collection of Cards
     *
     */
    public void shuffle(){
        CardList shuffledList = new CardArrayList(this.size);    //create a new CardArrayList
        Random rand = new Random();
        for(int i = this.size - 1; i >= 0; i--){
            shuffledList.add(this.remove(rand.nextInt(i + 1)));   //randomly remove cards from the original List and add them to the new one
        }
        for(int i = 0; i < shuffledList.size(); i++){            //put all the cards back into the original List in the new shuffled order
            this.add(shuffledList.get(i));
        }
    }

    /** Empty the list of all items.
     *
     */
    public void clear(){
        this.ca = new Card[10];
        this.size = 0;
    }

    /** Returns true if the array is larger than the number of cards in it
     *
     */
    public boolean isRoom(){
        return this.ca.length > this.size;
    }

    /** Copies the contents of the internal array to a new array this is twice as large
     *   This sets the internal array to that new array
     *
     */
    public void expand(){
        Card[] caNew = new Card[2*this.ca.length];
        for(int i=0; i < this.size; i++){
            caNew[i] = this.ca[i];
        }
        this.ca = caNew;
    }


}
