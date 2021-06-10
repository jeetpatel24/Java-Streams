import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌

    /*

    List<Person> females = new ArrayList<>();

    for (Person person : people) {

      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }

    females.forEach(System.out::println);

    */

    // Declarative approach
    // 1. filter
    System.out.println("1. Filter");
    List<Person> females =
            people.stream()
          .filter(person -> person.getGender().equals(Gender.FEMALE))
          .collect(Collectors.toList());
    females.forEach(System.out::println);
    System.out.println();

    //2.sort
    System.out.println("2. Sort");
    List<Person> sorted = people.stream()
            .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
            .collect(Collectors.toList());

    //sorted(Comparator.comparing(Person::getAge).reversed()
    //sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender)).reversed()
    sorted.forEach(System.out::println);
    System.out.println();

    //All match
    System.out.println("3. All match");
    boolean allMatch = people.stream()
            .allMatch(person -> person.getAge() > 5);
    System.out.println(allMatch);
    System.out.println();

    //Any match
    System.out.println("4. Any match");
    boolean anyMatch = people.stream()
            .anyMatch(person -> person.getAge() > 20);
    System.out.println(anyMatch);
    System.out.println();

    //None match
    System.out.println("5. None match");
    boolean noneMatch = people.stream()
            .noneMatch(person -> person.getName().equals("Antonia"));
    System.out.println(noneMatch);
    System.out.println();

    //max
    System.out.println("6. max");
    people.stream()
            .max(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);
    System.out.println();

    //min
    System.out.println("7. min");
    people.stream()
            .min(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);
    System.out.println();

    //group
    System.out.println("8. group");
    Map<Gender, List<Person>> groupByGender = people.stream()
            .collect(Collectors.groupingBy(Person::getGender));

    groupByGender.forEach((gender, people1) -> {
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });
    System.out.println();

    //misceleneous
    System.out.println("9. misceleneous");
    Optional<String> oldestFemale = people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .max(Comparator.comparing(Person::getAge))
            .map(Person::getName);
    oldestFemale.ifPresent(System.out::println);
    System.out.println();
  }


  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }
}
