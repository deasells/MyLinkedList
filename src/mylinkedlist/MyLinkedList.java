package mylinkedlist;

/*
 * This application is a singly linked-list that will add, remove,
 * insert and print from a singly linked-list. 
 *
 * @creator DeAndre Sellers
 * @created 02018.10.31
 * @deasells
 */

class Node {

   // instance variables...
   private Object item;
   private Node next;

   // constructor... 
   public Node(Object x) { item = x; next = null; }

   // getter/setter methods...
   public Object getItem() { return item; }
   public Node getNext() { return next; }
   public void setItem(Object item) { this.item = item; }
   public void setNext(Node n) { next = n; }

   // return a String object representation of "this" Node object...
   public String toString() {
      return item + " (next is " + (next == null ? "" : "not ") + "null)";
   }

}

public class MyLinkedList {

   private Node head = null;     // nth(1) is the head Node object
   private int size = 0;         // size == 0 implies empty linked-list

   // return the number of items in "this" linked-list...
   public int getSize() { return size; }

   // return the head Node for "this" linked-list...
   public Node getHead() { return head; }

   // set the size of "this" linked-list to zero...
   public void clear() { size = 0; head = null; }

   /**
    * Prints "this" linked-list starting at the Node object parameter.
    * The print format:  [ followed by a space followed the elements
    * separated by a space followed by ]  Example:  [ 0 1 2 3 4 1 ]
    *
    * @param Node to begin printing from
    * @return this MyLinkedList
    */
   public MyLinkedList print(Node n) {
       System.out.print("[ ");
       while (n != null) {
           System.out.print(n.getItem() + " ");
           n = n.getNext();
       }
       System.out.println("]");
       return this;
   }

   /**
    * Removes an item from this MyLinkedList object.
    *
    * @param item to remove from this MyLinkedList object
    * @return true if item removed; else return false
    */
   public boolean remove(Object item) {
       if (head == null)
           return false;
       if (head.getItem() == item) {
          head = head.getNext();
          size--;
          return true;
       }
       
       Node pointer1 = head;
       Node pointer2 = head.getNext();
       while (pointer2 != null) {
           if (pointer2.getItem().equals(item)) {
               pointer1.setNext(pointer2.getNext());
               size--;
               return true;
           } 
           pointer1 = pointer1.getNext();
           pointer2 = pointer2.getNext();
       }
       return false;
   }

   /**
    * Inserts an item into this MyLinkList object.
    *
    * Insert examples:
    * [7 8 9] insert(5, 0) does nothing (return false)
    * [7 8 9] insert(5, 1) results in [5 7 8 9]
    * [7 8 9] insert(5, 2) results in [7 5 8 9]
    * [7 8 9] insert(5, 3) results in [7 8 5 9]
    * [7 8 9] insert(5, 4) does nothing (return false)
    *
    * @param item to insert into this MyLinkedList object
    * @param index where item is to be inserted
    * @return true if item inserted; else return false
    */
   public boolean insert(Object item, int at_i) {
       Node newNode = new Node(item);
       Node pointer1 = head;
       
       if (at_i < 1 || at_i > size) 
           return false;
       if (at_i == 1) {
           newNode.setNext(head);
           head = newNode;
           size++;
       } else {
           for (int i = 1; i < at_i - 1; i++) {
               pointer1 = pointer1.getNext();
           }
           newNode.setNext(pointer1.getNext());
           pointer1.setNext(newNode);
           size++;
       }
       return true;
   }

   /**
    * Adds an item to the list.
    * 
    * @param item to insert into the list
    * @return modified list
    *
    */
   public MyLinkedList add(Object item) {
      size++;
      Node n = new Node(item);
      if (head == null) 
         head = n; 
      else {
         Node t = head;
         while (t.getNext() != null) 
            t = t.getNext();
         t.setNext(n);
      }
      return this;
   }

   /*
    * test class MyLinkedList...
    */
   public static void main(String[] argv) {

      MyLinkedList list = new MyLinkedList();
      Integer[] z = { new Integer(0), new Integer(1), new Integer(2),
                      new Integer(3), new Integer(4), new Integer(1), };
      for (int i = 0; i < z.length; i++)
         list.add(z[i]);
      System.out.println("test remove()...");
      System.out.print("linked-list: ");              
      list.print(list.head);
      remove(list, z[1]);
      remove(list, z[0]);
      remove(list, z[1]);
      remove(list, z[4]);
      remove(list, z[2]);
      remove(list, z[3]);
      remove(list, z[2]);

      System.out.println("\ntest insert()...");
      for (int i = 0; i < z.length; i++)
         list.add(z[i]);
      System.out.print("linked-list: ");              
      list.print(list.head);
      Integer rm0 = new Integer(9);
      Integer rm1 = new Integer(6);
      insert(list, rm0, 2);
      insert(list, new Integer(8), 0);
      insert(list, new Integer(5), 9);
      insert(list, rm1, list.getSize());
      //insert(list, rm1, 1);
      remove(list, rm0);
      remove(list, rm1);
   }

   static void remove(MyLinkedList mll, Object i) {
      char c = mll.remove(i) ? 'T' : 'F';
      System.out.print("remove(" + i + "): " + c + "; ");
      mll.print(mll.head);
   }

   static void insert(MyLinkedList mll, Object o, int i) {
      char c = mll.insert(o, i) ? 'T' : 'F';
      System.out.print("insert(" + o + ", " + i + "): " + c + "; ");
      mll.print(mll.head);
   }
}