package com.mibrahim.restaurant;

import java.util.ArrayList;

public class RestaurantMenu {

	ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();

	public boolean addMenuItem(RestaurantMenuItem newMenuItem) {
		if (doesItemExist(newMenuItem)) {
			throw new IllegalArgumentException("Duplicate Item");
		}
		return menuItems.add(newMenuItem);
	}

	public boolean doesItemExist(RestaurantMenuItem newMenuItem) {
		boolean Exists = false;
		if (menuItems.contains(newMenuItem)) {
			Exists = true;
		}
		return Exists;
	}

}
