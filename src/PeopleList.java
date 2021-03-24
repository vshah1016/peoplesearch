import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.Arrays;

public class PeopleList {
    public int comparisons = 0;
    Person[] people = new Person[0];

    public PeopleList(Person person) {
        this.people = new Person[1];
        people[0] = person;
    }

    public PeopleList() {
    }

    public void addPerson(Person person) {
        Person[] copy = new Person[people.length + 1];
        System.arraycopy(people, 0, copy, 0, people.length);
        copy[people.length] = person;
        people = copy;
    }

    public void remove(int i) {
        Person[] copy = new Person[people.length - 1];
        if (i >= 0) System.arraycopy(people, 0, copy, 0, i);
        if (people.length - (i + 1) >= 0) System.arraycopy(people, i + 1, copy, i + 1 - 1, people.length - (i + 1));
        people = copy;
    }

    public boolean isEmpty() {
        return people.length == 0;
    }

    public int binarySearch(String search) {
        nameSort();
        comparisons = 0;
        int left = 0, right = people.length - 1;
        while (left <= right) {
            int middle = (right + left) / 2;
            int directionSearch = search.trim().compareTo(people[middle].name);
            comparisons++;
            if (directionSearch > 0) left = middle + 1;
            else if (directionSearch < 0) right = middle - 1;
            else return middle;
        }
        return -1;
    }

    public int sequentialSort(String search) {
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

    private void display() {
        JFrame jFrame = new JFrame();
        PersonChart studentChart = new PersonChart();
        DefaultTableModel defaultTableModel = (DefaultTableModel) studentChart.table.getModel();
        Arrays.stream(new String[]{"Name", "Age"}).forEach(defaultTableModel::addColumn);
        TableColumnModel tableColumnModel = studentChart.table.getColumnModel();
        tableColumnModel.getColumn(1).setPreferredWidth(100);
        for (Person person : people) defaultTableModel.addRow(new String[]{person.name, Integer.toString(person.age)});
        jFrame.setContentPane(studentChart.frame);
        jFrame.pack();
        jFrame.setSize(500, 200);
        jFrame.setVisible(true);
    }

    public void showSortedList() {
        nameSort();
        display();
    }

}
