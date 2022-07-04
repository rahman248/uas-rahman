package io.shop.controller;

import io.shop.dao.ProductDao;
import io.shop.model.Product;
import io.shop.view.MainView;
import java.awt.Color;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rahman S
 * @since / 12:07:58 PM
 */
public class MainController {

    ProductDao dao;
    DefaultTableModel model;
    Product products;

    public MainController() {
        this.dao = new ProductDao();
        this.products = new Product();

    }

    public void loadTable(MainView view) {
        ArrayList<Product> listProduct = dao.all();

        model = (DefaultTableModel) view.getProductTable().getModel();

        model.setNumRows(0);

        listProduct.forEach((product) -> {
            Object[] row = new Object[6];
            row[0] = product.getKodeProduk();
            row[1] = product.getNamaProduk();
            row[2] = product.getHargaProduct();
            row[3] = product.getStockProduct();
            row[4] = product.getSatuanProduct();
            row[5] = product.getCreateProduct();
            model.addRow(row);
        });
    }

    public void save(MainView view) {

        int mProductId = 0;
        String mProductCode = "";
        ArrayList<Product> listProduct = dao.all();
        for (int i = 0; i < listProduct.size(); i++) {
            mProductId = listProduct.get(i).getIdProduk();
            mProductCode = listProduct.get(i).getKodeProduk();
        }

        Product existingProduct = dao.find(mProductId);
        //'2014,6,30 07,30,59'
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd HH,mm,ss");

        products = new Product();

        products.setKodeProduk(view.getKodeProductField().getText());
        products.setNamaProduk(view.getNameProductField().getText());
        products.setHargaProduct(view.getPriceProductField().getText());
        products.setStockProduct(view.getStockProductField().getText());
        products.setSatuanProduct(view.getSatuan().getSelectedItem().toString());
        products.setCreateProduct(formatter.format(new Date()));

        if (view.getKodeProductField().getText().isEmpty()) {

            JOptionPane.showMessageDialog(view, "Kode Produk Tidak Boleh Kosong");
            return;
        } else {
            products.save();
        }

        // only reset if creating new user
        if (mProductId == 0) {
            this.reset(view);
        }

        this.loadTable(view);

    }

    public void update(MainView view) {

        ArrayList<Product> listProduct = dao.all();
        Product existingProduct = dao.find(getProductId(listProduct));
        //'2014,6,30 07,30,59'
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd HH,mm,ss");
        
        var selectedId = getProductId(listProduct);

        products = new Product();

        products.setKodeProduk(view.getKodeProductField().getText());
        products.setNamaProduk(view.getNameProductField().getText());
        products.setHargaProduct(view.getPriceProductField().getText());
        products.setStockProduct(view.getStockProductField().getText());
        products.setSatuanProduct(view.getSatuan().getSelectedItem().toString());
        products.setCreateProduct(formatter.format(new Date()));

        products.updateData(selectedId);
        if (selectedId == existingProduct.getIdProduk()) this.reset(view);
        this.loadTable(view);

    }

