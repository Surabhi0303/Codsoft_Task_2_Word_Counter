import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Word_Counter {



   public static int word_count=0;
  public   static int punctuation_count =0;
  public static int unique_words=0;
    public static int repeated_words=0;


   static Vector<Integer> v=new Vector<>();

    static char[] symbols= {
            '!', '@', '#', '$', '%', '&', '*', '(', ')', '-', '_', '+', '=', '[', ']',
            '{', '}', '|', '\\', ':', ';', '<', '>', ',', '.', '/', '?', '~', '`', '\'',
            '\"', '<', '>', '&', '^', '?', '°', '©', '®', '™', '§', '±', '÷', '×', 'µ',
            '¶', '¤', '¢', '£', '€', '¥', '₹', '₽', '₱', '₩', '₪', '₦', '₸', '₮', '₲', '₳',
            '₡', '₵', '₫', '₭', '₿'
    };

   static HashMap<String,Integer> map =new HashMap<>();
    public static void main(String[] args) {




    }



    public static Vector<Integer> Word_Counter(String str)
    {









        char charArray[]=str.toCharArray();

        int i=0;
        while (i<str.length())

        {

            //Removing Spaces
            while(charArray[i]==' '&& i<str.length())
            {
                i++;
            }

            if(i>=str.length())
            {
                break;
            }



            if(isCharInArray(symbols,charArray[i]))
            {


                punctuation_count++;
                i++; // Move to the next character
                continue;


            }




            int j=i+1;




            while (j<str.length() && !isCharInArray(symbols,charArray[j]) && charArray[j]!=' '
                   )

            {

                j++;
            }

            String word=str.substring(i,j);//end index -1;

            int count = map.getOrDefault(word, 0);

            map.put(word,count+1);


            word_count++;





            i=j;












        }






        for (String word : map.keySet()) {
            int count = map.get(word);


            if(count==1)
            {
                unique_words++;
            }

            else {
                repeated_words++;
            }
        }


        v.add(word_count);
        v.add(punctuation_count);
        v.add(unique_words);
        v.add(repeated_words);



        return v;
    }


    public static boolean isCharInArray(char[] array, char targetChar) {
        for (char element : array) {
            if (element == targetChar) {
                return true;
            }
        }
        return false;
    }
}



