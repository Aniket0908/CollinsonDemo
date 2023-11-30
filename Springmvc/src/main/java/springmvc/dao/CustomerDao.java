package springmvc.dao;

import java.util.List;

import springmvc.model.City;
import springmvc.model.Customer;
import springmvc.model.State;

public interface CustomerDao {
	public List<Customer> listAllCustomers(int pageid, int total, String colName, String order);
	public void saveOrUpdate(Customer customer);
	public Customer get(int id);
	public void deleteCustomer(int id);
	
	public List<State> getStates();
	public int getCount();
	public List<Customer> listAllCustomersAndSorting(int pageId, int total, int colName, int order);
	public int getsSearchCount(String searchField, String searchValue);
	public List<Customer> searchCustomers(String name, String input, int pageId, int total);
	public List<City> getCities(Integer state);
}
