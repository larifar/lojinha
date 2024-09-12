package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.model.User;
import com.larissa.virtual.lojinha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SendEmailService emailService;

    public User findUserByCPF(String cpf) { return repository.findUserByCPF(cpf);}

    public User findUserByEmail(String e){ return repository.findUserByEmail(e);}

    public User save(User user){
        String constraint = repository.findUserConstraint();
        if (constraint != null){
            jdbcTemplate.execute("begin; alter table users_access drop constraint " + constraint + ";commit;");
        }
        user = repository.save(user);
        repository.insertUserAccess(user.getId());
//        repository.insertUserAccess(user.getId(), "ROLE_ADMIN");

        StringBuilder message = new StringBuilder();
        message.append("<b>Obrigado por se cadastrar na Loja Virtual!</b>");
        message.append("<p>Seu login é: "+ user.getEmail() +"</p>");

        try {
            emailService.sendEmailHtml("Bem-vindo a Loja Virtual", message.toString(), user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }
}
