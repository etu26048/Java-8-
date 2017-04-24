/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        System.out.println("Sum of ages : " + people.stream().mapToInt(x -> x.getAge()).sum());

        System.out.println("***************");
        people.stream().forEach(e -> {
            e.setAge(12);
        });
        people.stream().forEach(System.out::println);

        System.out.println("***************");
        System.out.println("Laura : " + people.stream().filter(v -> v.getName().equals("Laura")).findFirst().get().getClass());

        List<Beacon> beacons = new ArrayList();
        beacons.add(new Beacon(new Long(1), "LGG", "23L", "", "m1", "i1", "Liège airport"));
        beacons.add(new Beacon(new Long(2), "BXX", "58D", "o2", "", "i2", "Bruxelles airport"));
        beacons.add(new Beacon(new Long(3), "LGG", "25R", "o3", "m3", "", "Liège airport"));

        beacons.stream()
                .map(Beacon::getAirport_name)
                .sorted()
                .collect(toList())
                .forEach(System.out::println);

        beacons.stream()
                .filter(v -> v.getAirport_name().equals("Liège airport"))
                .map(Beacon::getLandind_track)
                .sorted()
                .collect(toList())
                .forEach(System.out::println);

        /*System.out.println(beacons.stream()
        .filter(x -> x.getInner_beacon().equals("o1") || x.getMiddle_beacon().equals("o1") || x.getOuter_beacon().equals("o1"))
        .findFirst()
        .get().getAirport_name());*/
        Beacon beacon = beacons.stream()
                .filter(x -> x.getInner_beacon().equals("a") || x.getMiddle_beacon().equals("m1") || x.getOuter_beacon().equals("a"))
                .filter(v -> v.getAirport_name().contains("Liège airport"))
                .filter(k -> k.getLandind_track().contains("23L"))
                .findFirst()
                .orElse(null);

        System.out.println(beacon.getAirport_name() + " " + beacon.getLandind_track());

        if (beacon == null) {
            System.out.println("Beacon est null");
        }

        System.out.println(beacons.stream()
                .filter(x -> x.getAirport_name().equals("Liège airport"))
                .map(Beacon::getIcao_code)
                .findFirst()
                .get());

        int i, j, n;
        i = 0;
        n = i++;
        System.out.println("A : i = " + i + " n = " + n);

        Map<String, Double> linkedMap = new LinkedHashMap<>();

        for (int k = 0; k < 24; k++) {
            linkedMap.put(String.valueOf(k), (double) Math.round(Math.random() * 100));
        }

        linkedMap.keySet().stream().collect(toList());
        System.out.println(linkedMap.values().stream().mapToInt(h -> h.intValue()).sum());
        System.out.println(linkedMap.values().stream().max(Comparator.naturalOrder()).orElse(-1.00).getClass());
        linkedMap.put("1", 42.00);
        System.out.println(linkedMap.get("1"));
        System.out.println("Key set: " + linkedMap.entrySet().stream().map(x -> x.getKey()).collect(toList()));
        System.out.println("Dataset  : " + linkedMap.values());

        System.out.println(Integer.parseInt("24"));

        List<String> colors = new ArrayList<String>(Arrays.asList("#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"));

        System.out.println("toArray : " + Arrays.toString(colors.toArray(new String[colors.size()])));

        int var = 42;
        System.out.println(var > 0 ? var : 0);

        Map<String, HashMap<String, Double>> map = new HashMap<>();
        HashMap<String, Double> temp = new HashMap<>();
        for (int k = 0; k < 7; k++) {
            temp.put("i" + k, ((double) (Math.round(Math.random() * 100))));
        }

        map.put("Dataset 1 ", temp);

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(map.entrySet());
        System.out.println(map.keySet().toString());
        /* not the right way
        List<String> xx = (map.values().stream().map(m -> m.toString()).collect(toList()));
         */
        
        List<String> xx = map.values().iterator().next().keySet().stream().collect(toList());
        for (String value : xx) {
            System.out.println(value);
        }
        System.out.println(xx);
        System.out.println(map.values().iterator().next().keySet());
        System.out.println(map.values().iterator().next().values());
        System.out.println(map.values().stream().map(x -> x.keySet()).collect(toList()));
        System.out.println(map.values().stream().map(y -> y.values()).collect(toList()).getClass());
        System.out.println(map.values().iterator().next().values().stream().filter(g -> g == 86.0).count());
    }

}
