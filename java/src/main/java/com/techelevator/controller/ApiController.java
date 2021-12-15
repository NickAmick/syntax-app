package com.techelevator.controller;

import com.techelevator.dao.CommentDAO;
import com.techelevator.dao.ExampleDAO;
import com.techelevator.dao.JDoodleDAO;
import com.techelevator.dao.LanguageDAO;
import com.techelevator.exception.BadRequestException;
import com.techelevator.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class ApiController {

    private ExampleDAO exampleDAO;
    private LanguageDAO languageDAO;
    private CommentDAO commentDAO;
    private JDoodleDAO jDoodleDao = new JDoodleDAO();

    public ApiController(ExampleDAO exampleDAO, LanguageDAO languageDAO, CommentDAO commentDAO) {
        this.exampleDAO = exampleDAO;
        this.languageDAO = languageDAO;
        this.commentDAO = commentDAO;
    }

    @RequestMapping(path = "/examples", method = RequestMethod.GET)
    public List<Example> getExamples() {
        return exampleDAO.getAllExamples();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/examples", method = RequestMethod.POST)
    public void addExample(@RequestBody Example example, Principal principal) throws BadRequestException {
        try {
            exampleDAO.addExample(example, principal.getName());
        } catch (DataAccessException ex) {
//            throw new BadRequestException();
            ex.printStackTrace();
        }
    }

    @RequestMapping(path = "/examples", method = RequestMethod.PUT)
    public void updateExample(@RequestBody Example example) {
        try {
            exampleDAO.updateExample(example);
        } catch (DataAccessException ex) {
//            throw new BadRequestException();
            ex.printStackTrace();
        }
    }

    @RequestMapping(path = "/languages", method = RequestMethod.GET)
    public List<Language> getAllLanguages() {
        return languageDAO.getAllLanguages();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/languages", method = RequestMethod.POST)
    public void addLanguage(@RequestBody Language language) throws BadRequestException {
        try {
            languageDAO.addLanguage(language.getType());
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/languages", method = RequestMethod.PUT)
    public void updateLanguage(@RequestBody Language language) throws BadRequestException {
        try {
            boolean updated = languageDAO.updateLanguage(language);
            if (!updated) {
                throw new BadRequestException();
            }
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }

    @RequestMapping(path = "/addComment", method = RequestMethod.POST)
    public void addComment(@RequestBody Comment comment, Principal principal) throws BadRequestException {
        try {
            commentDAO.addComment(comment, principal);
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }

    @RequestMapping(path = "/compiler", method = RequestMethod.POST)
    public CompiledResponse addComment(@RequestBody Execution execution) throws BadRequestException {
        try {
            return jDoodleDao.compile(execution);
        } catch (DataAccessException e) {
            throw new BadRequestException();
        }
    }
}
