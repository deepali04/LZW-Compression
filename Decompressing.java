import java.lang.*;
import java.util.*;
import java.io.*;

class Decompressing {

	public static void main(String arg[]) {

		double bit_length = Double.parseDouble(arg[1]); //2nd parameter in command line is bit_length i.e number of encoding bits
		double MAX_TABLE_SIZE = Math.pow(2, bit_length); //MAX_TABLE_SIZE is based on the input bit length
		double tableSize = 256; //current size of the hashtable

		/***
			HashMap<KEY,VALUE> initialized with value for individual characters
			KEY = ASCII Value
			VALUE = ASCII Character
		***/
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 256; i++) {
		      map.put(i, Character.toString((char) i));
		}

		String inputFileName = arg[0]; //1st parameter in the command line is the input file name after encoding
		String outputFileName = inputFileName.substring(0,inputFileName.indexOf(".")) + "_decoded" + ".txt"; //output file

		/*** Initialization **/
		
		int code =0;
		String line = null;
		String new_string = "";
		BufferedReader bufferedReader = null; //Input File BufferedReader
		BufferedWriter output = null;		  //Input File BufferedWriter

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName),"UTF-16BE"));	
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName),"UTF-8"));	//Decoded data stored in 8 Bit format
			code = bufferedReader.read();	
            String string = map.get(code);
            if(code!=-1) //check for null file
            	output.write(string);

			/***
				Continue reading code (integer) until End of File, and check if contains in dictionary, if not present make an entry and update the string
            ***/
            while((code = bufferedReader.read()) != -1) {
            		
            		if(!(map.containsKey(code))) {	//check if map(code) is not defined
            			new_string = string + string.charAt(0);
            		}
            		else {
            			new_string = map.get(code);		
            		}
            		
            		output.write(new_string);        	//Write to file the decoded string    			
            		if(tableSize < MAX_TABLE_SIZE) {	//check if table not full
            			map.put((int)tableSize++, string + new_string.charAt(0)); 	//add code to the table and increase tableSize
            		}
            		string = new_string;

            }

            bufferedReader.close();    //Close the reader
	        output.flush();				//Flush the buffer
	        output.close();				//Close the writer

		} catch(Exception e) {
			e.printStackTrace();
		}



	}
}