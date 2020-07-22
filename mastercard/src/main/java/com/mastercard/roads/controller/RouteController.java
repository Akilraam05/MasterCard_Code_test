package com.mastercard.roads.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.roads.service.RoutesService;

@RestController
public class RouteController {
	private RoutesService routeService;

	@Autowired
	public RouteController(RoutesService routeService){
		this.routeService = routeService;
	}
	
	@RequestMapping("/connected")
	public String connected(@RequestParam @NotEmpty String origin, @RequestParam @NotEmpty String destination) {
		boolean connected = routeService.areBothCitiesConnected(origin, destination);
		if(connected)
			return "Yes";
		return "No";
	}
}