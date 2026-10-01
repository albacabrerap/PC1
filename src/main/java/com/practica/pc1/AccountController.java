package com.practica.pc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public AccountController(AccountRepository accountRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @GetMapping("/private")
    public ResponseEntity<UserInfoDto> getPrivateInfo() {
        Account account = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    @GetMapping("/admin")
    public ResponseEntity<UserInfoDto> getAdminInfo() {
        Account account = currentAccount();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new UserInfoDto(account.getUsername(), account.getRole().name()));
    }

    private Account currentAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        return (Account) principal;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDto<UserDto>> getAllUsers(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<UserDto> usersPage = accountService.getAllUsers(pageable);
        return ResponseEntity.ok(new PagedResponseDto<>(usersPage));
    }
}