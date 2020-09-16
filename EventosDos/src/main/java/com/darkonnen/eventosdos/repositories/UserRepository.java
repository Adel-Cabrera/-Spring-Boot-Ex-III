package com.darkonnen.eventosdos.repositories;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}