// Question: Social Media Follower Iterator

// You are tasked with designing a system for managing a user's followers on a social media platform using the Iterator Design Pattern. The system should allow iteration through a collection of followers while hiding the underlying implementation details of the collection.
// Requirements

//     Follower Collection:
//         Implement a FollowerCollection interface that defines a method to create an iterator.
//         Implement a FollowerList class that represents the follower collection.

//     Iterator:
//         Implement an Iterator interface with the following methods:
//             hasNext(): Returns true if there are more followers to iterate over.
//             next(): Returns the next follower in the collection.

//     Concrete Iterator:
//         Create a concrete iterator for the FollowerList class.

//     Client Code:
//         Use the iterator to iterate through a user's followers and print their names.

import java.util.ArrayList;
import java.util.List;

interface Iterator {
  boolean hasNext();
  String next();
}

class IteratorImpl implements Iterator {
  List<String> followerList;
  int currIndex;
  IteratorImpl(List<String> followerList) {
    this.followerList = followerList;
    this.currIndex = 0;
  }
  public boolean hasNext() {
    if (currIndex < followerList.size()) return true;
    else return false;
  }
  public String next() {
    String follower = followerList.get(currIndex);
    ++this.currIndex;
    return follower;
  }
  public void reset() {
    this.currIndex = 0;
  }
}

interface Collection {
  Iterator getIterator();
  void addItem(String follower);
}

class CollectionImpl implements Collection {
  List<String> followerList;
  CollectionImpl() {
    this.followerList = new ArrayList<>();
  }
  public Iterator getIterator() {
    return new IteratorImpl(this.followerList);
  }
  public void addItem(String follower) {
    this.followerList.add(follower);
  }
}

public class IteratorDesignPattern {
  public static void main(String []args) {
    Collection collection = new CollectionImpl();
    Iterator iter = collection.getIterator();
    collection.addItem("abhishek");
    collection.addItem("ajinkya");
    collection.addItem("lakshya");
    collection.addItem("sean");
    while (iter.hasNext()) System.out.println(iter.next());
  }
}
