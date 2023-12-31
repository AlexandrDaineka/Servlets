package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) throws Exception {
    if (id == 0) throw new Exception("Такой \"id\" не существует");
    Post post = repository.getById(id);
    if (post == null) throw new NotFoundException("Пост не найден");
    return post;
  }

  public Post save(Post post) throws Exception {
    return repository.save(post);
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

