package com.nila.library.controller;

import com.nila.library.entity.Reader;
import com.nila.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reader addReader(@RequestBody Reader Reader) {
        return readerService.addReader(Reader);
    }

    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable Long id, @RequestBody Reader newReader) {
        return readerService.updateReader(id, newReader);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }

}
