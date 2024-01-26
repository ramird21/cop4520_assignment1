# COP 4520 Assignment 1

## Running the Code:
To Compile the Code, do like so: javac Threading1.java  
To Run the Code, do like so: java Threading1

## Some proof of correctness:
I have tested my outputs against a non-multithreaded code I wrote
to test this and I am the same outputs...

## Efficiency:
I have tweaked the isPrime function a little to skip a few numbers,
which should great make this efficient. I have a also timed the threads doing
their jobs and the program is landing in the 18s, which meets requirements...

## Experimental evaluation:
A few experiments I have had with this assignment have while I was eliminating
race condition that existed along the shared list amongst the threads. I had the join()
function on the them and wrap a synchronize block to fix some issues. I also modified the
isPrime function to reduce execution time about half or so.