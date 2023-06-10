import java.util.*;

public class Task6_1 {
    public static void main(String[] args) {
        AppData appData = new AppData();
        List<Integer> lA = new ArrayList<Integer>();
        lA.add(1);
        lA.add(2);
        lA.add(3);
        lA.add(4);
        appData.addColumn("a", lA);

        List<Integer> lB = new ArrayList<Integer>();
        lB.add(5);
        lB.add(6);
        lB.add(7);
        lB.add(8);
        appData.addColumn("b", lB);

        System.out.println("Данные записаны в data.csv");
        appData.display();

        appData.save("data.csv");
    }
}
