import java.util.*;

public class TranslateTransaction{
    
    static Map<String, String> romanNumeralsToken = new HashMap<String, String>();
    static Map<String, Integer> romanNumeralsTokenValue = new HashMap<String, Integer>();
    static Map<String, String> questions = new HashMap<String, String>();
    static ArrayList<String> credits = new ArrayList<String>();
    static Map<String, Integer> elementValueList = new HashMap<String, Integer>();
    
    /**
     * processline adds the input to various maps<K,T> based on different conditions.
     * @param line
     */
    public static void processQuery(String line){
        String arr[] = line.split("((?<=:)|(?=:))|( )");
        if (line.endsWith("?")){
            questions.put(line,"");
        }else if (arr.length == 3 && arr[1].equalsIgnoreCase("is")){
            romanNumeralsToken.put(arr[0], arr[arr.length-1]);
        }else if(line.toLowerCase().endsWith("credits")){
            credits.add(line);
        }
    }
    
    /**
     * Maps tokens to Roman equivalent
     * {pish=X, tegj=L, prok=V, glob=I}
     */
    public static void translateQuery(){
        
        Iterator it = romanNumeralsToken.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry token = (Map.Entry)it.next();
            int integerValue = Utils.romanToInteger(token.getValue().toString());
            romanNumeralsTokenValue.put(token.getKey().toString(), integerValue);
        }
        mapMissingEntities();
    }
    
    /**
     * FInds the value of elements by decoding queries like [glob glob Silver is 34 Credits]
     */
    private static void mapMissingEntities(){
        for (int i = 0; i < credits.size(); i++) {
            deCodeMissingQuery(credits.get(i));
        }
    }
    
    /**
     * Calculates the values of various elements and appends the same to map elementValueList.
     * elementValueList :{Gold=14450.0, Iron=195.5, Silver=17.0}
     * @param query
     */
    private static void deCodeMissingQuery(String query){
        String array[] = query.split("((?<=:)|(?=:))|( )");
        int splitIndex = 0;
        int creditValue = 0; String element= null; String[] valueofElement = null;
        for (int i = 0; i < array.length; i++) {
            if(array[i].toLowerCase().equals("credits")){
                creditValue = Integer.parseInt(array[i-1]);
            }
            if(array[i].toLowerCase().equals("is")){
                splitIndex = i-1;
                element = array[i-1];
            }
            valueofElement = java.util.Arrays.copyOfRange(array, 0, splitIndex);
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < valueofElement.length; j++) {
            stringBuilder.append(romanNumeralsToken.get(valueofElement[j]));
        }
        int valueOfElementInDecimal = Utils.romanToInteger(stringBuilder.toString());
        if(valueOfElementInDecimal == 0){
        	System.out.println(query + " I have no idea what you are talking about."); 
        }else if (valueOfElementInDecimal == -1){
        	//Do nothing invalid input
        	System.out.println(query); 
        }else{
        	elementValueList.put(element, creditValue/valueOfElementInDecimal);
        }
    }
    
    /**
     * processReplyForQuery() itertates over the questions map that contain all the valid queries as keys.
     * It further invokes processReply() on each key for processing the response.
     */
    public static void processReplyForQuery(){
        Map<String, String> map = questions;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            processReply(entry.getKey());
        }
    }
    
    private static void processReply(String query){
        if (query.toLowerCase().startsWith("how much")){
            getRomanNumeralValue(query);
        }
        else if (query.toLowerCase().startsWith("how many")){
            getCreditValueOfElement(query);
        }
    }
    
    /**
     * getRomanNumeralValue() processes those queries seeking the Integer equivalent of any RomanNumeral and prints the result.
     * @param query
     */
    public static void getRomanNumeralValue(String query){
        if (Utils.isValidinput(query)== true){
            ArrayList<String> tokenValueToRoman = new ArrayList<String>();
            ArrayList<String> tokenValue = Utils.splitQuery(query);
            for (int i = 0; i < tokenValue.size(); i++) {
                tokenValueToRoman.add(romanNumeralsToken.get(tokenValue.get(i)));
            }
            int value = Utils.romanToInteger(tokenValueToRoman.toString());
            if(value == 0){
            	System.out.println(query+" : I have no idea what you are talking about");
            }else if (value == -1){
            	//Do nothing invalid input
            	System.out.println(query);
            }else{
            	tokenValue.add("is");tokenValue.add(Integer.toString(value));
            	Utils.outputFormatter(tokenValue);
            }
        }
        else{
            System.out.println(query+" : I have no idea what you are talking about");
        }
    }
    
    
    /**
     * getCreditValueOfElement() processes those queries seeking the Credit value of any quantity of elements and prints the result.
     * @param query
     */
    private static void getCreditValueOfElement(String query){
        if (Utils.isValidinput(query) == true){
            ArrayList<String> tokenValue = Utils.splitQuery(query);
            ArrayList<String> tokenValueToRoman = new ArrayList<String>();
            String element = null;
            for (int i = 0; i < tokenValue.size(); i++) {
                if(romanNumeralsToken.get(tokenValue.get(i)) != null){
                    tokenValueToRoman.add(romanNumeralsToken.get(tokenValue.get(i)));
                }
                else if (elementValueList.get(tokenValue.get(i)) != null){
                    element = tokenValue.get(i);
                }
                else{
                    System.out.println(query+" : I have no idea what you are talking about");
                }
            }
            int elementValue = (Utils.romanToInteger(tokenValueToRoman.toString()) * elementValueList.get(element));
            if(elementValue == 0){
            	System.out.println(query+" : I have no idea what you are talking about");
            }else if (elementValue == -1){
            	//Do nothing invalid input
            	System.out.println(query);
            }else{
            	 tokenValue.add("is");
            	 tokenValue.add(Integer.toString(elementValue));
            	 tokenValue.add("Credits");
                 Utils.outputFormatter(tokenValue);
            }
        }
        else{
            System.out.println(query+" : I have no idea what you are talking about");
        }
    }
    
}
