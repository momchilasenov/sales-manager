package project.salesmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.salesmanager.dao.SalesDao;
import project.salesmanager.model.Sale;

import java.util.List;

@Controller
public class SalesController
{
  @Autowired
  private SalesDao salesDao;

  //has no default - maps to any http request
  @RequestMapping("/")
  public String viewHomePage(Model model)
  {
    List<Sale> sales = salesDao.list();
    model.addAttribute("sales", sales);
    return "index";
  }

  @RequestMapping("/new")
  public String showNewForm(Model model)
  {
    Sale sale = new Sale();
    model.addAttribute("sale", sale);
    return "new_form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@ModelAttribute("sale") Sale sale)
  {
    salesDao.save(sale);
    return "redirect:/"; //redirect to home page
  }

  @RequestMapping("/edit/{id}")
  public ModelAndView showEditForm(@PathVariable(name = "id") int id)
  {
    ModelAndView modelAndView = new ModelAndView("edit_form");
    Sale sale = salesDao.get(id);
    modelAndView.addObject("sale", sale);
    return modelAndView;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(@ModelAttribute("sale") Sale sale)
  {
    salesDao.update(sale);
    return "redirect:/";
  }

  @RequestMapping("/delete/{id}")
  public String delete(@PathVariable(name = "id") int id)
  {
    salesDao.delete(id);
    return "redirect:/";
  }
}
