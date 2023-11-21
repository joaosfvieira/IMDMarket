package br.ufrn.imd.imdmarket.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.imdmarket.model.Product;

public class ProductRepository {
    private static ProductRepository instance;
    private static List<Product> productList;
    private static File productsFile;

    private ProductRepository() {
    }

    public static synchronized ProductRepository getInstance() {
        if (productsFile == null) {
            throw new RuntimeException("Inserir local do arquivo usando " +
                        "ProductRepository.setProductsFile(file).");
        }
        if (instance == null &&  productsFile != null) {
            instance = new ProductRepository();
            productList = new ArrayList<>();
            instance.setProductList(productList);
        }
        return instance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product p) {
        productList.add(p);
        ProductRepository.saveProductsInFile();
    }

    public void removeProductByIndex(Integer index) {
        productList.remove(index);
    }

    public void removeProduct(Product p) {
        productList.remove(p);
    }

    public boolean removeProductByCode(String productCode) {
        for(Product p : productList) {
            if (p.getCode().equalsIgnoreCase(productCode)) {
                productList.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(String productCode, String productName, String productDescription, String productStock) {
        for(Product p : productList) {
            if (p.getCode().equalsIgnoreCase(productCode)) {

                if(!productName.isEmpty()) {
                    p.setName(productName);
                }
                if(!productDescription.isEmpty()) {
                    p.setDescription(productDescription);
                }
                if(!productStock.isEmpty()) {
                    p.setStock(Integer.parseInt(productStock));
                }
                return true;
            }
        }
        return false;
    }

    public static void setProductsFile(File file) {
        ProductRepository.productsFile = file;
    }

    public static void saveProductsInFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ProductRepository.productsFile));

            List<String> lines = new ArrayList<>();
            ProductRepository productRepository = ProductRepository.getInstance();
            List<Product> products = productRepository.getProductList();

            for(Product p : products) {
                String formatedProduct = p.getCode() + ";" + p.getName() + ";" + p.getDescription() + ";" + p.getStock();
                lines.add(formatedProduct);
            }
            for (String line : lines) {
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();

            System.out.println("> Products saved in file database");
        } catch (Exception e) {
            System.out.println("> ERROR saving products in file database");
            e.printStackTrace();
        }
    }

    public static void readProductsFromFile() {
        try {
            ProductRepository productRepository = ProductRepository.getInstance();
            List<Product> products = productRepository.getProductList();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(ProductRepository.productsFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split com ponto e vírgula no arquivo products.txt linha a linha
                String[] objSplit = line.split(";");

                // Mapeando o vetor de strings para um objeto Product
                Product product = new Product(objSplit[0], objSplit[1], objSplit[2], Integer.valueOf(objSplit[3]));
                System.out.println(product.toString());
                // Salvando o obj product na lista de products
                products.add(product);

            }

            bufferedReader.close();
            System.out.println("> Products loaded from file database");
        } catch (Exception e) {
            System.out.println("> ERROR loading products from file database");
            e.printStackTrace();
        }
    }
}
