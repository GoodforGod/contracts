package io.uml.contracts.storage.impl;

import io.uml.contracts.model.dao.Comment;
import io.uml.contracts.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Component
public class CommentStorage extends BasicModifyStorage<Comment, String> {

    @Autowired
    public CommentStorage(CommentRepository repository) {
        super(repository);
    }
}
