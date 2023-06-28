package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.DTO.UsersRegisterDTO;
import com.example.MarketShop.Exception.AppException;
import com.example.MarketShop.Model.UserRole;
import com.example.MarketShop.Model.Users;
import com.example.MarketShop.Repository.RolesRepository;
import com.example.MarketShop.Repository.UsersRepository;
import com.example.MarketShop.Service.Interface.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<UsersDTO> getUsersList(Pageable pageable) {
        List list = usersRepository.findAll(pageable).getContent()
                .stream()
                .map((users) -> modelMapper.map(users, UsersDTO.class,"OrdersDTOList"))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public UsersRegisterDTO register(UsersRegisterDTO users) {
        Optional<Users> checkedUsers = usersRepository.findByEmail(users.getEmail());

        if(checkedUsers.isPresent())
            throw new AppException(HttpStatus.BAD_REQUEST, "Đã tồn tại tên tài khoản");

        Users newUsers = modelMapper.map(users, Users.class);
        newUsers.setUserRole(Set.of(new UserRole(newUsers, rolesRepository.findByRoleName("ROLE_USER"))));

        String encodePassword = passwordEncoder.encode(newUsers.getPassword());
        newUsers.setPassword(encodePassword);

        return modelMapper.map(usersRepository.save(newUsers), UsersRegisterDTO.class);
    }

    @Override
    public int totalPage() {
        return (int) usersRepository.count();
    }
}
