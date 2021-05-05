/*    Here's my program for ACA's software engineering aptitude assessment!  The program should cover all the requirements listed in the Project Guidelines.  In a proper
production release, there should be more thorough exceptions to make sure the files being processed are in an appropriate format, have the correct number of columns,
and have appropriate values in those columns(Strings in the first column and integers in the second column).  There should probably also be a simple GUI or prompt that
would let the user choose a text file name and directory to output to, and the program should be separated into appropriate methods and classes.

The CSV Files are a folder in the main program directory called "CSV Files" and the text output goes to a .txt file in the main directory.
*/

package classgrades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Jonas McCartha
 */
public class ClassGrades {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    String line;  
    String path;
    double total = 0.0;
    double avg;
    double grandTotal = 0.0;
    int totalStudents = 0;
    ArrayList<Double> avgList = new ArrayList<>();
    DirectoryChooser dc = new DirectoryChooser();

        // Save the console output to a text file.
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);   
    
        // Let user choose the directory 
        File directoryPath = dc.DirectoryChooser();
        
        // Gets a list of all files in the directory and attempts to process all of them.
        File filesList[] = directoryPath.listFiles();
        for(File file : filesList) {
            path = file.getAbsolutePath();
            
            // Print name of file that's being processed.
            System.out.println(file.getName()+ '\n');
            
            try {                  
                // Initialize variables for totalling and calculations.
                int[] numbers;
                int x = 0;
                int discardedStudents = 0;
                
                // Load file into BufferedReader and start processing lines.
                BufferedReader br = new BufferedReader(new FileReader(path));
                br.readLine();
                
                // while loop runs through .csv file until it reaches a null record.
                while ((line = br.readLine()) != null) {     
                    String[] values = line.split(",");  
                    numbers = new int[values.length];
                    
                    // for loop prints all values in the .csv file.
                    for(int i = 1;i < values.length;i++) {
                        
                        // Parse float into int, which automatically truncates decimals.
                        numbers[i] = (int) Float.parseFloat(values[i]);
                        
                        // Print student and grade only if the grade is a value other than 0.
                        if (numbers[i] != 0) {
                            total = total + numbers[i];
                            System.out.println( values[0] + " | " + numbers[i]);
                        } else {
                            discardedStudents++;
                        }
                    }
                x++;
                }
                
            // Calculate average grade.    
            avg = (total / x);
            
            // Add average to an ArrayList, to later determine highest average.
            avgList.add(avg);
            
            // Add all grades to a grand total, to later determine average of all grades.
            grandTotal = grandTotal + total;
            
            // Calculate total number of students across all classes.
            totalStudents = totalStudents + x;
            
            // Print out the students and respective grades in a class.
            System.out.println('\n' + "Number of Students: " + x);            
            System.out.println('\n' + "Average Grade: " + avg);            
            if (discardedStudents > 0) {
                System.out.println('\n' + "Number of Students Discarded from Calculations: " + discardedStudents);
            }
            
            // Print dividing line for visual clarity.
            System.out.println('\n' + "--------------------------------" + '\n');
            
            // Reset class grade total to 0.
            total = 0;
             
            // Close try and catch.
            } catch (FileNotFoundException e) {  
            }
        }   
        
            // Print out final tallies and averages after processing all .csv files.
            System.out.println('\n' + "***" + "Number of Students in All Classes = " + totalStudents + "***");
            System.out.println('\n' + "***" + "Average of All Students = " + Math.round(grandTotal / totalStudents) + "***");  
            System.out.println('\n' + "***" + "Highest Average = " + Collections.max(avgList) + "***");
    }
}
        
        
   


