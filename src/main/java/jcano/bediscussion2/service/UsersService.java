package jcano.bediscussion2.service;


import jcano.bediscussion2.dto.UserDTO;
import jcano.bediscussion2.entity.UsersEntity;
import jcano.bediscussion2.model.UserRequest;
import jcano.bediscussion2.repository.UsersRepository;
import jcano.bediscussion2.util.DatetimeUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final DatetimeUtil dateTimeUtil;
    private final ModelMapper modelMapper;

    public UserDTO registerUser(@NonNull UserRequest userRequest) {
        // check if email already exist
        if(usersRepository.findByEmail(userRequest.getEmail()) != null)throw new RuntimeException("Email already in use");

        UsersEntity usersEntity = UsersEntity.builder()
                .userId(UUID.randomUUID())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .mobileNumber(userRequest.getMobileNumber())
                .totalOrders(0)
                .successOrders(0)
                .createdDate(dateTimeUtil.currentDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build();


        //save to database
        usersRepository.save(usersEntity);

        return modelMapper.map(usersEntity, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> newList = new ArrayList<>();
        List<UsersEntity> oldList = usersRepository.findAll();
       oldList.forEach(user ->{
           newList.add(modelMapper.map(user, UserDTO.class));
       });


        return newList;
    }
    public UserDTO getUser(String email) {
        UsersEntity user = usersRepository.findByEmail(email);
        return modelMapper.map(user, UserDTO.class);
    }
}
