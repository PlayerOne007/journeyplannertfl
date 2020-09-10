/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journeyplanner;

/**
 *
 * @author Niroshan
 */
//Importing the required libraries: java.io, java.util, java.fx, and org.apache.poi
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;
import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FXMLDocumentController {

    //Array of strings containing the stations which are total 269 train stations
    public static String[] totalStations = {
        "Acton Town", "Aldgate", "Aldgate East", "Alperton", "Amersham", "Angel", "Archway", "Arnos Grove", "Arsenal",
        "Baker Street", "Balham", "Bank", "Barbican", "Barking", "Barkingside", "Barons Court", "Bayswater", "Becontree", "Belsize Park", "Bermondsey", "Bethnal Green", "Blackfriars", "Blackhorse Road", "Bond Street", "Borough", "Boston Manor", "Bounds Green", "Bow Road", "Brent Cross", "Brixton", "Bromley-by-Bow", "Buckhurst Hill", "Burnt Oak",
        "Caledonian Road", "Camden Town", "Canada Water", "Canary Wharf", "Canning Town", "Cannon Street", "Canons Park", "Chalfont & Latimer", "Chalk Farm", "Chancery Lane", "Charing Cross", "Chesham", "Chigwell", "Chiswick Park", "Chorleywood", "Clapham Common", "Clapham North", "Clapham South", "Cockfosters", "Colindale", "Colliers Wood", "Covent Garden", "Croxley",
        "Dagenham East", "Dagenham Heathway", "Debden", "Dollis Hill",
        "Ealing Broadway", "Ealing Common", "Earl's Court", "East Acton", "East Finchley", "East Ham", "East Putney", "Eastcote", "Edgware", "Edgware Road", "Edgware Road Bakerloo", "Elephant & Castle", "Elm Park", "Embankment", "Epping", "Euston", "Euston Square",
        "Fairlop", "Farringdon", "Finchley Central", "Finchley Road", "Finsbury Park", "Fulham Broadway",
        "Gants Hill", "Gloucester Road", "Golders Green", "Goldhawk Road", "Goodge Street", "Grange Hill", "Great Portland Street", "Green Park", "Greenford", "Gunnersbury",
        "Hainault", "Hammersmith District Piccadilly", "Hammersmith Hammersmith & City Circle", "Hampstead", "Hanger Lane", "Harlesden", "Harrow & Wealdstone", "Harrow-on-the-Hill", "Hatton Cross", "Heathrow Terminal 4", "Heathrow Terminal 5", "Heathrow Terminals 1, 2, 3", "Hendon Central", "High Barnet", "High Street Kensington", "Highbury & Islington", "Highgate", "Hillingdon", "Holborn", "Holland Park", "Holloway Road", "Hornchurch", "Hounslow Central", "Hounslow East", "Hounslow West", "Hyde Park Corner",
        "Ickenham",
        "Kennington", "Kensal Green", "Kensington (Olympia)", "Kentish Town", "Kenton", "Kew Gardens", "Kilburn", "Kilburn Park", "King's Cross St. Pancras", "Kingsbury", "Knightsbridge",
        "Ladbroke Grove", "Lambeth North", "Lancaster Gate", "Latimer Road", "Leicester Square", "Leyton", "Leytonstone", "Liverpool Street", "London Bridge", "Loughton",
        "Maida Vale", "Manor House", "Mansion House", "Marble Arch", "Marylebone", "Mile End", "Mill Hill East", "Monument", "Moor Park", "Moorgate", "Morden", "Mornington Crescent",
        "Neasden", "Newbury Park", "North Acton", "North Ealing", "North Greenwich", "North Harrow", "North Wembley", "Northfields", "Northolt", "Northwick Park", "Northwood", "Northwood Hills", "Notting Hill Gate",
        "Oakwood", "Old Street", "Osterley", "Oval", "Oxford Circus",
        "Paddington", "Park Royal", "Parsons Green", "Perivale", "Piccadilly Circus", "Pimlico", "Pinner", "Plaistow", "Preston Road", "Putney Bridge",
        "Queen's Park", "Queensbury", "Queensway",
        "Ravenscourt Park", "Rayners Lane", "Redbridge", "Regent's Park", "Richmond", "Rickmansworth", "Roding Valley", "Royal Oak", "Ruislip", "Ruislip Gardens", "Ruislip Manor", "Russell Square",
        "Seven Sisters", "Shepherd's Bush", "Shepherd's Bush Market", "Sloane Square", "Snaresbrook", "South Ealing", "South Harrow", "South Kensington", "South Kenton", "South Ruislip", "South Wimbledon", "South Woodford", "Southfields", "Southgate", "Southwark", "St. James's Park", "St. John's Wood", "St. Paul's", "Stamford Brook", "Stanmore", "Stepney Green", "Stockwell", "Stonebridge Park", "Stratford", "Sudbury Hill", "Sudbury Town", "Swiss Cottage",
        "Temple", "Theydon Bois", "Tooting Bec", "Tooting Broadway", "Tottenham Court Road", "Tottenham Hale", "Totteridge & Whetstone", "Tower Hill", "Tufnell Park", "Turnham Green", "Turnpike Lane",
        "Upminster", "Upminster Bridge", "Upney", "Upton Park", "Uxbridge",
        "Vauxhall", "Victoria",
        "Walthamstow Central", "Wanstead", "Warren Street", "Warwick Avenue", "Waterloo", "Watford", "Wembley Central", "Wembley Park", "West Acton", "West Brompton", "West Finchley", "West Ham", "West Hampstead", "West Harrow", "West Kensington", "West Ruislip", "Westbourne Park", "Westminster", "White City", "Whitechapel", "Willesden Green", "Willesden Junction", "Wimbledon", "Wimbledon Park", "Wood Green", "Wood Lane", "Woodford", "Woodside Park"
    };

    //Declarations of the various JavaFX elements used
    @FXML
    private TextField end;
    //Field used for the input of the arrival station

    @FXML
    private AnchorPane JourneyPlanner;
    //Main AnchorPane used for the displaying of the program

    @FXML
    private ImageView map_image;
    //ImageView element used for display the map 
    @FXML
    private TextArea output;
    // TextAREA design for the output of the journey

    @FXML
    private Button btn_search;
    //Button used for the actual searching of the route

    @FXML
    private ToggleButton toggle;
    //Button used for toggling Fast Time

    @FXML
    private TextField arrival;
    //Field used for the input of the departure station

    //Declarations of the variables used in the algorithm
    public static int[] cache = {0, 0};
    public static int j = 0;
    public static int present;
    //Multi-dimensional arays used for storing the indexes and contents of
    //elements from the array
    public static int[] store2 = new int[269];
    public static int[] line2 = new int[269];
    public static int[] line1 = new int[269];
    public static int[][] distances = new int[269][269];

    public static Integer[] line3 = new Integer[269];

    //Functions used to initialize and to determine whether or not the stations are known 
    public static int[][] startDis(int[][] array) {
        for (int c = 0; c < 269; c++) {
            for (int d = 0; d < 269; d++) {
                array[c][d] = 0;
                if (c == d) {
                    array[c][d] = 0;
                }
            }
        }
        return array;
    }

    public static int[] startLine2(int[] array) {
        for (int c = 0; c < 269; c++) {
            array[c] = 0;
        }
        return array;
    }

    public static Integer[] startLine2(Integer[] array) {
        for (int c = 0; c < 269; c++) {
            array[c] = 0;
        }
        return array;
    }

    //Function that operates the reset button 
    public void resetText() {
        String emptyString = "";
        //Sets text in the output, arrival, and end fields to an empty string i.e. resets it
        output.setText(emptyString);
        arrival.setText(emptyString);
        end.setText(emptyString);

    }

    //Controller funciton for the search button
    public void btn_clickSearch() throws FileNotFoundException, IOException {
        //Initializations of the distances and the lines
        startDis(distances);
        startLine2(line3);
        //Linking the Excel filepath
        String sheetFilePath = "London Underground data - Connections2.xlsx";
        //Declaration of the workbook and required variables for iterating through it
        FileInputStream input = new FileInputStream(new File(sheetFilePath));

        Workbook workbook = new XSSFWorkbook(input);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        //Loop that iterates through the cells in the excel file
        int i = 0;
        double distance = 0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        String start = cell.getStringCellValue();
                        int stationsIndex = Arrays.asList(totalStations).indexOf(start);
                        cache[i] = stationsIndex;
                        i++;
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        distance = (cell.getNumericCellValue());
                        break;
                }
            }
            distances[cache[0]][cache[1]] = (int) distance;
            distances[cache[1]][cache[0]] = (int) distance;
            i = 0;

        }
        //Close call methods for the workbook and inputstream
        workbook.close();
        input.close();
        //Declaration of variables needed to display the route itself in the textArea
        String departure1 = arrival.getText();
        String arrival1 = end.getText();
        String outputInfo = "";
        int distance1st = 1;
        int distance2nd = 0;
        int distance3rd = 0;
        int departureIndex = 0;
        //Loops and Conditionals that display the stations and the route to the user
        departureIndex = Arrays.asList(totalStations).indexOf(departure1); //start
        store2[0] = departureIndex;
        distance3rd = FindRoute.search(Arrays.asList(totalStations).indexOf(arrival1), departureIndex);
        FindRoute.getLine2(line3);
        //Conditional to determine if the button is selected or not
        if (toggle.isSelected()) {
            outputInfo = (departure1 + " - " + distance3rd / 2 + " minutes to - " + arrival1 + " - " + "Fast Time" + "\t" + "\n\n");
            present = Arrays.asList(totalStations).indexOf(departure1);
        } else {
            outputInfo = (departure1 + " - " + distance3rd + " minutes to - " + arrival1 + "\t" + "\n\n");

            present = Arrays.asList(totalStations).indexOf(departure1);
        }
        departureIndex = 0;
        do {
            for (i = 0; i < 269; i++) {
                distance1st = distances[present][i];
                if (distance1st > 0 && distance1st < 199 && i != present) {
                    distance2nd = FindRoute.search(Arrays.asList(totalStations).indexOf(arrival1), i);
                    if (distance2nd < distance3rd) {
                        departureIndex++;
                        distance3rd = distance2nd;
                        store2[departureIndex] = i;
                        line1[departureIndex] = distance2nd;
                        present = i;
                        j = departureIndex;
                    }
                }
            }

        } while (distance3rd != 0);
        //Conditional to determine if the button is selected or not
        if (toggle.isSelected()) {
            for (i = 0; i <= departureIndex; i++) {
                if (line3[store2[i]] == 99999) {
                    //Divides time by two and displays the route with times between the stations
                    outputInfo = outputInfo + (totalStations[store2[i]] + "  - " + 0 + "minutes" + "\t" + "\n");
                } else {
                    outputInfo = outputInfo + (totalStations[store2[i]] + " - " + line3[store2[i]] / 2 + "minutes" + "\t" + "\n");
                }
            }
            //Dispaly the route
            output.setText(outputInfo);
        } else {
            for (i = 0; i <= departureIndex; i++) {
                if (line3[store2[i]] == 99999) {
                    //Displays stations and the regular time between each of them
                    outputInfo = outputInfo + (totalStations[store2[i]] + "  - " + 0 + "minutes" + "\t" + "\n");
                } else {
                    outputInfo = outputInfo + (totalStations[store2[i]] + " - " + line3[store2[i]] + "minutes" + "\t" + "\n");
                }
            }
            //Dispaly the route
            output.setText(outputInfo);

        }
    }

}
