package ru.aizada.springcourse.CRUD.Controllerr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aizada.springcourse.CRUD.dao.PersonDao;
import ru.aizada.springcourse.CRUD.models.Person;

import javax.naming.Binding;
import javax.validation.Valid;

/*
@GetMapping — Обрабатывает get-запросы
@PostMapping — Обрабатывает post-запросы
@DeleteMapping — Обрабатывает delete-запросы
@PutMapping — Обрабатывает put-запросы
@PatchMapping — Обрабатывает patch-запросы
 */
@Controller
@RequestMapping("/people")
public class PeopleController {


    private PersonDao personDao;

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        //получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people",personDao.index());
        return  "People/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){ //извлечь id
        //ПОЛУчить одного человека по id
        model.addAttribute("person",personDao.show(id));
        return "People/show";
    }

    //добавление в БД
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "People/new";
    }

    // создать нового объекта
    @PostMapping()
    public String created(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "People/new";
        }
        personDao.save(person);
        return "redirect:/people"; //redirect на друг станицу
    }

    // станица для изменение
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDao.show(id));
        return "People/editH";
    }

    // для измение из формы изсенить
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "People/editH";
        }

        personDao.update(id,person);
        return "redirect:/people";

    }

    // удаление
    @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id){
         personDao.delete(id);
         return "redirect:/people";
        }

}





