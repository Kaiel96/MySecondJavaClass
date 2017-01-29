/**
 * JUnit tests for BST assignment.
 *
 * @author Paul Hatalsky
 * @version 2/8/2016 Developed for CPE 103 Program 4 
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
public class BSTTests
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

   @Test
   public void test01_verifySuperClass() // points = 5
   {
      assertTrue(BST.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyInterfaces() // points = 5
   {
      Class[] faces = BST.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Iterable.class);
   }

   @Test
   public void test03_verifyFields() // points = 5
   {
      int nodeCount = 0;
      int intCount = 0;
      Field[] fields = BST.class.getDeclaredFields();

      assertTrue(fields.length == 2);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType().toString().contains("BSTNode"))
         {
            nodeCount++;
         }
         else if (fields[i].getType() == int.class)
         {
            intCount++;
         }
      }

      assertTrue(nodeCount == 1);
      assertTrue(intCount == 1);
   }

   @Test
   public void test04_verifyConstructors() // points = 5
   {
      int count = 0;
      Constructor[] cons = BST.class.getDeclaredConstructors();

      assertTrue(cons.length == 1);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();
     
      assertTrue(params.length == 0);
   }

   @Test
   public void test05_verifyMethods() // points = 5
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = BST.class.getDeclaredMethods();

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

      assertTrue(countPublic == 11);
      assertTrue(countProtected == 0);
      
      // Removed test
      //assertTrue(countPackage == 1);
   }

   @Test
   public void test06_verifyInnerClasses() // points = 5
   {
      Class[] classes = BST.class.getDeclaredClasses();

      // Check for expected number of...
      assertTrue(classes.length == 5);
      
      // Make sure they have correct access and fields... 
      for (int i = 0; i < 5; i++)
      {
System.out.println(classes[i]);
         int m = classes[i].getModifiers();
         assertTrue(!Modifier.isProtected(m) && !Modifier.isPublic(m));
      }
     
   }

   @Test
   public void test07_sizeAtConstruction() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.size() == 0);
   }

   @Test
   public void test08_containsAtConstruction() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      assertFalse(bst.contains(999));
   }

   @Test
   public void test09_insertContains() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 128, 119};

      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         assertTrue(bst.size() == i + 1);

         for (int j = 0; j <= i; j++)
         {
            assertTrue(bst.contains(values[j]));
            assertFalse(bst.contains(values[j] - 1));
            assertFalse(bst.contains(values[j] + 1));
         }
      }
   }
   
   @Test(expected = NoSuchElementException.class)
   public void test10_maximumEmpty() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      bst.maximum();
   }
   
   @Test
   public void test11_minimum() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         if (i < 1)
         {
            assertTrue(bst.minimum() == 99);
         }
         else if (i < 5)
         {
            assertTrue(bst.minimum() == -4);
         }
         else
         {
            assertTrue(bst.minimum() == -89);
         }
      }
   }

   @Test
   public void test12_maximum() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         if (i < 2)
         {
            assertTrue(bst.maximum() == 99);
         }
         else if (i < 8)
         {
            assertTrue(bst.maximum() == 167);
         }
         else
         {
            assertTrue(bst.maximum() == 178);
         }
      }
   }

   @Test
   public void test13_toSortedList() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);
      }

      ArrayList<Integer> list = new ArrayList<Integer>();
      bst.toSortedList(list);

      int[] expected = new int[] {-89, -4, 13, 55, 78, 99, 139, 167, 174, 178};

      for (int i = 0; i < values.length; i++)
      {
         assertTrue(expected[i] == list.get(i));
      }
   }

   /////////////////////////////////////////////////////////////////////////////
   // New tests for Lab 8
    
   @Test(expected = NoSuchElementException.class)
   public void test14_removeAtConstruction() // points = 4
   {
     BST<Integer> bst = new BST<Integer>();
     bst.remove(99);
   }

   @Test
   public void test15_removeListOfOne() // points = 4
   {
     BST<Integer> bst = new BST<Integer>();
     bst.insert(99);
     bst.remove(99);

     assertTrue(bst.size() == 0);
     assertFalse(bst.contains(99));
   }

   @Test
   public void test16_removeListOfTwoLeft() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(88);

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(88));
      assertFalse(bst.contains(99));

      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(88);
     
      bst.remove(88);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(88));
   
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   public void test17_removeListOfTwoRight() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(108);

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(108));
      assertFalse(bst.contains(99));

      bst.remove(108);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(108));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(108);
     
      bst.remove(108);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(108));
   
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   public void test18_removeListOfThreeCompleteTree() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();

      // Root, root, root... 
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(88);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertTrue(bst.contains(99));
      
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));

      // Left, right, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertFalse(bst.contains(88));

      // Right, left, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(99);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(77));

      bst.remove(77);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
   }

   @Test
   public void test19_removeAllLargeTree() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
      int array[] = new int[]{34, 55, -33, 66, -87, 5, 7, 111, -49, -77, -3, 2, 8, -9, 11};

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      for (int i = 0; i < array.length; i++)
      {
         bst.remove(array[i]);

         assertTrue(bst.size() == array.length - i - 1);
         assertFalse(bst.contains(array[i]));

         for (int j = i + 1; j < array.length; j++)
         {
            assertTrue(bst.contains(array[j]));
         }
      }
   }

   @Test
   public void test20_treeHeightEmpty() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.treeHeight() == -1);
   }

   @Test
   public void test21_treeHeightOne() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(12);
      assertTrue(bst.treeHeight() == 0);
   }

   @Test
   public void test22_treeHeightVariousGreaterThanOne() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();

      // one Left (1)
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.treeHeight() == 1);
      
      // two left (2)
      bst.insert(66);
      assertTrue(bst.treeHeight() == 2);

      // two left and one right (2)
      bst.insert(95);
      assertTrue(bst.treeHeight() == 2);

      // two left and two right (2)
      bst.insert(97);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three left (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three right (2)
      bst.remove(79);
      bst.insert(93);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight seven complete (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight eight complete (right) (3)
      bst.insert(99);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left) (3)
      bst.remove(99);
      bst.insert(55);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left middle) (3)
      bst.remove(55);
      bst.insert(80);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (right middle) (3)
      bst.remove(80);
      bst.insert(91);
      assertTrue(bst.treeHeight() == 3);
   }
   
   @Test
   public void test23_treeHeightRandomBig() // points = 1
   {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -387));
      assertTrue(bst.treeHeight() == 20);
   }
    
   @Test
   public void test24_internalPathLengthEmpty() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.internalPathLength() == -1);
   }

   @Test
   public void test25_internalPathLengthOneElement() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(99);
      assertTrue(bst.internalPathLength() == 0);
   }
   
   @Test
   public void test26_internalPathLengthVariousGreaterThanOneElement() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();

      // one Left
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.internalPathLength() == 1);

      // two left
      bst.insert(55);
      assertTrue(bst.internalPathLength() == 3);

      // one right
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 1);

      // two right
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 3);

      // three complete
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(77);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 2);

      // four complete (left)
      bst.insert(66);
      assertTrue(bst.internalPathLength() == 4);

      // five complete (left-right)
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 6);

      // six complete (left-right-middle right)
      bst.insert(93);
      assertTrue(bst.internalPathLength() == 8);

      // seven complete
      bst.insert(79);
      assertTrue(bst.internalPathLength() == 10);

      // eight complete (a middle node)
      bst.insert(92);
      assertTrue(bst.internalPathLength() == 13);
   }

   @Test
   public void test27_internalPathLengthRandomBig() // points = 1
   {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -4873));
      assertTrue(bst.internalPathLength() == 10162);
   }

   @Test(timeout = 360)
   public void test28_insertBigOhNotTestedInLab07() // points = 4
   {
      BST<Integer> bst = makeTree(makeRandomArray(100000, -687));
   }
   
   @Test(timeout=200)
   public void test29_containsBigOhNotTestedInLab07() // points = 4
   {
      int[] array = makeRandomArray(40000, -352);
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         bst.contains(array[i]);
      }
   }
   
   @Test(timeout=320)
   public void test30_toSortedListBigOhNotTestedInLab07() // points = 4
   {
      int size = 80000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -629));

      ArrayList<Integer> list = new ArrayList<Integer>(size);
      bst.toSortedList(list);
   }
   
   @Test(timeout=240)
   public void test31_treeHeightBigOh() // points = 4
   {
      int size = 100000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -85194));

      bst.treeHeight();
   }
   
   @Test(timeout=360)
   public void test32_internalPathLengthBigOh() // points = 4
   {
      int size = 100000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -85194));

      bst.internalPathLength();
   }
   
   @Test(timeout=360)
   public void test33_removeBigOh() // points = 4
   {
      int[] array = makeRandomArray(35000, -772);
      BST<Integer> bst = makeTree(array);

      for (int value  : array)
      {
         bst.remove(value);
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // New tests for Program 4...

   @Test(expected = NoSuchElementException.class)
   public void test34_getAtConstruction()
   {
      BST<Integer> bst = new BST<Integer>();
      bst.get(99);
   }

   @Test(expected = NoSuchElementException.class)
   public void test35_getNoSuchElement()
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(-44);
      bst.get(99);
      
   }

   @Test
   public void test36_getBasic()
   {
      int[] array = new int[]{55, -88, 22, 66, 77, -11, 33, 44, 11, -22};
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         assertTrue(bst.get(new Integer(array[i])).equals(new Integer(array[i])));
      }
   }
   
   @Test
   public void test37_getRandomBig()
   {
      int [] array = makeRandomArray(5000, -11);
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         // Made sure deep Integer objects work in getBasic test.
         assertTrue(bst.get(array[i]) == array[i]);
      }
   }

   @Test(timeout = 38)
   public void test38_getBigOh()
   {
      int[] array = makeRandomArray(1100, -34);
      BST<Integer> bst = makeTree(array);

      for(int i = 0; i < array.length * 5; i++)
      {
         bst.get(array[i % array.length]);
      }
   }

   @Test
   public void test39_iteratorEmpty()
   {
      BST<Integer> bst = new BST<Integer>();

      for(Integer i : bst)
      {
         fail();
      }
   }
  
   @Test
   public void test40_iteratorBasic()
   {
      int[] array = new int[]{55, -88, 22, 66, 77, -11, 33, 44, 11, -22};
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertTrue(element.equals(array[i]));
         i++;
      }

      assertTrue(i == array.length);

      Iterator<Integer> itOne = bst.iterator();
      Iterator<Integer> itTwo = bst.iterator();

      assertTrue(itOne != itTwo);
   }

   @Test
   public void test41_iteratorRandomBig()
   {
      int [] array = makeRandomArray(5000, -117);
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertTrue(element.equals(array[i]));
         i++;
      }
      
      assertTrue(i == array.length);
   }

    /* This test should detect if code constructs an O(N) stack
    * at construction (should be O(logN) stack).
    */
   // Test Removed to allow alternate implementation.
/*   @Test(timeout = 200)
   public void test42_iteratorConstructionBigOh()
   {
      int [] array = makeRandomArray(10000, -17);
      BST<Integer> bst = makeTree(array);

      for(int i = 0; i < 10000; i++)
      {
         Iterator<Integer> it = bst.iterator();
      }
   }
*/
   @Test(timeout = 120)
   public void test43_iteratorNextBigOh()
   {
      int [] array = makeRandomArray(10000, -333);
      BST<Integer> bst = makeTree(array);
      double sum = 0;

      for(int i = 0; i < 50; i++)
      {
         for (Integer element : bst)
         {
            sum += element;
         }
      }
   }

   @Test(expected = UnsupportedOperationException.class)
   public void test44_iteratorRemove()
   {
      BST<Integer> bst = new BST<Integer>();
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

   private BST<Integer> makeTree(int[] array)
   {
      BST<Integer> bst = new BST<Integer>();

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      return bst;
   }
} 
