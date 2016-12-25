package com.shkil.usermanagement.gui;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.shkil.usermanagement.User;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {"ID","Имя","Фамилия"};
	private List users = null;
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	
	public  UserTableModel(Collection users) {
		this.users = new ArrayList(users);	
	}

	public int getRowCount() {
		return users.size();
	}

	
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	

	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

}
