package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.AddressDTO;
import exeGemHub.gemhub.Entity.Address;
import exeGemHub.gemhub.Entity.User;
import exeGemHub.gemhub.Entity.UserPrinciple;
import exeGemHub.gemhub.Repository.AddressRepo;
import exeGemHub.gemhub.Repository.UserRepo;
import exeGemHub.gemhub.Service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    public List<Address> getAddress() {
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String username = userPrinciple.getUsername();
        User user = userRepo.findByUsername(username);
        return addressRepo.findAddressByUser(user);
    }

    @Override
    public void addAddress(AddressDTO addressDTO) {
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String username = userPrinciple.getUsername();
        User user = userRepo.findByUsername(username);
        Address address = new Address();
        address.setName(addressDTO.getName());
        address.setAddress(addressDTO.getAddress());
        address.setPhone(addressDTO.getPhone());
        address.setDescription(addressDTO.getDescription());
        address.setProvince(addressDTO.getProvince());
        address.setCity(addressDTO.getCity());
        address.setWrap(addressDTO.getWrap());
        address.setStatus(false);
        address.setUser(user);
        addressRepo.save(address);

    }

    @Override
    public Address getAddressDefault() {
        List<Address> list = getAddress();
        return list.stream().filter(address -> address.getStatus()).findFirst().get();
    }

    @Override
    public List<Address> updateAddressDefault(int id) {
        List<Address> list = getAddress();

        // Tìm địa chỉ hiện tại có trạng thái mặc định
        Optional<Address> currentDefaultOpt = list.stream()
                .filter(Address::getStatus)
                .findFirst();

        // Tìm địa chỉ cần cập nhật theo id
        Optional<Address> updateAddressOpt = addressRepo.findById(id);

        if (!updateAddressOpt.isPresent()) {
            throw new IllegalArgumentException("Address with id " + id + " not found.");
        }

        Address updateAddress = updateAddressOpt.get();

        if (currentDefaultOpt.isPresent()) {
            Address currentDefault = currentDefaultOpt.get();
            if (currentDefault.getId() == updateAddress.getId()) {
                return list;
            }
            currentDefault.setStatus(false);
            addressRepo.save(currentDefault);
        }

        updateAddress.setStatus(true);
        addressRepo.save(updateAddress);

        return getAddress();
    }
}
