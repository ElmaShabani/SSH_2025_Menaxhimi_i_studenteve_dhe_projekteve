package com.example.student.resource;


import com.example.student.domain.Contact;
import com.example.student.dto.ContactDto;
import com.example.student.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;


import static com.example.student.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;






@RestController
@RequestMapping("/contacts")
//@RequiredArgsConstructor
public class ContactResource {
    private final ContactService contactService;
    public ContactResource(ContactService contactService){
        this.contactService=contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDto contactDto) {
        return ResponseEntity.created(URI.create("/contacts/userID"))
                .body(contactService.createContact(contactDto));  // ✅ Nuk ka më probleme
    }



    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable(value = "id") String id) {
        Contact contact = contactService.getContact(id);

        ContactDto contactDto = new ContactDto(contact.getName(), contact.getPhone());

        return ResponseEntity.ok().body(contactDto);
    }

//    @PutMapping("/photo")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file) {
//        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
//    }



    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}