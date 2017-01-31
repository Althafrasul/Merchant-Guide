import java.util.*;
import java.lang.*;
import java.io.*;

class IntergalacticTransaction
{
    public static void main (String[] args) throws java.lang.Exception{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = stdin.readLine()) != null && line.length()!= 0) {
            TranslateTransaction.processQuery(line);
        }
        TranslateTransaction.translateQuery();
        TranslateTransaction.processReplyForQuery();
    }
}
