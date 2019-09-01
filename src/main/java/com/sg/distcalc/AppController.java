package com.sg.distcalc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AppController {
	@RequestMapping(value="/")
	public String hello() {
		return "Hey There";
	}
	
	/**
	 * Calculates the distance between two airports in kilometer
	 * @param src airport code of source airport code
	 * @param dst airport code of destination airport code
	 * @return distance in kilometers. If one or both of the airport codes are not found return -1
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/distance")
	public Distance distance(@RequestParam(value="src", defaultValue="") String src,@RequestParam(value="dst", defaultValue="") String dst) throws FileNotFoundException, IOException {

		if(src == "" || dst=="")
			return new Distance("","",-1);
	
		HashMap<String, LatLong> map = Utils.readDistanceCSV(src, dst);
		if(map.containsKey(src) && map.containsKey(dst)) {
			int distanceInKm = Utils.calculateDistanceInKilometer(map.get(src).getLatitude(),map.get(src).getLongitude(),map.get(dst).getLatitude(),map.get(dst).getLongitude());
			return new Distance(src,dst,distanceInKm);
		}
		return new Distance(src, dst, -1);
	}	
	 
	/**
	 * Calculates the rates between two airports
	 * API: /rates?src=NGP&dst=JBP
	 * @param src
	 * @param dst
	 * @return Rate between two airports
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/rates")
	public Rate rate (@RequestParam(value="src", defaultValue="NGP") String src, @RequestParam(value="dst", defaultValue="JBP") String dst ) throws FileNotFoundException, IOException {
		if(src == "" || dst =="" )
			return new Rate(src,dst,"-1");
		String rate = Utils.readRatesCSV(src, dst);
		return new Rate(src,dst,rate);
			
	}

}
