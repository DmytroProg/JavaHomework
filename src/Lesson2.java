import lombok.Getter;
import lombok.Setter;

public class Lesson2 {
    public static void main(String[] args){

    }
}

// Знаю що класи варто розділяти по файлах
// Це просто для зручності

// task1
class Human{
    private String name;
    private int age;

    public Human(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Builder extends Human{
    private String instrument;

    Builder(String name, int age, String instrument) {
        super(name, age);
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}

class Sailor extends Human{
    private String boat;

    Sailor(String name, int age, String boat) {
        super(name, age);
        this.boat = boat;
    }

    public String getBoat() {
        return boat;
    }

    public void setBoat(String boat) {
        this.boat = boat;
    }
}

@Getter
@Setter
class Pilot extends Human{
    private String plane;

    Pilot(String name, int age, String plane) {
        super(name, age);
        this.plane = plane;
    }
}

// task2
@Getter
@Setter
class Animal{
    private String name;
    private String fur;

    public Animal(String name, String fur) {
        this.name = name;
        this.fur = fur;
    }
}

class Tiger extends Animal{
    public Tiger(String name, String fur) {
        super(name, fur);
    }
}

class Crocodile extends Animal{
    public Crocodile(String name, String fur) {
        super(name, fur);
    }
}

class Kangaroo extends Animal{
    public Kangaroo(String name, String fur) {
        super(name, fur);
    }
}

// task3
@Getter
@Setter
class Money{
    private int amount;
    private int coins;

    public Money(int amount, int coins) {
        this.amount = amount;
        this.coins = coins;
    }

    @Override
    public String toString() {
        return amount + "." + (coins < 10 ? "0" : "")  + coins;
    }
}

@Getter
@Setter
class Product{
    private String name;
    private Money price;

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public void decreasePrice(int value){
        price.setAmount(price.getAmount() - value);
    }
}

// task4
abstract class Device {
    private String name;
    private String characteristics;

    public Device(String name, String characteristics) {
        this.name = name;
        this.characteristics = characteristics;
    }

    public void show() {
        System.out.println("Назва пристрою: " + name);
    }

    public void desc() {
        System.out.println("Характеристики: " + characteristics);
    }

    public abstract void sound();
}

class Kettle extends Device {
    public Kettle(String characteristics) {
        super("Чайник", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Чайник звук: Шипіння та свистіння");
    }
}

class Microwave extends Device {
    public Microwave(String characteristics) {
        super("Мікрохвильовка", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Мікрохвильовка звук: Гудіння та дзвінок");
    }
}

class Car extends Device {
    public Car(String characteristics) {
        super("Автомобіль", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Автомобіль звук: Ревіння двигуна");
    }
}

class Steamer extends Device {
    public Steamer(String characteristics) {
        super("Пароплав", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Пароплав звук: Гудіння сирени");
    }
}

// task5
abstract class MusicalInstrument {
    private String name;
    private String characteristics;

    public MusicalInstrument(String name, String characteristics) {
        this.name = name;
        this.characteristics = characteristics;
    }

    public void show() {
        System.out.println("Instrument Name: " + name);
    }

    public void desc() {
        System.out.println("Characteristics: " + characteristics);
    }

    public abstract void sound();
    public abstract void history();
}

class Violin extends MusicalInstrument {
    public Violin(String characteristics) {
        super("Violin", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Violin Sound: Screeching melody");
    }

    @Override
    public void history() {
        System.out.println("Violin History: The violin, sometimes known as a fiddle, is a wooden string instrument in the violin family.");
    }
}

class Trombone extends MusicalInstrument {
    public Trombone(String characteristics) {
        super("Trombone", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Trombone Sound: Bold and brassy");
    }

    @Override
    public void history() {
        System.out.println("Trombone History: The trombone is a musical instrument in the brass family.");
    }
}

class Ukulele extends MusicalInstrument {
    public Ukulele(String characteristics) {
        super("Ukulele", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Ukulele Sound: Light and happy strumming");
    }

    @Override
    public void history() {
        System.out.println("Ukulele History: The ukulele is a member of the lute family of instruments.");
    }
}

class Cello extends MusicalInstrument {
    public Cello(String characteristics) {
        super("Cello", characteristics);
    }

    @Override
    public void sound() {
        System.out.println("Cello Sound: Deep and resonant");
    }

    @Override
    public void history() {
        System.out.println("Cello History: The cello is a bowed string instrument with four strings.");
    }
}

// task 6 and 7
class Array implements IMath, ISort{
    private int[] arr;
    private int size;
    private int capacity;

    public Array(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0;
    }

    public void add(int element) {
        if (size < capacity) {
            arr[size] = element;
            size++;
        } else {
            System.out.println("Array is full. Cannot add element.");
        }
    }

    public int get(int index) {
        if (index >= 0 && index < size) {
            return arr[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int max() {
        int max = arr[0];
        for(int value : arr){
            if(value > max){
                max = value;
            }
        }

        return max;
    }

    @Override
    public int min() {
        int min = arr[0];
        for(int value : arr){
            if(value < min){
                min = value;
            }
        }

        return min;
    }

    @Override
    public float avg() {
        float sum = 0.0f;
        for(int value : arr){
            sum += value;
        }

        return sum / arr.length;
    }

    @Override
    public void sortAsc() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void sortDesc() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

interface IMath{
    int max();
    int min();
    float avg();
}

interface ISort{
    void sortAsc();
    void sortDesc();
}