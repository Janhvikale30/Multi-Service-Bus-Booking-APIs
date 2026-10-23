package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.entity.Bus;
import com.app.service.PurpleServiceI;

@RestController
@RequestMapping("/api/purple")
public class PurpleController {
	
	@Autowired
	private PurpleServiceI ps;
	
	@PostMapping("/create")
	public ResponseEntity<Bus> createData(@RequestBody Bus b) {
		Bus bus = ps.createData(b);
		return new ResponseEntity<Bus>(bus, HttpStatus.CREATED);
	}
	@GetMapping("/{busId}")
	public ResponseEntity<Bus> getSingleData(@PathVariable int busId){
		Bus bus = ps.getData(busId);
		if(bus==null) {
			return new ResponseEntity<Bus>(bus, HttpStatus.NOT_FOUND);
		}else {
		return new ResponseEntity<Bus>(bus, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Bus>> getAllData(){
	List<Bus> bus =	ps.getAll();
	return new ResponseEntity<List<Bus>>(bus, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@RequestBody Bus b, @PathVariable int busId){
		Bus bus = ps.updateBus(busId, b);
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable int busId) {
	    ps.deleteBus(busId);
	    return ResponseEntity.ok("Purple bus deleted successfully");
	}
	
	
	

//	@GetMapping("/allbuses")
//	public List<Bus> getAllPurpleBus(){
//		List<Bus> all = new ArrayList<Bus>();
//		all.add(new Bus(101, "Puple101", "Delhi", "Manali"));
//		all.add(new Bus(102, "Puple102", "Bhopal", "Nagpur"));
//		all.add(new Bus(103, "Puple103", "Mumbai", "Goa"));
//		return all;
//	}
//	
//	@GetMapping("/searchbuses/{boarding}/{destination}")
//	public List<Bus> boardingDestination(@PathVariable String boarding, @PathVariable String destination){
//		List<Bus> a = new ArrayList<Bus>();
//		
//		for(Bus b : getAllPurpleBus()) {
//			if(b.getBoarding().equalsIgnoreCase(boarding) && b.getDestination().equalsIgnoreCase(destination)) {
//				a.add(b);
//			}
//		}
//		return a;
//	}
}
