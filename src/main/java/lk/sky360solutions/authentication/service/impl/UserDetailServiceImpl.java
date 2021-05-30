package lk.sky360solutions.authentication.service.impl;

import lk.sky360solutions.authentication.config.MyUserDetails;
import lk.sky360solutions.authentication.model.User;
import lk.sky360solutions.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new MyUserDetails(user);
  }
}
