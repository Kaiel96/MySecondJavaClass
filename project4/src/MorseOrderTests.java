
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat.Field;

import org.junit.Test;

public class MorseOrderTests
{
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
      java.lang.reflect.Field[] fields = MorseOrder.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }
   
   @Test
   public void test08_verifyMorseOrderMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(MorseOrder.class, 2, 0, 0, -1);
   }
   private void verifyMethods(Class cl, int expectedPublic,int expectedPackage,int expectedProtected,int expectedPrivate)
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

}
