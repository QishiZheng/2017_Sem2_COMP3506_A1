# 2017_Sem2_COMP3506_A1
This is a copy of my solution for the COMP3506 Algorithms and Data Structure Assignment 1 in 2017 Semster 2 at The University of Queensland. 

Nothing from Java Framework Collection in the JDK was used for the implementation of the Abstract Data Types (ADTs) or Concrete Data Types (CDTs) in the assignment. The implementation of the data structures used in the assignment was based on basic Java language constructs and not libraries.

## Assignment Contents
### Introduction
Data structures are a core part of the design of most applications. Games are one problem domain in which the choice of data structures and algorithms is a crucial part of the design process. In this first assignment you will implement three concrete data types that could be used in a game.
### Details
You will implement and test three different data structures. The data structures are a **Grid**, **List** and **RemovableBag**. You are provided with Java interfaces to define these abstract data types (ADTs). You are also provided with initial shells of the classes for the concrete data types (CDTs) that you need to implement. You are provided with some initial JUnit tests for the CDTs. You will need to implement further JUnit tests to fully test your CDTs.

- The Grid ADT in this assignment is a collection that holds items in a two-dimensional grid layout. Each item in the grid is accessed by an (x, y) coordinate pair. A grid can be visualised as:

|     |      |    |    |
| --- |:----:| --:| --:|
|     |      |  A |    |
|     |      |    |    |
|     |      |    |    |


Where item A is at coordinate position (2, 1). The Grid<T> interface defines the public methods and behaviours of the ADT. You need to implement the RectangularGrid<T> class to provide a concrete implementation of the data structure.

- The List ADT is a collection that holds items in sequential order. Items can be inserted into any position in the list, rearranging the order of items in the list. The list has the concept of an internal cursor that refers to (or points to) an item in the list. Operations on the list (e.g. insert or remove) occur at the cursor position. The GameList<T> interface defines the public methods and behaviours of the ADT. (It is called GameList because the JDK already defines a List interface.) You need to implement the LinkedList<T> class to provide a concrete implementation of the data structure.

- The RemovableBag ADT is a collection that holds items without a specified ordering of the items. It is an extension of a Bag data structure. The two extensions are that items can be removed from the bag and that it is possible to iterate through the contents of the bag. Iteration is done through an internal cursor that refers to an item in the bag. This means that there is some implied internal ordering of items in the bag to allow the cursor to access each item efficiently, but the ordering of items in the bag does not have to reflect the order in which the items were added to the bag. You need to implement the FixedSizeBag<T> class to provide a concrete implementation of the data structure. A private data member and implementation of one constructor is provided for this class. This is to provide an example of one possible underlying implementation of the data structure. You do not have to use this provided implementation if you believe an alternative implementation is better.

Three JUnit test classes are provided: **FixedSizeBagTest**, **LinkedListTest** and **RectangularGridTest**. They are written using JUnit 4 and use the Hamcrest matcher framework. These provide a few initial tests of the three CDTs you need to implement. You will need to implement further JUnit tests of your CDTs. Your own JUnit tests should be in classes called: MyFixedSizeBagTest, MyLinkedListTest and MyRectangularGridTest. Do not add tests to the provided JUnit test classes. Your JUnit tests must be written using JUnit 4 but do not need to use the Hamcrest matcher framework.
You are also provided with the shell of a room-based game that makes use of the CDTs. Currently the shell does not provide any game play. It does use the CDTs to create a game world consisting of a number of rooms, and a player with an inventory sack. You do not need to implement any features of the game. It is provided simply as an example of how the ADTs could be used.
