package springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import springmvc.model.City;
import springmvc.model.Customer;
import springmvc.model.State;
import springmvc.model.rowmapper.CityRowMapper;
import springmvc.model.rowmapper.CustomerRowMapper;
import springmvc.model.rowmapper.StateRowMapper;

public class CustomerDaoImpl implements CustomerDao {

	JdbcTemplate jdbcTemplate;

	public CustomerDaoImpl(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Customer> listAllCustomers(int pageid, int total, String colName, String order) {
		String sql = "select c.* , s.state as stateName, ct.city as cityName "
				+ "from tbl_customer c "
				+ "left join tbl_state s on s.id = c.state_id "
				+ "left join tbl_city ct on ct.id = c.city_id"  + " order by " + colName +" "+order + " limit " + (pageid - 1) + "," + total;
		List<Customer> list = jdbcTemplate.query(sql, new CustomerRowMapper());
		return list;
	}

	@Override
	public void deleteCustomer(int id) {
		String sql = "DELETE FROM tbl_customer WHERE id=?";
		jdbcTemplate.update(sql, id);
	}


	@Override
	public void saveOrUpdate(Customer c) {

		
		if (c.getId() > 0) {
			
			String sql = "UPDATE tbl_customer SET first_name=?, last_name=?, gender=?, email=?, mobile_number=?, "
					+ "dob=?, website_url=?, address_line1=?, address_line2=?, address_line3=?, "
					+ "city_id=?,state_id=?, pincode=? WHERE id=?";

			jdbcTemplate.update(sql, c.getFirstName(), c.getLastName(), c.getGender(), c.getEmail(),
					c.getMobileNumber(), c.getDob(), c.getWebsiteURL(), c.getAddressLine1(), c.getAddressLine2(),
					c.getAddressLine3(), c.getCity().getId(), c.getState().getId(), c.getPincode(), c.getId());
		} else {

			String sql = "INSERT INTO tbl_customer(first_name,last_name,gender,email,mobile_number,"
					+ "dob,website_url,address_line1,address_line2,address_line3,state_id,city_id,pincode) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			jdbcTemplate.update(sql, c.getFirstName(), c.getLastName(), c.getGender(), c.getEmail(),
					c.getMobileNumber(), c.getDob(), c.getWebsiteURL(), c.getAddressLine1(), c.getAddressLine2(),
					c.getAddressLine3(), c.getState().getId(), c.getCity().getId(), c.getPincode());
		}
	}

	@Override
	public Customer get(int id) {
		String sql = "select c.* , s.state as stateName, ct.city as cityName "
				+ "from tbl_customer c "
				+ "left join tbl_state s on s.id = c.state_id "
				+ "left join tbl_city ct on ct.id = c.city_id WHERE c.id = ?";
		Object[] params = new Object[1];
		params[0] = id;
		return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), params);

	}

	@Override
	public List<State> getStates() {
		String sql = "SELECT * FROM tbl_state";
		return jdbcTemplate.query(sql, new StateRowMapper());
	}

	@Override
	public int getCount() {
		String sql = "Select count(*) from tbl_customer";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<City> getCities(Integer state) {
		String sql = "Select * from tbl_city where state_id =?";
		Object[] params = new Object[1];
		params[0] = state;
		List<City> list = jdbcTemplate.query(sql, new CityRowMapper(), params);
		return list;
	}

	@Override
	public List<Customer> searchCustomers(String searchField, String searchValue, int pageId, int total) {
		String sqlString = "";
		
		if(searchField.equals("firstName")) {
			sqlString = "first_name like '"+ searchValue + "%'";
		}else if(searchField.equals("lastName")) {
			sqlString = "last_name like '"+ searchValue + "%'";
		}else {
			sqlString = "gender like '"+ searchValue + "%'";
		}
		
		String sql = "select c.* , s.state as stateName, ct.city as cityName "
				+ "from tbl_customer c "
				+ "left join tbl_state s on s.id = c.state_id "
				+ "left join tbl_city ct on ct.id = c.city_id WHERE "+sqlString+ " limit " + (pageId - 1) + "," + total;
		
		
		
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}

	@Override
	public int getsSearchCount(String searchField, String searchValue) {
		
		String sqlString = "";
		
		if(searchField.equals("firstName")) {
			sqlString = "first_name like '"+ searchValue + "%'";
		}else if(searchField.equals("lastName")) {
			sqlString = "last_name like '"+ searchValue + "%'";
		}else {
			sqlString = "gender like '"+ searchValue + "%'";
		}
		String sql = "select count(*) "
				+ "from tbl_customer c "
				+ "left join tbl_state s on s.id = c.state_id "
				+ "left join tbl_city ct on ct.id = c.city_id WHERE "+sqlString+";";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Customer> listAllCustomersAndSorting(int pageId, int total, int colName, int order) {
		String sql = "select c.* , s.state as stateName, ct.city as cityName "
				+ "from tbl_customer c "
				+ "left join tbl_state s on s.id = c.state_id "
				+ "left join tbl_city ct on ct.id = c.city_id limit " + (pageId - 1) + "," + total + " orderby " + colName +" "+order;
		List<Customer> list = jdbcTemplate.query(sql, new CustomerRowMapper());
		
		return list;
	}
	
	

}
