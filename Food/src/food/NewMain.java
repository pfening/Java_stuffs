package food;

public class NewMain {

    public static void main(String[] args) {
        String str="mixed meat:500;acaroni pasta:250;cheese:100;tomato sause:1";
        String ing[]=str.split(";");
        for (String item : ing) {
            System.out.println("Material: " +item.split(":")[0]);
            System.out.println("Amount: " +item.split(":")[1]);
        }
    }
}
