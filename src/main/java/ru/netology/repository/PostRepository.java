package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class PostRepository {
  private ConcurrentHashMap<Long, Post> repositoryMap = new ConcurrentHashMap<>();
  private AtomicLong counter = new AtomicLong();

  public List<Post> all() {
    return new ArrayList<>(repositoryMap.values());
  }

  public Post getById(long id) {
    return repositoryMap.get(id);
  }

  public Post save(Post post) throws Exception {
    if (post.getId() == 0) {
      long id = counter.incrementAndGet();
      repositoryMap.put(id, new Post(id, post.getContent()));
      return new Post(id, post.getContent());
    }

    if (repositoryMap.containsKey(post.getId())) {
      repositoryMap.put(post.getId(), new Post(post.getId(), post.getContent()));
      return new Post(post.getId(), post.getContent());
    } else {
      throw new Exception("Такой \"id\" не существует");
    }
  }

  public void removeById(long id) {
    repositoryMap.remove(id);
  }
}
