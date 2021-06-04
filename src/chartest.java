

import java.util.List;

public class chartest {

    public static void main(String[] args) {

      StringBuilder s = new StringBuilder();
      s.append("a");
      s.append(System.lineSeparator());
      s.append("b");


      String s1 = s.toString();
        System.out.println(s1);

      String[] list = s1.split("\\\\n");

      StringBuilder result = new StringBuilder();

      for(String l: list) {
          result.append(l);
          result.append('\n');
      }

      result.deleteCharAt(result.length() - 1);
        System.out.println(result.toString());

        System.out.println("last");

        //System.out.println(s.toCharArray());
        //char[] encryptedString = P_RC4(getKey(),s.toCharArray(), s.length());

        //System.out.println(String.valueOf(encryptedString));
    }

    public static char[] P_RC4(char[] key, char[] input, int len)
    {
        char[] output = new char[len];
        char S[] = new char[256];
        char K[] = new char[256];
        char temp = 0;

        int i,j,t,x;

        j = 1;
        for(i=0;i<256;i++)
        {
            S[i] = (char)i;
            if(j > key.length) j = 1;
            K[i] = key[j-1];
            j++;
        }
        j = 0;
        for(i=0;i<256;i++)
        {
            j = (j + S[i] + K[i]) % 256;
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
        i = j = 0;
        for(x=0;x<len;x++)
        {
            i = (i+1) % 256;
            j = (j + S[i]) % 256;
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            t = (S[i] + (S[j] % 256)) % 256;

            int nVal = input[x] ^ S[t];
            output[x] = (char)nVal;
        }
        return output;
    }

    public static char[] getKey()
    {
        String key ="661C62F3-862F-48ab-8712-F3BB9A2BC239";
//        char keyC[] = new char[key.length()];
//        char chatTemp[] =  key.toCharArray();
//        for(int i=0;i<chatTemp.length;i++)
//        {
//          keyC[i]     = chatTemp[i];
//        }
        return key.toCharArray();
    }
}
