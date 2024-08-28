package com.nila.library.service;

import com.nila.library.entity.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> getAllReaders();
    Reader getReaderById(Long id);
    Reader addReader(Reader Reader);
    Reader updateReader(Long id, Reader newReader);
    void deleteReader(Long id);
}
