package io.shop.dao;

import io.shop.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rahman S
 * @since / 1:09:37 PM
 */
public class ProductDao extends BaseDao {

    public ProductDao() {
        this.table = "produk";
        this.primaryKey = "id_produk";
    }

    public ArrayList<Product> all() {
        return super.all(Product.class);
    }

    public Product find(int productId) {
        return super.find(productId, Product.class);
    }

    public void add(Product product) {
        Map<String, Object> data = new HashMap();

        data.put("kode_produk", product.getKodeProduk());
        data.put("nama_produk", product.getNamaProduk());
        data.put("satuan", product.getSatuanProduct());
        data.put("harga", product.getHargaProduct());
        data.put("stock", product.getStockProduct());
        data.put("create_date", "\"STR_TO_DATE('" + product.getCreateProduct() + "', '%Y,%m,%d %H,%i,%s')\"");

        super.add(data);
    }

  

    public void delete(Product product) {
        super.delete(String.valueOf(product.getIdProduk()));
    }

    public ResultSet cetak() {
        return super.print();
    }

    public void update(Product product, int id) {
        Map<String, Object> data = new HashMap();
        
        data.put("nama_produk", product.getNamaProduk());
        data.put("satuan", product.getSatuanProduct());
        data.put("harga", product.getHargaProduct());
        data.put("stock", product.getStockProduct());
            
        //id = product.getIdProduk();
        
        super.update(data, id);
    }

}
