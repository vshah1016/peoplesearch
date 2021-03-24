# Algorithm

# Person.java

The first thing I saw was that I need to make a class called `Person` with the fields `name` and `age`. I will
use `String` and `int` types for those variables.

Person looks like this so far:

```java
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

# Console

I am going to make a console that prompts the user for what they would like to do. This requires me to find out what
options I need to code. Currently, I see:
> 0. exit
> 1. Input a Person
> 2. Binary Search
> 3. Sequential Search
> 4. Display all people (Sorted)
>

# Add People

Adding somebody was easy, I just asked that they separated the name with the age using a comma and a space. For
example "Veer, 24" or "Mr. Geary, 28". Then I just use the `addPerson` method. I take in this input, split it on the
space and comma, then add a new Student object to the list.

# Searching

For the searching, I asked for the query string. Then I used the reach to search. If there were no results I said that.
Otherwise, if there were any matches I will print that, give them options to edit it or delete it as well.

# Sorting and Searching

```java
public int binarySearch(String search){
        nameSort();
        comparisons = 0;
        int left = 0, right = people.length - 1;
        while(left <= right){
            int middle = (right + left) / 2;
            int directionSearch = search.trim().compareTo(people[middle].name);
            comparisons++;
            if (directionSearch > 0) left = middle + 1;
            else if (directionSearch < 0) right = middle - 1;
            else return middle;
        }
        return -1;
    }

    public int sequentialSort(String search){
        for (int i = 0; i < people.length; i++) if (people[i].name.equalsIgnoreCase(search.trim())) return i;
        return -1;
    }

    private void swap(int a, int b) {
        Person temp = people[a];
        people[a] = people[b];
        people[b] = temp;
    }

    private void nameSort() {
        for (int i = 0; i < people.length - 2; i++) {
            int minimum = i;
            for (int j = i + 1; j < people.length; j++)
                if (people[j].name.compareToIgnoreCase(people[minimum].name) < 0) minimum = j;
            swap(i, minimum);
        }
    }
```

Not too much special about this code, it is an almost exact copy of the sequential sort. It was just ported, and some
bugs that were made from the port were fixed.

# Extra Credit - Display

For the extra credit, I made a GUI. This is a copy of the one on the selection sort program but modified to fit this
program.