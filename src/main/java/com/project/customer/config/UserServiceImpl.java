//    package com.project.customer.config;
//
//    import com.project.customer.entity.Customer;
//    import org.springframework.security.core.GrantedAuthority;
//    import org.springframework.security.core.authority.SimpleGrantedAuthority;
//    import org.springframework.security.core.userdetails.UserDetails;
//
//    import java.util.Collection;
//    import java.util.List;
//
//    public class UserServiceImpl implements UserDetails {
//
//        private final Customer customer;
//        public UserServiceImpl(Customer customer) {
//            super();
//            this.customer = customer;
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return List.of(new SimpleGrantedAuthority(customer.getRole().toString()));
//        }
//
//        @Override
//        public String getPassword() {
//            return customer.getPassword();
//        }
//
//        @Override
//        public String getUsername() {
//            return customer.getEmail();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//    }
