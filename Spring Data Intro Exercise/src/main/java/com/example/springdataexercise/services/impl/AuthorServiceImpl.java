package com.example.springdataexercise.services.impl;

import com.example.springdataexercise.entities.Author;
import com.example.springdataexercise.entities.utils.FileUtil;
import com.example.springdataexercise.repos.AuthorRepo;
import com.example.springdataexercise.services.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.example.springdataexercise.constants.Constants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepo authorRepo, FileUtil fileUtil) {
        this.authorRepo = authorRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        String[] fileContent = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            String[] params = r.split("\\s+");
            Author author = new Author(params[0], params[1]);

            this.authorRepo.saveAndFlush(author);
        });
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepo.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepo.getOne(id);
    }
}
