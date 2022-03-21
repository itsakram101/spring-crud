package repositories;

import models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Product product){
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    public List<Product> getProducts(){
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                return p;
            }
        });
    }

    public void setPrice(int id, String name, double price) {
        this.jdbcTemplate.update(
                "update product set price = ?, name = ? where id = ?",
                price, name, id);
    }

    public void deleteProduct(Integer id){
        String sql = "DELETE FROM product WHERE id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }
    
}
