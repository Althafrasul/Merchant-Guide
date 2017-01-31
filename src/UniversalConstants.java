import java.util.*;
import java.lang.*;

public class UniversalConstants {
    
    // Universal number system symbols
    public static final char ROMAN_M = 'M';
    public static final char ROMAN_D = 'D';
    public static final char ROMAN_C = 'C';
    public static final char ROMAN_L = 'L';
    public static final char ROMAN_X = 'X';
    public static final char ROMAN_V = 'V';
    public static final char ROMAN_I = 'I';
    
    // Metal Types
    
    static final int NONE = 0;
    static final int METAL_IRON = 1;
    static final int METAL_SILVER = 2;
    static final int METAL_GOLD = 3;
    
    // Universal Exchnage rate
    static final double IRON_PER_UNIT = 195.5;
    static final double SILVER_PER_UNIT = 17;
    static final double GOLD_PER_UNIT = 14450;
    
    // Rules for valid roman inputs
    static final String REGEX_MORE_THAN_3_OCCURANCE_I = ".*I.*I.*I";
    static final String REGEX_MORE_THAN_3_OCCURANCE_X = ".*X.*X.*X";
    static final String REGEX_MORE_THAN_3_OCCURANCE_C = ".*C.*C.*C";
    static final String REGEX_MORE_THAN_3_OCCURANCE_M = ".*M.*M.*M";
    static final String REGEX_V_MORE_THAN_ONE_OCCURANCE = ".*V.*V.*";
    static final String REGEX_D_MORE_THAN_ONE_OCCURANCE = ".*D.*D.*";
    static final String REGEX_L_MORE_THAN_ONE_OCCURANCE = ".*L.*L.*";
    static final String REGEX_MORE_THAN_1_OCCURANCE_OF_I_AS_PREFIX = "(I)\\1+(VXLCDM)";
    static final String REGEX_MORE_THAN_1_OCCURANCE_OF_X_AS_PREFIX = "(X)\\1+(LCDM)";
    static final String REGEX_MORE_THAN_1_OCCURANCE_OF_C_AS_PREFIX = "(C)\\1+(DM)";
    
    // Error Messages for invalid inputs
    static final String ERROR_MESSAGE_2_OCCURANCE = "Invalid Input D,L,V cannot occure twise";
    static final String ERROR_MESSAGE_4_OCCURANCE = "Invalid Input I,X,C,M cannot occure 4 times";
    static final String ERROR_MESSAGE_2_OCCURANCE_I_PREFIX = "Invalid Input I cannot occure more than once as prefix";
    static final String ERROR_MESSAGE_2_OCCURANCE_X_PREFIX = "Invalid Input X cannot occure more than once as prefix";
    static final String ERROR_MESSAGE_2_OCCURANCE_C_PREFIX = "Invalid Input C cannot occure more than once as prefix";
    
    public static int currentMetalType = -1;
    
}
