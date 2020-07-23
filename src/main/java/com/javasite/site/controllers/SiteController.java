package com.javasite.site.controllers;

import com.javasite.site.models.Site;
import com.javasite.site.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SiteController {
    @Autowired
    private SiteRepository siteRepository;

    @GetMapping("/recipes")
    public String blogMain(Model model){
        Iterable<Site> posts = siteRepository.findAll();
        model.addAttribute("posts", posts);
    return "recipes";
    }

    @GetMapping("/recipes/addRecipes")
    public String addRecipes(Model model){
        return "addRecipes";
    }

    @PostMapping("/recipes/addRecipes")
    public String addPostRecipes(@RequestParam String title, @RequestParam String products, @RequestParam String recipes, Model model){
        Site site = new Site(title, products, recipes);
        siteRepository.save(site);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/{id}")
    public String siteRecipes(@PathVariable(value = "id") long id, Model model){
        if(!siteRepository.existsById(id)){
            return "redirect:/recipes";
        }
        Optional<Site> site = siteRepository.findById(id);
        ArrayList<Site> result = new ArrayList<>();
        site.ifPresent(result::add);
        model.addAttribute("site", result);
        return "siteRecipes";
    }

    @GetMapping("/recipes/{id}/siteChanges")
    public String chandeRecipes(@PathVariable(value = "id") long id, Model model){
        if(!siteRepository.existsById(id)){
            return "redirect:/recipes";
        }

        Optional<Site> site = siteRepository.findById(id);
        ArrayList<Site> result = new ArrayList<>();
        site.ifPresent(result::add);
        model.addAttribute("site", result);
        return "siteChanges";
    }

    @PostMapping("/recipes/{id}/siteChanges")
    public String chandeRecipesPost(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String products, @RequestParam String recipes, Model model){
        Site site = siteRepository.findById(id).orElseThrow();
        site.setTitle(title);
        site.setProducts(products);
        site.setRecipes(recipes);
        siteRepository.save(site);
        return "redirect:/recipes";
    }

    @PostMapping("/recipes/{id}/siteDelete")
    public String deleteRecipesPost(@PathVariable(value = "id") long id, Model model){
        Site site = siteRepository.findById(id).orElseThrow();
        siteRepository.delete(site);
        return "redirect:/recipes";
    }
}
