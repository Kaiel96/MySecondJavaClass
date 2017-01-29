import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MorseToText implements BSTTranslator<MorseOrder> 
{
   MorseOrder[] myArray = new MorseOrder[MorseCode.size()];
   BST<MorseOrder> bst = new BST<MorseOrder>();
   
   private void createBST(int min, int max)
   {
      if (min > max)
      {
         return;
      }
      else 
      {
         int mid = (max + min) / 2;
         bst.insert(myArray[mid]);
         createBST(min,mid-1);
         createBST(mid+1, max);
      }
   }
   private void createArray()
   {
      for (int i = 0; i < MorseCode.size(); i++)
      {
         myArray[i] = new MorseOrder(MorseCode.get(i));
      }
   }
   public MorseToText()
   {
      createArray();
      Arrays.sort(myArray);
      createBST(0,MorseCode.size() - 1);
   }
   public BST<MorseOrder> getBST() 
   {
      return bst;
   }
   public String translate(String code) 
   {
      String[] sc = code.split("[ ]");
      for (int i = 0; i < sc.length ; i++);
      String s = "";
      for(int i = 0; i < sc.length; i++)
      {
    	 String st = sc[i];
    	 if (st.equals(""))
    	 {	 
    		 s += " ";
    	 }
    	 
    	 else
    	 {
    		 MorseCode mc = new MorseCode(null,st.trim());
    		 MorseOrder mo = new MorseOrder(mc);
    		 if (bst.contains(mo))
    			 s += (bst.get(mo).getCharacter());
    	 }
      }
      return s.trim();
   }
}
