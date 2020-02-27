package cse360assign2;

/**
 *   Author: Brett Brophy
 *   class ID: 319
 *   CSE360 Assignment#2 Version 2
 *   File is only SimpleList.java, however it is on github with 
 *   two other older versions
 *    
 *   Changes to this version:
 *   added size, last, first, and append methods
 *   
 **/



class SimpleList_Version2
{
    
     /**
     *   SimpleList contains method pertaining to basic array operations, such as
     *   add, remove, search, toString, last, first, size, and append methods. Descriptions
     *   of the functionality of these methods are described on the header of the methods themselves.

     *   
    **/
    
  private int indexSize = 10;  
  private int globalList [];
  private int count = 0;  

   public SimpleList_Version2 ()
   {  
    globalList = new int[14];  // 14 is an arbitrary value and has no significance in the program
   }

   
   /**
    * 
    * add method adds integers to a standard array by adding each new value to the front of the list.
    * The process is rather complicated, as the size of the list must be increased by 50% if the max
    * capacity (which is 10 by default) is hit. Once the count has passed 10, the new size will become
    * 15. First, create the main list if no count values have been added. Once count equals indexsize (the)
    * index that will be changed from 10 to 15, from 15 to 22, etc, will have such an equation executed.
    * The next part involves several array transfers. Because the array size is fixed, the only way to changed 
    * it is to create a new array all together, but doing so will erase the initial values of it. This
    * issue is dealt with by storing all the values from globalList into a temp array, creating a new
    * globallist with the correct count, and filling back the array from temp. The final for loop uses
    * the same logic from the first version, where all the elements are shifted one space to the right,
    * and the parameter integer of the original function call is placed in index 0 of the list. 
    * 
    */
   
   public void add (int value)
   {
         
       int newSize = indexSize;

         if (count == 0)
         {
         globalList = new int[newSize]; 
         }
       
           if (count==indexSize)
           {
               
           newSize = ((count) + (count / 2));  // calculate new array size of 50% of the original

           indexSize = indexSize + (indexSize / 2);  // insure that the condition is always correct
        
           int [] tempList = new int[indexSize + 1];
         
               for (int i = 0; i < count; i++)
               {
               tempList[i]=globalList[i];     // store into temp list
               }
         
               globalList = new int[newSize];   // create new global list
         
                   for (int i = 0; i < count; i++)
                   {
                    globalList[i] = tempList[i];   // store temp back into global list  
                   }    
                   
           }
        
          
            for (int i = count; i >= 1; i--)  // traverse list backwards starting from count
            {
             globalList[i] = globalList[i - 1];    // shift elements  one place to the right   
            } 
        
             globalList[0] = value;
             count++;
              
   }

   /**
    * 
    * By far the most complex and time-consuming method of this program. Remove method takes an integer
    * parameter and removes ALL occurrences of the value. This was particularly  difficult for several
    * of reasons. Elements are typically removed in arrays by shifting the elements to the left
    * of the parameter, this replacing it with its rightmost element. However, this approach does not work
    * well when there are two or more consecutive elements in the array. I fixed this issue by using
    * recursion. The even more frustrating issue is the fact that the array must be decreased by 25% 
    * when there are more than 25% spaces not occupied by a value within the scope of count. I will
    * admit, I could not get this part to work, and too many hours of my time was wasted trying to do so.
    * Every other function and their specifications works perfect to the best of my knowledge.. And this
    * method does correctly remove all the characters without any issues, from what I tested. The ONLY
    * issue I noticed is that the size is not decremented correctly, but the remove method is
    * otherwise unaffected. 
    * 
    * Logic of the method:
    * the values from the global list pertaining to the add and append methods, are stored in templist.
    * The second block contains a nested for loop, where the outer one traverses templist and has a condition
    * to search for the integer desired to be removed. If a match is found, then the inner for loop
    * starts at the current index and moves the remainder of the list to the left, deleting the value specified by 
    * the parameter. This will NOT work in one call if there are two or more identical elements next to 
    * each other, as only one of them will be replaced by its rightmost value. This is fixed using the
    * last for loop which is recursive.
    * 
    */
   
