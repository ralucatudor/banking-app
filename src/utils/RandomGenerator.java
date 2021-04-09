package utils;

//import java.util.UUID;

import java.util.Random;

public class RandomGenerator {
//    public static String generateString(){
//        return UUID.randomUUID().toString();
//    }

    public static String getNumericString(int size) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < size; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
