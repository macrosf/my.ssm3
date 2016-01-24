package cn.oss.ssm3.spitter.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import cn.oss.ssm3.spitter.domain.Spitter;
import cn.oss.ssm3.spitter.domain.Spittle;

/**
 * copy from project [spitter-persistence-jdbc-dao-support]
 * @author Administrator
 *
 */
public class SimpleJdbcSupportSpitterDao 
    extends SimpleJdbcDaoSupport implements SpitterDao {

  private static final String SQL_INSERT_SPITTER = "insert into spitter (username, password, fullname, email, update_by_email) values (?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE_SPITTER = "update spitter set username = ?, password = ?, fullname = ?, set email = ?"
          + "where id = ?";

  private static final String SQL_SELECT_SPITTER = ""
          + "select id, username, password, fullname, email from spitter";

  private static final String SQL_SELECT_SPITTER_BY_ID = SQL_SELECT_SPITTER
          + " where id=?";

  private static final String SQL_INSERT_SPITTLE = "" +
  "insert into spittle (spitter_id, spittleText, postedTime) values (?, ?, ?)";

  private static final String SQL_SELECT_SPITTLE = 
  "select id, spitter_id, spittleText, postedTime from spittle";

private static final String SQL_SELECT_RECENT_SPITTLE = 
  SQL_SELECT_SPITTLE + " where postedTime > ? order by postedTime desc";

  //<start id="java_getSpitterById" /> 
  @SuppressWarnings("deprecation")
public Spitter getSpitterById(long id) {
    return getSimpleJdbcTemplate().queryForObject(
            SQL_SELECT_SPITTER_BY_ID,
        new ParameterizedRowMapper<Spitter>() {
          public Spitter mapRow(ResultSet rs, int rowNum) 
              throws SQLException {
            Spitter spitter = new Spitter();
            spitter.setId(rs.getLong(1));
            spitter.setUsername(rs.getString(2));
            spitter.setPassword(rs.getString(3));
            spitter.setFullName(rs.getString(4));
            spitter.setEmail(rs.getString(5));
            return spitter;
          }
        }, id);
  }
  //<end id="java_getSpitterById" />

  //<start id="java_addSpitter" /> 
  public void addSpitter(Spitter spitter) {
    getSimpleJdbcTemplate().update(SQL_INSERT_SPITTER,
            spitter.getUsername(), 
            spitter.getPassword(),
            spitter.getFullName(),
            spitter.getEmail(),
            spitter.isUpdateByEmail());
    spitter.setId(queryForIdentity());
  }
  //<end id="java_addSpitter" />

  public void saveSpitter(Spitter spitter) {
    getSimpleJdbcTemplate().update(SQL_UPDATE_SPITTER,
            spitter.getUsername(), 
            spitter.getPassword(),
            spitter.getFullName(), 
            spitter.getEmail(),

            spitter.getId());
  }

  //<start id="java_queryForIdentity" /> 
  private long queryForIdentity() {
    return getSimpleJdbcTemplate().queryForLong("call identity()");
  }
  //<end id="java_queryForIdentity" />
  
  
  @SuppressWarnings("deprecation")
public List<Spittle> getRecentSpittle() {
    DateTime dt = new DateTime().minusDays(10000);
    
    return getSimpleJdbcTemplate().query(SQL_SELECT_RECENT_SPITTLE, 
          new ParameterizedRowMapper<Spittle>() {
      public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spittle spittle = new Spittle();
        
        spittle.setId(rs.getLong(1));
        spittle.setSpitter(getSpitterById(rs.getLong(2)));
        spittle.setText(rs.getString(3));
        spittle.setWhen(rs.getDate(4));
        
        return spittle;
      }
    }, dt.toDate());
  }
  
  public void saveSpittle(Spittle spittle) {
    getSimpleJdbcTemplate().update(SQL_INSERT_SPITTLE, new Object[] {
        spittle.getSpitter().getId(),
        spittle.getText(),
        new Date()
    });
    
  }

  public List<Spittle> getSpittlesForSpitter(
          Spitter spitter) {
    // TODO Auto-generated method stub
    return null;
  }
  

  public Spitter getSpitterByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteSpittle(long id) {
    throw new UnsupportedOperationException();
  }

  public Spittle getSpittleById(long id) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public List<Spitter> findAllSpitters() {
    // TODO Auto-generated method stub
    return null;
  }
}
