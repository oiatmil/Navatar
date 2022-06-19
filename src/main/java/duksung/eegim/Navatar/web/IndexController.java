package duksung.eegim.Navatar.web;

import duksung.eegim.Navatar.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final ProductService productService;

    public IndexController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("product", productService.getList());
        return "index";
    }

    @GetMapping("/products/{productNo}")
    public String getProduct(Model model, @PathVariable String productNo){

        Long pno = Long.parseLong(productNo);
        model.addAttribute("product", productService.getProduct(pno));
        model.addAttribute("details", productService.getProductDetail(pno));
        model.addAttribute("sizes", productService.getSize(pno));
        return "product";
    }

    @GetMapping("/brands/{brand}")
    public String getProductByBrand(Model model, @PathVariable String brand){
        model.addAttribute("product", productService.getListByBrand(brand));
        return brand;
    }

    @GetMapping("/brands/{brand}/{cate}")
    public String getProductByBrandNCate(Model model, @PathVariable String brand, @PathVariable String cate){
        model.addAttribute("product", productService.getListByBrandNCate(brand, cate));
        return brand;
    }
}