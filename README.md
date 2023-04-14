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
8 temperature sensors, each generating readings every minute over a period of one hour. the program creates a list of synchronized lists to store the temperature readings from each sensor. Each synchronized list ensures that the readings are accessed and modified safely from multiple threads. 
## Experimental Evaluation
A better might be to create a separate thread to handle the reporting, and use a thread-safe data structure to communicate the latest temperature readings to the reporting thread. This would eliminate the need to create and start a new thread for each report, and would allow the program to continue collecting temperature readings while the report is being generated. Due to unexpected life issue I wasn't able to implement this idea. :( 

## Efficiency
This solution isn't the most efficient because the program waits for each report thread to finish before moving on to the next iteration of the outer loop. Also, creates and starts a new thread for each temperature report. However, when running on the command line you should expect 2-3 sec to get the full results from the temperature list.
