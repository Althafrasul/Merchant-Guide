import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

 public static int romanToInteger(String romanNumeral) {
  Hashtable < Character, Integer > romanNumeralMap = map;
  int sumOfRomanNumeral = 0;
  int prev = 0;
  romanNumeral = formatString(romanNumeral);
  for (int i = romanNumeral.length() - 1; i >= 0; i--) {
   if(romanNumeralMap.containsKey(romanNumeral.charAt(i))){
				int temp = romanNumeralMap.get(romanNumeral.charAt(i));
				if (temp < prev)
					sumOfRomanNumeral -= temp;
				else
					sumOfRomanNumeral += temp;
				prev = temp;
		}
  }
  if(sumOfRomanNumeral != 0){
	  String error = checkSymentic(romanNumeral);
	 if(error != null){
		System.out.print("Error:" + error +" - "); 
		return -1;
	 }
  }
  return sumOfRomanNumeral;
 }

 /**
  * Formats the response to a query and displays it in readable format
  * @param output
  * @return
  */
 public static void outputFormatter(ArrayList < String > output) {
  System.out.println(output.toString().replace(",", "").replace("[", "").replace("]", ""));
 }

 /**
  * Applies regex on each input in the file to figure out the valid ones.
  * @param query
  * @return
  */
 public static boolean isValidinput(String query) {
  Pattern regex = Pattern.compile("[$&+,:;=@#|]");
  Matcher matcher = regex.matcher(query);
  if (matcher.find()) {
   return false;
  } else {
   return true;
  }

 }

 public static final Hashtable < Character, Integer > map = new Hashtable < Character, Integer > () {
  {
   put(UniversalConstants.ROMAN_I, 1);
   put(UniversalConstants.ROMAN_V, 5);
   put(UniversalConstants.ROMAN_X, 10);
   put(UniversalConstants.ROMAN_L, 50);
   put(UniversalConstants.ROMAN_C, 100);
   put(UniversalConstants.ROMAN_D, 500);
   put(UniversalConstants.ROMAN_M, 1000);
  }
 };

 private static String formatString(String stringToBeReplaced) {
  String formatedString = stringToBeReplaced.replace(",", "").replace("[", "").replace("]", "").replace(" ", "").trim();
  return formatedString;
 }

 /*
  *
  * 1) The symbols "I", "X", "C", and "M" can be repeated three times in
  * succession, but no more.
  *
  * 2) They may appear four times if the third and fourth are separated by a
  * smaller value, such as XXXIX.)
  *
  * 3) "D", "L", and "V" can never be repeated.
  *
  * 4) A number written in [16]Arabic numerals can be broken into digits. For
  * example, 1903 is composed of 1, 9, 0, and 3. To write the Roman numeral,
  * each of the non-zero digits should be treated separately. Inthe above
  * example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.
  */

 public final static String checkSymentic(String romanNumerals) {
  String errorMessage = null;

  // "I", "X", "C", and "M" can be repeated three times
  if (Pattern.compile(UniversalConstants.REGEX_MORE_THAN_3_OCCURANCE_I)
   .matcher(romanNumerals).find()) {
   errorMessage = UniversalConstants.ERROR_MESSAGE_4_OCCURANCE;
  }else if (Pattern.compile(UniversalConstants.REGEX_MORE_THAN_3_OCCURANCE_C)
  .matcher(romanNumerals).find()) {
  errorMessage = UniversalConstants.ERROR_MESSAGE_4_OCCURANCE;
  }else if (Pattern.compile(UniversalConstants.REGEX_MORE_THAN_3_OCCURANCE_X)
   .matcher(romanNumerals).find()) {
   errorMessage = UniversalConstants.ERROR_MESSAGE_4_OCCURANCE;
   }else if (Pattern.compile(UniversalConstants.REGEX_MORE_THAN_3_OCCURANCE_M)
		   .matcher(romanNumerals).find()) {
	   errorMessage = UniversalConstants.ERROR_MESSAGE_4_OCCURANCE;
   }

  // "D", "L", and "V" can never be repeated
  if (Pattern.compile(UniversalConstants.REGEX_V_MORE_THAN_ONE_OCCURANCE)
   .matcher(romanNumerals).find()) {
   errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE;
  }else if(Pattern.compile(UniversalConstants.REGEX_L_MORE_THAN_ONE_OCCURANCE)
		   .matcher(romanNumerals).find()){
	  errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE;
  }else if(Pattern.compile(UniversalConstants.REGEX_D_MORE_THAN_ONE_OCCURANCE)
		   .matcher(romanNumerals).find()){
	  errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE;
  }

  // "I" cannot occure more than once in from of C,X,L,C,D,M
  if (Pattern
   .compile(
    UniversalConstants.REGEX_MORE_THAN_1_OCCURANCE_OF_I_AS_PREFIX)
   .matcher(romanNumerals).find()) {

   errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE_I_PREFIX;
  }

  // "X" cannot occure more than once in from of L,C,D,M
  if (Pattern
   .compile(
    UniversalConstants.REGEX_MORE_THAN_1_OCCURANCE_OF_X_AS_PREFIX)
   .matcher(romanNumerals).find()) {

   errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE_X_PREFIX;
  
  }

  // "C" cannot occure more than once in from of D,M
  if (Pattern
   .compile(
    UniversalConstants.REGEX_MORE_THAN_1_OCCURANCE_OF_C_AS_PREFIX)
   .matcher(romanNumerals).find()) {

   errorMessage = UniversalConstants.ERROR_MESSAGE_2_OCCURANCE_C_PREFIX;
  }
  return errorMessage;
 }

 /**
  * Splits the query and returns an ArrayList containing only Roman numerals and elements
  * @param query
  * @return
  */
 public static ArrayList < String > splitQuery(String query) {
  ArrayList < String > queryArray = new ArrayList < String > (Arrays.asList(query.split("((?<=:)|(?=:))|( )")));
  int startIndex = 0, endIndex = 0;
  for (int i = 0; i < queryArray.size(); i++) {
   if (queryArray.get(i).toLowerCase().equals("is")) {
	   startIndex = i + 1;
   } else if (queryArray.get(i).toLowerCase().equals("?")) {
	   endIndex = i;
   }
  }
  String[] array = queryArray.toArray(new String[queryArray.size()]);
  return new ArrayList < String > (Arrays.asList(java.util.Arrays.copyOfRange(array, startIndex, endIndex)));
 }

}