package core;

import java.util.ArrayList;

import util.Address;
import util.Money;

public class PizzaStoreFacade implements PizzaStoreFacadeInterface {
	protected CentralTracker CentralTracker;
	protected Menu menu;
	
	/* Constructor */
	
	public PizzaStoreFacade(CentralTracker centralTracker, boolean debug) {
		CentralTracker = centralTracker;
		menu = centralTracker.getMenu();
		if(debug){
			defaultSetup();
		}
	}
	
	private void defaultSetup(){
		//create default menu
		clearMenu();
		for(MenuItemType type : getMenuItemTypeValues()){
			for (int i=1; i<=5; ++i)
			{
				if(type.toString().equals("Pizza")){
					addMenuItem(type.toString() + " " +i, "$10.50", type, "Desc for "+ type.toString()+ " "+i);
				}
				else if(type.toString().equals("Side")){
					addMenuItem(type.toString() + " " +i, "$5.50", type, "Desc for "+ type.toString()+ " "+i);
				}
				else{
					addMenuItem(type.toString() + " " +i, "$2.50", type, "Desc for "+ type.toString()+ " "+i);
				}
				
			}
		}	
		//set daily special to first pizza item
		//setDailySpecial(getMenuItemsByType(MenuItemType.PIZZA).get(0), "7.50");
		
		//create default coupons
		//second pizza item
		addCoupon(getMenuItemsByType(MenuItemType.PIZZA).get(1), 10);
		//first side item
		addCoupon(getMenuItemsByType(MenuItemType.SIDE).get(0), 5);
		
		//create default users
		createCustomer("Sandy Smith", "1205 Mulberry Street", "Fort Collins, CO",  "80525", "sandy");
		createCustomer("Stan Marsh", "1111 Main Street", "South Park, CO", "70567", "stanley");
		
		//create a default orders/transactions
		int sandyOrderId = startNewOrder("sandy", true);
		addItemToOrder(sandyOrderId, getMenuItemsByType(MenuItemType.PIZZA).get(0), 5);
		addItemToOrder(sandyOrderId, getMenuItemsByType(MenuItemType.SIDE).get(0), 2);
		addItemToOrder(sandyOrderId, getMenuItemsByType(MenuItemType.DRINK).get(0), 7);
		submitOrder(sandyOrderId);
		renderCashPayment(sandyOrderId, getTransacationTotal(getPendingTransactions().get(0)));
		markOrderComplete(sandyOrderId);
		
		int stanOrderId = startNewOrder("stanley", false);
		addItemToOrder(stanOrderId, getMenuItemsByType(MenuItemType.PIZZA).get(0), 1);
		addItemToOrder(stanOrderId, getMenuItemsByType(MenuItemType.DRINK).get(1), 1);		
		submitOrder(stanOrderId);
	}

	
	/* Menu Methods */
	
	public int addMenuItem(String name, String price, MenuItemType type, String description){
		MenuItem newMenuItem = new MenuItem(name, menu.generateMenuItemId(), new Money(price), type, description);
		menu.addItem(newMenuItem);
		return newMenuItem.getId();
	}
	
	public void clearMenu(){
		menu.removeAll();
	}

	public String getMenuItemDescription(int itemId){
		return menu.getMenuItemById(itemId).getDescription();
	}
	
	public String getMenuItemName(int itemId){
		return menu.getMenuItemById(itemId).getName();
	}

	public String getMenuItemPrice(int itemId){
		return menu.getMenuItemById(itemId).getPrice().toString();
	}
	