    public int getProductId(ArrayList<Product> listProduct) {
        int mProductId = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            mProductId = listProduct.get(i).getIdProduk();

        }
        return mProductId;
    }

    public void setFormFieldFromClickedTable(MainView view) {
        int selectedRow = view.getProductTable().getSelectedRow();

        String productCode = view.getProductTable().getValueAt(selectedRow, 0).toString();
        String productName = view.getProductTable().getValueAt(selectedRow, 1).toString();
        String productPrice = view.getProductTable().getValueAt(selectedRow, 2).toString();
        String productStock = view.getProductTable().getValueAt(selectedRow, 3).toString();
        String satuan = view.getProductTable().getValueAt(selectedRow, 4).toString();
        String createDate = view.getProductTable().getValueAt(selectedRow, 5).toString();

        view.getKodeProductField().setText(productCode);
        view.getNameProductField().setText(productName);
        view.getPriceProductField().setText(productPrice);
        view.getStockProductField().setText(productStock);
        view.getSatuan().setSelectedItem(satuan);
    }

    public void enableView(MainView view) {
        view.getSatuan().setEnabled(true);
        view.getSatuan().setSelectedItem("Pilih Satuan");

        view.getKodeProductField().setEnabled(true);
        view.getStockProductField().setForeground(new Color(255, 255, 255));

        view.getNameProductField().setEnabled(true);
        view.getNameProductField().setForeground(new Color(255, 255, 255));

        view.getPriceProductField().setEnabled(true);
        view.getPriceProductField().setForeground(new Color(255, 255, 255));

        view.getStockProductField().setEnabled(true);
        view.getStockProductField().setForeground(new Color(255, 255, 255));

        view.getSaveButton().setEnabled(true);

    }

    public void resetButton(MainView view) {
        view.getSatuan().setSelectedItem("Pilih Satuan");
        view.getNameProductField().setText("");
        view.getKodeProductField().setText("");
        view.getPriceProductField().setText("");
        view.getStockProductField().setText("");
        view.getSatuan().setEnabled(true);
        view.getSatuan().setSelectedItem("Pilih Satuan");
        view.getKodeProductField().setEnabled(true);
        view.getKodeProductField().setBackground(new Color(255, 255, 255));

        view.getNameProductField().setEnabled(true);
        view.getNameProductField().setBackground(new Color(255, 255, 255));

        view.getPriceProductField().setEnabled(true);
        view.getPriceProductField().setBackground(new Color(255, 255, 255));

        view.getStockProductField().setEnabled(true);
        view.getStockProductField().setBackground(new Color(255, 255, 255));

        view.getSaveButton().setEnabled(true);
        view.getUpdateButton().setEnabled(false);
        view.getDeleteButton().setEnabled(false);
        view.getCancelButton().setEnabled(false);
    }

    public void disableView(MainView view) {
        view.getKodeProductField().setEnabled(false);
        view.getKodeProductField().setBackground(new Color(218, 218, 218));
        view.getNameProductField().setEnabled(false);
        view.getNameProductField().setBackground(new Color(218, 218, 218));
        view.getPriceProductField().setEnabled(false);
        view.getPriceProductField().setBackground(new Color(218, 218, 218));
        view.getStockProductField().setEnabled(false);
        view.getStockProductField().setBackground(new Color(218, 218, 218));
        view.getSaveButton().setEnabled(false);
        view.getSatuan().setEnabled(false);
        view.getSatuan().setSelectedItem("Pilih Satuan");

    }

    public void disableSelectedView(MainView view) {
        view.getSatuan().setEnabled(true);
        view.getKodeProductField().setEnabled(false);
        view.getKodeProductField().setBackground(new Color(218, 218, 218));

        view.getNameProductField().setEnabled(true);
        view.getNameProductField().setForeground(new Color(0, 0, 0));
        view.getNameProductField().setBackground(new Color(255, 255, 255));

        view.getPriceProductField().setEnabled(true);
        view.getPriceProductField().setForeground(new Color(0, 0, 0));
        view.getPriceProductField().setBackground(new Color(255, 255, 255));

        view.getStockProductField().setEnabled(true);
        view.getStockProductField().setForeground(new Color(0, 0, 0));
        view.getStockProductField().setBackground(new Color(255, 255, 255));

        view.getSaveButton().setEnabled(false);
        view.getUpdateButton().setEnabled(true);
        view.getDeleteButton().setEnabled(true);
        view.getCancelButton().setEnabled(true);

    }

    public void reset(MainView view) {
        view.getSatuan().setSelectedItem("Pilih Satuan");
        view.getNameProductField().setText("");
        view.getKodeProductField().setText("");
        view.getPriceProductField().setText("");
        view.getStockProductField().setText("");
        view.getKodeProductField().setEnabled(false);
        view.getKodeProductField().setBackground(new Color(218, 218, 218));

        view.getNameProductField().setEnabled(false);
        view.getNameProductField().setBackground(new Color(218, 218, 218));

        view.getPriceProductField().setEnabled(false);
        view.getPriceProductField().setBackground(new Color(218, 218, 218));

        view.getStockProductField().setEnabled(false);
        view.getStockProductField().setBackground(new Color(218, 218, 218));

        view.getSaveButton().setEnabled(false);
        view.getSatuan().setEnabled(false);
        view.getSatuan().setSelectedItem("Pilih Satuan");
    }

    public void delete(MainView view) {

        int mProductId = 0;
        String mProductCode = "";
        ArrayList<Product> listProduct = dao.all();
        for (int i = 0; i < listProduct.size(); i++) {
            mProductId = listProduct.get(i).getIdProduk();
            mProductCode = listProduct.get(i).getKodeProduk();
        }

        if (mProductCode.equals("")) {
            JOptionPane.showMessageDialog(view, "Pilih product yang akan dihapus");

            return;
        }

        int response = JOptionPane.showConfirmDialog(null, "Menghapus product akan mempengaruhi data produk! apakah anda yakin?", "Konfirmasi Hapus", JOptionPane.WARNING_MESSAGE);

        if (response != JOptionPane.OK_OPTION) {
            return;
        }

        dao.find(mProductId).delete();

        this.reset(view);
        this.loadTable(view);
    }

    public void print(MainView view) {
        File dir1 = new File(".");
        String dirr = "";

        try {

            dirr = dir1.getCanonicalPath() + "/src/assets/";

            JasperDesign disain = JRXmlLoader.load(dirr + "laporan.jrxml");
            JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(dao.cetak());
            JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan, new HashMap(), resultSetDataSource);

            JasperViewer.viewReport(cetak);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak\n" + e);
        }

    }

}
