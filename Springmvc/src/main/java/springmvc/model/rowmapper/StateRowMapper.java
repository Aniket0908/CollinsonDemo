package springmvc.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



import springmvc.model.State;

public class StateRowMapper implements RowMapper<State> {
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		State state = new State();
		state.setId(rs.getInt("id"));
		state.setName(rs.getString("state"));
		return state;
	}
}
