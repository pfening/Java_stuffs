package passwordgen;

import java.util.Random;

public class PasswordGen {

    public static void main(String[] args) {
    
    char ch='A';

    System.out.println((int)ch);
    
    int ascii=66;
    
    System.out.println(Character.toString ((char) ascii));
    
    int START = 1;
    int END = 10;
    Random random = new Random();
    for (int idx = 1; idx <= 12; ++idx){
    int randomInt = random.nextInt(93)+33;
    //System.out.println(randomInt);
    System.out.print(Character.toString ((char) randomInt));
    
    }
    System.out.println();
}
}
