package hello.springmvc.validation;

import hello.springmvc.domain.Item;
import hello.springmvc.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ValidationItemController {
    private final ItemValidator itemValidator;
    private final ItemRepository itemRepository;
    private final SmartValidator validator;

//    @InitBinder
//    public void init(WebDataBinder dataBinder) {
//        log.info("init binder {}", dataBinder);
//        dataBinder.addValidators(itemValidator);
//    }

//    @PostMapping("/v1/add")
//    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
//                            RedirectAttributes redirectAttributes) {
//        ItemValidator.validate(item, bindingResult);
//        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
//            return "validation/v2/addForm";
//        }
//
//        //성공 로직
//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v2/items/{itemId}";
//    }
//
//    @PostMapping("/v2/add")
//    public String addItemV2(@Validated @ModelAttribute Item item, BindingResult bindingResult,
//                            RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
//            return "validation/v2/addForm";
//        }
//
//        //성공 로직
//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v2/items/{itemId}";
//    }

    @PostMapping("/v3/add")
    public String addItemV3(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/addForm";
        }
        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/v4/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        validator.validate(item, bindingResult, Item.SaveCheck.class, Default.class);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/addForm";
        }
        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

}
