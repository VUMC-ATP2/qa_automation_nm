package practice;

import org.testng.annotations.Test;

public class Practice {

    class Phone {
        public void call() {
            System.out.println("Call from phone");
        }
    }

    class SamsungPhone extends Phone {
        @Override
        public void call() {
            System.out.println("Call from samsung");
        }

        public void methodSamsung() {
            System.out.println("Samsung method");
        }
    }

    @Test
    public void test() {
        Phone samsung = new SamsungPhone();
        ((SamsungPhone) samsung).methodSamsung();

    }
}
