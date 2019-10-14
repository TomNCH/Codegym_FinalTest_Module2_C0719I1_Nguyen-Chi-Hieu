package com.managers.controllers;

import com.managers.models.Category;
import com.managers.models.Product;
import com.managers.services.CategoryService;
import com.managers.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category>categories(){
        return categoryService.findAll();
    }

    @GetMapping("/")
    public ModelAndView productList(Pageable pageable){
        Iterable<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("/list-product")
    public ModelAndView getAllProduct(@RequestParam("s") Optional<String> s, Pageable pageable) {

        Iterable<Product> productsList;
        if(s.isPresent()){
            productsList = productService.findAllByName(s.get());
        } else {
            productsList = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products",productsList);

        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("message","Created product successful");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product products = productService.findById(id);
        if (products != null){
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", products);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/update-product")
    public ModelAndView updateProduct(@ModelAttribute Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("student", product);
        modelAndView.addObject("message", "Edit product successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showRemoveForm(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("/delete-product")
    public ModelAndView removeProduct(@ModelAttribute ("product") Product product) {
        productService.remove(product.getId());
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message","Delete product successful");
        return modelAndView;
    }
}
