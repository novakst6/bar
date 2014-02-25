package cz.cvut.bar.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.cvut.bar.model.entity.UserEntity;
import cz.cvut.bar.service.manager.UserManager;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userManager;
    @Autowired
    private Assembler assembler;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {

        UserEntity user = userManager.findByUsername(string);

        if (user == null) {
            throw new UsernameNotFoundException("SECURITY> Can't find user [ " + string + " ]");
        }
        

        return assembler.buildUserFromUserEntity(user);

    }
}
