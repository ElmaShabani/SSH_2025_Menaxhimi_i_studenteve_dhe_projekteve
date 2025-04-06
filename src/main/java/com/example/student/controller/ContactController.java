package com.example.student.controller;

import com.example.student.domain.Contact;
import com.example.student.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepo contactRepository;

    @Autowired
    public ContactController(ContactRepo contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestBody Contact contact) {
        // Logic to handle the file (e.g., save it to a directory, cloud storage, etc.)
        String photoUrl = saveFile(file); // Assuming saveFile() handles the file saving

        return contactRepository.findById(id)
                .map(existingContact -> {
                    existingContact.setName(contact.getName());
                    existingContact.setEmail(contact.getEmail());
                    existingContact.setPhone(contact.getPhone());
                    existingContact.setAddress(contact.getAddress());
                    existingContact.setPhotoUrl(photoUrl);  // Update with the new photo URL
                    existingContact.setStatus(contact.getStatus());
                    existingContact.setTitle(contact.getTitle());

                    // Save the updated contact to the database
                    Contact updatedContact = contactRepository.save(existingContact);
                    return ResponseEntity.ok(updatedContact);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private String saveFile(MultipartFile file) {
        // Save file and return the URL or path
        String fileName = file.getOriginalFilename();
        // Example: Save file to disk and return the file path or URL
        return "path_to_saved_file/" + fileName;
    }
    @PutMapping("/photo")
    public ResponseEntity<String> updatePhoto(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        try {
            // Save the file (you can implement the logic to save the file to a storage location)
            String fileUrl = saveFile(file);

            // Retrieve the contact and update the photo URL
            Contact contact = contactRepository.findById(id).orElse(null);

            if (contact == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
            }

            contact.setPhotoUrl(fileUrl);
            contactRepository.save(contact);

            return ResponseEntity.ok("Photo updated successfully");

        } catch (Exception e) {
            // Handle any exceptions (this will catch all exceptions)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating photo");
        }
    }
//Elmaz duhet komentet me ja hek kodit ose psh i shenon shqip se doken qe jon chat

}
