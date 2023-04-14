# Programming Assignment 3
## How to run the program
1. Call the filepath in the command line
2. Type the following command:
```bash
javac fileName.java
```
3. Press enter then write this in the terminal:
```bash
java fileName 
```
4. Press enter then a printable result will appear in the command line.

# The Birthday Presents Party
## Proof of Correctness
The approach I took for this problem was to use ConcurrentLinkedQueue to store the gifts. At the start, the methods that can be accessed by multiple threads, such as insert and remove, are locked and then unlocked later. A HashSet to keep track of the gift tags to avoid adding duplicates. I also use an AtomicInteger to keep track of the number of thank you cards written. This method allows for safe thread implementation used to add and remove gifts from the queue in a thread-safe manner to prevent any threads from modifying the linked queue at the same time. 

## Experimental Evaluation
I was exploring another solution that uses a message queue to decouple the actions of adding presents to the linked list and writing "Thank you" notes. Each servant can be assigned to a separate queue, and messages can be exchanged between the queues to coordinate the actions of the servants. However, this approach would have been more complex than what is required for this assignment but can have a possible drawback of consuming too many resources affecting the time.

## Efficiency
I was getting two different types of runtime when running on the command line vs IDE. When I completely remove the print statement the run time was about 4-5sec. However, when left the print statement in the command line it took 80-90sec. The IDE always performed in under 2 seconds. The overall efficiency will differ depending on where you run the code. 

# Atmospheric Temperature Reading Module
## Proof of Correctness

## Experimental Evaluation


## Efficiency