	public ArrayList<Integer> getMenuItems() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(Integer id : menu.getMenuItems()){
			result.add(id);
		}
		return result;
	}
	/**
	 * 
	 * @param MenuItemType (enumeration limited to select values)
	 * @return an arraylist containing the id's of all menuItems on the menu of the specified type
	 */
	public ArrayList<Integer> getMenuItemsByType(MenuItemType type){
		return menu.getItemsByType(type);
	}
	
	public MenuItemType[] getMenuItemTypeValues(){
		return MenuItemType.values();
	}
	
	public int getNumberMenuItems(){
		return menu.getNumberMenuItems();
	}
	
	public void removeMenuItemById(int id){
		menu.removeItemById(id);
	}
	
	/* Daily Special */
	public void setDailySpecial(String name, String price, MenuItemType type, String description){
		MenuItem dailySpecial = new MenuItem(name, menu.generateMenuItemId(), new Money(price), type, description);
		menu.setDailySpecial(dailySpecial);
	}
	
	public String getDailySpecialName(){
		MenuItem dailySpecial = menu.getDailySpecial();
		if(dailySpecial != null){
			return dailySpecial.getName();
		}else{
			return "No Daily Special";
		}
	}
	
	public String getDailySpecialPrice(){
		MenuItem dailySpecial = menu.getDailySpecial();
		if(dailySpecial != null){
			return dailySpecial.getPrice().toString();
		}else{
			return "No Price";
		}
	}

	public String getDailySpecialDescription(){
		MenuItem dailySpecial = menu.getDailySpecial();
		if(dailySpecial != null){
			return dailySpecial.getDescription();
		}else{
			return "No Description";
		}
	}

	/* Order Methods */
	
	public int addItemToOrder(int orderId, int menuItemId, int quantity){
		CentralTracker.getPendingOrderById(orderId).addItem(new OrderItem(menuItemId,quantity));
		return orderId;
	}
	
	//NOTE: method does not work as intended, underlying implementation of hashmap renders input order meaningless.
	public int getFirstSubmittedOrderId(){
		return CentralTracker.getSubmittedOrders().get(0).getID();
	}
	
	//NOTE: method does not work as intended, underlying implementation of hashmap renders input order meaningless.
	public ArrayList<Integer> getFirstSubmittedOrderItems(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(Object id : CentralTracker.getSubmittedOrders().get(0).getItemIds()){
			result.add((Integer) id);
		}
		return result;
	}
	
	public int getOrderItemQuantity(int orderId, int itemId){
		return CentralTracker.getSubmittedOrderById(orderId).getItemByID(itemId).getQuantity();
	}

	public String getPendingOrderToHtmlString(int orderId) {
		return CentralTracker.getPendingOrderById(orderId).toHTMLString();
	}
	
	//returns the itemId associated with the coupon, or -1
	public int getPendingOrderCouponId(int orderId){
		if(CentralTracker.getPendingOrderById(orderId).getCouponUsed()){
			return CentralTracker.getPendingOrderById(orderId).getCoupon().getItemId();
		}
		else return -1;
	}

	public int getSubmitedOrderCouponId(int orderId){
		if(CentralTracker.getSubmittedOrderById(orderId).getCouponUsed()){
			return CentralTracker.getSubmittedOrderById(orderId).getCoupon().getItemId();
		}
		else return -1;
	}

	
	public boolean getPendingOrderDelivery(int orderId){
		return CentralTracker.getPendingOrderById(orderId).getDelivery();
	}
	
	public ArrayList<Integer> getSubmittedOrderItems(int orderId){
		return  CentralTracker.getSubmittedOrderById(orderId).getItemIds();
	}
	
	public String getSubmittedOrderToHtmlString(int orderId) {
		return CentralTracker.getSubmittedOrderById(orderId).toHTMLString();
	}
	
	public ArrayList<Integer> getSubmittedOrders(){
		return CentralTracker.getSubmittedOrdersIDs();
	}
	
	public void setOrderCoupon(int orderId, int couponId) {
		CentralTracker.getPendingOrderById(orderId).setCoupon(CentralTracker.getCouponById(couponId));
	}
	
	public int startNewOrder(String customerUsername, boolean delivery){
		Order order = new Order(CentralTracker.generateOrderID(),CentralTracker.getCustomerByUsername(customerUsername), menu, delivery);
		CentralTracker.addOrderToPending(order);
		return order.getID();
	}
	
	public void submitOrder(int orderId){
		CentralTracker.submitOrder(orderId);
    }
	
    
    public boolean submittedOrdersEmpty(){
    	return getSubmittedOrders().isEmpty();
    }
	
	
	/*Transaction Methods */
	
    public String getCustomerNameByTransaction(int transactionID){
    	Transaction transaction = CentralTracker.getTransaction(transactionID);
    	return transaction.getOrder().getCustomer().getName();
    }
    
	public ArrayList<Integer> getCompleteTransactions(){
		return CentralTracker.getCompleteTransactionsID();
	}
	
	public ArrayList<Integer> getPendingTransactions(){
		return CentralTracker.getPendingTransactionsID();
	}

	public ArrayList<Integer> getPendingTransactionItems(int transactionID){
		return  CentralTracker.getPendingTransaction(transactionID).getOrder().getItemIds();
	}
	
	public ArrayList<Integer> getCompleteTransactionItems(int transactionID){
		return  CentralTracker.getCompleteTransaction(transactionID).getOrder().getItemIds();
	}
	
	public int getTransactionItemQuantity(int transactionID, int itemId){
		return CentralTracker.getTransaction(transactionID).getOrder().getItemByID(itemId).getQuantity();
	}
	
	public String getTransacationTotal(int transactionID){
		return CentralTracker.getTransaction(transactionID).getOrder().getTotal().toString();
	}
	
	public String getTransactionReciept(int transactionID){
		return CentralTracker.getTransaction(transactionID).getSale().generateReceipt();
	}
    /**
	 * marks the order specified by the ID complete
	 * @param orderID the ID of the order
	 * @return the ID of the resulting transaction object (same as order ID)
	 */
    public int markOrderComplete(int orderID){
    	CentralTracker.markOrderComplete(orderID);
    	return orderID;
    }
    
    public Boolean markTransactionIncomplete(int transactionID){
    	Transaction transaction = CentralTracker.getTransaction(transactionID);
    	transaction.markIncomplete();
    	return false;
    }
    
	public String renderCashPayment(int transactionID, String amount){
		Transaction transaction = CentralTracker.getPendingTransaction(transactionID);
		transaction.renderCashPayment(amount);
		CentralTracker.markTransactionComplete(transaction);
		return transaction.getSale().generateReceipt();
	}
	
	public String renderCreditPayment(int transactionID, String cardNumber, String expirationDate){
		Transaction transaction = CentralTracker.getPendingTransaction(transactionID);
		transaction.renderCreditPayment(cardNumber,expirationDate);
		CentralTracker.markTransactionComplete(transaction);
		return transaction.getSale().generateReceipt();
	}
    
    public boolean isTransactionComplete(int transactionID){
    	Transaction transaction = CentralTracker.getTransaction(transactionID);
    	return transaction.isComplete();
    }
    
    public boolean isTransactionPending(int transactionID){
    	Transaction transaction = CentralTracker.getTransaction(transactionID);
    	return transaction.isPending();
    }
    
    /*Coupon Methods*/
	public void addCoupon(int itemId, int pointValue){
		Coupon coupon = new Coupon(itemId, pointValue);
		CentralTracker.addCoupon(coupon);
	}

	public ArrayList<Integer> getCouponIds(){
		return CentralTracker.getCouponIds();
	}
	
	public int getCouponPoints(int couponId){
		return CentralTracker.getCouponById(couponId).getPointValue();
	}

	public ArrayList<Integer> getCustomerCouponIds(String username){
		return CentralTracker.getCustomerCouponIds(CentralTracker.getCustomerByUsername(username));
	}

	public String getDollarsPerPoint(){
		return CentralTracker.getDollarsPerPoint().toString();
	}

	public void removeCoupon(int itemId){
		CentralTracker.removeCoupon(itemId);
	}

	public void setDollarsPerPoint(String money, int points){
		CentralTracker.setDollarsPerPoint(new Money(money), points);
	}
	
    
	/*Customer Methods*/
	
	public String createCustomer(String name, String street, String cityState,
			String zip, String username) {
		Address address = new Address(street, cityState, zip);
		Customer customer = new Customer(name, address, username);
		CentralTracker.addCustomer(customer);
		return username;
	}
	
	public boolean customerExists(String username){
		return CentralTracker.customerExists(username);
	}
	
    public String getCustomerName(String customerUsername){
    	return CentralTracker.getCustomerByUsername(customerUsername).getName();
    }
    
	public int getCustomerPointsByUserName(String username){
		return CentralTracker.getCustomerByUsername(username).getPoints();
	}

	public ArrayList<Integer> getCustomerOrderHistory(String username){
		return CentralTracker.getCustomerOrderHistory(username);
	}

}
