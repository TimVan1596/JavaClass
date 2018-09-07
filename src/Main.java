import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        show(100);
    }

    public static void show(int n) {
        if (n <= 0) {
            System.out.println("error");
        } else if (n == 1) {
            System.out.println("0");
        } else if (n == 2) {
            System.out.println("1");
        } else {
            n = n - 1;
            int i = 2;
            List list = new ArrayList();
            list.add(0);
            list.add(1);
            while (i <= n) {
                int tempa = (int)list.get(i - 1);
                int tempb = (int)list.get(i - 2);
                list.add(tempa+tempb);
                i++;
            }
            System.out.println(list.get(n));
        }
    }
}