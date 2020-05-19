import java.lang.*;
import java.util.*;
import java.io.*;

class Compressing {
	public static void main(String arg[]) {

		double bit_length = Double.parseDouble(arg[1]);    		//2nd parameter in command line is bit_length i.e number of encoding bits
		double MAX_TABLE_SIZE = Math.pow(2, bit_length);   		//MAX_TABLE_SIZE is based on the input bit length
		double tableSize = 256; 								//current size of the hashtable

		/***
			HashMap<KEY,VALUE> initialized code for individual characters
			KEY = ASCII Character
			VALUE = ASCII Value
		***/
			
		Map<String,Integer> map = new HashMap<>(); 				//Data structure
		for (int i = 0; i < 256; i++) {
		      map.put(Character.toString((char) i), i); 		//Initialize code for individual characters
		}

		//Initialization

		String inputFileName = arg[0]; 							//First parameter in the command line is the input file name
		String outputFileName = inputFileName.substring(0,inputFileName.indexOf(".")) + ".lzw"; 		//output file name
		String text = null; 									//String to contain the entire input data.
		String line = null;
		BufferedReader bufferedReader = null; 					//Input File BufferedReader
		BufferedWriter output = null; 							//Output File BufferedWriter
		
		try {
            
			bufferedReader = new BufferedReader(new FileReader(inputFileName));
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName),"UTF-16BE"));  //Encoded data stored in 16 Bit format

			//Read the input file and store in a string
            while((line = bufferedReader.readLine()) != null) {
            	text = line;									//read the file and save in a String
            }      

            //Continue reading characters until End of File
			//and check if contains in dictionary,
			//if not present make an entry and update the encodeString to symbol

            String encodeString = ""; 							// Initialize empty string
            if(text!=null){										//Condition to check for empty input file
				for(char c: text.toCharArray()) {  
					String symbol = "" + c;
					if (map.containsKey(encodeString + symbol))
						encodeString = encodeString + symbol;
					else {
						output.write(map.get(encodeString));		//Write to file the encoded string
						if(tableSize < MAX_TABLE_SIZE)				//check if table not full
							map.put(encodeString + symbol, (int) tableSize++);  //add code to the table and increase tableSize
						encodeString = "" + symbol;
					}
				}
            	output.write(map.get(encodeString));				//Write to file the encoded string
        	}
            bufferedReader.close();    //Close the reader
	        output.flush();				//Flush the buffer
	        output.close();				//Close the writer

	        }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + inputFileName + "'");                
	    }
	    
		catch(IOException ex) {
	            System.out.println("Error reading file '" + inputFileName + "'");                  
	    }
	    
		catch(Exception e) {
	            e.printStackTrace();
	    }
	        
	}
}