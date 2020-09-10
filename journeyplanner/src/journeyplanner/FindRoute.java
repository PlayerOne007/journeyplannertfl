/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journeyplanner;
//Importing required libraries: java.io, java.util, and org.apache.poi

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * @author Sunil
 */
public class FindRoute {

    //Array of strings containing the stations which are total 269 train stations
    public static String[] totalStations = {"Acton Town", "Aldgate", "Aldgate East", "Alperton", "Amersham", "Angel", "Archway", "Arnos Grove", "Arsenal",
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

    //Declarations of the variables used in the searching algorithm
    public static int[] save = {0, 0};
    public static int f;
    //Multi-dimensional arays used for storing the indexes and contents of
    //elements from the array
    public static Integer[] track2 = new Integer[269];
    public static Integer[] visited = new Integer[269];
    public static Integer[] unvisited = new Integer[269];
    public static int[][] distances = new int[269][269];

//Functions used to initialize and to determine whether or not the stations are known 
    public static Integer[] getLine2(Integer[] track3) {
        for (int c = 0; c < 269; c++) {
            track3[c] = track2[c];
        }

        //  System.out.println(track2[4]);
        return track3;

    }

    public static Integer[] startLine(Integer[] array) {
        for (int c = 0; c < 72361; c++) {
            array[c] = 0;
        }
        return array;
    }

    public static Integer[] startLine2(Integer[] array) {
        for (int c = 0; c < 269; c++) {
            array[c] = 99999;
        }
        return array;
    }

    public static int[][] startDis(int[][] array) {
        for (int c = 0; c < 269; c++) {
            for (int d = 0; d < 269; d++) {
                array[c][d] = 99999;
                if (c == d) {
                    array[c][d] = 0;
                }
            }
        }
        return array;
    }

    public static Integer[] startBlank(Integer[] array) {
        for (int c = 0; c < 269; c++) {
            array[c] = c;
        }
        return array;
    }

    public static int search(int end, int st) throws FileNotFoundException, IOException {
        //Initializations of the distances and the tracks
        startDis(distances);
        startLine2(track2);
        startLine2(visited);
        startBlank(unvisited);
        //Linking the Excel filepath
        String excelFilePath = "London Underground data - Connections2.xlsx";
        //Declaration of the workbook and required variables for iterating through it
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        //Loop that iterates through the cells in the excel file
        int i = 0;
        double dist = 0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        String St = cell.getStringCellValue();
                        int indexOftotalStations = Arrays.asList(totalStations).indexOf(St);
                        save[i] = indexOftotalStations;
                        i++;
                        break;

                    case Cell.CELL_TYPE_NUMERIC:
                        dist = (cell.getNumericCellValue());
                        break;
                }

            }
            distances[save[0]][save[1]] = (int) dist;
            distances[save[1]][save[0]] = (int) dist;
            i = 0;
        }
        //Close call methods for the workbook and inputstream
        workbook.close();
        inputStream.close();
        //Storing the indexes for the start and end station
        String start1 = totalStations[st];
        String end1 = totalStations[end];
        //Variables used in Dijkstra's Algorithm
        //Source: Drumm, K. (2016) Graph Data Structure 4. Dijkstra's Shortest Path Algorithm, youtube.com, 
        //[online] Available at: https://www.youtube.com/watch?v=pVfj6mxhdMw (Accessed 17 November 2017)
        int distance1 = 0;
        int distance2 = 0;
        int distance3 = 0;
        int l = 0;
        int d = 0;
        int v = 0;
        int dd = 0;
        //Implementantion of the algorithm
        if (start1 != end1) {
            //Start and end index of the entered stations stored in an adjacent matrix
            d = Arrays.asList(totalStations).indexOf(start1);
            dd = Arrays.asList(totalStations).indexOf(end1);
            do {
                //Once f is visited it will become visited as opposed to unvisited
                //It will the move on to the next cell, which are the inbetween stations and it will increment
                f = Arrays.asList(totalStations).indexOf(start1);
                visited[v] = f;//contains visited st
                unvisited[f] = 99999;
                v++;
                distance1 = 0;
                //For loops that  through the sheet to check for the distances
                //Starting at 0, moving to infinity, and then updating with the shortest distance
                for (i = 0; i < 269; i++) {
                    distance1 = distances[f][i];
                    if (distance1 > 0 && distance1 < 99999 && i != f && i != d) {
                        if (track2[i] >= distance1 + distance2) {//chekc if lower than the existing distance                   
                            track2[i] = distance1 + distance2;//update distance 
                        }
                    }
                }
                //The if statement will determine wheter or not the node is unvisited
                //which contains the index
                for (i = 0; i < 269; i++) {
                    if (true == Arrays.asList(unvisited).contains(i)) {
                        if (track2[i] < 99999 && track2[i] > 0) {
                            distance3 = track2[i];
                        }
                    }
                }
                //Determines if the current distance is greater or equal than track2
                //
                for (i = 0; i < 269; i++) {
                    if (true == Arrays.asList(unvisited).contains(i)) {
                        if (distance3 >= track2[i]) {
                            distance3 = track2[i];
                            l = i;
                        }
                    }
                }
                //Current distance is being updated with the shortest known distance
                distance2 = distance3;
                start1 = totalStations[l];
            } while (v != 269);

        } else {
            return 0;
        }
        //Shortest distance returned
        return track2[dd];
    }

}
