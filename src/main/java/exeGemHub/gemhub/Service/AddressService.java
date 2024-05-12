package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.DTO.AddressDTO;
import exeGemHub.gemhub.Entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddress();

    void addAddress(AddressDTO addressDTO);

    Address getAddressDefault();
}
