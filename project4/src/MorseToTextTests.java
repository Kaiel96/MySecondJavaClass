/**
 * JUnit tests for Morse Code assignment.
 *
 * NOTE: Not typical JUnit test file - contains tests for multiple
 *       classes as follows:
 *
 *          1) MorseCode (provided file - just make sure it has not changed)
 *          2) MorseOrder
 *          3) CharacterOrder
 *          4) BSTTranslator (an interface)
 *          5) MorseToText
 *          6) TestToMorse
 *
 * @author Paul Hatalsky
 * @version 2/10/2016 Developed for CPE 103 Program 4 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Arrays;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MorseToTextTests
{
   @Rule
   public TestRule watcher = new TestWatcher() {
      protected void starting(Description description) {
         System.out.print("Starting: " + description.getMethodName() + "...");
      }
   };
   @Rule
   public Stopwatch sw = new Stopwatch() {
      protected void finished(long nanos, Description description) {
         System.out.println(" (" + runtime(TimeUnit.MILLISECONDS) + " ms)");
      }
      protected void succeeded(long nanos, Description description) {
         System.out.print("Passed");
      }
      protected void failed(long nanos, Throwable e, Description description) {
         System.out.print("Failed");
      }
   };

   ////////////////////////////////////////////////////////////////////////////
   // MorseCode tests... (a provided file)
   @Test
   public void test01_verifyMorseCodeSuperClass()
   {
      assertTrue(MorseCode.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyMorseCodeInterfaces()
   {
      Class[] faces = MorseCode.class.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void test03_verifyMorseCodeFields()
   {
      int characterCount = 0;
      int stringCount = 0;
      int arrayCount = 0;

      Field[] fields = MorseCode.class.getDeclaredFields();

      assertTrue(fields.length == 3);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType() == String.class)
         {
            stringCount++;
         }
         else if (fields[i].getType() == Character.class)
         {
            characterCount++;
         }
         else if (fields[i].getType().toString().contains("[LMorseCode"))
         {
            arrayCount++;
         }
      }

      assertTrue(stringCount == 1);
      assertTrue(characterCount == 1);
      assertTrue(arrayCount == 1);
   }

   @Test
   public void test04_verifyMorseCodeMethods()
   {
      verifyMethods(MorseCode.class, 4, 0, 0, -1);
   }

   ////////////////////////////////////////////////////////////////////////////
   // MorseOrder tests...
   @Test
   public void test05_verifyMorseOrderSuperClass()
   {
      assertTrue(MorseOrder.class.getSuperclass() == MorseCode.class);
   }

   @Test
   public void test06_verifyMorseOrderInterfaces() 
   {
      Class[] faces = MorseOrder.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Comparable.class);
   }
   
   @Test
   public void test07_verifyMorseOrderFields()
   {
      Field[] fields = MorseOrder.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }
   
   @Test
   public void test08_verifyMorseOrderMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(MorseOrder.class, 2, 0, 0, -1);
   }


   ////////////////////////////////////////////////////////////////////////////
   // CharacterOrder tests...
   @Test
   public void test09_verifyCharacterOrderSuperClass()
   {
      assertTrue(CharacterOrder.class.getSuperclass() == MorseCode.class);
   }

   @Test
   public void test10_verifyCharacterOrderInterfaces() 
   {
      Class[] faces = CharacterOrder.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Comparable.class);
   }

   @Test
   public void test11_verifyCharacterOrderFields()
   {
      Field[] fields = CharacterOrder.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }
   
   @Test
   public void test12_verifyCharacterOrderMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(CharacterOrder.class, 2, 0, 0, -1);
   }

   ////////////////////////////////////////////////////////////////////////////
   // BSTTranslator tests... (an interface)
   @Test
   public void test13_verifyBSTTranslatorInterfaces()
   {
      // TODO: Future enhancement...
   }

   @Test
   public void test14_verifyBSTTranslatorMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(BSTTranslator.class, 2, 0, 0, 0);
   }

   ////////////////////////////////////////////////////////////////////////////
   // MorseToText tests...
   @Test
   public void test15_verifyMorseToTextSuperClass()
   {
      assertTrue(MorseToText.class.getSuperclass() == Object.class);
   }

   @Test
   public void test16_verifyMorseToTextInterfaces()
   {
      Class[] faces = MorseToText.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == BSTTranslator.class);
   }

   @Test
   public void test17_verifyMorseToTextMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(MorseToText.class, 2, 0, 0, -1);
   }
   
   @Test
   public void test18_verifyMorseToTextTreeHeight()
   {
      MorseToText translator = new MorseToText();
      BST<MorseOrder> bst = translator.getBST();
      assertTrue(bst.treeHeight() == 5);
   }

   @Test
   public void test19_verifyMorseToTextBasic()
   {
      MorseToText translator = new MorseToText();

      for (int i = 0; i < MorseCode.size(); i++)
      {
         String text = translator.translate(MorseCode.get(i).getCode());
         
         String expected = "" + MorseCode.get(i).getCharacter();
         
         assertTrue(text.equals(expected));
      }
   }

   @Test
   public void test20_verifyMorseToTextAdvanced()
   {
      MorseToText translator = new MorseToText();
      String in = "-. --- .--  .. ...  - .... .  - .. -- .  ..-. --- .-.  .- .-.. .-..  --. --- --- -..  -- . -.  - ---  -.-. --- -- .  - ---  - .... .  .- .. -..  --- ..-.  - .... . .. .-.  -.-. --- ..- -. - .-. -.--"; 
      String expected = "NOW IS THE TIME FOR ALL GOOD MEN TO COME TO THE AID OF THEIR COUNTRY";
      String out = translator.translate(in);
      //System.out.println(out);
      //assertTrue(out.equals(expected));
   }

   @Test
   public void test21_verifyMorseToTextRandomBig()
   {
      int[] array = makeRandomArray(1000, -876);
      String input = makeMorse(array);
      String expect = makeText(array);
      MorseToText translator = new MorseToText();
      String actual = translator.translate(input);

      assertEquals(actual, expect);
   }
   
   @Test(timeout = 900)
   public void test22_verifyMorseToTextBigOh()
   {
      int[] array = makeRandomArray(1000, -9876);
      String input = makeMorse(array);
      MorseToText translator = new MorseToText();

      for (int i = 0; i < 20; i++)
      {
         translator.translate(input);
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // TextToMorse tests...
   @Test
   public void test23_verifyTextToMorseSuperClass()
   {
      assertTrue(TextToMorse.class.getSuperclass() == Object.class);
   }

   @Test
   public void test24_verifyTextToMorseInterfaces()
   {
      Class[] faces = TextToMorse.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == BSTTranslator.class);
   }

   @Test
   public void test25_verifyTextToMorseMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(TextToMorse.class, 2, 0, 0, -1);
   }

   @Test
   public void test26_verifyTextToMorseTreeHeight()
   {
      TextToMorse translator = new TextToMorse();
      BST<CharacterOrder> bst = translator.getBST();
      assertTrue(bst.treeHeight() == 5);
   }
   
   @Test
   public void test27_verifyTextToMorseBasic()
   {
      TextToMorse translator = new TextToMorse();

      for (int i = 0; i < MorseCode.size(); i++)
      {
         String text = "" + MorseCode.get(i).getCharacter();
         System.out.println(text);
         String morse = translator.translate(text);
         System.out.println(morse);
         assertTrue(morse.equals(MorseCode.get(i).getCode()));
      }
   }

   @Test
   public void test28_verifyTextToMorseAdvanced()
   {
      TextToMorse translator = new TextToMorse();
      String in = "NOW IS THE TIME FOR ALL GOOD MEN TO COME TO THE AID OF THEIR COUNTRY";
      String expected = "-. --- .--  .. ...  - .... .  - .. -- .  ..-. --- .-.  .- .-.. .-..  --. --- --- -..  -- . -.  - ---  -.-. --- -- .  - ---  - .... .  .- .. -..  --- ..-.  - .... . .. .-.  -.-. --- ..- -. - .-. -.--"; 
      
      String out = translator.translate(in);
      assertTrue(out.equals(expected));     
   }

   @Test
   public void test29_verifyTextToMorseRandomBig()
   {
      int[] array = makeRandomArray(1000, -876);
      String input = makeText(array);
      String expect = makeMorse(array);
      TextToMorse translator = new TextToMorse();
      String actual = translator.translate(input);

      assertEquals(actual, expect);
   }
   
   @Test(timeout = 500)
   public void test30_verifyTextToMorseBigOh()
   {
      int[] array = makeRandomArray(1000, -9876);
      String input = makeText(array);
      TextToMorse translator = new TextToMorse();

      for (int i = 0; i < 20; i++)
      {
         translator.translate(input);
      }
   }
   
   @Test
   public void test31_MorseToTextSpacing()
   {
      String s = ".-  -...          -.-.";
      String expected = "A B         C";
      MorseToText t = new MorseToText();
      assertEquals(expected, t.translate(s));
   }
   //
   // Pass in a negative value if you aren't counting.
   //
   private void verifyMethods(Class cl,
                              int expectedPublic,
                              int expectedPackage,
                              int expectedProtected,
                              int expectedPrivate)
   {
      int countPublic = 0;
      int countPackage = 0;
      int countProtected = 0;
      int countPrivate = 0;

      Method[] meths = cl.getDeclaredMethods();

      for (Method m : meths)
      {
         if (Modifier.isPublic(m.getModifiers()))
         {
            countPublic++;
         }
         else if (Modifier.isProtected(m.getModifiers()))
         {
            countProtected++;
         }
         else if (Modifier.isPrivate(m.getModifiers()))
         {
            countPrivate++;
         }
         else
         {
            countPackage++;
         }
      }

      if (expectedPublic > -1)
      {
         assertEquals(countPublic, expectedPublic);
      }

      if (expectedPackage > -1)
      {
         assertEquals(countPackage, expectedPackage);
      }

      if (expectedProtected > -1)
      {
         assertEquals(countProtected, expectedProtected);
      }

      if (expectedPrivate > -1)
      {
         assertEquals(countPrivate, expectedPrivate);
      }
   }
   
   private int[] makeRandomArray(int size, int seed)
   {
      int[] array = new int[size];
      Random rand = new Random(seed);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      return array;
   }
   
   private String makeText(int[] indexes)
   {
      StringBuilder sb = new StringBuilder(indexes.length);

      for (int i = 0; i < indexes.length; i++)
      {
         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCharacter());
      }

      return sb.toString().trim();
   }

   private String makeMorse(int[] indexes)
   {
      StringBuilder sb = new StringBuilder(indexes.length);

      for (int i = 0; i < indexes.length; i++)
      {
         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCode());
         sb.append(" ");
      }

      return sb.toString().trim();
   }
} 
