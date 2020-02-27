package cse360assign2;

/**
 *   Author: Brett Brophy
 *   class ID: 319
 *   CSE360 Assignment#1 (Original Version)
 *   File contains impleList class, see SimpleList class description for more details
 **/

 
class SimpleList_InitialVersion
{
    /**
     *   SimpleList contains method pertaining to basic array operations. These include
     *   add, remove, search, toString methods. SimpleList class is connected to the Junit test file.
     *   There is no main method, output, or input, the methods only exist for the purpose
     *   of having their functionality tested by the junit file. 
    **/
  
  private int list [];    
  private int count = 0;  
  
  public SimpleList_InitialVersion ()
  {
  list = new int[11];   
  }

 /**
  * 
  *   Add method takes an integer parameter called value and adds it to the head of an array called list. 
  *   The array has a size capacity of 10, and is bounded by the constraint count. Count is incremented
  *   after each call to add. Notice that there are some parts in the code, such as in SimpleList () method
  *   where the array size is declared  as 11 instead of 10. It was coded this way in order to compensate for
  *   various index out of bounds that can occur.. However, it has been coded to only hold the most recent
  *   ten integers entered. After ten integers have been entered, the 11th will fall off of the list, and the 
  *   newest one will appear at the head of the list. 
  */   
 
  public void add (int value)
  {
  
    if (count<11)
    {
      for (int i = count; i >= 1; i--)  // traverse list backwards starting from count
      {
          list[i] = list[i-1];    // shift elemets one place to the right   
       }      
      list[0] = value;
      count++; 
     }
  
    if (count >= 11)  
    {  
     count--;
    }   
  }

  /**
   * Remove() method takes in an integer parameter and removes every occurrence of the character from
   * the array. This is not accomplished by deleting the elements themselves, but taking all the other 
   * elements (elements != deleteCharacter) and storing them in a DIFFERENT array called list2. This
   * step is completed by the termination of the first for loop. The second for loop takes the 
   * elements from list2 and then stores then BACK in list, thus simultaneously putting them in the correct
   * order, as well as replacing all elements of list in the range of count. As such, count is decremented
   * in every case that list[i] equals deleteCharacter. Notice that there may be elements in list that do
   * not get replaced by the elements in list2 after the second for loop. These elements are irrelevant,
   * as count will never be in the range these indices when the print methods are called. 
   */
  
  public void remove (int deleteCharacter)
  {
    
     int list2 [];   
     list2 = new int[10]; 
        
      for (int i = 0, j = 0; i < 10; i++, j++)  
      { 
        if (list[i] != deleteCharacter)
        {
         list2[j] = list[i];    // store elements from list into list2 if list[i] does not equal deleteCharacter     
        }
            else 
            {
            j--;  // keep index j on the same spot if a match is found
            count--;
            }
      }    
    
      for (int i = 0; i < count; i++)
      {
      list[i] = list2[i];        // store elemts from list2 back into list
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
     str = str + " " + list[i];
    }

     str = str.substring(1, str.length());  // strings such as " abc" will be trimmed to "abc"
    
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
       if (list[i] == elementPrameter)
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