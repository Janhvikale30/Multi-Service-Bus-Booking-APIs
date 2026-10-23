package com.app.service;

import java.util.List;

import com.app.entity.Bus;

public interface PurpleServiceI {

	Bus createData(Bus b);

	Bus getData(int busId);

	List<Bus> getAll();

	Bus updateBus( int busId ,Bus b);

	void deleteBus(int busId);

}
