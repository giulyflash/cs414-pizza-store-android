package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Menu{
	private static int CurrentID;
	private HashMap<Integer, MenuItem> menuItems;
	private MenuItem dailySpecial;
	
	public Menu(){
		CurrentID = 0;
		menuItems = new HashMap<Integer, MenuItem>();
	}

	public int generateMenuItemId(){
		return CurrentID++;
	}
	
	public void addItem(MenuItem item){
		menuItems.put(item.getId(), item);
	}
	
	public void addItems(ArrayList<MenuItem> items){
		for(MenuItem item:items){
			this.addItem(item);
		}
	}
	
	public MenuItem getDailySpecial(){
		return dailySpecial;
	}
	
	public MenuItem getMenuItemById(int id){
		return menuItems.get(id);
	}
	
	public int getNumberMenuItems(){
		return menuItems.size();
	}
	
	public ArrayList<Integer> getItemsByType(MenuItemType type){
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(Entry<Integer, MenuItem> item:menuItems.entrySet()){
			if(item.getValue().getType().equals(type)){
				items.add(item.getKey());
			}
		}
		return items;
	}
	
	public Set<Integer> getMenuItems(){
		return menuItems.keySet();
	}
	
	public void removeAll(){
		menuItems.clear();
	}
	
	public void removeItem(MenuItem item){
		menuItems.remove(item.getId());
	}
	
	public void removeItemById(int id){
		menuItems.remove(id);
	}
	
	public void removeItems(ArrayList<MenuItem> items){
		for(MenuItem item:items){
			this.removeItem(item);
		}
	}
	
	public void setDailySpecial(MenuItem dailySpecial){
		this.dailySpecial = dailySpecial;
	}
	
	public int numberOfItems(){ 
		return menuItems.size();
	}
}
