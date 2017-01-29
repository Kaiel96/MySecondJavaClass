import java.util.ArrayList;

import javax.lang.model.element.Element;

public class test {
   public static void main(String[] args)
   {
      ArrayList <Integer> al = new ArrayList<Integer>();
      al.add(0,5);
      al.add(1, 2);
      al.remove(0);
      System.out.println(al.get(1));
   }

}
