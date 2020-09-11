package com.darkonnen.backendninja.components;

import org.springframework.stereotype.Component;

import com.darkonnen.backendninja.entities.Contact;
import com.darkonnen.backendninja.models.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModelToContact(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstName(contactModel.getFirstName());
		contact.setLastName(contactModel.getLastName());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		return contact;
	}

	public ContactModel convertContactToContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstName(contact.getFirstName());
		contactModel.setLastName(contact.getLastName());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());
		return contactModel;
	}

}
