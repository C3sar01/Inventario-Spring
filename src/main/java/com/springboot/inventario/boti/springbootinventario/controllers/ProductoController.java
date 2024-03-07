package com.springboot.inventario.boti.springbootinventario.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventario.boti.springbootinventario.models.Product;
import com.springboot.inventario.boti.springbootinventario.response.ProductResponseRest;
import com.springboot.inventario.boti.springbootinventario.services.IProductService;
import com.springboot.inventario.boti.springbootinventario.util.ProductExcelExport;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins= {"http://localhost:5173"})
@RestController
@RequestMapping("/api")
public class ProductoController {

   private IProductService productService;
    public ProductoController(IProductService productService) {
        this.productService = productService;
    }

    //Crear producto
    @PostMapping("/create-products")
    public ResponseEntity<ProductResponseRest> save(
            //@RequestParam("picture")MultipartFile picture,
            @RequestParam("name")String name,
            @RequestParam("price")int price,
            @RequestParam("stock")int stock,
            @RequestParam("categoryId")Long categoryId) throws IOException
    {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);


        return response;
    }

    //Listar producto por id
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = productService.searchById(id);
        return response;
    }

    //Filtrar por nombre
    @GetMapping("/products/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
        ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
        return response;
    }

    //Eliminar por id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
        return response;
    }

    //Listar
    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> search(){
        ResponseEntity<ProductResponseRest> response = productService.search();
        return response;
    }

    //Actualizar
     @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> update(
            @PathVariable Long id,
            @RequestParam("categoryId") Long categoryId,
            //@RequestParam("picture")MultipartFile picture,
            @RequestParam("name")String name,
            @RequestParam("price")int price,
            @RequestParam("stock")int stock) throws IOException
    {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        ResponseEntity<ProductResponseRest> response = productService.update(id, categoryId, product);

        return response;
    }

    //Exportar a Excel
    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=productos.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<ProductResponseRest> productsResponse = productService.search();
        ProductExcelExport excelExporter = new ProductExcelExport(productsResponse.getBody().getProduct().getProducts());
        excelExporter.export(response);

    }
}

