package intrf;

public class Cat implements Animal {

    @Override
    public void speak() {
        System.out.println("Mau!");
    }

    @Override
    public void walk() {
        System.out.println("Walkie!");
    }
}
