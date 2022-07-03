package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class credentialServiceImpl {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public List<Credential> getAllCredential(Integer userId){return credentialMapper.getCredentialForUser(userId);}

    public credentialServiceImpl(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void insertCredential(Credential credential, Integer userId){
        Credential newCredential = new Credential(null, credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(),userId);
        this.encryptPassword(newCredential);
        credentialMapper.addCredential(newCredential);
    }

    public void updateCredential(Credential credential){
        encryptPassword(credential);
        credentialMapper.updateCredential(credential);
    }

    private void encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
    }
}
