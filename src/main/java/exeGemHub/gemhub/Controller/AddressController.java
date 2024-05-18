package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.AddressDTO;
import exeGemHub.gemhub.Entity.Address;
import exeGemHub.gemhub.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAddress() {
        return addressService.getAddress();
    }
    @PostMapping
    public void addresses(@RequestBody AddressDTO addressDTO) {
         addressService.addAddress(addressDTO);
    }
    @GetMapping("/default")
    public Address getAddressDefault() {
        return  addressService.getAddressDefault();
    }
    @PutMapping("/updateDefault/{id}")
    public List<Address> updateAddressDefault(@PathVariable("id") int id) {
        return addressService.updateAddressDefault(id);
    }
}
