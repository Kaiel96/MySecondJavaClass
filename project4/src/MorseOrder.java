
public class MorseOrder extends MorseCode
implements Comparable<MorseOrder>
{
   public MorseOrder(Character letter, String code)
   {
      super(letter,code);
   }
   
   public MorseOrder(MorseCode morseCode) 
   {
      super(morseCode);
   }

   public int compareTo(MorseOrder mo) 
   {
      return this.getCode().compareTo(mo.getCode());
   }
   
   
}
