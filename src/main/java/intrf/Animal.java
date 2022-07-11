package intrf;

public interface Animal {

    void speak();

    void walk();

    default void eat() {
        System.out.println("I am eating!");
    }
}
