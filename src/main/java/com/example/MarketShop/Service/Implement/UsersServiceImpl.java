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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<UsersDTO> getUsersList(Pageable pageable) {
        List list = usersRepository.findAll(pageable).getContent()
                .stream()
                .map((users) -> modelMapper.map(users, UsersDTO.class, "OrdersDTOList"))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public UsersRegisterDTO register(UsersRegisterDTO users) {
        Optional<Users> checkedUsers = usersRepository.findByEmail(users.getEmail());

        if (checkedUsers.isPresent())
            throw new AppException(HttpStatus.BAD_REQUEST, "Đã tồn tại tên tài khoản");

        Users newUsers = modelMapper.map(users, Users.class);
        newUsers.setUserRole(Set.of(new UserRole(newUsers, rolesRepository.findByRoleName("ROLE_USER"))));
        newUsers.setPassword(passwordEncoder.encode(newUsers.getPassword()));
        newUsers.setCreate_at(LocalDateTime.now());
        newUsers.setUpdate_at(LocalDateTime.now());

        return modelMapper.map(usersRepository.save(newUsers), UsersRegisterDTO.class);
    }

    @Override
    public int totalPage() {
        return (int) usersRepository.count();
    }
}