   public void remove (int deleteCharacter)
   {
      
       int [] tempList = new int[indexSize + 2]; // create temp list
      
         for (int i = 0; i < count; i++)
         {
          tempList[i] = globalList[i];     // store values into temp list
         }
          
            for (int i = 0; i < count; i++)
            {
               if (tempList[i] == deleteCharacter)
               {     
                  for (int j = i, k = 0; k <= count - i; j++, k++)
                  {  
                   tempList[j] = tempList[j + 1];  // once a match is found, move the remaining values to the left
                  }
                  count--;
               }
            }            
      
             if ((((indexSize - count) / indexSize) * 100) > 25)  // formula is not correct and this line almost never executes
              {
              indexSize = indexSize - (indexSize / 4); // The correct formula is (100-((count/a)*a)) > 25, but unfixable errors appeared when I used this
              }
      
              globalList = new int[indexSize];
      
                 for (int i = 0; i < count; i++)
                 {
                  globalList[i] = tempList[i];    
                 }
             
                    for (int i = 0; i < count; i++)
                    {
                       if (globalList[i] == deleteCharacter)
                       {
                       remove(deleteCharacter);  // recursive call to fix consecutive occurrences of the same character
                       }
                    }               
   }
  
   
   /**
    * 
    * Append method uses nearly the exact same logic as the add method, except for the final block. 
    * If could is zero then there are no elements in the array, and calling this method with that condition
    * in place will of course add the value to the zero index of the array. Otherwise, it  the value must
    * be appended at the tail of the list, and this is accomplished by setting the global list at index
    * count equal to the method parameter. 
    * 
    * 
    */
   
   
   public void append (int value)
   {
         
       int newSize = indexSize;

       if (count == 0)
       {
       globalList = new int[newSize]; 
       }
       
           if (count == indexSize)
           {
            newSize = ((count) + (count / 2));
            indexSize = indexSize + (indexSize / 2);
        
            int [] list2 = new int[indexSize + 1];
         
              for (int i = 0; i < count; i++)
              {
              list2[i]=globalList[i];     
              }
         
              globalList = new int[newSize]; 
         
              for (int i = 0; i < count; i++)
              {
              globalList[i] = list2[i];     
              }
                   
          }
        
              if (count == 0)
              {
               globalList[0] = value;
              }
                 else if (count >= 1)
                 {
                 globalList[count]=value;  // append value to tail of list
                 }
                 
                 count++;    
                 
   }
  
   
   /**
    * 
    * first method returns the first element of the array, which is the zero element. Returns zero
    * if no element in the array.
    * 
    */
   
  public int first()
  {
      if (count==0)
      {
       return -1;
        }
      else
      {    
       return globalList[0];
      }
        
  }
  
     /**
    * 
    * last method returns the last element in the array. Returns -1 if no elements are in the array.
    * Notice that the count has -1 in the index. The count represents all numbers starting from one onwards,
    * meaning zero is not included. Yet indices include zero, meaning that the index at the last element (count)
    * of the array will result in an out of bounds error. Thus
    * is is made count - 1. 
    * 
    */
  
  public int last()
  {
        int lastValue = 0; 
      
         if (count == 0)
        {
          lastValue = -1;   //  globalList[0];
        }
           else if (count >= 1)
           {
            lastValue = globalList[count - 1];
           }
            return lastValue;
   }
  
   /**
    * 
    * size represents all possible spaces in the array not necessarily including ones bounded by count.
    * These spaces are determined by the add and remove functions, where the original capacity is 10, and
    * this number will increase by 50% of count passes 10, and will decrease by 25% if there are more
    * than 25% empty spaces (I wish).
    * 
    */
   
  public int size()
  {
      return indexSize;
  }
  
    /**
   * Count means the number of elements in stored in the array list. It is incremented 
   * or decremented in the add() and remove() methods, and the result can be retrieved
   * in the method below.  
   */
  
  public int count ()
  {
   return count;
  }
  
    /**
   * 
   * toString() method concatenates previous sections of array list with ones from newly called methods,
   *  separating the entries with a space. Elements will appear newest first, oldest last, with elements
   * older than 10 calls falling off the list. There was an issue with the add method that the very first 
   * index (most recent) is white space in index zero. The fix to this is a substring assignment of the
   * string str, which removes the very first element (the white space). Notice that a new block has been
   * added to this version, namely the if (count > 0) condition. This is used to circumvent out of bounds
   * errors when count = 0; 
   */
  
  public String toString()
  { 
      
   String str = "";
   
   for (int i = 0; i < count; i++)
    {
     str = str + " " + globalList[i];
    }
   
     if (count > 0)
     {
      str = str.substring(1, str.length()); 
     }
   
     return str;
   
  }
  
    /**
   * 
   * search() takes an integer prameter and returns the index of the first occurrence of the number,
   * and retrns -1 if no match is found. If no match is found by the termination of the for loop,
   * the next condition assigns index variable to -1. 
   */
  
  public int search (int elementPrameter)
  {
    
    int index = 0;
    int flag = -1;
    
     for (int i = 0; i <= count; i++)
     {
       if (globalList[i] == elementPrameter)
       {
       index = i;
       flag = 0;
       i = count + 1;
       }    
     }   
 
    if (flag == -1)
    {
    index = flag;   
    }
 
    return index;
  }
  
}