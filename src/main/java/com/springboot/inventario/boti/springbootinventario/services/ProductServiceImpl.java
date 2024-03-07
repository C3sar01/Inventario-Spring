package com.springboot.inventario.boti.springbootinventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.inventario.boti.springbootinventario.models.Category;
import com.springboot.inventario.boti.springbootinventario.models.Product;
import com.springboot.inventario.boti.springbootinventario.repositories.CategoryRepository;
import com.springboot.inventario.boti.springbootinventario.repositories.ProductRepository;
import com.springboot.inventario.boti.springbootinventario.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements IProductService {

    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @SuppressWarnings("unused")
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar categoria por id
            Optional<Category> category = categoryRepository.findById(categoryId);

            if(category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //Salvamos producto
            Product productSave = productRepository.save(product);

            if(productSave != null) {
                list.add(productSave);
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto guardado");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar producto por id
            Optional<Product> product = productRepository.findById(id);

            if(product.isPresent()){
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //buscar producto por name
            listAux = productRepository.findByNameContainingIgnoreCase(name);

            if(listAux.size() > 0){
                for (Product product : listAux) {

                    list.add(product);
                }

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> deleteById(Long id) {
        ProductResponseRest response = new ProductResponseRest();

        try {
            //borrar producto por id
            productRepository.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Producto eliminado");


        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //buscar producto
            listAux = (List<Product>) productRepository.findAll();

            if(listAux.size() > 0){
                for (Product product : listAux) {

                    list.add(product);
                }

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @SuppressWarnings({ "unused", "null" })
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> update(Long id, Long categoryId, Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar categoria por id
            Optional<Category> category = categoryRepository.findById(categoryId);

            if(category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //buscamos producto a actualizar
            Optional<Product> productSearch = productRepository.findById(id);

            if(productSearch.isPresent()) {
                //actualizamos producto

                productSearch.get().setName(product.getName());
                productSearch.get().setStock(product.getStock());
                productSearch.get().setPrice(product.getPrice());
                productSearch.get().setCategory(product.getCategory());

                //salvamos producto en base de datos
                Product  productToUpdate = productRepository.save(productSearch.get());

                if (productToUpdate != null){
                    list.add(productToUpdate);
                    response.setMetadata("Respuesta ok", "00", "Producto actualizado");
                    response.getProduct().setProducts(list);
                }else {
                    response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
                    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }
    
}
