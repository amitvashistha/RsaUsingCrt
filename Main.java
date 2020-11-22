package com.Amit;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==========RSA with Chinese Remainder Theorem==========\n");
	    int i=0;
	    long p=0;
	    long q=0;

        System.out.println("\nSelecting 2 random prime numbers: ");
	    while(i<2){
            long randomNum = 1009 + (int)(Math.random() * (8964));
            if(isPrime(randomNum)){
             if(i==1){
                 q=randomNum;
                    break;
                }
                p=randomNum;
                i++;
            }
        }
        System.out.println("\tp = "+p);
        System.out.println("\tq = "+q);
        System.out.println("\nCalculating n=pq: ");
        long n = p*q;
        System.out.println("\tn = p*q = "+p+" * "+q+" = "+n);
        System.out.println("\nCalculating Euler's Totient Function f(n) = (p-1)(q-1) = n - (p+q-1): ");
        long totientFunction = n-(p+q-1);
        System.out.println("\tf(n) = "+totientFunction);
        System.out.println("\nSelecting e such that 1 < e < f(n) and gcd(e, f(n)) = 1 (e and f(n)"+
                " are co-primes:\n\tSetting e = 5483");
        long e = 5483;
        System.out.println("\nCalculating d from de = 1 (mod f(n)) (d is the multiplicative inverse of e): ");
        long d = modinv(e, totientFunction);
        System.out.println("\td= "+d);
        System.out.println("\nCRT starts here: ");
        long dP = modinv(e,(p-1));
        long dQ = modinv(e,q-1);
        long qInv = modinv(q,p);
        System.out.println("\tdP = e^-1 mod (p-1) = "+e+" ^-1 mod "+(p-1)+" = "+dP
                + "\n\tdQ = e^-1 mod (q-1) = "+e+" ^-1 mod "+(q-1)+" = "+dQ
                +"\n\tqInv = q^-1 mod p ="+q+" ^-1 mod "+p+" = "+qInv);
        System.out.println("\nPublic key(e, n) = ("+e+", "+n+") and new Private key"+
                " for CRT based decryption (p, q, dP, dQ, qInv) = ("+p+", "+q+", "+dP+", "+dQ+", "+qInv+")");
        System.out.println("For Encryption of message m: c = m^e (mod n)");
        System.out.print("\nEnter Message m: ");
        String message = scanner.nextLine();
        int messageLength = message.length();
        int[] messageArrayInt = new int[messageLength];
        long[] cipher =  new long[messageLength];
        for(int j=0;j<messageLength;j++){
            char character = message.charAt(j);
            messageArrayInt[j]=(int)character;
            cipher[j] = (power(messageArrayInt[j], e).mod(BigInteger.valueOf(n))).longValue();
            System.out.println("\tEncrypting \""+message.charAt(j)+"\""+"(ascii = "+messageArrayInt[j]+
                    "): "+messageArrayInt[j]+" ^ "+e+" (mod "+n+") = "+cipher[j]);
        }
        System.out.print("Encrypted Message: ");
        for(int j=0;j<messageLength;j++)
            System.out.print(cipher[j]+" ");

        System.out.println("\nTo decrypt the encrypted message we"+
                " use Chinese Remainder Theorem(Garner's Formula): \n\t"+
                "m1 = (c ^ dP) % p \n\t"+"m2 = (c ^ dQ) % q \n\t"+
                "h = qInv * (m1 - m2) % p \n\t"+"m = m2 + h*q\n");
        char[] decMessage = new char[messageLength];
        for(int j=0;j<messageLength;j++){
            long m1 = (power(cipher[j], dP).mod(BigInteger.valueOf(p))).longValue();
            long m2 = (power(cipher[j], dQ).mod(BigInteger.valueOf(q))).longValue();
            long h = (qInv*(m1-m2))%p;
            int m = (int) (m2+h*q);
            decMessage[j] = (char)m;
            System.out.println("Decrypting "+cipher[j]+": \n\tm1 = "+"("+cipher[j]+" ^ "+dP+") % "+p+" = "+m1
                    +"\n\tm2 = ("+cipher[j]+" ^ "+dQ+") % "+q+" = "+m2
                    +"\n\th = "+qInv+" * ("+m1+" - "+m2+") % "+p+" = "+h
                    +"\n\tm = "+m2+" + "+h+" * "+q+" = "+m+"\n\t"+m+" \""+(char)m+"\"");
    }
        String finalMessage = String.valueOf(decMessage);
        System.out.println("Decrypted ciphertext m = "+finalMessage);

    }

    public static boolean isPrime(long num){
        if(num<=1)
            return false;
        if(num==2)
            return true;
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0)
                return false;
        }
        return true;
    }

    public static Egcd egcd(long a, long b){
        Egcd newegcd  = new Egcd();
        if(a==0){
            newegcd.setA(b);
            newegcd.setB(0);
            newegcd.setC(1);
            return newegcd;
        }else {
            Egcd negcd = egcd((b%a),a);
            long x = negcd.getC();
            long y = negcd.getB();
            negcd.setC(y);
            negcd.setB((x-(b/a)*y));
            return negcd;
        }
    }

    public static long modinv(long a, long m){
        Egcd newegcd = egcd(a,m);
        if(newegcd.getA()!=1){
            return 0;
        }else{
            return ((m+newegcd.getB())%m)%m;
        }
    }
    public static BigInteger power(long a, long b){
        int B = (int) b;
        BigInteger result = new BigInteger("1");
        result= result.multiply(BigInteger.valueOf(a));
        result = result.pow(B);
        return result;
    }

//    public static long encryptDecrypt(long a, long b, long n){
//        BigInteger result = power(a,b);
//        BigInteger bigN = BigInteger.valueOf(n);
//        result = result.remainder(bigN);
//        return result.longValue();
//    }



}
