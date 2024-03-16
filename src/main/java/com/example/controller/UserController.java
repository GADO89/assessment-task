package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired private UserService service;

    @GetMapping("log")
    public String loginPage(){
        return "index";
    }

    @GetMapping("/users/new")
    public String userRegister(Model model){
        User user= new User();
        model.addAttribute("user",user);
     return "user_form";
    }

    @PostMapping("/image")
    public String upload(Model model){
        User user= new User();
        model.addAttribute("user",user);
        return "upload";
    }
    @PostMapping("/users/save")
    public String saveUser(User user,
             @RequestParam("image")MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()){
            String fileName= StringUtils.
                    cleanPath(multipartFile.getOriginalFilename());
              user.setPhotos(fileName);
              User saveUser=service.save(user);
              String upload= "images/"+user.getId();

              FileUploadUtil.saveFile(upload, fileName, multipartFile);
        }else {
            if (user.getPhotos().isEmpty());{
                user.setPhotos(null);
                service.save(user);
            }
        }
        service.save(user);
     return "index";
    }

    @GetMapping("/show")
    public String showExampleView(Model model)
    {
        List<User> products = service.getAllProduct();
        model.addAttribute("products", products);
        return "index";
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/add")
    public String showAddProduct()
    {

        return "redirect:/listProducts";
    }

    @PostMapping("/addP")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("pname") String name,
                              @RequestParam("price") int price,
                              @RequestParam("desc") String desc)
    {
        service.saveProductToDB(file, name, desc, price);
        return "redirect:/listProducts";
    }

    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {

        service.deleteProductById(id);
        return "redirect:/listProducts.";
    }

    @PostMapping("/changeName")
    public String changePname(@RequestParam("id") Long id,
                              @RequestParam("newPname") String name)
    {
        service.chageProductName(id, name);
        return "redirect:/listProducts.";
    }
    @PostMapping("/changeDescription")
    public String changeDescription(@RequestParam("id") Long id ,
                                    @RequestParam("newDescription") String description)
    {
        service.changeProductDescription(id, description);
        return "redirect:/listProducts.html";
    }


}
