# RsaUsingCrt
A java program implementing public-key encryption algorithm, RSA using Chinese Remainder Theorem
A brief explanation of the code: 
The various methods in the code are:
  isPrime – takes a number and returns whether it is prime or not
  egcd – takes 2 numbers and returns their Greatest Common Divisor along with the remainder and quotient to help with the modinv method
  modinv – takes 2 numbers and returns the modular inverse of the first number with respect to the other number
  power – takes a and b and returns a^b. Helps in the encryptDecrypt method
  encryptDecrypt – takes a, b and c and returns a^b(mod c) 
The main class:
  First, we choose random prime integers p and q between a self-defined range using the isPrime method and Math.random. 
  calculate n and f(n)
  choose a prime integer e
  calculate d which is modular inverse of e with respect to f(n) using the modinv method
  calculate dP, dQ and qInv all making use of modinv method
  ask for user input message which is a string message
  store the ascii values of each character of message in an int array
  convert the ascii values to cipher-text and store in a long array cipher
  use garner’s formula and calculate m1, m2, h and finally ascii value m making use of encryptDecrypt method
  convert m to char and store in a char array decMessage
  convert array to string finalMessage using String.valueOf. finalMessage is our decrypted message
