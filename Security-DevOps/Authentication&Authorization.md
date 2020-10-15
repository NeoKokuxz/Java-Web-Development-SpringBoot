# Authentication and Authorization

## What is Authentication?
> Authentication confirms your identity. It is a process that proves that you are the person who you say you are. In the digital world, the most common way to authenticate is to use a username and password. For example, while logging into your eCommerce account, you prove your identity by providing an email and a password, sometimes followed by an OTP or a second factor. There are many other ways to authenticate, and these are depicted in the figure below.

## Common Mechanisms for Authentication
- Token-based authentication 
  - an object you have or you control proves who you are (see https://scotch.io/tutorials/the-ins-and-outs-of-token-based-authentication) (note: we do use this later in the form of JWT, but we first authenticate with the username and password, then sending the token on each subsequent request)

- OAuth 
  - is an industry-standard protocol for authorization that provides a token on your behalf once you’ve authenticated to the external service (see https://auth0.com/docs/protocols/oauth2 if you've ever seen a "Sign in with Google/Facebook/etc", this is likely done using OAuth)

- Time based token (TOTP) 
  - a token is generated with something only you know. This token changes after some time period (see https://www.freecodecamp.org/news/how-time-based-one-time-passwords-work-and-why-you-should-use-them-in-your-app-fdd2b9ed43c3/ This is very commonly used in Two Factor Authentication as the 2nd factor. If you have random codes you need to enter, they may have been generated using this scheme)

- Biometric authentication 
  - such as Fingerprints or Facial recognition (popular on smartphones)

 
### Network Authentication Protocols
Most of the standard mechanisms for authentication utilizes either of the following two "Network authentication protocols": 
- i). Kerberos,
- ii). Secure Sockets Layer (SSL) / Transport Layer Security (TLS).

## Hashing

### What is Hashing?
Hashing is the process of generating a unique value (hash) for a given text, string, or numeric input (key). The generated value (hash) itself could be either text, string, or numeric, which depends upon the underlying Hash function. A Hash function is a one-way mathematical function which is used to generate a unique value for a given input.

### Hashing is Irreversible
Let there be a function f, which can generate unique hash values for a given set of input. Another function g can get the original value back if the hash value is given as input. In such a case, the function f would be called reversible, as we can get the original value back. Hence, f cannot be used as a Hash function. Following is an example of a reversible function:

## Hashing Algorithms
- MD5: The MD5 Message-Digest Algorithm is a hash function that accepts an input message of any length, and correspondingly produces a 128-bit (16-byte) hash value. Mostly, MD5 is used to verify data integrity. It was proposed by Ronal Rivest in 1992, as specified in RFC 1321. MD5 is comparatively unsafe, as it might get reversed by using brute-force-attack. Also, the chances of collision are very high in MD5. For non-critical applications, MD5 can be a good choice as it is computationally faster than other algorithms.

- SHA: The SHA (Secure Hash Algorithm) is a set (SHA-0, SHA-1, SHA-2, and SHA-3) of cryptographic hash functions developed by the National Institute of Standards and Technology (NIST). In comparison to MD5, SHA generates secure hashes. SHA-1 is a 160-bit hash function. SHA-2 is further of two types: SHA-256 and SHA-512. SHA-256 is a 256-bit hash function that provides 128 bits of security in the case of collision attacks, while SHA-512 is a 512-bit hash function is designed for 256 bits of security. SHA-3 supports the same hash lengths as SHA-2. Chances of collision are high in SHA as well, but lesser than MD5. Thus, SHA-2 could be a good choice for general purpose application with a limited set of inputs, such as a University portal.

- bCrypt: It is generally used to generate the hash for user-passwords. bCrypt is based on the Blowfish cipher algorithm. It has a crucial phase for key setup. This phase starts with encrypting a sub-key and uses the output of this encryption to change another sub-key. This way, the bCrypt involves iterative steps for generating the hash, making it a preferred choice of developers for critical applications.

- sCrypt: It is a computationally intensive password-based key derivation function, proposed in 2016, as specified in RFC 7914. As part of the algorithm, it generates a large vector of pseudorandom bit strings. Thus, it requires a large amount of memory for computation. It isn't easy for a brute-force-attacker to reverse the hash, as it would involve a significantly high amount of time, memory, and a high number (billion) of attempts. Other password-based key derivation functions such as PBKDF1 and PBKDF2 have relatively low resource demands.

### Collision
In several scenarios, two different keys can generate the same hash. Such a scenario is called Collision. If we use a simple hash function, such as input length or sum of ASCII code of all characters, then it might lead to a collision. A collision can be resolved by using any of the following Collision Resolution Techniques:

- Separate Chaining - It is a type of Open Hashing technique. The idea is to store the keys corresponding to collision (same) hash outputs in a Linked List. There would be a separate Linked List for each unique hash output.
- Open Addressing - It is also called Closed Hashing. In this approach, for a given set of $n$ input keys, we take a data structure that can accommodate more than $n$ keys. The idea is to store the keys corresponding to collision (same) hash outputs in the next available slot in the data structure.
  - Linear or quadratic probing - Keep probing until an empty slot is found.
  - Double Hashing - We use two hash functions - one for hashing, and another for calculating the offset. Then, this offset is appended to the output of the first hash function. This way, the final output is expected to be collision-free value.

### Points to Consider
- In a web application, the Salting must be done on the Server.
- While hashing user-passwords, the Salt should be generated randomly. It is preferable if the Salt is unique for each user's password.
- For numeric Salt, it is good to use secure algorithms such as Cryptographically Secure Pseudo-Random Number Generator (CSPRNG) . Java has java.security.SecureRandom class for generating PRNG
- For pseudo-random alpha-numeric string generator, you may use Apache class, as org.apache.commons.text.RandomStringGenerator
- When we use Salting, there are two separate steps involved - (i) Generate the salted password, and (ii) Verify the salted password. We would see the detailed implementation in the project, where we would implement bCrypt hashing algorithm along with Salting.

## Implementing Hashing (SHA) along with Salting:
In the following example, SHA-256 algorithm is used for hashing, and Salting is done by using an instance of java.security.SecureRandom class. For hashing, we can create an instance of java.security.MessageDigest to use any of the hashing algorithms SHA-1, SHA-256, SHA-512, or any other as mentioned here.
```java
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.MessageDigest;

public class SaltExample {

    public static void main(String[] args)throws NoSuchAlgorithmException, NoSuchProviderException {
        String passwordToHash = "password";
        byte[] salt = createSalt();

        String securePassword = get_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
    }


    // Method to generate the hash. 
//It takes a password and the Salt as input arguments
    private static String get_SecurePassword(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // Method to generate a Salt
    private static byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
```
