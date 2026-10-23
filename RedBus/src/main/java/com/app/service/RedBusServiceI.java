package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.entity.Bus;

public interface RedBusServiceI {

	Bus createData(Bus b);

     List<Bus> getAll();

	 Bus updateBus(int busId, Bus b);

	 void deleteBus(int busId);

}
