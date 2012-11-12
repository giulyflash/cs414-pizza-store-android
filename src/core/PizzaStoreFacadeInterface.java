package core;

import java.util.ArrayList;

public interface PizzaStoreFacadeInterface{
	
	/* Menu Methods */
	public int addMenuItem(String name, String price, MenuItemType type, String description);
	public void clearMenu();
	public String getMenuItemDescription(int itemId);
	public String getMenuItemName(int itemId);
	public String getMenuItemPrice(int itemId);
	public ArrayList<Integer> getMenuItems();
	public ArrayList<Integer> getMenuItemsByType(MenuItemType type);
	public MenuItemType[] getMenuItemTypeValues();
	public int getNumberMenuItems();
	public void removeMenuItemById(int id);
	/* Daily Special */
	public void setDailySpecial(String name, String price, MenuItemType type, String description);
	public String getDailySpecialName();
	public String getDailySpecialPrice();
	public String getDailySpecialDescription();
	
	/* Order Methods */
	public int addItemToOrder(int orderId, int menuItemId, int quantity);
	public int getFirstSubmittedOrderId();
	public ArrayList<Integer> getFirstSubmittedOrderItems();
	public int getOrderItemQuantity(int orderId, int itemId);
	public int getPendingOrderCouponId(int orderId);
	public String getPendingOrderToHtmlString(int orderId);
	public boolean getPendingOrderDelivery(int orderId);
	public int getSubmitedOrderCouponId(int orderId);
	public ArrayList<Integer> getSubmittedOrderItems(int orderId);
	public String getSubmittedOrderToHtmlString(int orderId);
	public ArrayList<Integer> getSubmittedOrders();
	public int markOrderComplete(int orderID);
	public void setOrderCoupon(int orderId, int couponId);
	public int startNewOrder(String customerUsername, boolean delivery);
	public void submitOrder(int orderId);
	public boolean submittedOrdersEmpty();
	
	/*Transaction Methods */
	public String getCustomerNameByTransaction(int transactionID);
	public ArrayList<Integer> getCompleteTransactions();
	public ArrayList<Integer> getPendingTransactions();
	public ArrayList<Integer> getPendingTransactionItems(int transactionID);
	public ArrayList<Integer> getCompleteTransactionItems(int transactionID);
	public int getTransactionItemQuantity(int transactionID, int itemId);
	public String getTransacationTotal(int transactionID);
	public String getTransactionReciept(int transactionID);
	public Boolean markTransactionIncomplete(int transactionID);
	public String renderCashPayment(int transactionID, String amount);
	public String renderCreditPayment(int transactionID, String cardNumber, String expirationDate);
	public boolean isTransactionComplete(int transactionID);
	public boolean isTransactionPending(int transactionID);
	
	
	/*Coupon Methods*/
	public void addCoupon(int itemId, int pointValue);
	public ArrayList<Integer> getCouponIds();
	public int getCouponPoints(int couponId);
	public ArrayList<Integer> getCustomerCouponIds(String username);
	public String getDollarsPerPoint();
	public void removeCoupon(int itemId);
	public void setDollarsPerPoint(String money, int points);
	
	/*Customer Methods*/
	public String createCustomer(String name, String street, String cityState, String zip, String username);	
	public boolean customerExists(String username);
	public String getCustomerName(String customerUsername);
	public int getCustomerPointsByUserName(String username);
	public ArrayList<Integer> getCustomerOrderHistory(String username);
}
