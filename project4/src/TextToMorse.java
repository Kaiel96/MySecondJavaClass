import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextToMorse implements BSTTranslator<CharacterOrder> 
{
   CharacterOrder[] myArray = new CharacterOrder[MorseCode.size()];
   BST<CharacterOrder> bst = new BST<CharacterOrder>();
   
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
         myArray[i] = new CharacterOrder(MorseCode.get(i));
      }
   }

   public TextToMorse()
   {
      createArray();
      Arrays.sort(myArray);
      createBST(0,MorseCode.size()-1);
   }
   
   public BST<CharacterOrder> getBST() 
   {
      return bst;
   }
   
   public String translate(String string) 
   {
	   Scanner sc = new Scanner (string);
	   String s = "";
	   
	   while (sc.hasNext())
	   {
		   String word = sc.next();
		   
		   for (int j = 0; j < word.length(); j++)
		   {
				 MorseCode mc = new MorseCode(word.charAt(j), null);
	    		 CharacterOrder co = new CharacterOrder(mc);
	    		 
	    		 if (bst.contains(co))
	    		 {
	    	        s += (bst.get(co).getCode());
	    		    s += " ";
	    		 }
		   }
		   s += " ";
	   }
	   return s.trim();
   }
}
