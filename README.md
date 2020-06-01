# Introduction:

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. It is an adaptive compression algorithm that does not assume prior knowledge of the input data distribution. This algorithm works well when the input data is sufficiently large and there is redundancy in the data. It is simple to implement and has the potential for very high throughput in hardware implementations.

# Steps
1. Encoding or Compressing
2. Decoding or Decompressing

# How Algorithm works
Input File --> Compressed File --> Decompressed File
1. Input file is encoded by Compressing.java
2. Compressed file is decoded to original text using Decompressing.java


# Pseudocode
 
--->Encoding

~~~
MAX_TABLE_SIZE=2(bit_length) //bit_length is number of encoding bits
initialize TABLE[0 to 255] = code for individual characters
STRING = null
while there are still input symbols:
SYMBOL = get input symbol
if STRING + SYMBOL is in TABLE:
STRING = STRING + SYMBOL
else:
output the code for STRING
If TABLE.size < MAX_TABLE_SIZE: // if table is not full
add STRING + SYMBOL to TABLE // STRING + SYMBOL now has a code
STRING = SYMBOL
output the code for STRING
~~~

--->Decoding

~~~
MAX_TABLE_SIZE=2(bit_length)
initialize TABLE[0 to 255] = code for individual characters
CODE = read next code from encoder
STRING = TABLE[CODE]
output STRING
while there are still codes to receive:
CODE = read next code from encoder
if TABLE[CODE] is not defined: // needed because sometimes the
NEW_STRING = STRING + STRING[0] // decoder may not yet have code!
else:
NEW_STRING = TABLE[CODE]
output NEW_STRING
if TABLE.size < MAX_TABLE_SIZE:
add STRING + NEW_STRING[0] to TABLE
STRING = NEW_STRING
~~~


# Compiling the program
~~~
javac filename.java
~~~
After this command, a class file named filename.class will be created.


# Running the program
~~~
java filename InputfileName Bitlength

#InputfileName - The file to be compresssed
#Bitlength - The length to be used
~~~


# Data Structure
~~~
HashMap data structure is used to implement the algorithm and it contains the ASCII characters as KEY along with its ASCII value as VALUE for encoding and vice versa in case of decoding.
~~~

# Syntax:
~~~
Map<String, Integer> map = new HashMap<>();
~~~

# Compressing.java
~~~
The Compressing class uses HashMap where
ASCII Character : KEY 
ASCII Value : VALUE
~~~

# Decompressing.java

~~~
The Decompressing class uses HashMap in opposite fashion
ASCII Value:KEY
ASCII Character:VALUE
~~~



# Important points:
~~~
1. BufferedReader and BufferedWriter for reading and writing to files.
2. For encoding, Compressing.java file is used. It will generate compressed (LZW) file.
3. For decoding compressed file, Decompressing.java is used. It will generate decoded text file, whose contents will be same as the initial input file.
4. The compression file is created using charset UTF_16BE and stored in 16-bit format.
~~~


# Application of LZW
~~~
1. GIF and TIFF files
2. Adobe Acrobat Softwaare
~~~
