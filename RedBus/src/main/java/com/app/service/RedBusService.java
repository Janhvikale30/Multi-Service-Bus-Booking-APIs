package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.app.entity.Bus;
import com.app.repository.RedBusRepository;

@Repository
public class RedBusService implements RedBusServiceI {

	@Autowired
	private RedBusRepository rr;

	@Override
	public Bus createData(Bus b) {
		Bus s = rr.save(b);
		return s;
	}

	@Override
	public List<Bus> getAll() {
		List<Bus> bus = rr.findAll();
		return bus;
	}

	@Override
	public Bus updateBus(int busId, Bus updatedBus) {

		Bus existingBus = rr.findById(busId).orElseThrow(() -> new RuntimeException("Bus not found: " + busId));

		existingBus.setBusNo(updatedBus.getBusNo());
		existingBus.setBoarding(updatedBus.getBoarding());
		existingBus.setDestination(updatedBus.getDestination());

		return rr.save(existingBus);
	}

	@Override
	public void deleteBus(int busId) {
	    Bus bus = rr.findById(busId)
	            .orElseThrow(() ->
	                    new RuntimeException("RedBus bus not found: " + busId));

	    rr.delete(bus);
	}

}
