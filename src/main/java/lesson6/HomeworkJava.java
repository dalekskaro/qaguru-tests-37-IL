package lesson6;

public class HomeworkJava {

    public static void main(String... args) {
        int aInt = 10;
        int bInt = 3;
        double aDouble = 3.14;
        byte aByte = 127;
        byte bByte = 10;
        byte sumByte;
        byte byteOperation;

        // 0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        System.out.println("aInt = " + aInt);
        System.out.println("bInt = " + bInt);
        System.out.println("aInt + bInt = " + (aInt + bInt));
        System.out.println("aInt - bInt = " + (aInt - bInt));
        System.out.println("aInt * bInt = " + (aInt * bInt));
        System.out.println("aInt / bInt = " + (aInt / bInt));

        // 1) применить несколько арифметических операций над int и double в одном выражении
        System.out.println("----------");
        System.out.println("aInt = " + aInt);
        System.out.println("aDouble = " + aDouble);
        System.out.println("(aInt + aDouble) % (aInt / aDouble) * aDouble = " + ((aInt + aDouble) % (aInt / aDouble) * aDouble));

        // 2) применить несколько логических операций ( < , >, >=, <= )
        System.out.println("----------");
        System.out.println("1   <  6 : " + (1 < 6));
        System.out.println("1   >  6 : " + (1 > 6));
        System.out.println("6   >= 6 : " + (6 >= 6));
        System.out.println("6.1 <= 6 : " + (6.1 <= 6));

        // 3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение
        // 4) получить переполнение при арифметической операции
        System.out.println("----------");
        System.out.println("aByte = " + aByte);
        System.out.println("bByte = " + bByte);
        System.out.println("aByte + bByte = " + (aByte + bByte)); // = 137, так как тип не указан
        sumByte = (byte) (aByte + bByte);
        System.out.println("sumByte = aByte + bByte = " + sumByte); // = -119, так как тип byte - переполнение получено

        byteOperation = (byte) (Byte.MAX_VALUE * 2);
        System.out.println("Byte.MAX_VALUE = " + Byte.MAX_VALUE);
        System.out.println("Byte.MAX_VALUE * 2 = " + (Byte.MAX_VALUE * 2));
        System.out.println("byteOperation = Byte.MAX_VALUE * 2 = " + (byteOperation)); // = -2 - переполнение
    }
}
