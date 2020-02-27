package cse360assign2;

/**
 *   Author: Brett Brophy
 *   class ID: 319
 *   CSE360 Assignment#2 Version 1
 *   
 *   File contains impleList class, see SimpleList class description for more details
 *   
 *   Changes to this version: add and remove methods hevily updated,list is
 *   now globallist, as well as slight changes to tostring method
 **/

 
class SimpleList_Version1
{
    /**
     *   SimpleList contains method pretaining to basic array operations. These include
     *   add, remove, search, toString methods. SimpleList vlass is connected to the Junit test file.
     *   There is no main method, output, or input, the methods only exist for the purpose
     *   of having their functionality tested by the junit file. 
    **/
  
  
  private int count = 0;  
  
    private int indexSize = 10;  
  private int globalList [];
  
  
  public SimpleList_Version1 ()
  {
  globalList = new int[11];   
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
   * toString() method concatinates previous sections of array list with ones from newly called methods,
   * seperating the entries with a space. Elements will appear newest first, oldest last, with elements
   * older than 10 calls falling off the list. There was an issue with the add method that the very first 
   * index (most recent) is white space in index zero. The fix to this is a substring assignment of the
   * string str, which removes the very first element (the white space). 
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