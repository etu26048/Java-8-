/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8;

import java.util.Arrays;
import static java.util.Comparator.comparing;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author dark-
 */
public class Java8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println(" --> Sorting HashMap with stream <-- \n");
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        LinkedHashMap<String, Integer> sortedMap = unsortMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .limit(6)
                .collect(Collectors.toMap(entry -> ((Map.Entry<String, Integer>) entry).getKey(), entry -> ((Map.Entry<String, Integer>) entry).getValue(), (k, v) -> {
                    throw new IllegalArgumentException(k + "already exists");
                }, LinkedHashMap::new));

        System.out.println("Ordered");
        System.out.println(sortedMap);

        System.out.println("\n --> Stream on List <-- \n");

        List<Person> people = Arrays.asList(new Person("Dan", 23), new Person("Laura", 22), new Person("Billy", 50), new Person("George", 21));

        List<String> nameSortedByAge = people.stream()
                .filter(x -> x.getAge() > 21)
                .sorted(comparing(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("--> Name sorted by age and > 21");
        System.out.println(nameSortedByAge);

        List<Person> sortedPeople = people.stream()
                .sorted(comparing(Person::getAge))
                .collect(Collectors.toList());

        System.out.println("--> People sorted by age");
        sortedPeople.forEach(e -> {
            System.out.println("Name : " + e.getName() + " - Age : "
                    + e.getAge());
        });

        System.out.println("All people are older than 20 yo ? " + people.stream().allMatch(x -> x.getAge() > 20));
        System.out.println("At least one person is older than 60 yo ? " + people.stream().anyMatch(x -> x.getAge() > 60));
        System.out.println("None people are younger than 20 yo ? " + people.stream().noneMatch(x -> x.getAge() < 20));

        // filtering before limiting
        List<Person> getFirstTwoPeopleAbove22 = people.stream()
                .filter(p -> p.getAge() > 22)
                .limit(2)
                .collect(toList());
        System.out.println(getFirstTwoPeopleAbove22);
        // limiting before filtering
        List<Person> getTheFirst2PeopleAndApplyFilter = people.stream()
                .limit(2)
                .filter(p -> p.getAge() > 22)
                .collect(toList());
        System.out.println(getTheFirst2PeopleAndApplyFilter);
        System.out.println("Sum of ages : "+people.stream().mapToInt(x -> x.getAge()).sum());
        
    }

}
