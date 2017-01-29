
public class CharacterOrder extends MorseCode
implements Comparable<CharacterOrder>
{
   public CharacterOrder(Character letter, String code)
   {
      super(letter,code);
   }
   
   public CharacterOrder(MorseCode morseCode) 
   {
      super(morseCode);
   }

   public int compareTo(CharacterOrder co) 
   {
      return this.getCharacter().compareTo(co.getCharacter());
   }
   
   
}
