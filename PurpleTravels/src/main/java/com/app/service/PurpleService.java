package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Bus;
import com.app.repository.PurpleRepository;

@Service
public class PurpleService implements PurpleServiceI {

	@Autowired
	private PurpleRepository pr;

	@Override
	public Bus createData(Bus b) {
		Bus bus = pr.save(b);
		return bus;
	}

	@Override
	public Bus getData(int busId) {
		Optional<Bus> op = pr.findById(busId);

		if (op.isPresent()) {
			Bus bus = op.get();
			return bus;
		}
		return null;
	}

	@Override
	public List<Bus> getAll() {
		List<Bus> bus = pr.findAll();
		return bus;
	}

	@Override
	public Bus updateBus(int busId, Bus bus) {

	    Bus existingBus = pr.findById(busId)
	            .orElseThrow(() ->
	                    new RuntimeException("Bus not found with ID: " + busId));

	    existingBus.setBusNo(bus.getBusNo());
	    existingBus.setBoarding(bus.getBoarding());
	    existingBus.setDestination(bus.getDestination());
	    

	    return pr.save(existingBus);
	}

	@Override
	public void deleteBus(int busId) {
		  Bus bus = pr.findById(busId)
		            .orElseThrow(() ->
		                    new RuntimeException("Purple bus not found: " + busId));

		    pr.delete(bus);
		
	}

}
