package arduino;

public class Converter {
    static int in=9750560;//16777215
    static int red;
    static int green;
    static int blue;
    static int R;//16580608
    static int G;//64768
    static int B;//253
        
    public static void main(String[] args) {
        //RGB = (R*65536)+(G*256)+B
        convert(in);
    }
    
    public static void convert(double input){
        System.out.println((int)input);
        double a = (double) (input/256);
        B =(int) ((a-((int)a))*256);        
        double c = (int)(input-B); 
        double d = (double) (c/65536);
        G =(int) ((d-((int)d))*256);                
        R=(int)d;
        System.out.println("blue: " + B);
        System.out.println("green: " + G);
        System.out.println("red: " + R);

    }
}
