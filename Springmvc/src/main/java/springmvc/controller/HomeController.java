package springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springmvc.dao.CustomerDao;
import springmvc.model.City;
import springmvc.model.Customer;
import springmvc.model.State;

@Controller
public class HomeController {
	@Autowired
	private CustomerDao customerDao;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
	public ModelAndView listCustomer(HttpServletRequest request ,ModelAndView model) {
		String colName = request.getParameter("colName");
		String order = request.getParameter("order");
		
		if(colName == null || order.trim().equals("") || order == null) {
			colName = "id";
			order = "asc";
		}
		
		
		int total = 5;
		int pageId=1;
		
		if(request.getParameter("p")!=null) {
		 pageId = Integer.parseInt(request.getParameter("p"));
		}
		if (pageId > 1) {
			pageId = (pageId - 1) * total + 1;
		} else {
			pageId = 1;
		}
		List<Customer> listCustomer = customerDao.listAllCustomers(pageId, total, colName,order);
		
		System.out.println(listCustomer.toString());
		int totalRecord = customerDao.getCount();
		int totalPages = totalRecord / total;
		if (totalRecord % 5 > 0) {
			totalPages++;
		}
		model.addObject("listCustomer", listCustomer);
		model.addObject("pageid", pageId);
		model.addObject("totalPages", totalPages);
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
	public ModelAndView newCustomer(ModelAndView model) {
		Customer newCustomer = new Customer();
		model.addObject("customer", newCustomer);

		List<State> list = customerDao.getStates();
		for(State state : list) {
			System.out.println(state.getId());
			System.out.println(state.getName());
		}
//		System.out.println(list.toString());
		model.addObject("statelist", list);
		model.setViewName("CustomerForm");
		return model;
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public ModelAndView saveCustomer(@ModelAttribute Customer customer) {

		if (customer.getPincode().isEmpty()) {
			customer.setPincode(null);
		}
		if (customer.getMobileNumber().isEmpty()) {
			customer.setMobileNumber(null);
		}
		
		customerDao.saveOrUpdate(customer);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("id"));
		customerDao.deleteCustomer(customerId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editCustomer", method = RequestMethod.GET)
	public ModelAndView editCustomer(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDao.get(customerId);
		List<State> stateList = customerDao.getStates();
		List<City> cityList = customerDao.getCities(customer.getState().getId());
		ModelAndView model = new ModelAndView("CustomerForm");
		model.addObject("customer", customer);
		model.addObject("statelist", stateList);
		model.addObject("citylist", cityList);
		return model;
	}


	@RequestMapping(value = "/getCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<City> getCity(@RequestParam("state") Integer state) {

		return customerDao.getCities(state);

	}
	
	
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.GET)
	public ModelAndView searchCustomer(HttpServletRequest request, ModelAndView model) {
		String searchField = request.getParameter("searchField");
		String searchValue = request.getParameter("searchValue");
		System.out.println(searchField);
		System.out.println(searchValue);
		int total = 5;
		int pageId=1;
		
		if(request.getParameter("p")!=null) {
		 pageId = Integer.parseInt(request.getParameter("p"));
		}
		if (pageId > 1) {
			pageId = (pageId - 1) * total + 1;
		} else {
			pageId = 1;
		}
		
		int totalRecord = customerDao.getsSearchCount(searchField, searchValue);
		System.out.println(totalRecord);
		int totalPages = totalRecord / total;
		if (totalRecord % 5 > 0) {
			totalPages++;
		}
		List<Customer> list = customerDao.searchCustomers(searchField, searchValue, pageId, total);
		
		model.addObject("listCustomer", list);
		model.addObject("searchField", searchField);
		model.addObject("searchValue", searchValue);
		model.addObject("pageid", pageId);
		model.addObject("search", true);
		model.addObject("totalPages", totalPages);
		model.setViewName("index");
		return model;
	}

}
