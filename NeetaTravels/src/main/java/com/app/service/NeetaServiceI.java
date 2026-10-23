package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Bus;

public interface NeetaServiceI {

	Bus createData(Bus b);

	Bus getSingleData(int busId);

	List<Bus> getAll();

	Bus updateBus(int busId, Bus b);

	void deleteBus(int busId);

}
