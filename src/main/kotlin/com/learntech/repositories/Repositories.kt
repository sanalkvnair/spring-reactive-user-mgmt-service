package com.learntech.repositories

import com.learntech.model.Users
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : ReactiveCrudRepository<Users, Int>
