package com.learntech.configurations

import com.learntech.repositories.UsersRepository
import com.learntech.services.UserManagementService
import com.learntech.services.UserManagementServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderManagementConfiguration {
    @Bean
    fun getUserManagementService(usersRepository: UsersRepository): UserManagementService {
        return UserManagementServiceImpl(usersRepository)
    }
}
