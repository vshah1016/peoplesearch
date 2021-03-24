public class Main {
    private static final Client client = new Client();
    private static final PeopleList peopleList = new PeopleList();

    public static void main(String[] args) {
        int choice = client.prompt();
        while (client.isRunAgain()) {
            switch (choice) {
                case 1 -> addPeople();
                case 2 -> binary();
                case 3 -> sequential();
                case 4 -> peopleList.showSortedList();
            }
            choice = client.prompt();
        }
        System.exit(0);
    }

    private static void sequential() {
        System.out.print("Input the name of the person you would like to sequential search for: ");
        String input = client.gatherInput().trim();
        int i = peopleList.sequentialSort(input);
        if (i == -1) System.out.println("There was no result for " + input);
        else {
            search(input, i);
            System.out.println("There were " + i + " comparisons to find this person.");
        }
    }

    private static void binary() {
        System.out.print("Input the name of the person you would like to binary search for: ");
        String input = client.gatherInput().trim();
        int i = peopleList.binarySearch(input);
        if (i == -1) System.out.println("There was no result for " + input);
        else {
            search(input, i);
            System.out.println("There were " + peopleList.comparisons + " comparisons to find this person.");
        }
    }

    private static void search(String input, int i) {
        System.out.println(input + " was found!");
        System.out.println(peopleList.people[i].toString());

        int option = client.searchPrompt();

        switch (option) {
            default -> {
            }
            case 1 -> {
                System.out.print("What is the new name/age that you want to change to: ");
                input = client.gatherInput();
                try {
                    int age = Integer.parseInt(input);
                    String name = peopleList.people[i].name;
                    peopleList.remove(i);
                    peopleList.addPerson(new Person(name, age));
                    System.out.println("Changed!");
                } catch (NumberFormatException e) {
                    int age = peopleList.people[i].age;
                    String name = input.trim();
                    peopleList.remove(i);
                    peopleList.addPerson(new Person(name, age));
                    System.out.println("Changed!");
                } catch (Exception e) {
                    System.out.println("Could not add. Error.");
                }
            }
            case 2 -> peopleList.remove(i);
        }
    }

    private static void addPeople() {
        System.out.print("Input the person you would like to add in Array format (e.x \"Veer, 16\"): ");
        String input = client.gatherInput().trim();
        String[] parts = input.split(", ");
        peopleList.addPerson(new Person(parts[0], Integer.parseInt(parts[1])));
        System.out.println(parts[0] + " was added!");
    }


}
