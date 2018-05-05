# RsaUsingCrt
A java program implementing public-key encryption algorithm, RSA using Chinese Remainder Theorem

A brief explanation of the code: 


The various methods in the code are:

  1. isPrime – takes a number and returns whether it is prime or not
  
  2. egcd – takes 2 numbers and returns their Greatest Common Divisor along with the remainder and quotient to help with the modinv method
  
  3. modinv – takes 2 numbers and returns the modular inverse of the first number with respect to the other number
  
  4. power – takes a and b and returns a^b. Helps in the encryptDecrypt method
  
  5. encryptDecrypt – takes a, b and c and returns a^b(mod c) 


The main class:
  
  1. First, we choose random prime integers p and q between a self-defined range using the isPrime method and Math.random. 
  
  2. calculate n and f(n)
  
  3. choose a prime integer e
  
  4. calculate d which is modular inverse of e with respect to f(n) using the modinv method
  
  5. calculate dP, dQ and qInv all making use of modinv method
  
  6. ask for user input message which is a string message
  
  7. store the ascii values of each character of message in an int array
  
  8. convert the ascii values to cipher-text and store in a long array cipher
  
  9. use garner’s formula and calculate m1, m2, h and finally ascii value m making use of encryptDecrypt method
  
  10. convert m to char and store in a char array decMessage
  
  11. convert array to string finalMessage using String.valueOf. finalMessage is our decrypted message
