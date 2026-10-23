package com.app.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.app.service.NeetaServiceI;

@RestController
@RequestMapping("/api/neeta")
public class NeetaController {

	@Autowired
	private NeetaServiceI ns;

	@PostMapping("/create")
	public ResponseEntity<Bus> createData(@RequestBody Bus b) {
		Bus bus = ns.createData(b);
		return new ResponseEntity<Bus>(bus, HttpStatus.CREATED);
	}
	
	@GetMapping("/{busId}")
	public ResponseEntity<Bus> getSingle(@PathVariable int busId) {

	Bus bus = ns.getSingleData(busId);
		return new ResponseEntity<Bus>(bus, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public List<Bus> getAllData(){
	List<Bus> bus =	ns.getAll();
		return bus;
	}
	
	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@RequestBody Bus b, @PathVariable int busId){
		Bus bus = ns.updateBus(busId, b);
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable int busId) {
	    ns.deleteBus(busId);
	    return ResponseEntity.ok("Neeta bus deleted successfully");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//	@GetMapping("/allbuses")
//	public List<Bus> getAllNeetaBus() {
//		List<Bus> all = new ArrayList<Bus>();
//		all.add(new Bus(101, "neeta101", "Pune", "Mumbai"));
//		all.add(new Bus(102, "neeta102", "Nagpur", "Bhopal"));
//		all.add(new Bus(103, "neeta103", "Indore", "Jabalpur"));
//		all.add(new Bus(104, "neeta104", "Banglore", "Pune"));
//		return all;
//	}
//
//	@GetMapping("/searchbuses/{boarding}/{destination}")
//	public List<Bus> boardingDestination(@PathVariable String boarding, @PathVariable String destination) {
//		List<Bus> a = new ArrayList<Bus>();
//
//		for (Bus b : getAllNeetaBus()) {
//			if (b.getBoarding().equalsIgnoreCase(boarding) && b.getDestination().equalsIgnoreCase(destination)) {
//				a.add(b);
//			}
//		}
//		return a;
//	}

	

}
