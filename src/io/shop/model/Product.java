/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.shop.model;

import io.shop.dao.ProductDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Rahman S
 * @since / 1:04:20 PM
 */
public class Product implements BaseModel {

    private int idProduk;
    private String kodeProduk;
    private String namaProduk;
    private String satuanProduct;
    private String hargaProduct;
    private String stockProduct;
    private String createProduct;

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getSatuanProduct() {
        return satuanProduct;
    }

    public void setSatuanProduct(String satuanProduct) {
        this.satuanProduct = satuanProduct;
    }

    public String getHargaProduct() {
        return hargaProduct;
    }

    public void setHargaProduct(String hargaProduct) {
        this.hargaProduct = hargaProduct;
    }

    public String getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(String stockProduct) {
        this.stockProduct = stockProduct;
    }

    public String getCreateProduct() {
        return createProduct;
    }

    public void setCreateProduct(String createProduct) {
        this.createProduct = createProduct;
    }

    @Override
    public void save() {
        new ProductDao().add(this);

    }

    @Override
    public void delete() {
        if (this.idProduk != 0) {
            new ProductDao().delete(this.idProduk + "");
        }
    }

    @Override
    public void fillFromResultSet(ResultSet result) throws SQLException {
        setIdProduk(result.getInt("id_produk"));
        setKodeProduk(result.getString("kode_produk"));
        setNamaProduk(result.getString("nama_produk"));
        setSatuanProduct(result.getString("satuan"));
        setHargaProduct(result.getString("harga"));
        setStockProduct(result.getString("stock"));
        setCreateProduct(result.getString("create_date"));
    }

    @Override
    public void updateData(int id) {
          this.idProduk = id;
          new ProductDao().update(this, this.idProduk);

    }

}
