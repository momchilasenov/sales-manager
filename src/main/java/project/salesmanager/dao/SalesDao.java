package project.salesmanager.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.salesmanager.model.Sale;

import java.util.List;

@Repository
public class SalesDao
{
  private final JdbcTemplate jdbcTemplate;

  public SalesDao(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Sale> list()
  {
    String sql = "SELECT * FROM sales";
    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sale.class));
  }

  public void save(Sale sale)
  {
    SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    insertActor.withTableName("sales").usingColumns("item", "quantity", "amount");

    BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(sale);
    insertActor.execute(parameterSource);
  }

  public Sale get(int id)
  {
    String sql = "SELECT * FROM sales WHERE id=?";
    return jdbcTemplate.queryForObject
        (sql, BeanPropertyRowMapper.newInstance(Sale.class), id);
  }

  public void update(Sale sale)
  {
    String sql = "UPDATE SALES SET item=:item, " +
        "quantity=:quantity, amount=:amount WHERE id=:id";
    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    template.update(sql, param);
  }

  public void delete(int id)
  {
    String sql = "DELETE FROM sales WHERE id=?";
    jdbcTemplate.update(sql, id);
  }
}
