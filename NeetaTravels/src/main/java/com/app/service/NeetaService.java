package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Bus;
import com.app.repository.NeetaRepository;

@Service
public class NeetaService implements NeetaServiceI {

	@Autowired
	NeetaRepository nr;

	@Override
	public Bus createData(Bus b) {
		Bus s = nr.save(b);
		return s;
	}

	@Override
	public Bus getSingleData(int busId) {
		Optional<Bus> bus = nr.findById(busId);
		if (bus.isPresent()) {
			Bus b = bus.get();
			return b;
		}
		return null;
	}

	@Override
	public List<Bus> getAll() {
		List<Bus> bus = nr.findAll();
		return bus;
	}

	@Override
	public Bus updateBus(int busId, Bus bus) {

	    Bus existingBus = nr.findById(busId)
	            .orElseThrow(() ->
	                    new RuntimeException("Bus not found with ID: " + busId));

	    existingBus.setBusNo(bus.getBusNo());
	    existingBus.setBoarding(bus.getBoarding());
	    existingBus.setDestination(bus.getDestination());
	    

	    return nr.save(existingBus);
	}

	@Override
	public void deleteBus(int busId) {
	    Bus bus = nr.findById(busId)
	            .orElseThrow(() ->
	                    new RuntimeException("Neeta bus not found: " + busId));

	    nr.delete(bus);
	}

}
