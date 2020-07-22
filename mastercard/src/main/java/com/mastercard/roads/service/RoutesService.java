package com.mastercard.roads.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class RoutesService 
{
	private HashMap<String, HashSet<String>> routesMap = null;
	public RoutesService() {
		routesMap = new HashMap<>();
	}
	@PostConstruct
	public void init() {
		try (Stream<String> stream = Files.lines(Paths.get(ResourceUtils.getFile("classpath:connected_cities.txt").getAbsolutePath()))) {
			stream
			.forEach(line -> { 
				String[] cities = line.split(", ");
				addTwoWayConnection(getOnlyStrings(cities[0]), getOnlyStrings(cities[1]));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void addConnect(String origin, String destination)
	{
		HashSet<String> adjacent = routesMap.get(origin);
		if (adjacent == null)
		{
			adjacent = new HashSet<String>();
		}
		adjacent.add(destination);
		routesMap.put(origin, adjacent);
	}
	public void addTwoWayConnection(String origin, String destination)
	{
		addConnect(origin, destination);
		addConnect(destination, origin);
	}

	public String getOnlyStrings(String s) {
		Pattern pattern = Pattern.compile("[^a-z A-Z]");
		Matcher matcher = pattern.matcher(s);
		String filtered = matcher.replaceAll("");
		return filtered;
	}
	public boolean areBothCitiesConnected(String origin, String destination) {
		return isReachable( origin, destination );
	}
	public boolean isReachable(String origin, String destination) 
	{
		if(origin.equals(destination)) {
			return true;
		}
		if((routesMap.get(origin)!= null && routesMap.get(origin).contains(destination)) || (routesMap.get(destination) != null && routesMap.get(destination).contains(origin))) {
			return true;
		}
		HashMap<String, Boolean> visitedCities = new HashMap<>(); 
		LinkedList<String> toBeTracedFrom = new LinkedList<String>(); 
		visitedCities.put(origin, true); 
		toBeTracedFrom.add(origin); 
		Iterator<String> iter; 
		while (toBeTracedFrom.size()!=0)
		{
			origin = toBeTracedFrom.poll(); 
			String connectedCity; 
			Optional<HashSet<String>> destSet = Optional.ofNullable(routesMap.get(origin));
			if(destSet.isPresent()) {
				iter = destSet.get().iterator(); 
				while (iter.hasNext()) 
				{ 
					connectedCity = iter.next(); 
					if (connectedCity.equals(destination))
						return true; 
					if (!visitedCities.containsKey(connectedCity)) 
					{
						visitedCities.put(connectedCity, true); 
						toBeTracedFrom.add(connectedCity); 
					} 
				} 
			}
		} 
		return false; 
	} 
}