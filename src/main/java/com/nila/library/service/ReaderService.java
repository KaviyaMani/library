package com.nila.library.service;

import com.nila.library.entity.Reader;
import com.nila.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ReaderService {

    @Autowired
    ReaderRepository ReaderRepository;

    public List<Reader> getAllReaders() {
        return ReaderRepository.findAll();
    }

    public Reader getReaderById(Long id) {
        return ReaderRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader with id "+id +" not found"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Reader addReader(Reader Reader) {
        return ReaderRepository.save(Reader);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Reader updateReader(Long id, Reader newReader) {

        Reader Reader = getReaderById(id);
        Reader.setName(Objects.toString(newReader.getName(), Reader.getName()));
        Reader.setAddress(Objects.toString(newReader.getAddress(), Reader.getAddress()));
        Reader.setPhoneNumber(Objects.toString(newReader.getPhoneNumber(), Reader.getPhoneNumber()));
        Reader.setEmailAddress(Objects.toString(newReader.getEmailAddress(), Reader.getEmailAddress()));

        return ReaderRepository.save(Reader);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteReader(Long id) {
        ReaderRepository.deleteById(id);
    }

}
