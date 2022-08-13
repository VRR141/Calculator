import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        System.out.println(calc(temp));
    }

    public static String calc(String input){
        Calculator calculator = new Calculator();
        return calculator.runCalculate(input);
    }
}

class Calculator {

    private final String[] roman = {"N", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
            "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX",
            "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
            "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
            "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
            "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public String runCalculate(String input){

            if (!containsArithmeticOperation(input)){
                throw new RuntimeException("BAD ARITHMETIC OPERATION");
            }

            char[] chars = input.toCharArray();
            int regex = 0;
            char regexC = '.';

            for (int i = 0; i < chars.length; i++){
                if (chars[i] == '+' || chars[i] == '-' || chars[i] == '/' || chars[i] == '*'){
                    regex = i;
                    regexC = chars[i];
                }
            }

            var chars1 = new char[regex];
            var chars2 = new char[chars.length - regex - 1];
            for (int i = 0; i < regex; i++){
                chars1[i] = chars[i];
            }
            for (int i = 1 + regex; i < chars.length; i ++){
                chars2[i - regex - 1] = chars[i];
            }

            String oneS = String.copyValueOf(chars1).trim();
            String twoS = String.copyValueOf(chars2).trim();

            boolean isRoman = false;

            for (String s : roman) {
                if (s.equals(oneS)) {
                    isRoman = true;
                    break;
                }
            }

            String res = null;

            if (isRoman) {
                int one = convertRomanToNum(oneS);
                int two = convertRomanToNum(twoS);
                booms(one, two);
                int result = calculate(one, two, regexC);
                if (result < 1) {
                    throw new RuntimeException("BAD ROMAN RESULT");
                }
                res = String.valueOf(convertNumToRoman(result));

            }
            else {
                int one = Integer.parseInt(oneS);
                int two = Integer.parseInt(twoS);
                booms(one, two);
                int result = calculate(one, two, regexC);
                res = String.valueOf(result);
            }
            return res;
        }


    private boolean containsArithmeticOperation(String s){
        return s.contains("-") || s.contains("+") || s.contains("/") || s.contains("*");
    }

    private int calculate(int one, int two, char regex) {
        int result = -99;
        switch (regex) {
            case '-' -> result = one - two;
            case '+' -> result = one + two;
            case '/' -> result = one / two;
            case '*' -> result = one * two;
            default -> {
            }
        }
        return result;
    }

    private String convertNumToRoman (int numArabian) {
        return roman[numArabian];
    }

    private int convertRomanToNum (String romans) {
        for (int i = 0; i < roman.length; i++){
            if (romans.equals(roman[i])){
                return i;
            }
        }
        return -1;
    }

    private void booms(int one, int two){
        if ((one < 1 || one >10) || (two < 1 || two > 10)){
            throw new RuntimeException("Incorrect input");
        }
    }
}