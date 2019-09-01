package com.sg.distcalc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import au.com.bytecode.opencsv.CSVReader;

public class Utils {
	
	public static final String CODES_FILE_NAME = "AirPortcodes.csv";
	public static final String RATES_FILE_NAME = "Rates.csv";
	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	
	/**
	 * Returns a map of both airport's code(key) and its corresponding Latitude and Longitude (value).
	 * Takes two airport codes, reads a CSV file, searches them in this file and stores the latitude and longitude of these airport codes in a Map.
	 * @param srcCode 
	 * @param destCode
	 * @return 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HashMap<String, LatLong> readDistanceCSV(String srcCode,String destCode) throws FileNotFoundException, IOException {
		final int CODE_INDEX = 4, LAT_INDEX = 6, LNG_INDEX = 7;
		HashMap<String, LatLong> map = new HashMap<>();
		Resource resource = new ClassPathResource(CODES_FILE_NAME);
		CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream())); 
        String[] nextRecord; 
       
        while ((nextRecord = csvReader.readNext()) != null) { 
        	//If both the airports are already in hashmap, break
        	if(map.size()==2)
        		break;
        	
        	if(srcCode.equals(nextRecord[CODE_INDEX]) || destCode.equals(nextRecord[CODE_INDEX])) 
        		map.put(nextRecord[CODE_INDEX], new LatLong(Double.parseDouble(nextRecord[LAT_INDEX]),Double.parseDouble(nextRecord[LNG_INDEX])));
        	    
        } 

		return map;
	}
	
	public static String readRatesCSV(String srcCode, String destCode) throws FileNotFoundException, IOException {
		System.out.println("readRatesCSV" + " | " + srcCode + " | " + destCode);
		final int SRC_INDEX = 1, DST_INDEX = 2, RATE_INDEX = 3;
		Resource resource = new ClassPathResource(RATES_FILE_NAME);
		CSVReader csvReader = new CSVReader (new InputStreamReader(resource.getInputStream()));
		String [] nextRecord;
		
		while ((nextRecord = csvReader.readNext()) != null) { 
			System.out.println(nextRecord[SRC_INDEX] + " | " + nextRecord[DST_INDEX] + " | " + nextRecord[RATE_INDEX]);
			if(srcCode.equals(nextRecord[SRC_INDEX]) && destCode.equals(nextRecord[DST_INDEX])) {
				
        		return nextRecord[RATE_INDEX];
        	}
        } 
		return "-1";
		
	}
	
	
	
	/**
	 * Calculates the distance in kilometers between two sets of latitude-longitude
	 * @param srcLat source latitude
	 * @param srcLng source longitude
	 * @param dstLat distance latitude
	 * @param dstLng distance longitude
	 * @return
	 */
	public static int calculateDistanceInKilometer(double srcLat, double srcLng,double dstLat, double dstLng) {
        double latDistance = Math.toRadians(srcLat - dstLat);
        double lngDistance = Math.toRadians(srcLng - dstLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
          + Math.cos(Math.toRadians(srcLat)) * Math.cos(Math.toRadians(dstLat))
          * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	 }

}
