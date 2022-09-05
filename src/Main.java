import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Yong", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
// Считаем несовершеннолетних:

        long peopleCount = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних:" + " " + peopleCount);

//       Ищем фамилии призывников:

        List<String> warriorList = persons.stream()
                .filter(age -> age.getAge() <= 27 && age.getAge() >= 18)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("В списке призывников:"+  " "+warriorList.stream().count()+" фамилий");

//        Ищем потенциально работоспособных:

       List<String> womanWorkers = persons.stream()
               .filter(woman->woman.getEducation()==Education.Higher && woman.getSex()==Sex.Woman)
               .filter(age->age.getAge()>=18 && age.getAge()<=60)
               .map(Person::getFamily)
               .collect(Collectors.toList());

       List<String> manWorkers = persons.stream()
               .filter(man->man.getEducation()==Education.Higher && man.getSex()==Sex.Man)
               .filter(age->age.getAge()>=18 && age.getAge()<=65)
               .map(Person::getFamily)
               .collect(Collectors.toList());

    }
}
