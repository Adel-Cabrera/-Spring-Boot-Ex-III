package com.darkonnen.pagination.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.darkonnen.pagination.repositories.DojoRepository;

@Service
@Transactional
public class DojoService {
	@Autowired
	private DojoRepository dojoRepo;

	public DojoService(DojoRepository dojoRepo) {
		this.dojoRepo = dojoRepo;
	}

	private static final int PAGE_SIZE = 5;

	public Page<Object[]> dojosPerPage(int pageNumber) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "name");
		Page<Object[]> dojos = dojoRepo.allDojosAndNinjas(pageRequest);
		return dojoRepo.allDojosAndNinjas(pageRequest);
	}
}
