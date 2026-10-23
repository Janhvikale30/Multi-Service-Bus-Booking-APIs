package com.app.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Bus;
import com.app.service.RedBusServiceI;

@RestController
@RequestMapping("/api/redbus")
public class RedBusController {
	@Autowired
	private RestTemplate rt;
	@Autowired
	private RedBusServiceI rs;
	
	@PostMapping("/createneeta")
	public ResponseEntity<Bus> createData(@RequestBody Bus b) {
		String neetaUrl = "http://localhost:9091/api/neeta/create";
		ResponseEntity<Bus> neetaData = rt.postForEntity(neetaUrl,b,Bus.class);
		
		
		
		Bus bus =   rs.createData(b);
		return new ResponseEntity<>(bus, HttpStatus.CREATED);
	}
	
	@PostMapping("/createpurple")
	public ResponseEntity<Bus> createDataPurple(@RequestBody Bus b) {
		
		Bus bus =   rs.createData(b);
		String purpleUrl = "http://localhost:9092/api/purple/create";
		ResponseEntity<Bus> purpleData = rt.postForEntity(purpleUrl,b,Bus.class);
		
		
		return new ResponseEntity<>(bus, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Bus>> getAllData() {

	    // Get buses from RedBus database
	    List<Bus> redBusList = rs.getAll();

	    // Get buses from Neeta
	    String neetaUrl = "http://localhost:9091/api/neeta/getAll";

	    ResponseEntity<List<Bus>> neetaData = rt.exchange(
	            neetaUrl,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Bus>>() {}
	    );

	    // Get buses from PurpleTravels
	    String purpleUrl = "http://localhost:9092/api/purple/getAll";

	    ResponseEntity<List<Bus>> purpleData = rt.exchange(
	            purpleUrl,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Bus>>() {}
	    );

	    // Combine all bus lists
	    Map<Integer, Bus> uniqueBuses = new LinkedHashMap<>();

	    for (Bus b : redBusList) {
	        uniqueBuses.put(b.getBusId(), b);
	    }

	    for (Bus b : neetaData.getBody()) {
	        uniqueBuses.putIfAbsent(b.getBusId(), b);
	    }

	    for (Bus b : purpleData.getBody()) {
	        uniqueBuses.putIfAbsent(b.getBusId(), b);
	    }

	    List<Bus> allBuses = new ArrayList<>(uniqueBuses.values());

	    return new ResponseEntity<>(allBuses, HttpStatus.OK);
	}
	
	@GetMapping("/neeta/{busId}")
	public ResponseEntity<Bus> getNeetaBusById(@PathVariable int busId) {

	    String neetaUrl = "http://localhost:9091/api/neeta/" + busId;

	    try {
	        ResponseEntity<Bus> response =
	                rt.getForEntity(neetaUrl, Bus.class);

	        if (response.getBody() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(response.getBody());

	    } catch (HttpClientErrorException.NotFound e) {
	        return ResponseEntity.notFound().build();
	    }
	}


	@GetMapping("/purple/{busId}")
	public ResponseEntity<Bus> getPurpleBusById(@PathVariable int busId) {

	    String purpleUrl = "http://localhost:9092/api/purple/" + busId;

	    try {
	        ResponseEntity<Bus> response =
	                rt.getForEntity(purpleUrl, Bus.class);

	        if (response.getBody() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(response.getBody());

	    } catch (HttpClientErrorException.NotFound e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/neeta/update/{busId}")
	public ResponseEntity<Bus> updateNeetaBus(
	        @PathVariable int busId,
	        @RequestBody Bus b) {

	    String neetaUrl =
	            "http://localhost:9091/api/neeta/update/" + busId;

	    try {
	        // Update bus in Neeta database
	        ResponseEntity<Bus> response = rt.exchange(
	                neetaUrl,
	                HttpMethod.PUT,
	                new HttpEntity<>(b),
	                Bus.class
	        );

	        Bus updatedBus = response.getBody();

	        if (updatedBus == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Update the corresponding bus in RedBus database
	        Bus redBus = rs.updateBus(busId, b);

	        return ResponseEntity.ok(redBus);

	    } catch (HttpClientErrorException.NotFound e) {
	        return ResponseEntity.notFound().build();
	    }
	}


	@PutMapping("/purple/update/{busId}")
	public ResponseEntity<Bus> updatePurpleBus(
	        @PathVariable int busId,
	        @RequestBody Bus b) {

	    String purpleUrl =
	            "http://localhost:9092/api/purple/update/" + busId;

	    try {
	        // Update bus in Purple Travels database
	        ResponseEntity<Bus> response = rt.exchange(
	                purpleUrl,
	                HttpMethod.PUT,
	                new HttpEntity<>(b),
	                Bus.class
	        );

	        Bus updatedBus = response.getBody();

	        if (updatedBus == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Update the corresponding bus in RedBus database
	        Bus redBus = rs.updateBus(busId, b);

	        return ResponseEntity.ok(redBus);

	    } catch (HttpClientErrorException.NotFound e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/neeta/delete/{busId}")
	public ResponseEntity<String> deleteNeetaBus(@PathVariable int busId) {

	    String url = "http://localhost:9091/api/neeta/delete/" + busId;

	    rt.delete(url);
	    rs.deleteBus(busId);

	    return ResponseEntity.ok(
	            "Bus deleted successfully from Neeta Travels and RedBus");
	}


	@DeleteMapping("/purple/delete/{busId}")
	public ResponseEntity<String> deletePurpleBus(@PathVariable int busId) {

	    String url = "http://localhost:9092/api/purple/delete/" + busId;

	    rt.delete(url);
	    rs.deleteBus(busId);

	    return ResponseEntity.ok(
	            "Bus deleted successfully from Purple Travels and RedBus");
	}
	
	
	
	
	
	
	
	
	
	
	

//	@GetMapping("/allbuses")
//	public List<List<Bus>> getAllBuses() {
//
//		String neetaUrl = "http://localhost:9091/api/neeta/allbuses";
//		List neetaData = rt.getForObject(neetaUrl, List.class);
//
//		String purpleUrl = "http://localhost:9092/api/purple/allbuses";
//		List purpleData = rt.getForObject(purpleUrl, List.class);
//
//		List<List<Bus>> all = new ArrayList<List<Bus>>();
//		all.add(neetaData);
//		all.add(purpleData);
//		return all;
//	}
//	
//	@GetMapping("/searchbuses/{boarding}/{destination}")
//	public List<List<Bus>> getBoardingDestination(@PathVariable String boarding, @PathVariable String destination){
//		
//		String neetaUrl = "http://localhost:9091/api/neeta/searchbuses/"+boarding+"/"+destination;
//		List list1 = rt.getForObject(neetaUrl, List.class);
//		
//		String purpleUrl = "http://localhost:9092/api/purple/searchbuses/"+boarding+"/"+destination;
//		List list2 = rt.getForObject(purpleUrl, List.class);
//		
//		List<List<Bus>> a = new ArrayList<List<Bus>>();
//		a.add(list1);
//		a.add(list2);
//		return a;
//	}

}
