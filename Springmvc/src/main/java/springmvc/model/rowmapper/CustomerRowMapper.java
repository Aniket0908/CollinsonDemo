package springmvc.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import springmvc.model.City;
import springmvc.model.Customer;
import springmvc.model.State;

public class CustomerRowMapper implements RowMapper<Customer> {

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		State state = new State();
		City city = new City();
		
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setGender(rs.getString("gender"));
		customer.setEmail(rs.getString("email"));
		customer.setMobileNumber(rs.getString("mobile_number"));
		customer.setDob(sdf.format(rs.getDate("dob")));
		customer.setWebsiteURL(rs.getString("website_url"));
		customer.setAddressLine1(rs.getString("address_line1"));
		customer.setAddressLine2(rs.getString("address_line2"));
		customer.setAddressLine3(rs.getString("address_line3"));
		state.setId(rs.getInt("state_id"));
		state.setName(rs.getString("stateName"));
		customer.setState(state);
		city.setId(rs.getInt("city_id"));
		city.setName(rs.getString("cityName"));
		customer.setCity(city);
		customer.setPincode(rs.getString("pincode"));
		return customer;
	}

}
