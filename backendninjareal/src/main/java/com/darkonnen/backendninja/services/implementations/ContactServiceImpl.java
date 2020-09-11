package com.darkonnen.backendninja.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.darkonnen.backendninja.components.ContactConverter;
import com.darkonnen.backendninja.entities.Contact;
import com.darkonnen.backendninja.models.ContactModel;
import com.darkonnen.backendninja.repositories.ContactRepository;
import com.darkonnen.backendninja.services.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModelToContact(contactModel));
		return contactConverter.convertContactToContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModel = new ArrayList<ContactModel>();
		for(Contact contact: contacts) {
			contactModel.add(contactConverter.convertContactToContactModel(contact));
		}
			
		return contactModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContactToContactModel(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(contact != null) {
			contactRepository.deleteById(contact.getId());;
		}
		
	}
	
	
	
	

}
