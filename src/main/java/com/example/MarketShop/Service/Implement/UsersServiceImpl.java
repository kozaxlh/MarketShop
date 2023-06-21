package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.DTO.UsersDTO;
import com.example.MarketShop.Repository.UsersRepository;
import com.example.MarketShop.Service.Interface.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDTO> getUsersList(Pageable pageable) {
        List list = usersRepository.findAll(pageable).getContent()
                .stream()
                .map((users) -> modelMapper.map(users, UsersDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public int totalPage() {
        return (int) usersRepository.count();
    }
}
