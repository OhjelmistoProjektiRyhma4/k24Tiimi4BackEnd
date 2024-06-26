package com.dogproductinventory.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.dogproductinventory.app.domain.DogProductRepository;
import com.dogproductinventory.app.domain.ManufacturerRepository;
import com.dogproductinventory.app.domain.ProductTypeRepository;
import com.dogproductinventory.app.domain.DogProduct;

@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private DogProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manuRepository;

    @Autowired
    private ProductTypeRepository typeRepository;
    

    

    @GetMapping("/") //TODO: Loogisin sijainti metodille?
    public String etuSivu() {
        return "index";
    }

    // listaa kaikki tuotteet
    @GetMapping("/productlist")
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "productlist";
    }

    // lomake uudelle tuotteelle
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addproduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new DogProduct());
        model.addAttribute("manufacturer", manuRepository.findAll());
        model.addAttribute("type", typeRepository.findAll());
        return "productform";
    }

    // tuotteen muokkaus
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editproduct/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        model.addAttribute("product", productRepository.findById(productId));
        model.addAttribute("manufacturer", manuRepository.findAll());
        model.addAttribute("type", typeRepository.findAll());
        return "productform";
    }

    // tallentaa tiedot repositoryyn ja palaa sivulle joka näyttää listan
    @PostMapping("/saveproduct")
    public String saveProduct(@Valid @ModelAttribute("product") DogProduct product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturer", manuRepository.findAll());
            model.addAttribute("type", typeRepository.findAll());
            return "productform";
        } else {
            productRepository.save(product);
            return "redirect:/productlist";
        }
    }

    // tuotteen poisto
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") Long produId) {
        productRepository.deleteById(produId);
        return "redirect:/productlist";
    }
}
